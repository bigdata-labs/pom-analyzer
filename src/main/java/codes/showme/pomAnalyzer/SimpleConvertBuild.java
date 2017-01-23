package codes.showme.pomAnalyzer;

import codes.showme.mavenrepocrawler.domain.ErrorPomContent;
import codes.showme.mavenrepocrawler.domain.PomContent;
import codes.showme.pomAnalyzer.entity.simple.Pom;
import codes.showme.pomAnalyzer.utils.SimpleConverter;
import io.ebean.PagedList;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by jeremie on 2017/1/14.
 */
public class SimpleConvertBuild {
    private static AtomicInteger pageIndex = new AtomicInteger(0);
    private static int pageSize = 1000;
    private static int totalCount = 0;
    private static int totalPage = 0;
    private static int ThreadCount = 10;

    public static void main(String[] args) {
        totalCount = PomContent.countPomContent();
        totalPage = totalCount / pageSize;
        if (totalCount % pageSize != 0) {
            totalPage++;
        }
        ExecutorService executorService = Executors.newFixedThreadPool(ThreadCount + 5);
        for (int i = 0; i < ThreadCount; i++) {
            executorService.execute(new Converter());
        }
        executorService.shutdown();
    }

    public static class Converter implements Runnable {
        SimpleConverter simpleConverter = new SimpleConverter();

        @Override
        public void run() {
            while (true) {
                PagedList<PomContent> contentPagedList = PomContent.listPomContentOrderById(pageIndex.getAndIncrement(), pageSize);
                if (contentPagedList == null || contentPagedList.getList().size() <= 0) {
                    break;
                }
                System.out.println("Convert page " + (contentPagedList.getPageIndex() + 1) + "/" + totalPage);
                contentPagedList.getList().forEach(pomContent -> {
                    try {
                        Pom pom = simpleConverter.buildPom(pomContent.getContent());
                        if (pom == null) {
                            ErrorPomContent errorPomContent = new ErrorPomContent();
                            errorPomContent.setId(pomContent.getId());
                            errorPomContent.setLink(pomContent.getLink());
                            errorPomContent.setContent(pomContent.getContent());
                            errorPomContent.save();
                            System.out.println("Convert null error: " + pomContent.getId() + " : " + pomContent.getLink());
                        }
                    } catch (Exception e) {
                        ErrorPomContent errorPomContent = new ErrorPomContent();
                        errorPomContent.setId(pomContent.getId());
                        errorPomContent.setLink(pomContent.getLink());
                        errorPomContent.setContent(pomContent.getContent());
                        errorPomContent.save();
                        System.out.println("Convert exception error: " + pomContent.getId() + " : " + pomContent.getLink());
                    }
                });
            }
        }
    }
}
