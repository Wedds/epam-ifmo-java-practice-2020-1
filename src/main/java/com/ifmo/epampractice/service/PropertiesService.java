package com.ifmo.epampractice.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class PropertiesService {
    private static final String FOLDER = "properties/";
    private Properties props;

    public PropertiesService(final String fileName) {
        this.props = new Properties();
        try (InputStream stream = getClass().getClassLoader().getResourceAsStream(FOLDER + fileName)) {
            if (stream == null) {
                throw new IllegalArgumentException("Can't reach required properties file!");
            }
            props.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(final String name) {
        return props.getProperty(name);
    }

}
