package net.yzx66.struct.chord.enhanced;

import net.yzx66.commen.IPNode;
import net.yzx66.struct.Tree;

public class EnhancedTree extends Tree<IPNode> implements StructEnhanced {

    private IPNode maxData;
    private IPNode minData;

    @Override
    public IPNode getNearestBiggerIPNode(int hashCode) {
        return getNearestIPNode(hashCode , true);
    }

    @Override
    public IPNode getNearestSmallerIPNode(int hashCode) {
        return getNearestIPNode(hashCode , false);
    }

    private IPNode getNearestIPNode(int hashCode , boolean isBigger){
        if(root == null){
            return null;
        }
        hashCode %= IPNode.SUM_FRAGMENT;
        IPNode data = IPNode.getIncompleteNodeWithHashToQuery(hashCode);

        return doGetNearestIPNode(data ,isBigger);
    }

    private IPNode doGetNearestIPNode(IPNode data , boolean isBigger){
        Node<IPNode> compareNode = root;
        while(compareNode != null){
            if(isFirstDataBiggerThanSecondData(data , compareNode.data)){
                Node<IPNode> biggerNode = compareNode.right;

                IPNode res;
                if(isBigger){
                    res = getNearestBiggerDataFromRightBranch(biggerNode , data);
                }else {
                    res = getNearestSmallerDataFromRightBranch(biggerNode , compareNode ,data);
                }

                if(res != null){
                    return res;
                }
                compareNode = biggerNode;
            }else if(isFirstDataSmallerThanSecondData(data , compareNode.data)){
                Node<IPNode> smallerNode = compareNode.left;

                IPNode res;
                if(isBigger){
                    res = getNearestBiggerDataFromLeftBranch(compareNode , smallerNode , data);
                }else {
                    res = getNearestSmallerDataFromLeftBranch(smallerNode ,data);
                }

                if(res != null){
                    return res;
                }
                compareNode = smallerNode;
            }else {
                return compareNode.data;
            }
        }

        return buildDataCircle(data , isBigger);
    }

    private IPNode getNearestBiggerDataFromRightBranch(Node<IPNode> biggerNode , IPNode data){
        //只有最右边会等于null
        if(biggerNode != null && isFirstDataSmallerThanSecondData(data , biggerNode.data)){
            //在较大节点的左子树找一个值更加接近的 因为该左子树的所有节点的值也在（biggerNode.data , comparenNode.data)
            IPNode nearestBiggerIPNode = getChildTreeNearestData(biggerNode.left, data ,true);
            return nearestBiggerIPNode != null ? nearestBiggerIPNode : biggerNode.data;
        }
        return null;
    }
    private IPNode getNearestBiggerDataFromLeftBranch(Node<IPNode> biggerNode , Node<IPNode> smallerNode , IPNode data){
        //只有最左边会等于null
        if(smallerNode != null && isFirstDataBiggerThanSecondData(data , smallerNode.data)){
            //在较小节点的右子树找一个值更加接近的 因为该右子树的所有节点的值也在(compareNode.data , smllarNode.data)
            IPNode nearestBiggerIPNode = getChildTreeNearestData(smallerNode.right, data ,true);
            return nearestBiggerIPNode != null ? nearestBiggerIPNode : biggerNode.data;
        }
        return null;
    }
    private IPNode getNearestSmallerDataFromRightBranch(Node<IPNode> biggerNode ,Node<IPNode> smallerNode , IPNode data){
        //只有最右边会等于null
        if(biggerNode != null && isFirstDataSmallerThanSecondData(data , biggerNode.data)){
            //在较大节点的左子树找一个值更加接近的 因为该左子树的所有节点的值也在（biggerNode.data , comparenNode.data)
            IPNode nearestSmallerIPNode = getChildTreeNearestData(biggerNode.left, data , false);
            return nearestSmallerIPNode != null ? nearestSmallerIPNode : smallerNode.data;
        }
        return null;
    }
    private IPNode getNearestSmallerDataFromLeftBranch(Node<IPNode> smallerNode , IPNode data){
        //只有最左边会等于null
        if(smallerNode != null && isFirstDataBiggerThanSecondData(data , smallerNode.data)){
            //在较小节点的右子树找一个值更加接近的 因为该右子树的所有节点的值也在(compareNode.data , smllarNode.data)
            IPNode nearestSmallerIPNode = getChildTreeNearestData(smallerNode.right, data , false);
            return nearestSmallerIPNode != null ? nearestSmallerIPNode : smallerNode.data;
        }
        return null;
    }

    private IPNode getChildTreeNearestData(Node<IPNode> childTreeRoot , IPNode data , boolean isNeedBigger){
        if(childTreeRoot == null){
            return null;
        }

        IPNode res = null;
        while(childTreeRoot != null){
            if(isFirstDataBiggerThanSecondData(childTreeRoot.data , data)){
                if(isNeedBigger){
                    res = childTreeRoot.data;
                }
                childTreeRoot = childTreeRoot.left;
            }else if(isFirstDataSmallerThanSecondData(childTreeRoot.data , data)){
                if(!isNeedBigger){
                    res = childTreeRoot.data;
                }
                childTreeRoot = childTreeRoot.right;
            }else {
                return childTreeRoot.data;
            }
        }

        return res;
    }

    //只有大于最大或者小于最小才会到这个方法
    private IPNode buildDataCircle(IPNode data , boolean isBigger){
        refreshMaxDataAndMinData();
        if(isBigger){ //即顺时针
            return minData;
        }else { //即逆时针
            return maxData;
        }
    }

    private void refreshMaxDataAndMinData(){
        maxData = findNodeMaxData(root);
        minData = findNodeMinData(root);
    }
}
