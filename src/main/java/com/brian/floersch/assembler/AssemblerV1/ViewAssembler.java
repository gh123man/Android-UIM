package com.brian.floersch.assembler.AssemblerV1;

import android.view.View;
import android.view.ViewGroup;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class ViewAssembler {

    private static final String WIDTH = "width";
    private static final String HEIGHT = "height";

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

    protected void setView(View view) {
        mView = view;
        mParent.addView(mView);
    }

    protected int pxToDp(float px) {
        final float scale = getAssemblerContext().getContext().getResources().getDisplayMetrics().density;
        return (int) (px * scale + 0.5f);
    }

    protected void applyProperties(View view, JSONObject jsonObject) throws JSONException {

        ViewGroup.LayoutParams params = mView.getLayoutParams();
        if (mJsonObject.has(WIDTH)) {
            params.width = pxToDp(mJsonObject.getInt(WIDTH));
        }

        if (mJsonObject.has(HEIGHT)) {
            params.width = pxToDp(mJsonObject.getInt(HEIGHT));
        }
        mView.setLayoutParams(params);
    }
}
