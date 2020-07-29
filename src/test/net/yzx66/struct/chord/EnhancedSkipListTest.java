package net.yzx66.struct.chord;

import net.yzx66.commen.IPNode;
import net.yzx66.struct.chord.enhanced.EnhancedSkipList;
import org.junit.Before;
import org.junit.Test;

public class EnhancedSkipListTest {
    
    private EnhancedSkipList skipList;
    
    @Before
    public void init(){
        skipList = new EnhancedSkipList();
        skipList.add(new IPNode(15));
        skipList.add(new IPNode(10));
        skipList.add(new IPNode(20));
        skipList.add(new IPNode(8));
        skipList.add(new IPNode(13));
        skipList.add(new IPNode(12));
        skipList.add(new IPNode(17));
        skipList.add(new IPNode(19));
    }

    @Test
    public void testGetBigger(){
        assert skipList.getNearestBiggerIPNode(0).getstartHash() == 8;
        assert skipList.getNearestBiggerIPNode(99).getstartHash() == 8;
        assert skipList.getNearestBiggerIPNode(16).getstartHash() == 17;
        assert skipList.getNearestBiggerIPNode(18).getstartHash() == 19;
        assert skipList.getNearestBiggerIPNode(11).getstartHash() == 12;
        assert skipList.getNearestBiggerIPNode(9).getstartHash() == 10;
    }

    @Test
    public void testSmaller(){
        assert skipList.getNearestSmallerIPNode(0).getstartHash() == 20;
        assert skipList.getNearestSmallerIPNode(99).getstartHash() == 20;
        assert skipList.getNearestSmallerIPNode(16).getstartHash() == 15;
        assert skipList.getNearestSmallerIPNode(18).getstartHash() == 17;
        assert skipList.getNearestSmallerIPNode(11).getstartHash() == 10;
        assert skipList.getNearestSmallerIPNode(9).getstartHash() == 8;
    }
    
}
