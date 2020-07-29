package net.yzx66.type.single;

import net.yzx66.struct.chord.SelfStructFunctionAchieve;
import net.yzx66.struct.chord.enhanced.StructEnhanced;

public class SingleChordStruct extends SelfStructFunctionAchieve {
    public SingleChordStruct(StructEnhanced structEnhanced) {
        super(structEnhanced);
    }

    @Override
    public void postProcessorJoin(String ip , boolean isAddSuccess){/*not do anything*/ }
    @Override
    public void postProcessorLeave(String ip){/*not do anything*/ }
}
