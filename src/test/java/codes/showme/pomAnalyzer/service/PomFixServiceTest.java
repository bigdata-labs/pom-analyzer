package codes.showme.pomAnalyzer.service;

import codes.showme.pomAnalyzer.entity.simple.Pom;
import codes.showme.pomAnalyzer.utils.SimpleConverter;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @author guanhong 2017/1/16.
 */
public class PomFixServiceTest {
    private final static Logger logger = LoggerFactory.getLogger(PomFixServiceTest.class);

    private final static String POM_URL = "/Users/jeremie/programme/IdeaProjects/pom-analyzer/demo";

    @Test
    public void testFixPom() {
        SimpleConverter simpleConverter = new SimpleConverter();
        Pom pom = simpleConverter.buildPom(new File(POM_URL, "codes.showme-test-1.0.pom"));
        PomFixService pomFixService = new PomFixService();
        pomFixService.pomService = (artifactId, groupId, version) -> simpleConverter.buildPom(new File(POM_URL, groupId + "-" + artifactId + "-" + version + ".pom"));
        pomFixService.completePom(pom);
        pomFixService.fixPom(pom);
        System.out.println("123");
    }
}
