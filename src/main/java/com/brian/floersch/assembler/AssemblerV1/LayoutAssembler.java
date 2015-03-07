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

    private static final String CHILDREN = "uim_children";

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

            if (assembler == null) {
                throw new JSONException("Error in JSON or unsupported view");
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
        return l;
    }

    private RelativeLayout assembleRelativeLayout() {
        RelativeLayout l = new RelativeLayout(getAssemblerContext().getContext());
        return l;
    }

    @Override
    protected void applyProperties(View view, JSONObject jsonObject) throws JSONException {
        super.applyProperties(view, jsonObject);

        if (view instanceof LinearLayout) {
            LinearLayourAttributeHelper.applyAttributes(jsonObject, (LinearLayout) view);
        }

        if (view instanceof RelativeLayout) {
            RelativeLayourAttributeHelper.applyAttributes(jsonObject, (RelativeLayout) view);
        }
    }
}
