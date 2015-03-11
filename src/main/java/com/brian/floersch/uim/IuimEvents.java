package com.brian.floersch.uim;

import com.brian.floersch.uim.AssemblerV1.events.Event;

/**
 * Events used by UIM
 */
public interface IuimEvents {
    /**
     * Called on a view event
     *
     * @param event the event
     */
    public void onEvent(Event event);
}
