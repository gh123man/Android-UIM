package com.brian.floersch.assembler.AssemblerV1.events;

import android.view.View;
import android.widget.SeekBar;

import com.brian.floersch.assembler.IuimEvents;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by brian on 3/7/15.
 */
public class GlobalEventHandler implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    private IuimEvents mEventHandler;
    private HashMap<String, String[]> mPackager;
    private View mRootView;

    public GlobalEventHandler(IuimEvents eventHandler, View root) {
        mEventHandler = eventHandler;
        mPackager = new HashMap<>();
        mRootView = root;
    }

    public void addEventPackage(String key, String[] packaged) {
        mPackager.put(key, packaged);
    }

    private ArrayList<Packaged> resolvePackage(String id) {
        if (mPackager.containsKey(id)) {
            ArrayList<Packaged> views = new ArrayList<>();
            String[] eventPackage = mPackager.get(id);

            for (String s : eventPackage) {
                View v = mRootView.findViewById(s.hashCode());
                views.add(new Packaged(s, v));
            }
            return views;

        }
        return null;
    }

    @Override
    public void onClick(View v) {
        String id = (String) v.getTag();
        Event e = new Event(id, v, resolvePackage(id), Event.Type.ON_CLICK);
        mEventHandler.onEvent(e);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) { }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {}
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        String id = (String) seekBar.getTag();
        Event e = new Event(id, seekBar, resolvePackage(id), Event.Type.ON_STOP_TRACKING_TOUCH);
        mEventHandler.onEvent(e);
    }
}
