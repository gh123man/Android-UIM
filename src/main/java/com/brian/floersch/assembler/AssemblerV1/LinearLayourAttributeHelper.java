package com.brian.floersch.assembler.AssemblerV1;

import android.view.Gravity;
import android.widget.LinearLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by brian on 3/7/15.
 */
public class LinearLayourAttributeHelper {

    private static final String BASELINE_ALIGNED = "baselineAligned";
    private static final String BASELINE_ALIGNED_CHILD_INDEX = "baselineAlignedChildIndex";
    private static final String GRAVITY = "gravity";
    private static final String MEASURE_WITH_LARGEST_CHILD = "measureWithLargestChild";
    private static final String ORIENTATION = "orientation";
    private static final String VERTICAL = "vertical";

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

    public static void applyAttributes(JSONObject jsonObject, LinearLayout layout) throws JSONException {

        if (jsonObject.has(BASELINE_ALIGNED)) {
            layout.setBaselineAligned(jsonObject.getBoolean(BASELINE_ALIGNED));
        }

        if (jsonObject.has(BASELINE_ALIGNED_CHILD_INDEX)) {
            layout.setBaselineAlignedChildIndex(jsonObject.getInt(BASELINE_ALIGNED_CHILD_INDEX));
        }

        if (jsonObject.has(GRAVITY)) {
            layout.setGravity(GRAVITY_MAP.get(jsonObject.getString(GRAVITY)));
        }

        if (jsonObject.has(MEASURE_WITH_LARGEST_CHILD)) {
            layout.setMeasureWithLargestChildEnabled(jsonObject.getBoolean(MEASURE_WITH_LARGEST_CHILD));
        }

        if (jsonObject.has(MEASURE_WITH_LARGEST_CHILD)) {
            layout.setMeasureWithLargestChildEnabled(jsonObject.getBoolean(MEASURE_WITH_LARGEST_CHILD));
        }

        if (jsonObject.has(ORIENTATION)) {
            layout.setOrientation(jsonObject.getString(ORIENTATION).equals(VERTICAL) ? LinearLayout.VERTICAL : LinearLayout.HORIZONTAL);
        } else {
            (layout).setOrientation(LinearLayout.VERTICAL);
        }
    }

}
