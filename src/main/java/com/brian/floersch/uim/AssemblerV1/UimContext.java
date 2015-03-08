package com.brian.floersch.uim.AssemblerV1;

import android.content.Context;
import android.view.View;

import com.brian.floersch.uim.AssemblerV1.events.GlobalEventHandler;
import com.brian.floersch.uim.IuimEvents;

/**
 * Created by brian on 3/1/15.
 */
public class UimContext {
    private GlobalEventHandler mEventHandler;
    private Context mContext;

    public UimContext(IuimEvents uimEvents, Context context, View rootView) {
        mEventHandler = new GlobalEventHandler(uimEvents, rootView);
        mContext = context;
    }

    public GlobalEventHandler getEventHandler() {
        return mEventHandler;
    }

    public Context getContext() {
        return mContext;
    }

}
