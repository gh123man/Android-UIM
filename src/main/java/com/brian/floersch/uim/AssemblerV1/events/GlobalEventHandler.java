package com.brian.floersch.uim.AssemblerV1.events;

import android.view.View;
import android.widget.SeekBar;

import com.brian.floersch.uim.IuimEvents;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Global event handler class used for handling all view related events
 */
public class GlobalEventHandler implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, View.OnLongClickListener {

    private IuimEvents mEventHandler;
    private HashMap<String, String[]> mPackager;
    private View mRootView;

    /**
     * Constructs a new GloblaEventHandler class instance
     *
     * @param eventHandler the UIM event handler
     * @param root         the root view
     */
    public GlobalEventHandler(IuimEvents eventHandler, View root) {
        mEventHandler = eventHandler;
        mPackager = new HashMap<>();
        mRootView = root;
    }

    /**
     * Adds a package of views to the event
     *
     * @param key      String view id
     * @param packaged string Array of view ids
     */
    public void addEventPackage(String key, String[] packaged) {
        mPackager.put(key, packaged);
    }

    /**
     * Assembles the view package for an event
     *
     * @param id the String view id
     * @return the collection of views in the package
     */
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
    public boolean onLongClick(View v) {
        String id = (String) v.getTag();
        Event e = new Event(id, v, resolvePackage(id), Event.Type.ON_LONG_CLICK);
        mEventHandler.onEvent(e);
        return true;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        String id = (String) seekBar.getTag();
        Event e = new Event(id, seekBar, resolvePackage(id), Event.Type.ON_STOP_TRACKING_TOUCH);
        mEventHandler.onEvent(e);
    }

}
