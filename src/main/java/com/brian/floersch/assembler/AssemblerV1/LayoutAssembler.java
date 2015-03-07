package com.brian.floersch.assembler.AssemblerV1;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LayoutAssembler extends ViewAssembler {

    private static final String LINEAR_LAYOUT = "LinearLayout";
    private static final String RELATIVE_LAYOUT = "RelativeLayout";

    private static final String CHILDREN = "children";

    public static final ArrayList<String> LAYOUTS = new ArrayList<String>() {{
        add(LINEAR_LAYOUT);
        add(RELATIVE_LAYOUT);
    }};

    private String mLayoutType;

    public LayoutAssembler(JSONObject jsonObject, AssemblerContext assemblerContext, ViewGroup parent, String key) {
        super(jsonObject, assemblerContext, parent);
        mLayoutType = key;
    }

    @Override
    public void setUpView() throws JSONException {

        switch (mLayoutType) {
            case LINEAR_LAYOUT:
                setView(assembleLinearLayout());
                break;

            case RELATIVE_LAYOUT:
                setView(assembleRelativeLayout());
                break;
        }


        JSONArray children = getJsonObject().getJSONArray(CHILDREN);


        for (int i = 0; i < children.length(); i++) {
            JSONObject obj = children.getJSONObject(i);
            String key = obj.keys().next();

            ViewAssembler assembler = null;

            if (LayoutAssembler.LAYOUTS.contains(key)) {
                assembler = new LayoutAssembler(obj.getJSONObject(key), getAssemblerContext(), getView(), key);
            } else if (WidgetAssembler.WIDGETS.contains(key)) {
                assembler = new WidgetAssembler(obj.getJSONObject(key), getAssemblerContext(), getView(), key);
            }

            assembler.parse();

        }
    }

    @Override
    public ViewGroup getView() {
        return (ViewGroup) super.getView();
    }

    private LinearLayout assembleLinearLayout() {
        LinearLayout l = new LinearLayout(getAssemblerContext().getContext());
        l.setOrientation(LinearLayout.VERTICAL);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        l.setLayoutParams(params);
        return l;
    }

    private RelativeLayout assembleRelativeLayout() {
        RelativeLayout l = new RelativeLayout(getAssemblerContext().getContext());
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        l.setLayoutParams(params);
        return l;
    }

    @Override
    protected void applyProperties(View view, JSONObject jsonObject) throws JSONException {
        super.applyProperties(view, jsonObject);

        if (view instanceof LinearLayout) {
            LinearLayourAttributeHelper.applyAttributes(jsonObject, (LinearLayout) view);
        }
    }
}
