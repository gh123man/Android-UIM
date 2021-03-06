package com.brian.floersch.uim.AssemblerV1;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by brian on 3/7/15.
 */
public class ViewAttributeHelper {

    private static final String MATCH_DP = "^\\d+dp$";
    private static final String MATCH_PX = "^\\d+px$";
    private static final String STRIP_CHARS = "[^0-9]";
    private static final String MATCH_WRAP_CONTENT = "^wrap_content$";
    private static final String MATCH_MATCH_PARENT = "^match_parent$";
    private static final String MATCH_FILL_PARENT = "^fill_parent$";
    private static final String MATCH_PADDING = "^padding";

    private static final String PADDING = "padding";
    private static final String PADDING_LEFT = "paddingLeft";
    private static final String PADDING_RIGHT = "paddingRight";
    private static final String PADDING_TOP = "paddingTop";
    private static final String PADDING_BOTTOM = "paddingBottom";

    protected static final HashMap<String, Integer> GRAVITY_MAP = new HashMap<String, Integer>() {{
        put("top", Gravity.TOP);
        put("bottom", Gravity.BOTTOM);
        put("left", Gravity.LEFT);
        put("right", Gravity.RIGHT);
        put("center_vertical", Gravity.CENTER_VERTICAL);
        put("fill_vertical", Gravity.FILL_VERTICAL);
        put("center_horizontal", Gravity.CENTER_HORIZONTAL);
        put("fill_horizontal", Gravity.FILL_HORIZONTAL);
        put("center", Gravity.CENTER);
        put("fill", Gravity.FILL);
        put("clip_vertical", Gravity.CLIP_VERTICAL);
        put("clip_horizontal", Gravity.CLIP_HORIZONTAL);
        put("start", Gravity.START);
        put("end", Gravity.END);
    }};

    public static int parseGravity(String gravityString) {
        String[] params = gravityString.split("\\|");
        int gravity = 0;
        for (String s : params) {
            gravity |= GRAVITY_MAP.get(s);
        }
        return gravity;
    }


    public static int pxToDp(float px, Context context) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px * scale + 0.5f);
    }

    public static int parseDim(String dim, Context context) {
        int outVal;
        String striped = dim.replaceAll(STRIP_CHARS,"");
        if (dim.matches(MATCH_DP)) {
            outVal = pxToDp(Integer.parseInt(striped), context);
        } else if (dim.matches(MATCH_PX)) {
            outVal = Integer.parseInt(striped);
        } else if (dim.matches(MATCH_MATCH_PARENT) || dim.matches(MATCH_FILL_PARENT)) {
            outVal = ViewGroup.LayoutParams.MATCH_PARENT;
        } else if (dim.matches(MATCH_WRAP_CONTENT)) {
            outVal = ViewGroup.LayoutParams.WRAP_CONTENT;
        } else { //DEFAULT (MAY CHANGE)
            outVal = ViewGroup.LayoutParams.WRAP_CONTENT;
        }
        return outVal;
    }

    public static void applyAttributes(JSONObject jsonObject, View view, Context context) throws JSONException {

        if (jsonObject.has(PADDING) || jsonObject.has(PADDING_LEFT) || jsonObject.has(PADDING_TOP) || jsonObject.has(PADDING_RIGHT) || jsonObject.has(PADDING_BOTTOM)) {
            applyPadding(jsonObject, context, view);
        }


    }

    private static void applyPadding(JSONObject jsonObject, Context context, View view) throws JSONException {

        int l,r,t,b;
        if (jsonObject.has(PADDING)) {
            l = r = t = b = parseDim(jsonObject.getString(PADDING), context);
        } else {
            l = view.getPaddingLeft();
            r = view.getPaddingRight();
            t = view.getPaddingTop();
            b = view.getPaddingBottom();
        }
        view.setPadding(
            jsonObject.has(PADDING_LEFT) ? parseDim(jsonObject.getString(PADDING_LEFT), context) : l,
            jsonObject.has(PADDING_TOP) ? parseDim(jsonObject.getString(PADDING_TOP), context) : t,
            jsonObject.has(PADDING_RIGHT) ? parseDim(jsonObject.getString(PADDING_RIGHT), context) : r,
            jsonObject.has(PADDING_BOTTOM) ? parseDim(jsonObject.getString(PADDING_BOTTOM), context) : b
        );
    }

}
