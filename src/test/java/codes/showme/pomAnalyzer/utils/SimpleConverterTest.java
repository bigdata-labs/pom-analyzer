package codes.showme.pomAnalyzer.utils;

import codes.showme.pomAnalyzer.entity.simple.Dependency;
import codes.showme.pomAnalyzer.entity.simple.Pom;
import org.apache.commons.lang3.StringUtils;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author guanhong 2017/1/5.
 */
//@Ignore
public class SimpleConverterTest {

    @Test
    public void testBuildPom() throws IOException {
        String path = "/Users/jeremie/pomPath";
        //String path = "C:/Users/jeremie/Desktop/新建文件夹";
        File file = new File(path);
        File[] subFile = file.listFiles();
        if (subFile != null) {
            for (File pomFile : subFile) {
                if (pomFile.getName().endsWith(".pom") && !ConvertorTest.blackList.contains(pomFile.getName())) {
                    System.out.println(pomFile.getName());
                    SimpleConverter convertor = new SimpleConverter();
                    InputStream inputStream = new FileInputStream(pomFile);
                    Pom pom = convertor.buildPom(inputStream);
                    inputStream.close();
                }
            }
        }
        System.out.println("123");
    }

    private Pattern pattern = Pattern.compile("\\$\\{.+\\}");

    @Test
    public void testSingalBuildPom() throws IOException {
        String path = "/Users/jeremie/programme/IdeaProjects/pom-analyzer/pom.xml";
        File pomFile = new File(path);
        System.out.println(pomFile.getName());
        SimpleConverter convertor = new SimpleConverter();
        InputStream inputStream = new FileInputStream(pomFile);
        Pom pom = convertor.buildPom(inputStream);
        inputStream.close();
        System.out.println();
        if (pom.getParent() != null) {
            System.out.println("parent:" + pom.getParent().getGroupId() + ":" + pom.getParent().getArtifactId() + ":" + pom.getParent().getVersion());
        }
        System.out.println();
        System.out.println("dependencyManagement:");
        for (Dependency dependency : pom.getDependencyManagement().getDependencies()) {
            if (StringUtils.isBlank(dependency.getVersion()) || pattern.matcher(dependency.getVersion()).matches()) {
                System.out.println(dependency.getGroupId() + ":" + dependency.getArtifactId() + ":" + dependency.getVersion());
            }
        }
        System.out.println();
        System.out.println("dependencies:");
        for (Dependency dependency : pom.getDependencies()) {
            if (StringUtils.isBlank(dependency.getVersion()) || pattern.matcher(dependency.getVersion()).matches()) {
                System.out.println(dependency.getGroupId() + ":" + dependency.getArtifactId() + ":" + dependency.getVersion());
            }
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("compile result:");
        Map<String, String> properties = pom.getProperties();
        System.out.println();
        System.out.println("properties");
        Map<String, String> propertiesTrans = new HashMap<>();
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
            propertiesTrans.put("${" + entry.getKey() + "}", entry.getValue());
        }
        System.out.println();
        pom.setGroupId(getByProperties(propertiesTrans, pom.getGroupId()));
        pom.setArtifactId(getByProperties(propertiesTrans, pom.getArtifactId()));
        pom.setVersion(getByProperties(propertiesTrans, pom.getVersion()));
        System.out.println("dependencyManagement:");
        for (Dependency dependency : pom.getDependencyManagement().getDependencies()) {
            dependency.setVersion(getByProperties(propertiesTrans, dependency.getVersion()));
            System.out.println(dependency.getGroupId() + ":" + dependency.getArtifactId() + ":" + dependency.getVersion());
        }
        System.out.println();
        System.out.println("dependencies:");
        for (Dependency dependency : pom.getDependencies()) {
            if (StringUtils.isNotBlank(dependency.getVersion())) {
                dependency.setVersion(getByProperties(propertiesTrans, dependency.getVersion()));
            }
            if (StringUtils.isBlank(dependency.getVersion())) {
                for (Dependency dependencyManagement : pom.getDependencyManagement().getDependencies()) {
                    if (dependencyManagement.getGroupId().equals(dependency.getGroupId()) && dependencyManagement.getArtifactId().equals(dependency.getArtifactId())) {
                        dependency.setVersion(dependencyManagement.getVersion());
                    }
                }
            }
            System.out.println(dependency.getGroupId() + ":" + dependency.getArtifactId() + ":" + dependency.getVersion());
        }
    }


    private String getByProperties(Map<String, String> propertiesTrans, String temp) {
        if (pattern.matcher(temp).matches()) {
            return propertiesTrans.getOrDefault(temp, temp);
        }
        return temp;
    }
}
