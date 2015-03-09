package com.brian.floersch.uim.AssemblerV1;

import android.content.Context;
import android.view.ViewGroup;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by brian on 3/7/15.
 */
public class MarginLayoutParamsHelper {

    private static final String MARGIN = "layout_margin";
    private static final String MARGIN_BOTTOM = "layout_marginBottom";
    private static final String MARGIN_TOP = "layout_marginTop";
    private static final String MARGIN_LEFT = "layout_marginLeft";
    private static final String MARGIN_RIGHT = "layout_marginRight";

    public static void applyAttributes(JSONObject jsonObject, ViewGroup.MarginLayoutParams params, Context context) throws JSONException {

        int l,r,t,b;
        l = r = t = b = jsonObject.has(MARGIN) ? ViewAttributeHelper.parseDim(jsonObject.getString(MARGIN), context) : 0;
        params.setMargins(
            jsonObject.has(MARGIN_LEFT) ? ViewAttributeHelper.parseDim(jsonObject.getString(MARGIN_LEFT), context) : l,
            jsonObject.has(MARGIN_TOP) ? ViewAttributeHelper.parseDim(jsonObject.getString(MARGIN_TOP), context) : t,
            jsonObject.has(MARGIN_RIGHT) ? ViewAttributeHelper.parseDim(jsonObject.getString(MARGIN_RIGHT), context) : r,
            jsonObject.has(MARGIN_BOTTOM) ? ViewAttributeHelper.parseDim(jsonObject.getString(MARGIN_BOTTOM), context) : b
        );

    }

}
