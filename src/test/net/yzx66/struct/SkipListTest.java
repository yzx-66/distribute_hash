package net.yzx66.struct;

import net.yzx66.commen.IPNode;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class SkipListTest {

    private SkipList<IPNode> skipList;

    @Before
    public void init(){
        skipList = new SkipList<>();
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
    public void testAdd(){
        skipList.show();
    }

    @Test
    public void testDelete(){
        skipList.delete(IPNode.getIncompleteNodeWithHashToQuery(8));
        skipList.delete(IPNode.getIncompleteNodeWithHashToQuery(10));
        skipList.delete(IPNode.getIncompleteNodeWithHashToQuery(19));
        skipList.show();
    }

    @Test
    public void testGetCompleteNode(){
        System.out.println(skipList.getCompleteNode(IPNode.getIncompleteNodeWithHashToQuery(8)));
        System.out.println(skipList.getCompleteNode(IPNode.getIncompleteNodeWithHashToQuery(10)));
        System.out.println(skipList.getCompleteNode(IPNode.getIncompleteNodeWithHashToQuery(20)));
        System.out.println(skipList.getCompleteNode(IPNode.getIncompleteNodeWithHashToQuery(99)));
    }

    @Test
    public void testGetDatasByScope(){
        IPNode start = new IPNode(9);
        IPNode end = new IPNode(20);
        System.out.println(skipList.getDatasByScope(start,end));
    }

    @Test
    public void testGetAllDatas(){
        System.out.println(skipList.getAllDatas());
    }

}
