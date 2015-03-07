package com.brian.floersch.assembler.AssemblerV1.events;

import android.view.View;

import com.brian.floersch.assembler.IuimEvents;

/**
 * Created by brian on 3/7/15.
 */
public class GlobalEventHandler implements View.OnClickListener {

    private IuimEvents mEventHandler;

    public GlobalEventHandler(IuimEvents eventHandler) {
        mEventHandler = eventHandler;
    }

    @Override
    public void onClick(View v) {
        Event e = new OnClickEvent((String) v.getTag());
        mEventHandler.onEvent(e);
    }
}
