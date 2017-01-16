package codes.showme.pomAnalyzer.entity.simple;


import java.util.List;
import java.util.Map;

/**
 * @author guanhong 2017/1/3 下午4:37.
 */
public class Pom {
    private Pom parent;
    private String name;
    private String url;
    private String modelVersion;
    private String groupId;
    private String artifactId;
    private String version;
    private String packaging;
    private List<String> modules;
    private String description;
    private String organization;
    private String relativePath;
    private Map<String, String> properties;
    private DependencyManagement dependencyManagement;
    private List<Dependency> dependencies;

    public Pom getParent() {
        return this.parent;
    }

    public void setParent(Pom parent) {
        this.parent = parent;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getModelVersion() {
        return this.modelVersion;
    }

    public void setModelVersion(String modelVersion) {
        this.modelVersion = modelVersion;
    }

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

    public String getPackaging() {
        return this.packaging;
    }

    public void setPackaging(String packaging) {
        this.packaging = packaging;
    }

    public List<String> getModules() {
        return this.modules;
    }

    public void setModules(List<String> modules) {
        this.modules = modules;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrganization() {
        return this.organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getRelativePath() {
        return this.relativePath;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }

    public DependencyManagement getDependencyManagement() {
        return this.dependencyManagement;
    }

    public void setDependencyManagement(DependencyManagement dependencyManagement) {
        this.dependencyManagement = dependencyManagement;
    }

    public List<Dependency> getDependencies() {
        return this.dependencies;
    }

    public void setDependencies(List<Dependency> dependencies) {
        this.dependencies = dependencies;
    }

    public Map<String, String> getProperties() {
        return this.properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }
}
