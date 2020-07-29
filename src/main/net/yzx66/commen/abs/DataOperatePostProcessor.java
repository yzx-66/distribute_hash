package net.yzx66.commen.abs;

//DataOperate的后置拓展点
public interface DataOperatePostProcessor {

     void postProcessInsert(String k, Object v);
     void postProcessUpdate(String k, Object v);
     void postProcessDelete(String k);
     void postProcessGet(String k , Object v);
}

