package fileUtilsMove;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class moveFile {

    public static void main(String args[]) {
        try {
            File srcFile = new File("D:\\logs\\log.log");
            File dirFile = new File("D:\\test");
            if (fileValid(srcFile, dirFile)) {
                FileUtils.moveFileToDirectory(srcFile, dirFile, false);
                System.out.println("File moved successfully!");
            }
        } catch (IOException e) {
            System.out.println("Failed!");
            e.printStackTrace();
        }
    }

    public static boolean fileValid(File sourceFile, File destinationDir) {
        // decide if src, dst exists
        if (!sourceFile.exists()) {
            System.out.println("False");
            return false;
        } else if (!destinationDir.exists()) {
            destinationDir.mkdirs();
        }
        String nameOfSourceFile = sourceFile.getName();
        File destinationFile = new File(destinationDir, nameOfSourceFile);
        return true;
    }
}



