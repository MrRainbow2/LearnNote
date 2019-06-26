package com.sankuai.test.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author renxinlei
 * @version V1.0
 * @Description:
 * @date 2019/5/29 10:22
 */
public class FileOperate {
    public static void main(String[] args) throws IOException {
        int a;
        File source = new File("/Users/renxinlei","PI-soapui-project.xml");
        FileInputStream inputStream = new FileInputStream(source);
        File target = new File("/Users/renxinlei/FileTest/target.xml");
        FileOutputStream outputStream = new FileOutputStream(target);
        byte[] fileBytes = new byte[1024];

        while ((inputStream.read(fileBytes)) != -1) {
            outputStream.write(fileBytes);
        }
    }
}
