package net.yzx66.commen;

import net.yzx66.struct.SkipList;
import net.yzx66.utils.MD5Util;

import java.util.List;


/**
 * chord网络每个ip节点的存储结构
 */
public class IPNode implements Comparable<IPNode>{

    public static final int SUM_FRAGMENT = 2048;

    private String ip;
    private int startHash;
    private SkipList<Entry> datas;

    public IPNode(){
        datas=new SkipList<>();
    }

    public IPNode(String ip){
        this.ip=ip;
        startHash = MD5Util.string2MD5HashCode(ip) % SUM_FRAGMENT;
        datas=new SkipList<>();
    }

    //TODO 测试使用
    public IPNode(int startHash){
        this.startHash = startHash;
    }


    public String getIp(){
        return ip;
    }

    public int getstartHash(){ 
        return startHash; 
    }

    public List<Entry> getDatas() {
        return datas.getAllDatas();
    }

    public Entry getData(String k){
        int hash = calculatHash(k);
        return datas.getCompleteNode(Entry.getIncompleteEntryWithStartHashToQuery(hash));
    }

    public void addData(String k, Object v){
        datas.add(new Entry(k,v));
    }

    public void deleteData(String k){
        int kHash = MD5Util.string2MD5HashCode(k) % SUM_FRAGMENT;
        datas.delete(Entry.getIncompleteEntryWithStartHashToQuery(kHash));
    }

    //返回的 (start , end]
    public List<Entry> getDatasByHash(int start,int end){
        //说明过了中间临界点
        if(start > end){
            List<Entry> leftCircleDatas = datas.getDatasByScope(Entry.getIncompleteEntryWithStartHashToQuery(start),
                    Entry.getIncompleteEntryWithEndHashToQuery(SUM_FRAGMENT - 1));
            //因为返回的（start ，end ] 所以要从-1 开始才可以返回 0
            List<Entry> rightCircleDatas = datas.getDatasByScope(Entry.getIncompleteEntryWithStartHashToQuery(-1) ,
                    Entry.getIncompleteEntryWithEndHashToQuery(end));

            leftCircleDatas.addAll(rightCircleDatas);
            return leftCircleDatas;
        }
        return datas.getDatasByScope(Entry.getIncompleteEntryWithStartHashToQuery(start),
                Entry.getIncompleteEntryWithEndHashToQuery(end));
    }

    //单例：用于获取包含索引数据的不完整节点，用该不完整的节点去查询完整的节点
    public static IPNode getIncompleteNodeWithHashToQuery(int ipHashCode){
        Lazy.sigleIpNode.startHash = ipHashCode % SUM_FRAGMENT;
        return Lazy.sigleIpNode;
    }
    public static IPNode getIncompleteNodeWithIPToQuery(String ip){
        Lazy.sigleIpNode.startHash = MD5Util.string2MD5HashCode(ip) % IPNode.SUM_FRAGMENT; ;
        return Lazy.sigleIpNode;
    }
    //懒汉式
    private static class Lazy{
        private final static IPNode sigleIpNode = new IPNode();
    }

    public static int calculatHash(String k){
        return MD5Util.string2MD5HashCode(k) % IPNode.SUM_FRAGMENT;
    }

    @Override
    public int compareTo(IPNode o) {
        return Integer.compare(startHash,o.startHash);
    }

    @Override
    public String toString() {
        return "IPNode{" +
                "ip='" + ip + '\'' +
                ", startHash=" + startHash +
                ", datas=" + datas +
                '}';
    }

    public static class Entry implements Comparable<Entry>{
        String k;
        Object v;
        int hash;

        Entry(){}

        Entry(String k, Object v) {
            this.k = k;
            this.v = v;
            this.hash = MD5Util.string2MD5HashCode(k) % SUM_FRAGMENT;
        }

        public String getK() {
            return k;
        }

        public Object getV() {
            return v;
        }

        //单例：用于获取包含索引数据的不完整节点，用该不完整的节点去查询完整的节点
         static Entry getIncompleteEntryWithStartHashToQuery(int startHash){
            StartHashLazy.sigleEntry.hash = startHash;
            return StartHashLazy.sigleEntry;
        }
        //单例：用于获取包含索引数据的不完整节点，用该不完整的节点去查询完整的节点
         static Entry getIncompleteEntryWithEndHashToQuery(int endHash){
            EndHashLazy.sigleEntry.hash = endHash;
            return EndHashLazy.sigleEntry;
        }
        //懒汉式
        private static class StartHashLazy{
            private final static Entry sigleEntry = new Entry();
        }
        private static class EndHashLazy{
            private final static Entry sigleEntry = new Entry();
        }

        @Override
        public int compareTo(Entry o) {
            return Integer.compare(hash , o.hash);
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "k='" + k + '\'' +
                    ", v=" + v +
                    ", hash=" + hash +
                    '}';
        }
    }


}
