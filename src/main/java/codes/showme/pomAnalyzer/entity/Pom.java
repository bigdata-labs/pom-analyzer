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
    private List<String> modules;
    private String description;
    private String organization;
    private String inceptionYear;
    private Scm scm;
    private Management issueManagement;
    private Management ciManagement;
    private Map<String, String> properties;
    private Map<String, String> distributionManagement;
    private DependencyManagement dependencyManagement;
    private List<Dependency> dependencies;
    private List<License> licenses;
    private List<Profile> profiles;
    private List<Developer> developers;
    private Build build;

    public Dependency getParent() {
        return this.parent;
    }

    public void setParent(Dependency parent) {
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

    public String getInceptionYear() {
        return this.inceptionYear;
    }

    public void setInceptionYear(String inceptionYear) {
        this.inceptionYear = inceptionYear;
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

    public Map<String, String> getProperties() {
        return this.properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    public Map<String, String> getDistributionManagement() {
        return this.distributionManagement;
    }

    public void setDistributionManagement(Map<String, String> distributionManagement) {
        this.distributionManagement = distributionManagement;
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

    public List<License> getLicenses() {
        return this.licenses;
    }

    public void setLicenses(List<License> licenses) {
        this.licenses = licenses;
    }

    public List<Profile> getProfiles() {
        return this.profiles;
    }

    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }

    public List<Developer> getDevelopers() {
        return this.developers;
    }

    public void setDevelopers(List<Developer> developers) {
        this.developers = developers;
    }

    public Build getBuild() {
        return this.build;
    }

    public void setBuild(Build build) {
        this.build = build;
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

    public static class License {
        private String name;
        private String url;
        private String distribution;
        private String comments;

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

        public String getDistribution() {
            return this.distribution;
        }

        public void setDistribution(String distribution) {
            this.distribution = distribution;
        }

        public String getComments() {
            return this.comments;
        }

        public void setComments(String comments) {
            this.comments = comments;
        }
    }

    public static class Developer {
        private String id;
        private String name;
        private String email;
        private List<String> roles;

        public String getId() {
            return this.id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return this.email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public List<String> getRoles() {
            return this.roles;
        }

        public void setRoles(List<String> roles) {
            this.roles = roles;
        }
    }
}
