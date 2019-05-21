package TestCodeFormat;

import java.util.List;
public class TestFormat<I extends List> {
    public static void main(String[] args) {
        Apple apple = new Apple("红",10);
        chageColor(apple);
        System.out.println(apple.getColor());
    }
    public static void chageColor (Apple apple){
        apple.setColor("绿");
    }
}
