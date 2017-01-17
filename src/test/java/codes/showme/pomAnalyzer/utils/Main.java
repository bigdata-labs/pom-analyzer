package codes.showme.pomAnalyzer.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 转存pom文件
 *
 * @author guanhong 2017/1/4.
 */
public class Main {
    private static String repoPath = "/Users/jeremie/programme/IdeaProjects/repo";
    private static String destinationPath = "/Users/jeremie/pomPath";
/*    private static String repoPath = "D:/temporary/programme/maven/repo";
    private static String destinationPath = "D:/pomPath";*/

    public static void main(String[] args) throws IOException {
        File parentFile = new File(repoPath);
        File destinationPathFile = new File(destinationPath);
        if (!parentFile.exists() && !parentFile.isDirectory()) {
            System.err.println("repo path should be directory path");
            return;
        }
        if (destinationPathFile.exists() && !destinationPathFile.isDirectory()) {
            System.err.println("destination path should be directory path");
            return;
        }
        destinationPathFile.mkdirs();
        Queue<File> dealingFilePath = new LinkedBlockingQueue<>();
        dealingFilePath.add(parentFile);
        while (dealingFilePath.size() > 0) {
            File[] subFileList = dealingFilePath.poll().listFiles();
            if (subFileList != null) {
                for (File file : subFileList) {
                    if (file.isDirectory()) {
                        dealingFilePath.add(file);
                    } else {
                        if (file.getName().endsWith(".pom")) {
                            File destinationFile = new File(destinationPath, file.getName());
                            if (destinationFile.exists()) {
                                System.err.println(destinationFile.getName() + " is exists!");
                            } else {
                                FileUtils.copyFile(file, destinationFile);
                                System.out.println("copy file " + destinationFile.getName() + " successful!");
                            }
                        }
                    }
                }
            }
        }
    }
}
