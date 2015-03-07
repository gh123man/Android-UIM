package com.brian.floersch.assembler.AssemblerV1;

import android.content.Context;

import com.brian.floersch.assembler.AssemblerV1.events.GlobalEventHandler;
import com.brian.floersch.assembler.IuimEvents;

/**
 * Created by brian on 3/1/15.
 */
public class AssemblerContext {
    private GlobalEventHandler mEventHandler;
    private Context mContext;

    public AssemblerContext(IuimEvents uimEvents, Context context) {
        mEventHandler = new GlobalEventHandler(uimEvents);
        mContext = context;
    }

    public GlobalEventHandler getEventHandler() {
        return mEventHandler;
    }

    public Context getContext() {
        return mContext;
    }

}
