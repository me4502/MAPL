package com.me4502.MAPL.events;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.me4502.MAPL.Cancellable;

public class RegisteredEventHandler<Type> {

    public Type[] classes;
    public RegisteredEvent[][] methods;

    private final Type[] sources;

    public RegisteredEventHandler(Type[] sources) {

        this.sources = sources;
    }

    public boolean scanClasses(Event event) {
        HashMap<Type, RegisteredEvent[]> tempList = new HashMap<Type, RegisteredEvent[]>();
        if (sources == null)
            return false;
        for (Type h : sources) {
            if (h instanceof EventListener) {
                List<RegisteredEvent> meth = new ArrayList<RegisteredEvent>();
                Class c = h.getClass();
                for (Method m : c.getMethods()) {
                    method: {
                        if (m.isAnnotationPresent(Listener.class)) {
                            for (Class p : m.getParameterTypes()) {
                                if (!p.isInstance(event))
                                    break method;
                            }
                            Listener list = m.getAnnotation(Listener.class);
                            RegisteredEvent rev = new RegisteredEvent(m, list.requireOn(), list.ignoreCancelled());
                            if (!meth.contains(rev))
                                meth.add(rev);
                            if (event instanceof Cancellable) {
                                if (list.ignoreCancelled() && ((Cancellable) event).isCancelled())
                                    continue;
                            }
                        }
                    }
                }
                if (!meth.isEmpty()) {
                    System.out.println("Adding Methods to... " + h.getClass().getName());
                    tempList.put(h, meth.toArray(new RegisteredEvent[meth.size()]));
                }
            }
        }
        classes = (Type[]) new Object[tempList.size()];
        methods = new RegisteredEvent[tempList.size()][4]; /*
                                                            * 4 of the same
                                                            * event handlers per
                                                            * class?
                                                            */
        int num = 0;
        for (Map.Entry<Type, RegisteredEvent[]> en : tempList.entrySet()) {
            classes[num] = en.getKey();
            methods[num] = en.getValue();
            num++;
        }

        return true;
    }
}