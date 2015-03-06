package com.brian.floersch.assembler.AssemblerV1;

import android.content.Context;

import com.brian.floersch.assembler.IuimEvents;

/**
 * Created by brian on 3/1/15.
 */
public class AssemblerContext {
    private IuimEvents mEventHandler;
    private Context mContext;

    public AssemblerContext(IuimEvents mEventHandler, Context context) {
        mEventHandler = mEventHandler;
        mContext = context;
    }

    public IuimEvents getEventHandler() {
        return mEventHandler;
    }

    public Context getContext() {
        return mContext;
    }

}
