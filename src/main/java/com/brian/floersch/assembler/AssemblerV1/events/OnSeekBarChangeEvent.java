package com.brian.floersch.assembler.AssemblerV1.events;

/**
 * Created by brian on 3/7/15.
 */
public class OnSeekBarChangeEvent extends Event {

    private int mProgress;

    public OnSeekBarChangeEvent(String objectId, int progress) {
        super(objectId);
        mProgress = progress;
    }

    public int getProgress() {
        return mProgress;
    }

}
