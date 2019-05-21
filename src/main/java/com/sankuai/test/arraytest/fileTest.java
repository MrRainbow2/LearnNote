package com.sankuai.test.arraytest;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author renxinlei
 * @version V1.0
 * @Description:
 * @date 2019/3/8 16:00
 */
public class fileTest {

    public static void main(String[] args) {

    }

    public List<String> readFile() {
        File file = new File("/Users/renxinlei/Desktop/beijingJson.txt");

        try {
            InputStream inputStream = new FileInputStream(file);
            InputStreamReader reader = new InputStreamReader(inputStream);
        } catch (FileNotFoundException e) {
            System.out.println("找不到文件");
            return new ArrayList<>(0);
        }
        return new ArrayList<>(0);
    }
}
