package com.brian.floersch.assembler;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.brian.floersch.assembler.AssemblerV1.RootAssembler;
import com.brian.floersch.assembler.AssemblerV1.UimContext;

import org.json.JSONException;
import org.json.JSONObject;

public class UiAssembler {

    private static final String VERSION = "Version";

    private String mJson;
    private ViewGroup mParent;
    private RootAssembler mParser;
    private IuimEvents mEventHandler;
    private Context mContext;

    public UiAssembler(String json, IuimEvents eventHandler, Context context, ViewGroup parent) {
        mJson = json;
        mEventHandler = eventHandler;
        mContext = context;
        mParent = parent;
    }

    public ViewGroup getView() {
        return mParser.getView();
    }

    public void parseAndApplyView() throws JSONException{
        JSONObject rootObj = new JSONObject(mJson);

        int version = rootObj.getInt(VERSION);

        switch (version) {
            case 1:
                mParser = new RootAssembler(rootObj, new UimContext(mEventHandler, mContext, mParent), mParent);
                mParser.parse();
                break;
        }
    }

    public View getViewByStringId(String id) {
        return mParent.findViewById(id.hashCode());
    }
}
