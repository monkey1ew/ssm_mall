package com.fqh.utils;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

/**
 * @author 海盗狗
 * @version 1.0
 */
public class ParamUtils {
    private static String uid;

    public static String createUid() {
        uid = UUID.randomUUID().toString();
        String replace = uid.replace('-', 'v');
        return replace.substring(0, 22);
    }

    public static String encoding(String str) throws UnsupportedEncodingException {
        return new String(str.getBytes("ISO8859-1"), "UTF-8");
    }
}
