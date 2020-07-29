package net.yzx66.struct;

import java.util.ArrayList;
import java.util.List;

public class Tree<T> extends StructBasicTemplate<T> {
    protected Node<T> root;

    @Override
    public boolean doAdd(T data){
        Node<T> newNode = new Node<>(data);
        if(isNewRoot(newNode)){
            return true;
        }
        return addNode(newNode);
    }

    private boolean isNewRoot(Node<T> newNode){
        if(root == null){
            root = newNode;
            return true;
        }
        return false;
    }

    private boolean addNode(Node<T> newNode){
        //起始查找节点为root
        Node<T> compareNode = root;

        while(true){
            if(isFirstDataBiggerThanSecondData(newNode.data,compareNode.data)){
                if(compareNode.right != null){
                    compareNode = compareNode.right;
                }else {
                    compareNode.right = newNode;
                    return true;
                }
            }else if(isFirstDataSmallerThanSecondData(newNode.data,compareNode.data)){
                if(compareNode.left != null){
                    compareNode = compareNode.left;
                }else {
                    compareNode.left = newNode;
                    return true;
                }
            }else {
                return false;
            }
        }
    }

    @Override
    public T doGetCompleteNode(T incompletelNodeWithIndex){
        //起始查找节点为root
        Node<T> compareNode = root;

        while(compareNode != null){
            if(isFirstDataBiggerThanSecondData(incompletelNodeWithIndex,compareNode.data)){
                compareNode = compareNode.right;
            }else if(isFirstDataSmallerThanSecondData(incompletelNodeWithIndex,compareNode.data)){
                compareNode = compareNode.left;
            }else {
                return compareNode.data;
            }
        }
        
        return null;
    }

    @Override
    public void doDelete(T incompletelNodeWithIndex){
        Node<T> compareNode = root;
        Node<T> compareNodeParent = null;
        boolean isLeftChild = true;

        while(compareNode != null){
            if(isFirstDataBiggerThanSecondData(incompletelNodeWithIndex,compareNode.data)){
                compareNodeParent = compareNode;
                isLeftChild = false;
                compareNode = compareNode.right;
            }else if (isFirstDataSmallerThanSecondData(incompletelNodeWithIndex,compareNode.data)){
                compareNodeParent = compareNode;
                isLeftChild = true;
                compareNode = compareNode.left;
            }else {
                if(isBeEmptyTree(compareNode)){
                    return;
                }

                if(isDeleteLeafNode(compareNode,compareNodeParent,isLeftChild)){
                    return;
                }

                doDeleteNotLeafNode(compareNode);
                return;
            }
        }
    }

    private boolean isBeEmptyTree(Node<T> compareNode){
        if(root == compareNode && root.left == null && root.right == null){
            root = null;
            return true;
        }
        return false;
    }

    private boolean isDeleteLeafNode(Node<T> compareNode,Node<T> compareNodeParent,boolean isLeftChild){
        if(compareNode.right == null && compareNode.left == null){
            if(isLeftChild){
                compareNodeParent.left = null;
            }else {
                compareNodeParent.right = null;
            }
            return true;
        }
        return false;
    }

    private void doDeleteNotLeafNode(Node<T> compareNode){
        T data;
        if(compareNode.right != null){
            data = findNodeMinData(compareNode.right);
        }else {
            data = findNodeMaxData(compareNode.left);
        }
        //该data对应节点一定是上面三种的一种
        delete(data);
        compareNode.data = data;
    }

    protected T findNodeMaxData(Node<T> node){
        while(node != null){
            if(node.right == null){
                return node.data;
            }
            node = node.right;
        }
        return null;
    }

    protected T findNodeMinData(Node<T> node){
        while(node != null){
            if(node.left == null){
                return node.data;
            }
            node = node.left;
        }
        return null;
    }

    @Override
    public void show(){
       leftFirstShow(root);
    }

    private void leftFirstShow(Node<T> node){
        if(node != null){
            leftFirstShow(node.left);
            System.out.println(node.data);
            leftFirstShow(node.right);
        }
    }

    @Override
    public List<T> getAllDatas() {
        List<T> list = new ArrayList<>();
        leftFirstAdd(list , root);
        return list;
    }

    private void leftFirstAdd(List<T> list , Node<T> node){
        if(node != null){
            leftFirstAdd(list , node.left);
            list.add(node.data);
            leftFirstAdd(list ,node.right);
        }
    }

    //因为非静态内部类无法声明static对象，所以不好实现单例
    //同时Node是private的，所以在内部new Node时，可以保证T_ = T
    protected static class Node<T_>{

        //声明为public是为了Tree的子类可以访问该内部类的属性
        public T_ data;
        public Node<T_> right;
        public Node<T_> left;

        Node(T_ data){
            this.data = data;
        }
    }
}
