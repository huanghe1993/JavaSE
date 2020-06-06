package com.huanghe.stack;

/**
 * @author River
 * @date 2020/6/6 9:10
 * @description
 */
public class Demo2StackOutOfMemory {

    public static int count;

    public static void main(String[] args) {
        try {
            method1();
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println(count);
        }
    }

    private static void method1() {
        count++;
        method1();
    }

}
