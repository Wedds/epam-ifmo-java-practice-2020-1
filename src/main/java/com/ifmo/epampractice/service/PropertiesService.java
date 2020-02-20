package com.ifmo.epampractice.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

//public class PropertiesService {
//    private static final String DEFAULT_PATH = "src/test/resources/properties/";
//
//    public static class PropertyFile {
//        private static String fileName;
//
//        public PropertyFile(String fileName) {
//            PropertyFile.fileName = fileName;
//        }
//
//        public String getPropsByName(String name) {
//            Properties properties = new Properties();
//            try {
//                properties.load(new FileReader(DEFAULT_PATH + fileName));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return properties.getProperty(name);
//        }
//    }
//
//}


public class PropertiesService {
    private static final String DEFAULT_FOLDER = "properties/";
    private Properties props;

    public PropertiesService(String fileName) {
        this.props = new Properties();
        try (InputStream stream = getClass().getClassLoader().getResourceAsStream(DEFAULT_FOLDER + fileName)) {
            if (stream == null) {
                throw new FileNotFoundException();
            }
            props.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPropsByName(String name) {
        return props.getProperty(name);
    }

}
