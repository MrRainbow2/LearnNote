package com.sankuai.test.Serializable;

import com.sankuai.test.Serializable.model.OldDog;

import java.io.*;

/**
 * @author renxinlei
 * @version V1.0
 * @Description:
 * @date 2018/9/10 15:45
 */
public class Serializable {

    static public void main(String[] args) throws IOException {
        OldDog laoWang = new OldDog();
        laoWang.setAge(24);
        laoWang.setName("王建国");
//        laoWang.setHouseName("LovingApartment");
        laoWang.setTailLength("2m");
//        serializableMethod(laoWang);
        deSerializableMethod();
    }

    static public void serializableMethod(OldDog oldDog) {
        try (ObjectOutputStream op = new ObjectOutputStream(new FileOutputStream(new File("/Users/renxinlei/dogTest.txt")))) {
            op.writeObject(oldDog);
            op.flush();
            System.out.println("OldDog 序列化成功！！！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static public void deSerializableMethod() {
        File text = new File("/Users/renxinlei/dogTest.txt");
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(text))) {
            OldDog oldDog = (OldDog) inputStream.readObject();
            System.out.println("OldDog 反序列化成功！！！");
            System.out.println(oldDog);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
        }

    }

}
