package com.brian.floersch.assembler.AssemblerV1;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by brian on 3/7/15.
 */
public class ViewAttributeHelper {

    private static final String MATCH_DP = "^\\d+dp$";
    private static final String MATCH_PX = "^\\d+px$";
    private static final String STRIP_CHARS = "[^0-9]";
    private static final String MATCH_WRAP_CONTENT = "^wrap_content$";
    private static final String MATCH_MATCH_PARENT = "^match_parent$";

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
        } else if (dim.matches(MATCH_MATCH_PARENT)) {
            outVal = ViewGroup.LayoutParams.MATCH_PARENT;
        } else if (dim.matches(MATCH_WRAP_CONTENT)) {
            outVal = ViewGroup.LayoutParams.WRAP_CONTENT;
        } else { //DEFAULT (MAY CHANGE)
            outVal = ViewGroup.LayoutParams.WRAP_CONTENT;
        }
        return outVal;
    }

    public static void applyAttributes(JSONObject jsonObject, LinearLayout layout) throws JSONException {

    }

}
