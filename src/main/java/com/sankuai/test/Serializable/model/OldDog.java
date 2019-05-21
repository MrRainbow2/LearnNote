package com.sankuai.test.Serializable.model;

import java.io.Serializable;

/**
 * @author renxinlei
 * @version V1.0
 * @Description:
 * @date 2018/9/10 15:47
 */
public class OldDog implements Serializable{
    private String name;
    private Integer age;
//    private String houseName;
    static String owner = "RenXinLei";
    transient String tailLength;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTailLength() {
        return tailLength;
    }

    public void setTailLength(String tailLength) {
        this.tailLength = tailLength;
    }
//    public String getHouseName() {
//        return houseName;
//    }
//
//    public void setHouseName(String houseName) {
//        this.houseName = houseName;
//    }

    @Override
    public String toString() {
        return "OldDog{" +
                "name='" + name + '\'' +
                ", age=" + age +
//                ", houseName='" + houseName + '\'' +
                ", tailLength='" + tailLength + '\'' +
                ", owner'" + owner + '\'' +
                '}';
    }
}
