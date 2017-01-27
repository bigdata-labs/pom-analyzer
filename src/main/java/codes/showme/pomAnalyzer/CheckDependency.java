package codes.showme.pomAnalyzer;


import codes.showme.config.Configuration;
import codes.showme.config.PropertiesConfig;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * @author guanhong 2017/1/27.
 */
public class CheckDependency {
    private final static Logger logger = LoggerFactory.getLogger(CheckDependency.class);

    private static final Configuration config = new PropertiesConfig();

    private final static Pattern denepencyPattern = Pattern.compile(".+;.+;.+,.+;.+;.+");

    public static void main(String[] args) {
        int count = 0;
        int totalCount = 0;
        String BASE_PATH = config.getDependenciesResultDirectory();
        String DEPENDENCY_FILE_NAME = config.getDependenciesFileName();
        File dependencyFile = new File(BASE_PATH, DEPENDENCY_FILE_NAME);
        LineIterator it = null;
        try {
            it = FileUtils.lineIterator(dependencyFile, "UTF-8");
            while (it.hasNext()) {
                final String line = it.nextLine();
                totalCount++;
                if (!denepencyPattern.matcher(line).matches() || line.contains("null")) {
                    System.out.println(line);
                    count++;
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (it != null) {
                it.close();
            }
        }
        System.out.println(count);
        System.out.println(totalCount);

    }
}
