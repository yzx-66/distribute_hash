package net.yzx66.utils;

import net.yzx66.utils.MD5Util;
import org.junit.Test;
import sun.security.provider.MD5;

public class MD5UtilTest {

    @Test
    public void testString2MD5HashCode(){
        System.out.println(MD5Util.string2MD5HashCode("127.0.0.1"));
    }
}
