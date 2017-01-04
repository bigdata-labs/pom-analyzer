package codes.showme.pomAnalyzer.entity;


import java.util.List;
import java.util.Map;

/**
 * @author guanhong 2017/1/3 下午4:49.
 */
public class Plugin {
    private String groupId;
    private String artifactId;
    private String version;
    private String extensions;
    private Map<String, String> configuration;
    private List<Execution> executions;
    private List<Dependency> dependencies;



    public String getGroupId() {
        return this.groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return this.artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getExtensions() {
        return this.extensions;
    }

    public void setExtensions(String extensions) {
        this.extensions = extensions;
    }

    public Map<String, String> getConfiguration() {
        return this.configuration;
    }

    public void setConfiguration(Map<String, String> configuration) {
        this.configuration = configuration;
    }

    public List<Execution> getExecutions() {
        return this.executions;
    }

    public void setExecutions(List<Execution> executions) {
        this.executions = executions;
    }

    public List<Dependency> getDependencies() {
        return this.dependencies;
    }

    public void setDependencies(List<Dependency> dependencies) {
        this.dependencies = dependencies;
    }

}