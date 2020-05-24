package com.huanghe.generic.method;

import sun.rmi.runtime.Log;

/**
 * @author River
 * @date 2020/5/24 10:01
 * @description 泛型方法与可变参数
 */
public class GenericMethodWithArgs {

    public <T> void printMsg( T... args){
        for(T t : args){
            System.out.println("t is " + t);
        }
    }
}
