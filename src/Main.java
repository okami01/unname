import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("输入目标文件夹：");
        String destDir = scan.next();
        File destFile = new File(destDir);
        if (!destFile.isDirectory()){
            System.err.println("没有找到文件夹：" + destDir);
            System.exit(0);
        }
        System.out.print("输入需要去除的文件名：");
        String destName = scan.next();
        long starTime = System.currentTimeMillis();

        listDir(destDir, destName);

        long endTime = System.currentTimeMillis();
        long time = endTime - starTime;
        System.out.println("耗时: " + time);
    }

    private static void listDir(String destDir, String destName) {
        File destFile = new File(destDir);
        if (destFile.exists() && destFile.isDirectory()) {
            File[] files = destFile.listFiles();
            if (files == null || files.length == 0) {
                return;
            }
            List<File> fileList = Arrays.asList(files);
            for (File file : fileList) {
                String name = file.getName();
                if (name.contains(destName)) {
                    String newName = name.replace(destName, "day");
                    file.renameTo(new File(file.getParent(), newName));
                }
                if (file.isDirectory()) {
                    System.out.println(file.getName());
                    listDir(file.getPath(), destName);
                }
            }
        }
    }
}
