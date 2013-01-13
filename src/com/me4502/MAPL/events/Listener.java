package com.me4502.MAPL.events;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Listener {

    boolean ignoreCancelled() default true;

    boolean requireOn() default true;
}