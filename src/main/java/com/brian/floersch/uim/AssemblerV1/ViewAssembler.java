package com.brian.floersch.uim.AssemblerV1;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public abstract class ViewAssembler {

    public static final String ID = "id";
    private static final String EVENT_PACKAGE = "uim_eventPackage";
    private static final String UIM_HANDLE_EVENT = "uim_handleEvents";
    private static final String ON_CLICK = "onClick";
    private static final String ON_LONG_CLICK = "onLongClick";

    private final JSONObject mJsonObject;
    private View mView;
    private final ViewGroup mParent;
    private final UimContext mUimContext;
    private ArrayList<String> mEventsToHanlde;

    public ViewAssembler(JSONObject jsonObject, UimContext uimContext, ViewGroup parent) {
        mJsonObject = jsonObject;
        mUimContext = uimContext;
        mParent = parent;
    }

    public void parse() throws JSONException {
        setUpView();
        setUpEventsToHanlde();
        applyProperties(mView, mJsonObject);
    }

    public abstract void setUpView() throws JSONException ;

    public View getView() {
        return mView;
    }

    public UimContext getAssemblerContext() {
        return mUimContext;
    }

    public ViewGroup getParent() {
        return mParent;
    }

    public JSONObject getJsonObject() {
        return mJsonObject;
    }

    protected void setView(View view) throws JSONException {
        mView = view;
        mParent.addView(mView);
        if (mJsonObject.has(ID)) {
            mView.setTag(mJsonObject.getString(ID));
        }
    }

    protected void applyProperties(View view, JSONObject jsonObject) throws JSONException {

        if (mJsonObject.has(ID)) {
            mView.setId(mJsonObject.getString(ID).hashCode());
        }

        ViewAttributeHelper.applyAttributes(jsonObject, mView, getAssemblerContext().getContext());

        ViewGroup.LayoutParams params = view.getLayoutParams();
        ViewGroupParamsHelper.applyAttributes(jsonObject, params, getAssemblerContext().getContext());

        if (jsonObject.has(EVENT_PACKAGE) && jsonObject.has(ID)) {
            getAssemblerContext().getEventHandler().addEventPackage(jsonObject.getString(ID), unpackEventPackage(jsonObject.getJSONArray(EVENT_PACKAGE)));
        }

        if (hasEvent(ON_CLICK)) {
            view.setOnClickListener(getAssemblerContext().getEventHandler());
        }

        if (hasEvent(ON_LONG_CLICK)) {
            view.setOnLongClickListener(getAssemblerContext().getEventHandler());
        }

        if (params instanceof ViewGroup.MarginLayoutParams) {
            MarginLayoutParamsHelper.applyAttributes(jsonObject, (ViewGroup.MarginLayoutParams) params, getAssemblerContext().getContext());
        }

        if (params instanceof RelativeLayout.LayoutParams) {
            RelativeLayoutParamsHelper.applyAttributes(jsonObject, (RelativeLayout.LayoutParams) params);
        }

        if (params instanceof LinearLayout.LayoutParams) {
            LinearLayoutParamsHelper.applyAttributes(jsonObject, (LinearLayout.LayoutParams) params);
        }

        mView.setLayoutParams(params);
    }

    private void setUpEventsToHanlde() throws JSONException {
        if (mJsonObject.has(UIM_HANDLE_EVENT)) {
            JSONArray arr = mJsonObject.getJSONArray(UIM_HANDLE_EVENT);
            mEventsToHanlde = new ArrayList<>();
            for (int i = 0; i < arr.length(); i++) {
                mEventsToHanlde.add(arr.getString(i));
            }
        }
    }


    public boolean hasEvent(String event) {
        return mEventsToHanlde != null && mEventsToHanlde.contains(event);
    }

    private String[] unpackEventPackage(JSONArray arr) throws JSONException {
        String[] outArr = new String[arr.length()];
        for (int i = 0; i < arr.length(); i++) {
            outArr[i] = arr.getString(i);
        }
        return outArr;
    }

}
