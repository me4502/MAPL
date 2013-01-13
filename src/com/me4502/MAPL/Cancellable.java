package com.me4502.MAPL;

/**
 * A interface to implement on anything that is cancellable; be it events or
 * multiplayer sessions.
 * 
 * @author Me4502
 * 
 */
public interface Cancellable {

    /**
     * Check to see if this has been cancelled.
     * 
     * @return the cancellation status of this.
     */
    public boolean isCancelled();

    /**
     * Set whether or not this is cancelled.
     * 
     * @param cancel
     *            The new cancelled state.
     */
    public void setCancelled(boolean cancel);
}
