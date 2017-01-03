package codes.showme.pomAnalyzer.entity;

import java.util.Map;

/**
 * @author guanhong 2017/1/3 下午5:26.
 */
public class Profile {
    private String id;
    private Build build;
    private Activation activation;
    private Map<String, String> properties;
    //todo convert to object
    private String repositories;
    private String pluginRepositories;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Build getBuild() {
        return this.build;
    }

    public void setBuild(Build build) {
        this.build = build;
    }

    public Activation getActivation() {
        return this.activation;
    }

    public void setActivation(Activation activation) {
        this.activation = activation;
    }

    public String getRepositories() {
        return this.repositories;
    }

    public void setRepositories(String repositories) {
        this.repositories = repositories;
    }

    public String getPluginRepositories() {
        return this.pluginRepositories;
    }

    public void setPluginRepositories(String pluginRepositories) {
        this.pluginRepositories = pluginRepositories;
    }

    public Map<String, String> getProperties() {
        return this.properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    public static class Activation {
        private String activeByDefault;
        private Map<String, String> property;

        public String getActiveByDefault() {
            return this.activeByDefault;
        }

        public void setActiveByDefault(String activeByDefault) {
            this.activeByDefault = activeByDefault;
        }

        public Map<String, String> getProperty() {
            return this.property;
        }

        public void setProperty(Map<String, String> property) {
            this.property = property;
        }
    }
}
