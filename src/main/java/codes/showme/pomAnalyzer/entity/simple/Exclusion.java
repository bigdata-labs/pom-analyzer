package codes.showme.pomAnalyzer.entity.simple;

/**
 * @author guanhong 2017/1/3.
 */
public class Exclusion {
    private String groupId;
    private String artifactId;

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

    public boolean simpleEqual(Exclusion exclusion) {
        if (exclusion == null) {
            return false;
        }
        return this.getArtifactId().equals(exclusion.getArtifactId()) && this.getGroupId().equals(exclusion.getGroupId());
    }

}
