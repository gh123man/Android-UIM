package com.brian.floersch.assembler.AssemblerV1;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class ViewAssembler {

    private static final String WIDTH = "width";
    private static final String HEIGHT = "height";

    private static final String MATCH_DP = "^\\d+dp$";
    private static final String MATCH_PX = "^\\d+px$";
    private static final String STRIP_CHARS = "[^0-9]";
    private static final String MATCH_WRAP_CONTENT = "^wrap_content$";
    private static final String MATCH_MATCH_PARENT = "^match_parent$";

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

        if (mJsonObject.has("id")) {
            mView.setId(mJsonObject.getString("id").hashCode());
        }

        ViewGroup.LayoutParams params = mView.getLayoutParams();

        if (mJsonObject.has(WIDTH)) {
            params.width = parseDim(mJsonObject.getString(WIDTH));
        }

        if (mJsonObject.has(HEIGHT)) {
            params.height = parseDim(mJsonObject.getString(HEIGHT));
        }

        if (view.getParent() instanceof RelativeLayout) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
            RelativeLayoutParamsHelper.applyAttributes(jsonObject, layoutParams);
        }

        if (view.getParent() instanceof LinearLayout) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
            LinearLayoutParamsHelper.applyAttributes(jsonObject, layoutParams);
        }

        mView.setLayoutParams(params);
    }

    private int parseDim(String dim) {
        int outVal;
        String striped = dim.replaceAll(STRIP_CHARS,"");
        if (dim.matches(MATCH_DP)) {
            outVal = pxToDp(Integer.parseInt(striped));
        } else if (dim.matches(MATCH_PX)) {
            outVal = Integer.parseInt(striped);
        } else if (dim.matches(MATCH_MATCH_PARENT)) {
            outVal = ViewGroup.LayoutParams.MATCH_PARENT;
        } else if (dim.matches(MATCH_WRAP_CONTENT)) {
            outVal = ViewGroup.LayoutParams.WRAP_CONTENT;
        } else { //DEFAULT (MAY CHANGE)
            outVal = ViewGroup.LayoutParams.WRAP_CONTENT;
        }
        return outVal;
    }
}
