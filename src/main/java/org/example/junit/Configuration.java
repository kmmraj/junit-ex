package org.example.junit;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {

//    private Configuration() {
//    }

    private static Boolean enabled;

    public static boolean isEnabled() {
        if (enabled == null){
            loadFromProperties();
        }
        return enabled;
    }

    private static void loadFromProperties() {
        Properties properties = readProperties();
        enabled = "true".equals(properties.getProperty("enabled"));
    }

    private static Properties readProperties() {
        Properties properties = new Properties();

        try(FileInputStream fileInputStream = new FileInputStream("some.properties")) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }


    public int add(int x, int y) {
        if (Configuration.isEnabled()) {
            return x + y;
        }
        return 0;
    }
}
