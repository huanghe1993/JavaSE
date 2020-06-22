package com.huanghe.chapter.two;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author River
 * @date 2020/6/12 21:28
 * @description
 */
public class Demo1OutputStream {

    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("io/a.txt");
        // 将指定的字节(97)写入此输出流
        fos.write(97);
        fos.close();
    }

}
