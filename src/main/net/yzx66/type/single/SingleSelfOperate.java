package net.yzx66.type.single;

import net.yzx66.commen.abs.AbstractChordStruct;
import net.yzx66.operate.SelfOperateFunctionAchieve;

public class SingleSelfOperate extends SelfOperateFunctionAchieve {
    public SingleSelfOperate(AbstractChordStruct chordStruct) {
        super(chordStruct);
    }

    @Override
    public void postProcessInsert(String k, Object v) {/*not do anything*/ }
    @Override
    public void postProcessUpdate(String k, Object v) {/*not do anything*/}
    @Override
    public void postProcessDelete(String k) {/*not do anything*/}
    @Override
    public void postProcessGet(String k, Object v)  {/*not do anything*/ }
}
