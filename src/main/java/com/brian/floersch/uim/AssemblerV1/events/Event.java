package com.brian.floersch.uim.AssemblerV1.events;

import android.view.View;

import java.util.ArrayList;

/**
 * Created by brian on 3/7/15.
 */
public class Event {

    public static enum Type {
        ON_CLICK,
        ON_STOP_TRACKING_TOUCH,
        ON_LONG_CLICK
    }

    private final String mObjectId;
    private final ArrayList<Packaged> mPackagedViews;
    private final Type mType;
    private final View mRoot;

    public Event(String objectId, View view, ArrayList<Packaged> packagedViews, Type type) {
        mObjectId = objectId;
        mPackagedViews = packagedViews;
        mType = type;
        mRoot = view;
    }

    public ArrayList<Packaged> getPackagedViews() {
        return mPackagedViews;
    }

    public String getId() {
        return mObjectId;
    }

    public View getRootView() {
        return mRoot;
    }

    public Type getType() {
        return mType;
    }

    public boolean hasPackage() {
        return mPackagedViews != null;
    }
}
