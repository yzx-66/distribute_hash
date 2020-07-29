package net.yzx66.utils;

import net.yzx66.utils.StringUtil;
import org.junit.Test;

public class StringUtilTest {

    @Test
    public void testIpString2Int(){
        System.out.println(StringUtil.ipString2Int("127.0.0.1"));
    }
}
