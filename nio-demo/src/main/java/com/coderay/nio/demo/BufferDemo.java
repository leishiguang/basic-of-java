package com.coderay.nio.demo;

import java.nio.ByteBuffer;

public class BufferDemo {

    public static void main(String[] args){
        //初始化(堆外内存）
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4);
        //初始化(JVM堆内存）
        //ByteBuffer byteBuffer = ByteBuffer.allocate(4);
        System.out.println(String.format("初始化之后，容量：%s，位置：%s，限制：%s",byteBuffer.capacity(),byteBuffer.position(),byteBuffer.limit()));
        //写入三字节
        byteBuffer.put((byte)1);
        byteBuffer.put((byte)2);
        byteBuffer.put((byte)3);
        System.out.println(String.format("写入3字节之后，容量：%s，位置：%s，限制：%s",byteBuffer.capacity(),byteBuffer.position(),byteBuffer.limit()));
        //转化为读取
        byteBuffer.flip();
        System.out.println(byteBuffer.get());
        System.out.println(byteBuffer.get());
        System.out.println(String.format("读取2字节之后，容量：%s，位置：%s，限制：%s",byteBuffer.capacity(),byteBuffer.position(),byteBuffer.limit()));
        //再次写入，此时在读模式，直接写入会覆盖数据。clear()方法清除整个缓冲区。compact()方法仅清除已阅读的数据。转为写入模式
        byteBuffer.compact();
        System.out.println(String.format("转为写入模式，容量：%s，位置：%s，限制：%s",byteBuffer.capacity(),byteBuffer.position(),byteBuffer.limit()));
        byteBuffer.put((byte)4);
        byteBuffer.put((byte)5);
        byteBuffer.put((byte)6);
        //重新读取
        byteBuffer.flip();
        System.out.println(byteBuffer.get());
        System.out.println(byteBuffer.get());
        System.out.println(byteBuffer.get());
        System.out.println(byteBuffer.get());
        System.out.println(String.format("读取4字节之后，容量：%s，位置：%s，限制：%s",byteBuffer.capacity(),byteBuffer.position(),byteBuffer.limit()));

        // rewind() 重置position为0
        // mark() 标记position的位置
        // reset() 重置position为上次mark()标记的位置
    }
}
