package com.brian.floersch.uim.AssemblerV1;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class WidgetAssembler extends ViewAssembler {

    private static final String BUTTON = "Button";
    private static final String TEXT_VIEW = "TextView";
    private static final String SEEK_BAR = "SeekBar";
    private static final String ON_STOP_TRACKING_TOUCH = "onStopTrackingTouch";

    public static final ArrayList<String> WIDGETS = new ArrayList<String>() {{
        add(BUTTON);
        add(TEXT_VIEW);
        add(SEEK_BAR);
    }};

    private String mLayoutType;

    public WidgetAssembler(JSONObject jsonObject, UimContext uimContext, ViewGroup parent, String key) {
        super(jsonObject, uimContext, parent);
        mLayoutType = key;
    }

    @Override
    public void setUpView() throws JSONException  {
        switch (mLayoutType) {
            case BUTTON:
                setView(assembleButton());
                break;
            case TEXT_VIEW:
                setView(assembleTextView());
                break;
            case SEEK_BAR:
                setView(assembleSeekBar());
                break;
        }
    }

    private Button assembleButton() {
        Button button = new Button(getAssemblerContext().getContext());
        return button;
    }

    private TextView assembleTextView() {
        TextView tv = new TextView(getAssemblerContext().getContext());
        return tv;
    }

    private SeekBar assembleSeekBar() {
        SeekBar seekBar = new SeekBar(getAssemblerContext().getContext());
        return seekBar;
    }

    @Override
    protected void applyProperties(View view, JSONObject jsonObject) throws JSONException {
        super.applyProperties(view, jsonObject);



        if (view instanceof TextView) {
            TextViewAttributeHelper.applyAttributes(jsonObject, (TextView) view);
        }

        if (view instanceof SeekBar && hasEvent(ON_STOP_TRACKING_TOUCH)) {
            ((SeekBar) view).setOnSeekBarChangeListener(getAssemblerContext().getEventHandler());
        }

    }


}
