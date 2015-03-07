package com.brian.floersch.assembler.AssemblerV1.events;

/**
 * Created by brian on 3/7/15.
 */
public class Event {

    private String mObjectId;

    public Event(String objectId) {
        mObjectId = objectId;
    }

    public String getId() {
        return mObjectId;
    }
}
