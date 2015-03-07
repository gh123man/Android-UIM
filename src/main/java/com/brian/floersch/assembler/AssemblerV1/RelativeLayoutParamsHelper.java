package com.brian.floersch.assembler.AssemblerV1;

import android.widget.RelativeLayout;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by brian on 3/7/15.
 */
public class RelativeLayoutParamsHelper {

    private static final String TO_LEFT_OF = "toLeftOf";
    private static final String TO_RIGHT_OF = "toRightOf";
    private static final String BELOW = "below";
    private static final String ABOVE = "above";
    private static final String ALIGN_PARENT_TOP = "alignParentTop";
    private static final String ALIGN_PARENT_BOTTOM = "alignParentBottom";
    private static final String ALIGN_PARENT_RIGHT = "alignParentRight";
    private static final String ALIGN_PARENT_LEFT = "alignParentLeft";
    private static final String ALIGN_BASELINE = "alignBaseline";
    private static final String ALIGN_LEFT = "alignLeft";
    private static final String ALIGN_RIGHT = "alignRight";
    private static final String ALIGN_TOP = "alignRight";
    private static final String CENTER_HORIZONTAL = "centerHorizontal";
    private static final String CENTER_IN_PARENT = "centerInParent";
    private static final String CENTER_VERTICAL = "centerVertical";

    public static void applyAttributes(JSONObject jsonObject, RelativeLayout.LayoutParams layoutParams) throws JSONException {
        applyRelativeLayoutRuleIfExists(jsonObject, layoutParams, TO_LEFT_OF, RelativeLayout.LEFT_OF);
        applyRelativeLayoutRuleIfExists(jsonObject, layoutParams, TO_RIGHT_OF, RelativeLayout.RIGHT_OF);
        applyRelativeLayoutRuleIfExists(jsonObject, layoutParams, BELOW, RelativeLayout.BELOW);
        applyRelativeLayoutRuleIfExists(jsonObject, layoutParams, ABOVE, RelativeLayout.ABOVE);
        applyRelativeLayoutRuleIfExists(jsonObject, layoutParams, ALIGN_LEFT, RelativeLayout.ALIGN_LEFT);
        applyRelativeLayoutRuleIfExists(jsonObject, layoutParams, ALIGN_RIGHT, RelativeLayout.ALIGN_RIGHT);
        applyRelativeLayoutRuleIfExists(jsonObject, layoutParams, ALIGN_TOP, RelativeLayout.ALIGN_TOP);

        applyRelativeLayoutBooleanIfExists(jsonObject, layoutParams, ALIGN_PARENT_TOP, RelativeLayout.ALIGN_PARENT_TOP);
        applyRelativeLayoutBooleanIfExists(jsonObject, layoutParams, ALIGN_PARENT_BOTTOM, RelativeLayout.ALIGN_PARENT_BOTTOM);
        applyRelativeLayoutBooleanIfExists(jsonObject, layoutParams, ALIGN_PARENT_RIGHT, RelativeLayout.ALIGN_PARENT_RIGHT);
        applyRelativeLayoutBooleanIfExists(jsonObject, layoutParams, ALIGN_PARENT_LEFT, RelativeLayout.ALIGN_PARENT_LEFT);
        applyRelativeLayoutBooleanIfExists(jsonObject, layoutParams, ALIGN_BASELINE, RelativeLayout.ALIGN_BASELINE);
        applyRelativeLayoutBooleanIfExists(jsonObject, layoutParams, ALIGN_PARENT_TOP, RelativeLayout.ALIGN_PARENT_TOP);
        applyRelativeLayoutBooleanIfExists(jsonObject, layoutParams, CENTER_HORIZONTAL, RelativeLayout.CENTER_HORIZONTAL);
        applyRelativeLayoutBooleanIfExists(jsonObject, layoutParams, CENTER_IN_PARENT, RelativeLayout.CENTER_IN_PARENT);
        applyRelativeLayoutBooleanIfExists(jsonObject, layoutParams, CENTER_VERTICAL, RelativeLayout.CENTER_VERTICAL);
    }

    private static void applyRelativeLayoutBooleanIfExists(JSONObject obj, RelativeLayout.LayoutParams layoutParams, String propName, int prop) throws JSONException {
        if (obj.has(propName) && obj.getBoolean(propName)) {
            layoutParams.addRule(prop);
        }
    }

    private static void applyRelativeLayoutRuleIfExists(JSONObject obj, RelativeLayout.LayoutParams layoutParams, String propName, int prop) throws JSONException {
        if (obj.has(propName)) {
            layoutParams.addRule(prop, obj.getString(propName).hashCode());
        }
    }
}
