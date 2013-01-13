package com.me4502.MAPL;

import java.io.File;

/**
 * The base configuration class. Can be extended for different file formats.
 * 
 * @author Me4502
 * 
 */
public abstract class Configuration {

    public File configurationFile;

    public Configuration(File configurationFile) {
        this.configurationFile = configurationFile;
        load();
    }

    /**
     * Called to load the config.
     */
    public abstract void load();

    /**
     * Called to save the config.
     */
    public abstract void save();
}