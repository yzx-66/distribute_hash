package net.yzx66.operate;

import net.yzx66.commen.IPNode;
import net.yzx66.commen.abs.AbstractChordStruct;
import net.yzx66.commen.abs.AbstractDataOperate;
import net.yzx66.commen.abs.DataOperatePostProcessor;

public abstract class SelfOperateFunctionAchieve extends AbstractDataOperate {

    public SelfOperateFunctionAchieve(AbstractChordStruct chordStruct) {
        super(chordStruct);
    }


    @Override
    protected void insertSelf(String k, Object v){
        IPNode node = chordStruct.getIPNodeByKey(k);
        node.addData(k, v);
    }


    @Override
    protected void updateSelf(String k, Object v){
        IPNode node = chordStruct.getIPNodeByKey(k);
        node.deleteData(k);
        node.addData(k , v);
    }


    @Override
    protected Object getSelf(String k) {
        IPNode node = chordStruct.getIPNodeByKey(k);
        return node.getData(k).getV();
    }


    @Override
    protected void deleteSelf(String k){
        IPNode node = chordStruct.getIPNodeByKey(k);
        node.deleteData(k);
    }



}
