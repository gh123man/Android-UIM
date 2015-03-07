package com.brian.floersch.assembler.AssemblerV1;

import android.content.Context;
import android.widget.LinearLayout;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by brian on 3/7/15.
 */
public class LinearLayoutParamsHelper {

    private static final String GRAVITY = "gravity";
    private static final String WEIGHT = "weight";

    public static void applyAttributes(JSONObject jsonObject, LinearLayout.LayoutParams layoutParams) throws JSONException {

        if (jsonObject.has(GRAVITY)) {
            layoutParams.gravity = LinearLayourAttributeHelper.GRAVITY_MAP.get(jsonObject.getString(GRAVITY));
        }

        if (jsonObject.has(WEIGHT)) {
            layoutParams.weight = (float) jsonObject.getDouble(WEIGHT);
        }

    }

}
