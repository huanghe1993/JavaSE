package com.huanghe.chapter8;

/**
 * @author River
 * @date 2020/6/27 20:37
 * @description
 */
public class ProducerConsumerVersion2 {

    private int i = 0 ;

    final private Object LOCK = new Object();

    private volatile boolean isProduced = false;

    public void produce(){
        synchronized (LOCK){
            if (isProduced){
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }


    }



}
