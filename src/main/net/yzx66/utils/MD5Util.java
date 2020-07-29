package net.yzx66.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {

    public static int string2MD5HashCode(String s){
        return  Math.abs(DigestUtils.md5Hex(s).hashCode());
    }
}
