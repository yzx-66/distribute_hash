package net.yzx66.commen.abs;

import net.yzx66.commen.ChordStruct;
import net.yzx66.commen.IPNode;

public abstract class AbstractChordStruct implements ChordStruct , ChordStructPostProcessor{

    public abstract IPNode getIPNodeByKey(String k);

    @Override
    public boolean join(String ip) {

        boolean isAddSuccess = joinSelf(ip);
        postProcessorJoin(ip , isAddSuccess);
        return isAddSuccess;
    }

    @Override
    public void leave(String ip) {
        leaveSelf(ip);
        postProcessorLeave(ip);
    }

    protected abstract boolean joinSelf(String ip);
    protected abstract void leaveSelf(String ip);
}

