package com.brian.floersch.assembler.AssemblerV1;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class WidgetAssembler extends ViewAssembler {

    private static final String BUTTON = "Button";
    private static final String TEXT_VIEW = "TextView";

    public static final ArrayList<String> WIDGETS = new ArrayList<String>() {{
        add(BUTTON);
        add(TEXT_VIEW);
    }};

    private String mLayoutType;

    public WidgetAssembler(JSONObject jsonObject, AssemblerContext assemblerContext, ViewGroup parent, String key) {
        super(jsonObject, assemblerContext, parent);
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

    @Override
    protected void applyProperties(View view, JSONObject jsonObject) throws JSONException {
        super.applyProperties(view, jsonObject);

        view.setOnClickListener(getAssemblerContext().getEventHandler());

        if (view instanceof TextView) {
            TextViewAttributeHelper.applyAttributes(jsonObject, (TextView) view);
        }

    }


}
