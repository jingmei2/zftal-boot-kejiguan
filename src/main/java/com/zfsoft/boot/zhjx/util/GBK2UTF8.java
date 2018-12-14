package com.zfsoft.boot.zhjx.util;

import org.mozilla.intl.chardet.nsDetector;
import org.mozilla.intl.chardet.nsICharsetDetectionObserver;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GBK2UTF8 {


    /**
     * 实现单文件转换
     * @param fileName
     */
    public static void gbk2Utf8(String fileName) {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            StringBuffer sb = new StringBuffer();
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "GBK"));
            String str;
            while ((str = reader.readLine()) != null) {
                sb.append(str).append("\r\n");
            }

            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8"));
            writer.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 递归获取指定目录下所有指定类型文件
     *
     * @param strPath
     *            文件夹地址
     * @param suffix
     *            文件名后缀
     * @return
     */
    public static List<File> getFileList(String strPath, String suffix) {
        List<File> filelist = new ArrayList<File>();
        File dir = new File(strPath);
        File[] files = dir.listFiles(); // 该文件目录下文件全部放入数组
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName();
                if (files[i].isDirectory()) { // 如果是文件夹就递归调用
                    getFileList(files[i].getAbsolutePath(), suffix);
                } else if (fileName.endsWith(suffix)) {
                    filelist.add(files[i]);
                }
            }
        }
        return filelist;
    }


    // 是否找到匹配字符集
    private static boolean isFind = false;
    // 如果完全匹配某个字符集检测算法, 则该属性保存该字符集的名称. 否则(如二进制文件)其值就为默认值 null
    private static String encoding = null;

    /**
     * 获取文件的编码
     *
     * @param file
     * @return 文件编码，若无，则返回null
     * @throws IOException
     */
    private static String guessFileCharset(File file) throws IOException {
        nsDetector det = new nsDetector();
        det.Init(new nsICharsetDetectionObserver() {
            public void Notify(String charset) {
                isFind = true;
                encoding = charset;
            }
        });

        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));

        byte[] buf = new byte[1024];
        int len;
        boolean done = false;
        boolean isAscii = true;

        while ((len = bis.read(buf, 0, buf.length)) != -1) {
            if (isAscii) {
                isAscii = det.isAscii(buf, len);
            } else if (!done) {
                done = det.DoIt(buf, len, false);
            }
        }
        det.DataEnd();

        if (isAscii) {
            encoding = "ASCII";
            isFind = true;
        } else if (!isFind) {
            String prob[] = det.getProbableCharsets();
            if (prob.length > 0) {
                encoding = prob[0]; // 在没有发现情况下，则取第一个可能的编码
            }
        }
        return encoding;
    }

    public static void main(String[] args) {
        //List<File> files = getFileList("D:\\Workspace\\zftal-mobile\\zftal-mobile-core", ".java");
        List<File> files = getFileList("D:\\Workspace\\zftal-mobile\\zftal-mobile-core\\src", "*");
        for (File file : files) {
            String charset = null;
            try {
                charset = guessFileCharset(file.getAbsoluteFile());
            } catch (IOException e) {
                System.err.println("获取文件编码发生异常！");
            }
            System.out.println(file.getName() + "[" + charset + "]");
            if ("GB2312".equals(charset)) {
                gbk2Utf8(file.getAbsolutePath());
            } else if("GBK".equals(charset)){
                gbk2Utf8(file.getAbsolutePath());
            }
        }
    }
}
