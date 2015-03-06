package com.brian.floersch.assembler;

import android.content.Context;
import android.view.ViewGroup;

import com.brian.floersch.assembler.AssemblerV1.AssemblerContext;
import com.brian.floersch.assembler.AssemblerV1.RootAssembler;

import org.json.JSONException;
import org.json.JSONObject;

public class UiAssembler {

    private static final String VERSION = "Version";

    private String mJson;
    private ViewGroup mRootView, mParent;
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

    public void parse() throws JSONException{
        JSONObject rootObj = new JSONObject(mJson);

        int version = rootObj.getInt(VERSION);

        switch (version) {
            case 1:
                mParser = new RootAssembler(rootObj, new AssemblerContext(mEventHandler, mContext), mParent);
                mParser.parse();
                break;
        }



    }
}
