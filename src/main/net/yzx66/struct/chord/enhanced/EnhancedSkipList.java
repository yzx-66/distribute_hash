package net.yzx66.struct.chord.enhanced;

import net.yzx66.commen.IPNode;
import net.yzx66.struct.SkipList;

public class EnhancedSkipList extends SkipList<IPNode> implements StructEnhanced {
    
    @Override
    public IPNode getNearestBiggerIPNode(int hashCode) {
        return getNearestIPNode(hashCode , true);
    }

    @Override
    public IPNode getNearestSmallerIPNode(int hashCode) {
        return getNearestIPNode(hashCode , false);
    }

    private IPNode getNearestIPNode(int hashCode , boolean isBigger){
        if(size == 0){
            return null;
        }
        hashCode %= IPNode.SUM_FRAGMENT;
        IPNode withHashToQuery = IPNode.getIncompleteNodeWithHashToQuery(hashCode);
        Node<IPNode> selfOrNearestLeftNode = findSelfOrNearestLeftNode(withHashToQuery);

        if(isExist(selfOrNearestLeftNode , withHashToQuery)){
            return selfOrNearestLeftNode.data;
        }
        
        if(isBigger){
            return doGetNearestBiggerData(selfOrNearestLeftNode);
        }else {
            return doGetNearestSmallerData(selfOrNearestLeftNode);
        }
    }

    private IPNode doGetNearestBiggerData(Node<IPNode> nearestLeftNode) {
        Node<IPNode> resNode = nearestLeftNode.right;
        if(isTail(resNode)){
            Node<IPNode> minNode = getLowest(head).right;
            return minNode.data;
        }

        return resNode.data;
    }

    private IPNode doGetNearestSmallerData(Node<IPNode> nearestLeftNode) {
        if(isHead(nearestLeftNode)){
            Node<IPNode> maxNode = getLowest(tail).left;
            return maxNode.data;
        }

        return nearestLeftNode.data;
    }
}
