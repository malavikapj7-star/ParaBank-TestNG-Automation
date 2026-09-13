package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties = new Properties();

    public static void loadProperties() {

        try {
            FileInputStream file = new FileInputStream(
                    "src/test/resources/config.properties"
            );

            properties.load(file);
            file.close();

        } catch (IOException e) {
            throw new RuntimeException(
                    "Could not load config.properties", e
            );
        }
    }

    public static String getProperty(String key) {

        return properties.getProperty(key);
    }
}