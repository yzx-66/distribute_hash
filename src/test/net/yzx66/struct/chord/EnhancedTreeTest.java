package net.yzx66.struct.chord;

import net.yzx66.commen.IPNode;
import net.yzx66.struct.chord.enhanced.EnhancedTree;
import org.junit.Before;
import org.junit.Test;

public class EnhancedTreeTest {

    private EnhancedTree tree;

    @Before
    public void init(){
        tree = new EnhancedTree();
        tree.add(new IPNode(15));
        tree.add(new IPNode(10));
        tree.add(new IPNode(20));
        tree.add(new IPNode(8));
        tree.add(new IPNode(13));
        tree.add(new IPNode(12));
        tree.add(new IPNode(17));
        tree.add(new IPNode(19));
        //8 10 12 13 15 17 19 20
        tree.show();
    }

    @Test
    public void testGetBigger(){
        assert tree.getNearestBiggerIPNode(0).getstartHash() == 8;
        assert tree.getNearestBiggerIPNode(99).getstartHash() == 8;
        assert tree.getNearestBiggerIPNode(16).getstartHash() == 17;
        assert tree.getNearestBiggerIPNode(18).getstartHash() == 19;
        assert tree.getNearestBiggerIPNode(11).getstartHash() == 12;
        assert tree.getNearestBiggerIPNode(9).getstartHash() == 10;
    }

    @Test
    public void testSmaller(){
        assert tree.getNearestSmallerIPNode(0).getstartHash() == 20;
        assert tree.getNearestSmallerIPNode(99).getstartHash() == 20;
        assert tree.getNearestSmallerIPNode(16).getstartHash() == 15;
        assert tree.getNearestSmallerIPNode(18).getstartHash() == 17;
        assert tree.getNearestSmallerIPNode(11).getstartHash() == 10;
        assert tree.getNearestSmallerIPNode(9).getstartHash() == 8;
    }




}
