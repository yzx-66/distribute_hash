package net.yzx66.commen.abs;

import net.yzx66.commen.ChordStruct;
import net.yzx66.commen.DataOperate;

public abstract class AbstractDataOperate implements DataOperate , DataOperatePostProcessor {
    //桥接
    protected AbstractChordStruct chordStruct;

    public AbstractDataOperate(AbstractChordStruct chordStruct){
        this.chordStruct=chordStruct;
    }

    //为了职责单一，不提供ChordStruct的操作，对外提供访问ChordStruct的方法，由chord 统一包装
    final public ChordStruct getChordStruct(){
        return chordStruct;
    }

    @Override
    public void insert(String k, Object v) {

        insertSelf(k,v);
        postProcessInsert(k,v);
    }
    @Override
    public void update(String k, Object v) {
        updateSelf(k,v);
        postProcessUpdate(k,v);
    }
    @Override
    public void delete(String k) {
        deleteSelf(k);
        postProcessDelete(k);
    }
    @Override
    public Object get(String k){
        Object v = getSelf(k);
        postProcessGet(k , v);
        return v;
    }

    protected abstract void insertSelf(String k, Object v);
    protected abstract void updateSelf(String k, Object v);
    protected abstract void deleteSelf(String k);
    protected abstract Object getSelf(String k);
}
