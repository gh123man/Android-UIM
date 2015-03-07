package com.brian.floersch.assembler.AssemblerV1;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class ViewAssembler {

    private static final String ID = "id";

    private final JSONObject mJsonObject;
    private View mView;
    private final ViewGroup mParent;
    private final AssemblerContext mAssemblerContext;

    public ViewAssembler(JSONObject jsonObject, AssemblerContext assemblerContext, ViewGroup parent) {
        mJsonObject = jsonObject;
        mAssemblerContext = assemblerContext;
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

    public AssemblerContext getAssemblerContext() {
        return mAssemblerContext;
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
