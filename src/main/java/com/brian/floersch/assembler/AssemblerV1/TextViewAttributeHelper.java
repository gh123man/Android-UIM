package com.brian.floersch.assembler.AssemblerV1;

import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by brian on 3/7/15.
 */
public class TextViewAttributeHelper {

    private static final String TEXT = "text";
    private static final String GRAVITY = "gravity";

    public static void applyAttributes(JSONObject jsonObject, TextView view) throws JSONException {
        if (jsonObject.has(TEXT)) {
            view.setText(jsonObject.getString(TEXT));
        }
        if (jsonObject.has(GRAVITY)) {
            view.setGravity(ViewAttributeHelper.parseGravity(jsonObject.getString(GRAVITY)));
        }
    }

}
