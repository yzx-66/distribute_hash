package net.yzx66.struct.chord.enhanced;

import net.yzx66.commen.IPNode;
import net.yzx66.struct.StructBasic;

public interface StructEnhanced extends StructBasic<IPNode> {

    /**
     * 增加某个IP节点时，要选择该ip的hashcode的顺时针方向第一个节点，将下一个节点的部分数据迁移新增节点
     * 移除某个IP节点时，要选择该ip的hashcode的顺时针方向第一个节点，将该节点的值全部迁移到下一个节点
     * 插入值要选择节点时，要选择key的hashcode的顺时针方向第一个节点（即：最近的大于该hash的节点）
     */
    IPNode getNearestBiggerIPNode(int hashCode);

    /**
     * 在增加IP节点时，要找到逆时针方向第一个节点的hash值，新增节点将从顺时针方向第一个节点
     * 迁移 （逆时针方向第一个节点hash，新增节点hash] 区间的值
     */
    IPNode getNearestSmallerIPNode(int hashCode);
}
