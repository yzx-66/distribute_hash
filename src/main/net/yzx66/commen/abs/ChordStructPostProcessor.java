package net.yzx66.commen.abs;

//ChordStruct的拓展点
public interface ChordStructPostProcessor {


    void postProcessorJoin(String ip , boolean isAddSuccess);

    void postProcessorLeave(String ip);
}
