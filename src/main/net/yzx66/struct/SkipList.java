package net.yzx66.struct;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

//跳跃表
public class SkipList<T> extends StructBasicTemplate<T> {

    protected Node<T> head;
    protected Node<T> tail;
    protected int hight;
    protected int size;

    public SkipList(){
        head = new Node<>();
        tail = new Node<>();

        head.right = tail;
        tail.left = head;

        hight = 1;
        size = 0;
    }

    @Override
    public boolean doAdd(T data) {
        Node<T> selfOrNearestLeftNode = findSelfOrNearestLeftNode(data);
        if(isExist(selfOrNearestLeftNode , data)){
            return false;
        }


        Node<T> previousNode = null;
        Node<T> leftNode = selfOrNearestLeftNode;
        Random r = new Random();

        for(int i = 1; r.nextBoolean() || i == 1 ; i++){
            //最高层都是空层 保证后面的节点在找上一层时肯定存在上一层
            if(i >= hight){
                addHight();
            }
            Node<T> newNode = new Node<>(data);
            spliceNode(leftNode , newNode);

            while(leftNode.up == null){
                leftNode = leftNode.left;
            }
            leftNode = leftNode.up;

            //拼接该节点的上下层
            if(i > 1){
                previousNode.up = newNode;
                newNode.down = previousNode;
            }
            previousNode = newNode;
        }
        size ++;
        return true;
    }

    protected boolean isExist(Node<T> selfOrNearestLeftNode ,T data){
        return ! isHead(selfOrNearestLeftNode) &&
                isFirstDataEqualToSecondData(data , selfOrNearestLeftNode.data);
    }

    private void spliceNode(Node<T> leftNode , Node<T> newRightNode){
        newRightNode.right = leftNode.right;
        newRightNode.right.left = newRightNode;

        leftNode.right = newRightNode;
        newRightNode.left = leftNode;
    }

    private void addHight() {
        Node<T> newHead = new Node<>();
        Node<T> newTail = new Node<>();

        newHead.right = newTail;
        newTail.left = newHead;

        newHead.down = head;
        newTail.down = tail;

        head.up = newHead;
        tail.up = newTail;

        this.head = newHead;
        this.tail = newTail;
        hight++;
    }

    protected Node<T> findSelfOrNearestLeftNode(T data){
        Node<T> node = head;

        while(true){
            if(isHead(node)){
                node = node.right;
            }
            //升序排列
            while(!isTail(node) && !isFirstDataSmallerThanSecondData(data , node.data)){
                node = node.right;
            }

            if(node.left.down != null) {
                node = node.left.down;
            }else {
                //找到尾的情况：大于等于最后一个节点、前一个节点是头节点
                return node.left;
            }
        }
    }

    protected boolean isHead(Node<T> node){
        return node.data == null && node.left == null && node.right != null;
    }

    protected boolean isTail(Node<T> node){
        return node.data == null && node.right == null && node.left != null;
    }

    @Override
    public void doDelete(T incompletelNodeWithIndex) {
        Node<T> selfOrNearestLeftNode = findSelfOrNearestLeftNode(incompletelNodeWithIndex);
        if(! isExist(selfOrNearestLeftNode , incompletelNodeWithIndex)){
            return;
        }

       do {
           Node<T> leftNode = selfOrNearestLeftNode.left;
           Node<T> rightNode = selfOrNearestLeftNode.right;
           leftNode.right = rightNode;
           rightNode.left = leftNode;

           selfOrNearestLeftNode = selfOrNearestLeftNode.up;
       }while(selfOrNearestLeftNode != null);

       size --;
    }

    @Override
    public T doGetCompleteNode(T incompletelNodeWithIndex) {
        Node<T> selfOrNearestLeftNode = findSelfOrNearestLeftNode(incompletelNodeWithIndex);
        if(!isExist(selfOrNearestLeftNode , incompletelNodeWithIndex)){
            return null;
        }
        return selfOrNearestLeftNode.data;
    }

    @Override
    public void show() {
        Node<T> node = head;
        while(node.down != null){
            node = node.down;
        }
        node = node.right;

        while(!isTail(node)){
            System.out.println(node.data);
            node = node.right;
        }
    }

    //返回 (start , end]
    public List<T> getDatasByScope(T start , T end){
        if(size == 0){
            return Collections.emptyList();
        }

        List<T> res = new ArrayList<>();
        Node<T> selfOrNearestLeftNode = findSelfOrNearestLeftNode(start);
        //三种情况的起始节点都是后一个：
        // 存在元素返回head、返回值等于start的节点、返回小于start的节点（即下一个节点大于start的值或者tail）
        Node<T> resNode = selfOrNearestLeftNode.right;

        while(!isTail(resNode) && !isFirstDataBiggerThanSecondData(resNode.data , end)){
            res.add(resNode.data);
            resNode = resNode.right;
        }

        return res;
    }

    @Override
    public List<T> getAllDatas(){
        List<T> res = new ArrayList<>();
        Node<T> lowestStartNode = getLowest(head).right;
        while(!isTail(lowestStartNode)){
            res.add(lowestStartNode.data);
            lowestStartNode = lowestStartNode.right;
        }
        return res;
    }

    protected Node<T> getLowest(Node<T> node){
        while(node.down != null){
            node = node.down;
        }
        return node;
    }

    protected static class Node<T_>{

        public T_ data;

        public Node<T_> up;
        public Node<T_> down;
        public Node<T_> right;
        public Node<T_> left;

        Node(){}

        Node(T_ data){
            this.data = data;
        }
    }

}
