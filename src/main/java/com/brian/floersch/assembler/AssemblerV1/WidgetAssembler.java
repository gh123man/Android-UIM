package com.brian.floersch.assembler.AssemblerV1;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class WidgetAssembler extends ViewAssembler {

    private static final String BUTTON = "Button";
    private static final String TEXT = "text";
    private static final String TO_LEFT_OF = "toLeftOf";
    private static final String TO_RIGHT_OF = "toRightOf";
    private static final String BELOW = "below";
    private static final String ABOVE = "above";
    private static final String ALIGN_PARENT_TOP = "alignParentTop";
    private static final String ALIGN_PARENT_BOTTOM = "alignParentBottom";
    private static final String ALIGN_PARENT_RIGHT = "alignParentRight";
    private static final String ALIGN_PARENT_LEFT = "alignParentLeft";

    public static final ArrayList<String> WIDGETS = new ArrayList<String>() {{
        add(BUTTON);
    }};

    private String mLayoutType;

    public WidgetAssembler(JSONObject jsonObject, AssemblerContext assemblerContext, ViewGroup parent, String key) {
        super(jsonObject, assemblerContext, parent);
        mLayoutType = key;
    }

    @Override
    public void setUpView() {
        switch (mLayoutType) {
            case BUTTON:
                setView(assembleButton());
                break;
        }
    }

    private Button assembleButton() {
        Button button = new Button(getAssemblerContext().getContext());
        return button;
    }

    @Override
    protected void applyProperties(View view, JSONObject jsonObject) throws JSONException {
        super.applyProperties(view, jsonObject);

        if (view instanceof TextView) {
            if (jsonObject.has(TEXT)) {
                ((TextView) view).setText(jsonObject.getString(TEXT));
            }
        }

        if (view.getParent() instanceof RelativeLayout) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
            if (jsonObject.has(TO_LEFT_OF)) {
                layoutParams.addRule(RelativeLayout.LEFT_OF, jsonObject.getString(TO_LEFT_OF).hashCode());
            }
            if (jsonObject.has(TO_RIGHT_OF)) {
                layoutParams.addRule(RelativeLayout.RIGHT_OF, jsonObject.getString(TO_RIGHT_OF).hashCode());
            }
            if (jsonObject.has(BELOW)) {
                layoutParams.addRule(RelativeLayout.BELOW, jsonObject.getString(BELOW).hashCode());
            }
            if (jsonObject.has(ABOVE)) {
                layoutParams.addRule(RelativeLayout.ABOVE, jsonObject.getString(ABOVE).hashCode());
            }
            if (jsonObject.has(ALIGN_PARENT_TOP) && jsonObject.getBoolean(ALIGN_PARENT_TOP)) {
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            }
            if (jsonObject.has(ALIGN_PARENT_BOTTOM) && jsonObject.getBoolean(ALIGN_PARENT_BOTTOM)) {
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            }
            if (jsonObject.has(ALIGN_PARENT_RIGHT) && jsonObject.getBoolean(ALIGN_PARENT_RIGHT)) {
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            }
            if (jsonObject.has(ALIGN_PARENT_LEFT) && jsonObject.getBoolean(ALIGN_PARENT_LEFT)) {
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            }
        }

    }
}
