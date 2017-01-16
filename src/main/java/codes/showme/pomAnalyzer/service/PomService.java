package codes.showme.pomAnalyzer.service;

import codes.showme.pomAnalyzer.entity.simple.Pom;

/**
 * @author guanhong 2017/1/16 下午6:05.
 */
public interface PomService {
    Pom getParentPom(String artifactId, String groupId, String version);
}
