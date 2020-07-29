package net.yzx66.struct;

import net.yzx66.commen.IPNode;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TreeTest {

    private Tree<IPNode> ipNodeTree;

    @Before
    public void initTree(){
        ipNodeTree = new Tree<>();

        ipNodeTree.add(new IPNode(2));
        ipNodeTree.add(new IPNode(1));
        ipNodeTree.add(new IPNode(3));
        ipNodeTree.add(new IPNode(0));
    }

    @Test
    public void testAdd(){
        ipNodeTree.show();
    }

    @Test
    public void testGetCompleteNode(){
        IPNode ipToQuery = IPNode.getIncompleteNodeWithHashToQuery(0);
        IPNode completeNode = ipNodeTree.getCompleteNode(ipToQuery);
        System.out.println(completeNode);
    }

    @Test
    public void testDelete(){
        ipNodeTree.delete(IPNode.getIncompleteNodeWithHashToQuery(2));
        ipNodeTree.delete(IPNode.getIncompleteNodeWithHashToQuery(3));
        ipNodeTree.show();
    }

    @Test
    public void testGetAllDatas(){
        List<IPNode> datas = ipNodeTree.getAllDatas();
        for(IPNode node : datas){
            System.out.println(node);
        }
    }
}
