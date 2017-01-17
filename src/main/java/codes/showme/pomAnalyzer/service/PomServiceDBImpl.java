package codes.showme.pomAnalyzer.service;

import codes.showme.mavenrepocrawler.domain.Link;
import codes.showme.mavenrepocrawler.domain.PomContent;
import codes.showme.pomAnalyzer.entity.simple.Pom;
import codes.showme.pomAnalyzer.utils.SimpleConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author guanhong 2017/1/16.
 */
public class PomServiceDBImpl implements PomService {
    private static final Logger logger = LoggerFactory.getLogger(PomServiceDBImpl.class);

    private SimpleConverter simpleConverter = new SimpleConverter();

    @Override
    public Pom getParentPom(String artifactId, String groupId, String version) {
        Link parentLink = Link.findByUniqueVersion(artifactId, groupId, version);
        if (parentLink == null) {
            return null;
        }
        PomContent pomContent = PomContent.get(parentLink.getId());
        if (pomContent == null) {
            return null;
        }
        try {
            return simpleConverter.buildPom(pomContent.getContent());
        } catch (Exception e) {
            logger.error("pom转换失败：" + pomContent.getId() + ":" + pomContent.getLink(), e);
        }
        return null;
    }
}
