package com.me4502.MAPL.events;

import java.lang.reflect.Method;

public class RegisteredEvent {

    public Method method;

    public boolean requiresOn = true;

    public boolean ignoresCancelled = true;

    public RegisteredEvent(Method method, boolean on, boolean cancelled) {
        this.method = method;
        requiresOn = on;
        ignoresCancelled = cancelled;
    }
}
