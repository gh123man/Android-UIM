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
    private static final String TEXT = "text";

    public static final ArrayList<String> WIDGETS = new ArrayList<String>() {{
        add(BUTTON);
    }};

    private String mLayoutType;

    public WidgetAssembler(JSONObject jsonObject, AssemblerContext assemblerContext, ViewGroup parent, String key) {
        super(jsonObject, assemblerContext, parent);
        mLayoutType = key;
    }

    @Override
    public void setUpView() {
        switch (mLayoutType) {
            case BUTTON:
                setView(assembleButton());
                break;
        }
    }

    private Button assembleButton() {
        Button button = new Button(getAssemblerContext().getContext());
        return button;
    }

    @Override
    protected void applyProperties(View view, JSONObject jsonObject) throws JSONException {
        super.applyProperties(view, jsonObject);

        if (view instanceof TextView) {
            if (jsonObject.has(TEXT)) {
                ((TextView) view).setText(jsonObject.getString(TEXT));
            }
        }

    }


}
