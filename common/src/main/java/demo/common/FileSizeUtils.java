package demo.common;

import java.io.File;

/**
 * 文件大小工具类
 *
 * @author leishiguang
 * @since v1.0
 */
public class FileSizeUtils {

    /**
     * 获取文件大小，MB
     * @param file file
     * @return MB 文件大小字符串
     */
    public static String getFileLength(File file){
        checkFile(file);
        return file.length()/1048576 + "MB";
    }


    private static void checkFile(File file) {
        if(file == null){
            throw new NullPointerException("file 不能为空");
        }
    }
}
