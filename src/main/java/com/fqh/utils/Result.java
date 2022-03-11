package com.fqh.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 海盗狗
 * @version 1.0
 */
//返回信息类
public class Result {

    private int code; // 200- 成功, 300-失败
    private String message;
    private Map<String, Object> map = new HashMap<>();

    public Result() {
    }

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }


    public static Result success() {
        Result result = new Result();
        result.setCode(100);
        result.setMessage("成功");
        return result;
    }

    public static Result failed() {
        Result result = new Result();
        result.setCode(200);
        result.setMessage("失败");
        return result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Result add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }
}
