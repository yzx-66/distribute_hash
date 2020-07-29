package net.yzx66.struct;

import java.util.List;

public interface StructBasic<T> {

    boolean add(T data);

    /**
     * @param incompletelNodeWithIndex 删除节点，通过包含该节点索引的不完整节点
     */
    void delete(T incompletelNodeWithIndex);

    /**
     * @param incompletelNodeWithIndex 获得完整的节点，通过包含该节点索引的不完整节点
     */
    T getCompleteNode(T incompletelNodeWithIndex);

    void show();

    List<T> getAllDatas();
}
