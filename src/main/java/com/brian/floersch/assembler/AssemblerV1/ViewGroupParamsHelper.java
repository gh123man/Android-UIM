package com.brian.floersch.assembler.AssemblerV1;

import android.content.Context;
import android.view.ViewGroup;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by brian on 3/7/15.
 */
public class ViewGroupParamsHelper {

    private static final String WIDTH = "layout_width";
    private static final String HEIGHT = "layout_height";

    public static void applyAttributes(JSONObject jsonObject, ViewGroup.LayoutParams params, Context context) throws JSONException {

        if (jsonObject.has(WIDTH)) {
            params.width = ViewAttributeHelper.parseDim(jsonObject.getString(WIDTH), context);
        }

        if (jsonObject.has(HEIGHT)) {
            params.height = ViewAttributeHelper.parseDim(jsonObject.getString(HEIGHT), context);
        }
    }

}
