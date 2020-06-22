package com.huanghe.chapter.one;

/**
 * @author River
 * @date 2020/6/12 7:19
 * @description Java文件路径
 */
public class _01_JavaFilePath {

    public static void main(String[] args) {
        testClassGetResource2();

    }

    /**
     * 测试class.getResource获取文件路径
     */
    public static void testClassGetResource1() {
        System.out.println(_01_JavaFilePath.class.getResource(""));
        System.out.println(_01_JavaFilePath.class.getResource("/"));
    }

    /**
     * 测试class.getResource获取文件路径
     */
    public static void testClassGetResource2() {
        System.out.println(_01_JavaFilePath.class.getResource("file/test.properties"));
        System.out.println(_01_JavaFilePath.class.getResource("../"));
        System.out.println(_01_JavaFilePath.class.getResource("../txt/01-java-filepath.txt"));
        System.out.println(_01_JavaFilePath.class.getResource("/"));
    }

}
