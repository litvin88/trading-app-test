package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class Config {

    private static final Logger logger = LoggerFactory.getLogger(Config.class);
    private static final Properties props;

    static {
        props = new Properties();
        try {
            props.load(Config.class.getClassLoader().
                    getResourceAsStream("config.properties"));
        } catch (Exception ex) {
            logger.info("Config file read failed: {}", ex.fillInStackTrace().toString());
        }
    }

    public static String getBaseUrl() {
        return props.getProperty("base.url");
    }

}
