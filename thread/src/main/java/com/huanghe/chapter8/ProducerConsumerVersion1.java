package com.huanghe.chapter8;

/**
 * @author River
 * @date 2020/6/28 6:47
 * @description
 */
public class ProducerConsumerVersion1 {

    public static void main(String[] args) {

        Object LOCK = new Object();


        new Thread(() -> {
            while (true) {
                // 保证只有 一个执行
                synchronized (LOCK) {
                    System.out.println("告诉老板要的包子种类和数量");
                    try {
                        // 调用wait方法，放弃CPU执行权，进入到无限等待
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 唤醒之后执行的代码,ThreadA就有机会获得另一个线程释放的锁，并从等待的地方起开始执行
                    System.out.println("顾客开始吃包子.....");
                    System.out.println("------------------");
                }
            }
        },"consumer").start();

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(2_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (LOCK) {
                    // 老板5秒做包子
                    System.out.println("老板5秒钟之后做好了包子，告诉顾客可以吃包子了！");
                    // notify并不释放锁，只是告诉调用过wait方法的线程可以去参与获得锁的竞争了，但不是马上得到锁，因为锁还在别人手里，别人还没释放
                    // 直到执行完退出对象锁锁住的区域（synchronized修饰的代码块）后再释放锁。
                    LOCK.notify();
                    System.out.println("包子递给顾客！");
                }
            }
        },"producer").start();

    }


}
