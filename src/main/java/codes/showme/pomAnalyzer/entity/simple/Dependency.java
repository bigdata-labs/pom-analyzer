package codes.showme.pomAnalyzer.entity.simple;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author guanhong 2017/1/3.
 */
public class Dependency {
    private String groupId;
    private String artifactId;
    private String version;
    private String scope;
    private String type;
    private String optional;
    private String classifier;
    private List<Exclusion> exclusions;

    public String getGroupId() {
        return groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public String getVersion() {
        return version;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<Exclusion> getExclusions() {
        return this.exclusions;
    }

    public void setExclusions(List<Exclusion> exclusions) {
        this.exclusions = exclusions;
    }

    public void overwriteVersion(final String version) {
        this.version = version;
    }

    public String getScope() {
        return this.scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOptional() {
        return this.optional;
    }

    public void setOptional(String optional) {
        this.optional = optional;
    }

    public String getClassifier() {
        return this.classifier;
    }

    public void setClassifier(String classifier) {
        this.classifier = classifier;
    }

    public boolean simpleEqual(Dependency dependency) {
        if (dependency == null) {
            return false;
        }
        return StringUtils.equals(this.artifactId, dependency.artifactId) && StringUtils.equals(this.groupId, dependency.groupId);
    }
}

