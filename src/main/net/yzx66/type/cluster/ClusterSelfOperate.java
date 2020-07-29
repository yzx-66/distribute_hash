package net.yzx66.type.cluster;

import net.yzx66.commen.abs.AbstractChordStruct;
import net.yzx66.operate.SelfOperateFunctionAchieve;
import net.yzx66.type.cluster.socket.ChordServer;

//只负责数据改变的通讯工作
public class ClusterSelfOperate extends SelfOperateFunctionAchieve {

    public ClusterSelfOperate(AbstractChordStruct chordStruct) {
        super(chordStruct);
    }
    //chordServer通讯模块的实现
    public ClusterSelfOperate(AbstractChordStruct chordStruct, ChordServer chordServer){ super(chordStruct); }

    @Override
    public void postProcessInsert(String k, Object v) {
        notifyClusterDataInsert(k, v);
    }
    @Override
    public void postProcessUpdate(String k, Object v) {
        notifyClusterDataUpdate(k, v);
    }
    @Override
    public void postProcessDelete(String k) {
        notifyClusterDataDelete(k);
    }


    private void notifyClusterDataInsert(String k, Object v){
        //TODO 该方法给集群其他节点发送数据增加的消息
    }

    private void notifyClusterDataUpdate(String k, Object v){
        //TODO 该方法给集群其他节点发送数据改变的消息
    }

    private void notifyClusterDataDelete(String k){
        //TODO 该方法给集群其他节点发送数据删除的消息
    }

    @Override
    public void postProcessGet(String k, Object v) {

    }
}
