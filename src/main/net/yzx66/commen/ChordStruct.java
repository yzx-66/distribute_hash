package net.yzx66.commen;

import java.util.List;

/**
 * chrod网络的结构：
 * 数组、链表、跳跃表、树
 */
public interface ChordStruct {

    boolean join(String ip);

    void leave(String ip);

    List<IPNode> getAllIPNodes();
}
