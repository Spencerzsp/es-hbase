package com.daqsoft.test;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: daqsoft
 * @date: 2019/11/11
 */

public class MyJson {

    public static String jsonmap = "{\"001\":{\"name\":\"xiaohong\",\"password\":\"654321\"},\"002\":[{\"$ref\":\"$.001\"},{\"name\":\"xixi\",\"password\":\"789\"}]}";
    public static String jsonuser = "{\"name\":\"xiaohong\",\"password\":\"654321\"}";
    public static String jsonlist = "[{\"name\":\"xiaohong\",\"password\":\"654321\"},{\"name\":\"xixi\",\"password\":\"789\"}]";

    public static void main(String[] args) {
        User user = new User();
        user.setName("xiaohong");
        user.setPassword("123456");
        ArrayList<User> list = new ArrayList<User>();
        list.add(user);
        list.add(new User("xixi", "789"));
        Map map = new HashMap();
        map.put("001", user);
        map.put("002", list);

        // java对象转换为json字符串
        System.out.println(JSONObject.toJSONString(user));
        System.out.println(JSONObject.toJSONString(list));
        System.out.println(JSONObject.toJSONString(map));

        // json字符串转换为java对象
        System.out.println(JSONObject.parseObject(jsonuser, User.class));
        User user1 = JSONObject.parseObject(jsonuser, User.class);
        System.out.println(user1.getName());
        System.out.println(user1.getPassword());
    }
}
