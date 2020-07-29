package net.yzx66.utils;

public class StringUtil {

    public static int ipString2Int(String ip){
        String s = ip.replaceAll("\\.", "");
        return Integer.parseInt(s);
    }


}
