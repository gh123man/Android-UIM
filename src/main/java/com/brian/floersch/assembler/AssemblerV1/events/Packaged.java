package com.brian.floersch.assembler.AssemblerV1.events;

import android.view.View;

/**
 * Created by brian on 3/8/15.
 */
public class Packaged {

    private final String mId;
    private final View mView;

    protected Packaged(String id, View v) {
        mId = id;
        mView = v;
    }

    public String getId() {
        return mId;
    }

    public View getView() {
        return mView;
    }
}
