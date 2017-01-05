package codes.showme.pomAnalyzer.utils;

import codes.showme.pomAnalyzer.entity.Pom;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jeremie on 2017/1/2.
 */
public class ConvertorTest {
    private static Logger logger = LoggerFactory.getLogger(ConvertorTest.class);
    public static List<String> blackList = Arrays.asList("plexus-1.0.4.pom", "plexus-1.0.5.pom", "plexus-root-1.0.3.pom");

    @Test
    public void testBuildPom() throws IOException {
        String path = "/Users/jeremie/pomPath";
        //String path = "C:/Users/jeremie/Desktop/新建文件夹";
        File file = new File(path);
        File[] subFile = file.listFiles();
        if (subFile != null) {
            for (File pomFile : subFile) {
                if (pomFile.getName().endsWith(".pom") && !blackList.contains(pomFile.getName())) {
                    System.out.println(pomFile.getName());
                    Converter convertor = new Converter();
                    InputStream inputStream = new FileInputStream(pomFile);
                    Pom pom = convertor.buildPom(inputStream);
                    inputStream.close();
                }
            }
        }
        System.out.println("123");
    }


}
