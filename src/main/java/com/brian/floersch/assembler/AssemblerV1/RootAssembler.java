package com.brian.floersch.assembler.AssemblerV1;

import android.view.ViewGroup;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class RootAssembler {

    private ViewGroup mRootView, mParent;
    private AssemblerContext mAssemblerContext;
    private JSONObject mJsonObject;

    public RootAssembler(JSONObject jsonObject, AssemblerContext assemblerContext, ViewGroup parent) {
        mJsonObject = jsonObject;
        mAssemblerContext = assemblerContext;
        mParent = parent;
    }

    public ViewGroup getView() {
        return mRootView;
    }

    public void parse() throws JSONException {

        Iterator<?> keys = mJsonObject.keys();

        while (keys.hasNext()) {
            String key = (String) keys.next();
            if (mJsonObject.get(key) instanceof JSONObject) {
                if (LayoutAssembler.LAYOUTS.contains(key)) {
                    ViewAssembler assembler = new LayoutAssembler((JSONObject) mJsonObject.get(key), mAssemblerContext, mParent, key);
                    assembler.parse();
                    mRootView = (ViewGroup) assembler.getView();
                }
            }
        }

    }


}
