package com.brian.floersch.uim.AssemblerV1.events;

import android.view.View;

/**
 * Class for packaging views and ids
 */
public class Packaged {

    private final String mId;
    private final View mView;

    /**
     * Returns new Packaged instance
     *
     * @param id the view ID
     * @param v  the view
     */
    protected Packaged(String id, View v) {
        mId = id;
        mView = v;
    }

    /**
     * gets the id
     *
     * @return the id
     */
    public String getId() {
        return mId;
    }

    /**
     * gets the view
     *
     * @return the view
     */
    public View getView() {
        return mView;
    }
}
