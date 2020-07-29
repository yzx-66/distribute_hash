package net.yzx66.type.cluster.socket;

import java.util.List;

// 通讯模块的实现：netty、nio、bio ...
public interface ChordServer {

    void join(String ip);

    void leave(String ip);

    void insert(String insertDataIp );

    void sendDatas(String destIP , List<Object> datas);



}
