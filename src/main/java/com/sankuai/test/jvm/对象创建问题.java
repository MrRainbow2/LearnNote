package com.sankuai.test.jvm;

import org.openjdk.jol.info.ClassLayout;

/**
 * Object o = new Object(); 共占用多少字节。
 * https://blog.csdn.net/srs1995/article/details/109351177
 *
 * @author erjie
 */
public class 对象创建问题 {


    /**
     * java.lang.Object object internals:
     * OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
     * 0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
     * 4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
     * 8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
     * 12    4        (loss due to the next object alignment)
     * Instance size: 16 bytes
     * 在普通对象中,对象布局 前8位为markWord,主要用于保存对象hashCode,gc年龄，锁信息
     * 之后的4位标记着klassPointer(之所以为4位是因为jvm参数默认开启了指针压缩。-XX:-UseCompressedOops 指令可关闭)
     * 12位开始之后的4位则是用来填充的，对象大小必须可以整除 8
     */
    public static void objectLayOut() {
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    }

    /**
     * [Ljava.lang.Object; object internals:
     * OFFSET  SIZE               TYPE DESCRIPTION                               VALUE
     * 0     4                    (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
     * 4     4                    (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
     * 8     4                    (object header)                           4c 23 00 f8 (01001100 00100011 00000000 11111000) (-134208692)
     * 12     4                    (object header)                           0a 00 00 00 (00001010 00000000 00000000 00000000) (10)
     * 16    40   java.lang.Object Object;.<elements>                        N/A
     * Instance size: 56 bytes
     * 在数组对象中，对象布局前8为为markWord，记录内容与普通对象一致。
     * 之后的4位标记着KlassPointer.(之所以为4位是因为jvm参数默认开启了指针压缩。-XX:-UseCompressedOops 指令可关闭)
     * 12位开始之后的4位标记着数组长度。
     * 之后的位数则是数组中对象占用的空间数, 如果数组为对象数组，则保存引用。所占的位长度 = 数组长度 * 引用所占位数
     * 若数组是基本类型数组，例如 int , long , boolean 。所占位数 = 数组长度 * 基本类型长度 eg. long[10] 元素所占位数位 8 * 10
     * 数组最后为补齐部分，对象整体所占位数必须可以整除 8
     */
    public static void arrayLayOut() {
        Object[] o = new Object[11];
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    }

    /**
     * [I object internals:
     * OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
     * 0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
     * 4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
     * 8     4        (object header)                           6d 01 00 f8 (01101101 00000001 00000000 11111000) (-134217363)
     * 12    4        (object header)                           0b 00 00 00 (00001011 00000000 00000000 00000000) (11)
     * 16   44         int [I.<elements>                        N/A
     * 60    4        (loss due to the next object alignment)
     * Instance size: 64 bytes
     */
    public static void arrayBasicTypeLayOut() {
        int[] o = new int[11];
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    }


    public static void main(String[] args) {
//        objectLayOut();
//        arrayLayOut();
//        arrayBasicTypeLayOut();
//		boolean[] o = new boolean[3];
//		synchronized (o) {
//			System.out.println(ClassLayout.parseInstance(o).toPrintable());
//		}
    }
}
