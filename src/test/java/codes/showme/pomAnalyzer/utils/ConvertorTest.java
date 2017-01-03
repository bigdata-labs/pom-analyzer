package codes.showme.pomAnalyzer.utils;

import codes.showme.pomAnalyzer.entity.Pom;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Created by jeremie on 2017/1/2.
 */
public class ConvertorTest {
    private static Logger logger = LoggerFactory.getLogger(ConvertorTest.class);

    @Test
    public void test() throws IOException {
        /*String demoPath = "/Users/jeremie/programme/IdeaProjects/pom-analyzer/demo";
        File filePath = new File(demoPath);
        File[] files = filePath.listFiles();
        if (files == null){
            logger.error("pom files must not be null");
            return;
        }
        for (File file : files) {
            System.out.println(file.getName());
        }*/
        String path = "/Users/jeremie/programme/IdeaProjects/pom-analyzer/demo/spring-boot-starter-redis-1.4.3.RELEASE.pom";
        File pomFile = new File(path);
        Converter convertor = new Converter();
        InputStream inputStream = new FileInputStream(pomFile);
        Pom pom = convertor.buildPom(inputStream);
        inputStream.close();
        System.out.println("123");
    }
}
