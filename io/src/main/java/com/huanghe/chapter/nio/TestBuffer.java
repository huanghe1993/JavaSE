package com.huanghe.chapter.nio;





import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * @author River
 * @date 2020/6/17 7:41
 * 一、缓冲区（Buffer) :在Java NIO中负责数据的存取，缓冲区就是数组，用于存储不同类型的数据
 * <p>
 * 根据数据类型不同（boolean类型除外），提供相应类型的缓存区：
 * ByteBuffer
 * CharBuffer
 * ShortBuffer
 * IntBuffer
 * LongBuffer
 * FloatBuffer
 * DoubleBuffer
 * <p>
 * 上述缓冲区的管理方式几乎一致，都是通过allocate()获取缓存区
 * <p>
 * <p>
 * 二、缓冲区存取数据的两个核心方法：
 * put():存入数据到缓冲区中
 * get():获取缓冲区中的数据
 * <p>
 * <p>
 * 三、缓冲区的四个核心属性
 * capacity：容量，表示的是缓冲区中最大存储数据的容量，一旦声明不能改变
 * limit：界限，表示缓冲区中可以操作数据的大小。（limit后面的数据是不能进行读写）
 * position = 0：表示缓冲区中正在操作数据的位置。
 * <p>
 * position <= limit <= capacity
 * <p>
 * mark = -1;
 */
public class TestBuffer {

    @Test
    public void test1() {
        String str = "abcde";

        // 1.分配指定大小的缓存区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        System.out.println("-------------------allocate()-------------");
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());

        // 2.利用put()存入数据到缓冲区
        byteBuffer.put(str.getBytes());

    }

}
