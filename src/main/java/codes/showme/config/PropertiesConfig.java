package codes.showme.config;

import codes.showme.exception.LoadConfigFromPropertiesException;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

/**
 * Created by jack on 11/13/16.
 */
public class PropertiesConfig implements Configuration, Serializable{

    private static final long serialVersionUID = 5346106334271819144L;
    private Properties properties = new Properties();

    public PropertiesConfig() {
        InputStream in = getClass().getResourceAsStream("/env.properties");
        try {
            properties.load(in);
        } catch (IOException e) {
            throw new LoadConfigFromPropertiesException(e);
        }
    }

    public Properties getProperties() {
        return properties;
    }

    @Override
    public String getDependenciesResultDirectory() {
        return properties.getProperty("dependencies_result_path_directory", "");
    }
}
