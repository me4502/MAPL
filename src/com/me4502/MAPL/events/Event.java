package com.me4502.MAPL.events;

public abstract class Event {

    public void start() {
    }

    public abstract RegisteredEventHandler getHandler();
}