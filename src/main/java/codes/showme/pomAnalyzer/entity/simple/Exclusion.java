package codes.showme.pomAnalyzer.entity.simple;

import org.apache.commons.lang3.StringUtils;

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
        return StringUtils.equals(this.artifactId, exclusion.artifactId) && StringUtils.equals(this.groupId, exclusion.groupId);
    }

}
