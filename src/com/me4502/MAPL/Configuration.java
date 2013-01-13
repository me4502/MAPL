package com.me4502.MAPL;

import java.io.File;

public abstract class Configuration {

    public File configurationFile;

    public Configuration(File configurationFile) {
        this.configurationFile = configurationFile;
        load();
    }

    public abstract void load();

    public abstract void save();
}