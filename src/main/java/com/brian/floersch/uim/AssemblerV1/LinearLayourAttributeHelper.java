package com.brian.floersch.uim.AssemblerV1;

import android.widget.LinearLayout;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by brian on 3/7/15.
 */
public class LinearLayourAttributeHelper {

    private static final String BASELINE_ALIGNED = "baselineAligned";
    private static final String BASELINE_ALIGNED_CHILD_INDEX = "baselineAlignedChildIndex";
    private static final String GRAVITY = "layout_gravity";
    private static final String MEASURE_WITH_LARGEST_CHILD = "measureWithLargestChild";
    private static final String ORIENTATION = "orientation";
    private static final String VERTICAL = "vertical";

    public static void applyAttributes(JSONObject jsonObject, LinearLayout layout) throws JSONException {

        if (jsonObject.has(BASELINE_ALIGNED)) {
            layout.setBaselineAligned(jsonObject.getBoolean(BASELINE_ALIGNED));
        }

        if (jsonObject.has(BASELINE_ALIGNED_CHILD_INDEX)) {
            layout.setBaselineAlignedChildIndex(jsonObject.getInt(BASELINE_ALIGNED_CHILD_INDEX));
        }

        if (jsonObject.has(GRAVITY)) {
            layout.setGravity(ViewAttributeHelper.parseGravity(jsonObject.getString(GRAVITY)));
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
