package net.yzx66.type.cluster;

import net.yzx66.struct.chord.SelfStructFunctionAchieve;
import net.yzx66.struct.chord.enhanced.StructEnhanced;
import net.yzx66.type.cluster.socket.ChordServer;

//只负责节点增删的通讯工作
public class ClusterChordStruct extends SelfStructFunctionAchieve {

    public ClusterChordStruct(StructEnhanced structEnhanced) { super(structEnhanced); }
    //chordServer通讯模块的实现
    public ClusterChordStruct(StructEnhanced structEnhanced , ChordServer chordServer) { super(structEnhanced); }

    @Override
    public void postProcessorJoin(String ip , boolean isAddSuccess) {
        notityClusterNodeJoin(ip);
    }
    @Override
    public void postProcessorLeave(String ip) {
        notifyClusterNodeLeave(ip);
    }

    private void notityClusterNodeJoin(String ip){
        //TODO 该方法维护集群中其他节点的socket列表
        // 1、获取已加入集群的socket列表
        // 2、给每个socket发送消息
        // 3、等待ack 刷新本地列表
    }

    private void notifyClusterNodeLeave(String ip){
        //TODO 该方法维护集群中其他节点的socket列表
    }
}
