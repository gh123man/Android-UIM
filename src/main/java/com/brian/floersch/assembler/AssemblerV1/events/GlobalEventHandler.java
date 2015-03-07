package com.brian.floersch.assembler.AssemblerV1.events;

import android.view.View;
import android.widget.SeekBar;

import com.brian.floersch.assembler.IuimEvents;

/**
 * Created by brian on 3/7/15.
 */
public class GlobalEventHandler implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    private IuimEvents mEventHandler;

    public GlobalEventHandler(IuimEvents eventHandler) {
        mEventHandler = eventHandler;
    }

    @Override
    public void onClick(View v) {
        Event e = new OnClickEvent((String) v.getTag());
        mEventHandler.onEvent(e);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) { }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {}
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        Event e = new OnSeekBarChangeEvent((String) seekBar.getTag(), seekBar.getProgress());
        mEventHandler.onEvent(e);
    }
}
