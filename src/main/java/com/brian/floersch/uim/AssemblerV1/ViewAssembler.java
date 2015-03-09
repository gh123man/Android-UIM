package com.brian.floersch.uim.AssemblerV1;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class ViewAssembler {

    public static final String ID = "id";

    private final JSONObject mJsonObject;
    private View mView;
    private final ViewGroup mParent;
    private final UimContext mUimContext;

    public ViewAssembler(JSONObject jsonObject, UimContext uimContext, ViewGroup parent) {
        mJsonObject = jsonObject;
        mUimContext = uimContext;
        mParent = parent;
    }

    public void parse() throws JSONException {
        setUpView();
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

}
