package codes.showme.pomAnalyzer.entity;


import java.util.List;
import java.util.Map;

/**
 * @author guanhong 2017/1/3 下午4:37.
 */
public class Pom {
    private Dependency parent;
    private String name;
    private String url;
    private String modelVersion;
    private String groupId;
    private String artifactId;
    private String version;
    private String packaging;
    private String description;
    private String organization;
    private Scm scm;
    private Management issueManagement;
    private Management ciManagement;
    private Map<String, String> properties;
    private DependencyManagement dependencyManagement;
    private List<Dependency> dependencies;
    private List<Profile> profiles;
    private Build build;

    public Dependency getParent() {
        return this.parent;
    }

    public void setParent(Dependency parent) {
        this.parent = parent;
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

    public Map<String, String> getProperties() {
        return this.properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
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

    public Build getBuild() {
        return this.build;
    }

    public void setBuild(Build build) {
        this.build = build;
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

    public List<Profile> getProfiles() {
        return this.profiles;
    }

    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }

    public String getPackaging() {
        return this.packaging;
    }

    public void setPackaging(String packaging) {
        this.packaging = packaging;
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

    public Scm getScm() {
        return this.scm;
    }

    public void setScm(Scm scm) {
        this.scm = scm;
    }

    public Management getIssueManagement() {
        return this.issueManagement;
    }

    public void setIssueManagement(Management issueManagement) {
        this.issueManagement = issueManagement;
    }

    public Management getCiManagement() {
        return this.ciManagement;
    }

    public void setCiManagement(Management ciManagement) {
        this.ciManagement = ciManagement;
    }

    public static class Scm {
        private String url;
        private String connection;
        private String developerConnection;

        public String getUrl() {
            return this.url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getConnection() {
            return this.connection;
        }

        public void setConnection(String connection) {
            this.connection = connection;
        }

        public String getDeveloperConnection() {
            return this.developerConnection;
        }

        public void setDeveloperConnection(String developerConnection) {
            this.developerConnection = developerConnection;
        }
    }
}
