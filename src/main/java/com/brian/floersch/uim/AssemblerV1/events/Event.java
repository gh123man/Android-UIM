package com.brian.floersch.uim.AssemblerV1.events;

import android.view.View;

import java.util.ArrayList;

/**
 * Contains the context of an event
 */
public class Event {

    /**
     * Supported events
     */
    public static enum Type {
        ON_CLICK,
        ON_STOP_TRACKING_TOUCH,
        ON_LONG_CLICK
    }

    private final String mObjectId;
    private final ArrayList<Packaged> mPackagedViews;
    private final Type mType;
    private final View mRoot;

    /**
     * Creates a new Event class instance
     *
     * @param viewId        the string id of a view
     * @param view          the view instance
     * @param packagedViews a list of packaged views
     * @param type          the event type
     */
    public Event(String viewId, View view, ArrayList<Packaged> packagedViews, Type type) {
        mObjectId = viewId;
        mPackagedViews = packagedViews;
        mType = type;
        mRoot = view;
    }

    /**
     * Gets the array of packaged views
     *
     * @return ArrayList of packaged views
     */
    public ArrayList<Packaged> getPackagedViews() {
        return mPackagedViews;
    }

    /**
     * gets the event root view ID
     *
     * @return
     */
    public String getId() {
        return mObjectId;
    }

    /**
     * gets the event root view
     *
     * @return the root view
     */
    public View getRootView() {
        return mRoot;
    }

    /**
     * gets the event type
     *
     * @return the event type
     */
    public Type getType() {
        return mType;
    }

    /**
     * Returns true if there is an event package
     *
     * @return true if event package exists
     */
    public boolean hasPackage() {
        return mPackagedViews != null;
    }
}
