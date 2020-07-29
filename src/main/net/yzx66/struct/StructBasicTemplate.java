package net.yzx66.struct;

import java.util.List;

public abstract class StructBasicTemplate<T> implements StructBasic<T> {

    @Override
    public boolean add(T data){
        //确保每一个添加的元素都可以比较
        checkNodeCanCompare(data);
        return doAdd(data);
    }

    private void checkNodeCanCompare(T data) {
        if(! (data instanceof Comparable)){
            throw new IllegalArgumentException("没有实现Comparable接口！");
        }
    }

    protected abstract boolean doAdd(T data);

    @Override
    public void delete(T incompletelNodeWithIndex){
        //确保参数可以比较
        checkNodeCanCompare(incompletelNodeWithIndex);
        doDelete(incompletelNodeWithIndex);
    }

    protected abstract void doDelete(T incompletelNodeWithIndex);

    @Override
    public T getCompleteNode(T incompletelNodeWithIndex){
        //确保参数可以比较
        checkNodeCanCompare(incompletelNodeWithIndex);
        return doGetCompleteNode(incompletelNodeWithIndex);
    }

    protected abstract T doGetCompleteNode(T incompletelNodeWithIndex);

    /**
     * 给子类提供的三个比较函数
     */
    @SuppressWarnings("unchecked")//可以压制，因为在提供的模板方法起始都用checkNodeCanCompare先检查确保为Comparable的实现类
    protected boolean isFirstDataBiggerThanSecondData(T firstData,T secondData){
        Comparable firstParamNodeDataCompare = (Comparable) firstData;
        Comparable secondParamNodeDataCompare = (Comparable) secondData;
        return firstParamNodeDataCompare.compareTo(secondParamNodeDataCompare) > 0;
    }

    @SuppressWarnings("unchecked")//可以压制，因为在提供的模板方法起始都用checkNodeCanCompare先检查确保为Comparable的实现类
    protected boolean isFirstDataSmallerThanSecondData(T firstData,T secondData){
        Comparable firstParamNodeDataCompare = (Comparable) firstData;
        Comparable secondParamNodeDataCompare = (Comparable) secondData;
        return firstParamNodeDataCompare.compareTo(secondParamNodeDataCompare) < 0;
    }

    protected boolean isFirstDataEqualToSecondData(T firstData,T secondData){
        return ! isFirstDataBiggerThanSecondData(firstData , secondData) &&
                ! isFirstDataSmallerThanSecondData(firstData , secondData);
    }
}
