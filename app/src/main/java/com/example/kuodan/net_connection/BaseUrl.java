package com.example.kuodan.net_connection;

import com.example.kuodan.tool.TLog;
import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @author 孙贝贝
 * @packagename com.example.changtongyuan.net_connection
 * @date on 2019/5/21 15:33
 * @wechat 18813158027
 */
public class BaseUrl {

    public static RequestBody getBody(String json){
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        return body;
    }
    public static  String toJson(Object o){
        Gson gson =new Gson();
       return gson.toJson(o);
    }
    public static RequestBody getBody2(Object o){
        TLog.e("传值参数"+toJson(o));

        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),  toJson(o));
        return body;
    }


//先扫描货位码验证，在扫描二维码出库，根据输入的数量出库，一次验证和出库只能完成验证货位的出库。
//    public static String url="http://192.168.43.99:8080/cdly/a/mainfunction/wms/outWms/";
    public static String url="http://172.16.139.223:8080/cdly/a/mainfunction/wms/outWms/";






}
