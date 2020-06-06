package com.huanghe.stack;

/**
 * @author River
 * @date 2020/6/6 8:58
 * @description
 */
public class Demo1StackEscape {

    public static void main(String[] args) {
        // 主线程修改StringBuilder对象
        StringBuilder sb = new StringBuilder();
        sb.append(4);
        sb.append(5);
        sb.append(6);
        // 子线程也修改StringBuilder对象
        new Thread(() ->m2(sb)).start();


    }

    /**
     * 不会出现线程安全问题，sb是线程内的私有
     */
    public static void m1(){
        StringBuilder sb = new StringBuilder();
        sb.append(1);
        sb.append(2);
        sb.append(3);
        System.out.println(sb.toString());
    }

    /**
     * sb是方法的参数，有可能有其他的线程可以访问到
     * @param sb
     */
    public static void m2(StringBuilder sb){
        sb.append(1);
        sb.append(2);
        sb.append(3);
        System.out.println(sb.toString());

    }

    public static void m3(){
        StringBuilder sb = new StringBuilder();
        sb.append(1);
        sb.append(2);
        sb.append(3);
        System.out.println(sb.toString());

    }



}
