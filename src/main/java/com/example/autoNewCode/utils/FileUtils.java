package com.example.autoNewCode.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件处理工具类
 * 1.查询整个目录的文件夹
 * 2.递归获取某个目录下的所有文件夹
 */

public class FileUtils {
    /**
     * 得到相对路径
     * @param baseDir
     * @param file
     * @return
     */
    public static String getRelativePath(File baseDir, File file) {
        if (baseDir.equals(file)) {
            return "";
        }
        if (baseDir.getParentFile() == null) {
            return file.getAbsolutePath().substring(baseDir.getAbsolutePath().length());
        } else {
            return file.getAbsolutePath().substring(baseDir.getAbsolutePath().length() + 1);
        }
    }

    /**
     * 查询整个目录下的所有文件
     * @param dir
     * @return
     * @throws IOException
     */
    public static List<File> searchAllFile(File dir) throws IOException {
        ArrayList arrayList = new ArrayList();
        searchFiles(dir, arrayList);
        return arrayList;
    }

    /**
     * 递归获取某个目录下的所有文件
     * @param dir
     * @param collector
     */
    public static void searchFiles(File dir, List<File> collector) {
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            for (int i = 0; i < files.length; i++) {
                searchFiles(files[i], collector);
            }
        } else {
            collector.add(dir);
        }
    }

    /**
     * 递归获取某个目录下的所有文件并重命名
     * @param dir
     */
    public static void searchFilesAndRename(File dir) {
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            for (int i = 0; i < files.length; i++) {
                searchFilesAndRename(files[i]);
            }
        } else {
            String name = dir.getName();
            dir.renameTo(new File(name.substring(0,1).toUpperCase()+name.substring(1)));
        }
    }

    /**
     * 创建文件
     * @param dir
     * @param file
     * @return
     */
    public static File mkdir(String dir, String file) {
        if (dir == null) {
            throw new IllegalArgumentException("文件夹不许为空");
        }
        File result = new File(dir, file);
        if (result.getParentFile() != null) {
            result.getParentFile().mkdirs();
        }
        return result;
    }
}