package net.yzx66.struct.chord;

import net.yzx66.commen.IPNode;
import net.yzx66.commen.abs.AbstractChordStruct;
import net.yzx66.struct.chord.enhanced.StructEnhanced;

import java.util.List;

public abstract class SelfStructFunctionAchieve extends AbstractChordStruct {

    private StructEnhanced structEnhanced;

    public SelfStructFunctionAchieve(StructEnhanced structEnhanced){
        this.structEnhanced = structEnhanced;
    }

    @Override
    protected boolean joinSelf(String ip) {
        IPNode nearestSmallerIPNode = getNearestSmallerIPNode(ip);
        IPNode nearestBiggerIPNode = getNearestBiggerIPNode(ip);

        IPNode newIpNode = new IPNode(ip);
        if(!structEnhanced.add(newIpNode)){
            return false;
        }

        moveDataToNewIPNode(nearestSmallerIPNode , nearestBiggerIPNode , newIpNode);
        return true;
    }

    private IPNode getNearestSmallerIPNode(String newIp){
        return structEnhanced.getNearestSmallerIPNode(IPNode.calculatHash(newIp));
    }

    private IPNode getNearestBiggerIPNode(String newIp){
        return structEnhanced.getNearestBiggerIPNode(IPNode.calculatHash(newIp));
    }

    private void moveDataToNewIPNode(IPNode smallerIPNode ,IPNode biggerIPNode, IPNode newIpNode){
        if(smallerIPNode == null){
            return;
        }


        int newIpNodeHash = newIpNode.getstartHash();
        int smallerNodeHash = smallerIPNode.getstartHash();

        //（smallerNodeHash ， newIpNodeHash ]
        List<IPNode.Entry> datasByHash = biggerIPNode.getDatasByHash(smallerNodeHash, newIpNodeHash);
        for(IPNode.Entry entry : datasByHash){
            newIpNode.addData(entry.getK() , entry.getV());
            biggerIPNode.deleteData(entry.getK());
        }
    }

    @Override
    protected void leaveSelf(String ip) {
        List<IPNode.Entry> moveDatas = deleteIPNodeAndGetDatas(ip);
        moveDataToNearestSmallerIPNode(ip , moveDatas);
    }

    private List<IPNode.Entry> deleteIPNodeAndGetDatas(String ip){
        IPNode ipNode = structEnhanced.getCompleteNode(IPNode.getIncompleteNodeWithIPToQuery(ip));
        structEnhanced.delete(ipNode);
        return ipNode.getDatas();
    }

    private void moveDataToNearestSmallerIPNode(String deletedIp , List<IPNode.Entry> moveDatas){
        IPNode nearestBiggerIPNode = structEnhanced.getNearestBiggerIPNode(IPNode.calculatHash(deletedIp));
        if(nearestBiggerIPNode == null){
            throw new IllegalStateException("所有已经节点下线 数据丢失！丢失数据："+ moveDatas);
        }

        for(IPNode.Entry entry : moveDatas){
            nearestBiggerIPNode.addData(entry.getK() , entry.getV());
        }
    }

    @Override
    public IPNode getIPNodeByKey(String k) {
        return structEnhanced.getNearestBiggerIPNode(IPNode.calculatHash(k));
    }

    @Override
    public List<IPNode> getAllIPNodes() {
        return structEnhanced.getAllDatas();
    }
}
