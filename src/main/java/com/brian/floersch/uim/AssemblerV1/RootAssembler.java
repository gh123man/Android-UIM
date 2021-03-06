package com.brian.floersch.uim.AssemblerV1;

import android.view.ViewGroup;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class RootAssembler {

    private ViewGroup mRootView, mParent;
    private UimContext mUimContext;
    private JSONObject mJsonObject;

    public RootAssembler(JSONObject jsonObject, UimContext uimContext, ViewGroup parent) {
        mJsonObject = jsonObject;
        mUimContext = uimContext;
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
                    ViewAssembler assembler = new LayoutAssembler((JSONObject) mJsonObject.get(key), mUimContext, mParent, key);
                    assembler.parse();
                    mRootView = (ViewGroup) assembler.getView();
                }
            }
        }

    }


}
