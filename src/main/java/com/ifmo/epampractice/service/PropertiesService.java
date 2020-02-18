package com.ifmo.epampractice.service;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesService {
    private static final String DEFAULT_PATH = "src/test/resources/properties/";

    public static class PropertyFile {
        private static String fileName;

        public PropertyFile(String fileName) {
            PropertyFile.fileName = fileName;
        }

        public String getPropsByName(String name) {
            Properties properties = new Properties();
            try {
                properties.load(new FileReader(DEFAULT_PATH + fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return properties.getProperty(name);
        }
    }

}
