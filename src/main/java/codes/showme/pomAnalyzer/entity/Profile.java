package codes.showme.pomAnalyzer.entity;

import java.util.List;
import java.util.Map;

/**
 * @author guanhong 2017/1/3 下午5:26.
 */
public class Profile {
    private String id;
    private Build build;
    private Activation activation;
    private Map<String, String> properties;
    private List<Repository> repositories;
    private List<Repository> pluginRepositories;

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

    public List<Repository> getRepositories() {
        return this.repositories;
    }

    public void setRepositories(List<Repository> repositories) {
        this.repositories = repositories;
    }

    public List<Repository> getPluginRepositories() {
        return this.pluginRepositories;
    }

    public void setPluginRepositories(List<Repository> pluginRepositories) {
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

    public static class Repository {
        private String id;
        private String url;
        private String name;
        private Map<String, String> releases;
        private Map<String, String> snapshots;

        public String getId() {
            return this.id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUrl() {
            return this.url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Map<String, String> getReleases() {
            return this.releases;
        }

        public void setReleases(Map<String, String> releases) {
            this.releases = releases;
        }

        public Map<String, String> getSnapshots() {
            return this.snapshots;
        }

        public void setSnapshots(Map<String, String> snapshots) {
            this.snapshots = snapshots;
        }
    }
}
