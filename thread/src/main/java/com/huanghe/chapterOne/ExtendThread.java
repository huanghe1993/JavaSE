package com.huanghe.chapterOne;

/**
 * @author River
 * @date 2020/6/2 7:07
 * @description
 */
public class ExtendThread extends Thread {

    @Override
    public void run() {
        super.run();
        System.out.println("MyThread");
    }

    public static void main(String[] args) {
        ExtendThread thread1 = new ExtendThread();
        thread1.run();

    }
}
