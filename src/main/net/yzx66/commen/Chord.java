package net.yzx66.commen;

import net.yzx66.commen.abs.AbstractDataOperate;

import java.util.List;

//门面类
public class Chord implements ChordStruct,DataOperate {

    private ChordStruct chordStruct;
    private DataOperate dataOperate;

    public Chord(AbstractDataOperate dataOperate){
        this.dataOperate=dataOperate;
        this.chordStruct=dataOperate.getChordStruct();
    }

    @Override
    public boolean join(String ip) {
        return chordStruct.join(ip);
    }

    @Override
    public void leave(String ip) {
        chordStruct.leave(ip);
    }

    @Override
    public List<IPNode> getAllIPNodes() {
        return chordStruct.getAllIPNodes();
    }

    @Override
    public void insert(String k, Object v) {
        dataOperate.insert(k,v);
    }

    @Override
    public void update(String k, Object v) {
        dataOperate.update(k,v);
    }

    @Override
    public void delete(String k) {
        dataOperate.delete(k);
    }

    @Override
    public Object get(String k) {
        return dataOperate.get(k);
    }


}
