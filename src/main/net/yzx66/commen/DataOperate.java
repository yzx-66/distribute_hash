package net.yzx66.commen;

/**
 * 对数据的操作
 */
public interface  DataOperate {

    void insert(String k,Object v);

    void update(String k,Object v);

    void delete(String k);

    Object get(String k);
}