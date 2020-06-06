package com.huanghe.chapterOne;

/**
 * @author River
 * @date 2020/6/2 6:52
 * @description
 */
public class TryConcurrency {

    public static void main(String[] args) {

        new Thread(TryConcurrency::readFromDataBase).start();
        new Thread(TryConcurrency::writeFromDataBase).start();

    }

    private static void readFromDataBase(){
        try {
            println("Begin to read from db.");
            Thread.sleep(1000 * 3L);
            println("Read Data done and start handle it");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        println("The data handle finish and successful");
    }

    private static void writeFromDataBase(){
        try {
            println("Begin to write from db.");
            Thread.sleep(1000 * 3L);
            println("write Data done and start handle it");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        println("The data handle finish and successful");
    }

    public static void println(String message) {
        System.out.println(message);
    }
}
