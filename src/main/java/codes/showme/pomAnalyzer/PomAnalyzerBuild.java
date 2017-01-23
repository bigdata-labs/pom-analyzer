package codes.showme.pomAnalyzer;

import codes.showme.config.Configuration;
import codes.showme.config.PropertiesConfig;
import codes.showme.mavenrepocrawler.domain.PomContent;
import codes.showme.pomAnalyzer.entity.simple.Exclusion;
import codes.showme.pomAnalyzer.entity.simple.Pom;
import codes.showme.pomAnalyzer.service.PomFixService;
import codes.showme.pomAnalyzer.utils.SimpleConverter;
import io.ebean.PagedList;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author guanhong 2017/1/20.
 */
public class PomAnalyzerBuild implements Runnable {
    private final static Logger logger = LoggerFactory.getLogger(PomAnalyzerBuild.class);
    private static AtomicInteger pageIndex = new AtomicInteger(0);
    private static int pageSize = 1000;
    private static int totalCount = 0;
    private static int totalPage = 0;
    private static int ThreadCount = 20;
    private static final Configuration config = new PropertiesConfig();

    private static List<Long> BLACK_LIST = Arrays.asList(3011825L, 4695606L);

    private static String BASE_PATH = config.getDependenciesResultDirectory();
    private static String DEPENDENCY = "dependency.txt";
    private static File DEPENDENCY_FILE = new File(BASE_PATH, DEPENDENCY);

    private static SimpleConverter simpleConverter = new SimpleConverter();
    private static PomFixService pomFixService = new PomFixService();


    public static void main(String[] args) {
        logger.info("start work!");
        totalCount = PomContent.countPomContent();
        totalPage = totalCount / pageSize;
        if (totalCount % pageSize != 0) {
            totalPage++;
        }
        ExecutorService executorService = Executors.newFixedThreadPool(ThreadCount + 5);
        for (int i = 0; i < ThreadCount; i++) {
            executorService.execute(new PomAnalyzerBuild());
        }
        executorService.shutdown();
        logger.info("end work!");
    }


    private static String DEPENDENCY_IDENT_SEPARATOR = ";";
    private static String LINE_CONTENT_SEPARATOR = ",";


    public static void writeDependencies(Pom pom) {
        String pomIdent = pom.getGroupId() + DEPENDENCY_IDENT_SEPARATOR + pom.getArtifactId() + DEPENDENCY_IDENT_SEPARATOR + pom.getVersion();
        pom.getDependencies().forEach(dependency -> {
            StringBuilder content = new StringBuilder(pomIdent)
                    .append(LINE_CONTENT_SEPARATOR)
                    .append(dependency.getArtifactId())
                    .append(DEPENDENCY_IDENT_SEPARATOR)
                    .append(dependency.getGroupId())
                    .append(DEPENDENCY_IDENT_SEPARATOR)
                    .append(dependency.getVersion());
            List<Exclusion> exclusionList = dependency.getExclusions();
            if (exclusionList != null && exclusionList.size() > 0) {
                for (Exclusion exclusion : exclusionList) {
                    content = content.append(LINE_CONTENT_SEPARATOR)
                            .append(exclusion.getGroupId())
                            .append(DEPENDENCY_IDENT_SEPARATOR)
                            .append(exclusion.getArtifactId());
                }
            }
            writeFile(DEPENDENCY_FILE, content.toString());
        });
    }

    private static void writeFile(File file, String text) {
        try {
            FileUtils.write(file, text + IOUtils.LINE_SEPARATOR, Charset.forName("utf8"), true);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void run() {
        while (true) {
            PagedList<PomContent> contentPagedList = PomContent.listPomContentOrderById(pageIndex.getAndIncrement(), pageSize);
            if (contentPagedList == null || contentPagedList.getList().size() <= 0) {
                break;
            }
            System.out.println("running page: " + pageIndex + "/" + totalPage);
            contentPagedList.getList().forEach(pomContent -> {
                if (BLACK_LIST.contains(pomContent.getId())) {
                    return;
                }
                try {
                    Pom pom = simpleConverter.buildPom(pomContent.getContent());
                    pomFixService.completePom(pom);
                    pomFixService.fixPom(pom);
                    writeDependencies(pom);
                } catch (Exception e) {
                    logger.error(pomContent.getId() + " : " + pomContent.getLink() + " analyse error!", e);
                }
            });
        }
    }
}
