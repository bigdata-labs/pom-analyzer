package codes.showme.pomAnalyzer.service;

import codes.showme.pomAnalyzer.PomAnalyzerBuild;
import codes.showme.pomAnalyzer.entity.simple.Pom;
import codes.showme.pomAnalyzer.utils.SimpleConverter;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URISyntaxException;

/**
 * @author guanhong 2017/1/16.
 */
//@Ignore
public class PomFixServiceTest {
    private final static Logger logger = LoggerFactory.getLogger(PomFixServiceTest.class);

    @Test
    public void testFixPom() throws URISyntaxException {
        SimpleConverter simpleConverter = new SimpleConverter();
        String demoPomUrl = Pom.class.getClassLoader().getResource("demo").getFile();
        Pom pom = simpleConverter.buildPom(new File(demoPomUrl, "codes.showme-test-1.0.pom"));
        PomFixService pomFixService = new PomFixService();
        pomFixService.pomService = (artifactId, groupId, version) -> simpleConverter.buildPom(new File(demoPomUrl, groupId + "-" + artifactId + "-" + version + ".pom"));
        pomFixService.completePom(pom);
        pomFixService.fixPom(pom);
        PomAnalyzerBuild.writeDependencies(pom);
    }
}
