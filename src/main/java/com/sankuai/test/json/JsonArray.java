package com.sankuai.test.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.util.List;

/**
 * @author renxinlei
 * @version V1.0
 * @Description:
 * @date 2019/2/19 15:57
 */
public class JsonArray {
    public static void main(String[] args) {
        JSONArray array = new JSONArray();
        array.add(123);
        array.add(223);
        array.add(323);
        System.out.println(array);

        String jsonArrayStr = "[123,223,323]";
        JSONArray array1 = JSON.parseArray(jsonArrayStr);
        List<Integer> numList = array1.toJavaList(Integer.class);
        System.out.println(numList);
    }
}
