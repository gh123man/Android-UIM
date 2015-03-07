package com.brian.floersch.assembler.AssemblerV1;

import android.widget.RelativeLayout;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by brian on 3/7/15.
 */
public class RelativeLayourAttributeHelper {

    private static final String GRAVITY = "gravity";
    private static final String IGNORE_GRAVITY = "ignoreGravity";

    public static void applyAttributes(JSONObject jsonObject, RelativeLayout layout) throws JSONException {


        if (jsonObject.has(GRAVITY)) {
            layout.setGravity(ViewAttributeHelper.parseGravity(jsonObject.getString(GRAVITY)));
        }

        if (jsonObject.has(IGNORE_GRAVITY)) {
            layout.setIgnoreGravity(jsonObject.getString(IGNORE_GRAVITY).hashCode());
        }

    }

}
