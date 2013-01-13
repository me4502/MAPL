package com.me4502.MAPL.events;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Hashtable;

public class CustomClassLoader extends ClassLoader {

    private final Hashtable<String, Class> classes = new Hashtable<String, Class>();

    public CustomClassLoader() {
        super(CustomClassLoader.class.getClassLoader());
    }

    public Class loadClass(String name, String url) throws ClassNotFoundException {
        try {
            if (classes.containsKey(name))
                return classes.get(name);
            URL myUrl = new URL(url);
            URLConnection connection = myUrl.openConnection();
            InputStream input = connection.getInputStream();
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int data = input.read();
            while (data != -1) {
                buffer.write(data);
                data = input.read();
            }
            input.close();
            byte[] classData = buffer.toByteArray();

            Class classy = defineClass(name, classData, 0, classData.length);
            if (classes.containsKey(name))
                return classes.get(name);
            classes.put(name, classy);
            return classy;
        } catch (NoClassDefFoundError e) {
            throw new ClassNotFoundException(name);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ClassNotFoundException(name);
        }
    }

    public void unloadClass(String name) {
        classes.remove(name);
    }

    public void unloadAll() {
        classes.clear();
    }
}
