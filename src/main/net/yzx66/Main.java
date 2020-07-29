package net.yzx66;

import net.yzx66.commen.Chord;
import net.yzx66.commen.IPNode;
import net.yzx66.commen.abs.AbstractChordStruct;
import net.yzx66.commen.abs.AbstractDataOperate;
import net.yzx66.struct.chord.enhanced.EnhancedSkipList;
import net.yzx66.struct.chord.enhanced.EnhancedTree;
import net.yzx66.type.single.SingleChordStruct;
import net.yzx66.type.single.SingleSelfOperate;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("******************************** 测试 Tree 作为一致性哈希环 ********************************");
        testFuction(initChord(true));
        System.out.println("\n******************************** 测试 SkipList 作为一致性哈希环 ********************************");
        testFuction(initChord(false));
    }

    private static Chord initChord(boolean isTreeStruct){
        return new Chord(getDataOperateWrapper(isTreeStruct));
    }

    private static AbstractDataOperate getDataOperateWrapper(boolean isTreeStruct){
        return isTreeStruct ?  new SingleSelfOperate(getTreeChordStruct()) : new SingleSelfOperate(getSkipListChordStruct());
    }

    private static AbstractChordStruct getSkipListChordStruct(){
        return new SingleChordStruct(new EnhancedSkipList());
    }

    private static AbstractChordStruct getTreeChordStruct(){
        return new SingleChordStruct(new EnhancedTree());
    }

    private static void testFuction(Chord chord){
        initIps(chord);

        testAddData(chord);
        testGetData(chord);
        testJoin(chord);
        testLeave(chord);
    }

    private static void initIps(Chord chord){
        System.out.println("=============== 测试初始化 IPNODE ===============");
        chord.join("127.0.0.1");
        chord.join("192.168.56.129");
        chord.join("192.168.56.130");

        show(chord);
    }

    private static void testAddData(Chord chord) {
        System.out.println("=============== 测试添加数据[(1,1),(2,2),(3,3),(4,4),(5,5)]后的数据分片 ===============");
        chord.insert("1",1);
        chord.insert("2",2);
        chord.insert("3",3);
        chord.insert("4",4);
        chord.insert("5",5);

        show(chord);
    }

    private static void testGetData(Chord chord){
        System.out.println("=============== 测试查找数据 ===============");
        System.out.println("查找：2，结果：" + chord.get("2"));
        System.out.println("查找：4，结果：" + chord.get("4"));
        System.out.println();
    }


    private static void testJoin(Chord chord) {
        System.out.println("=============== 测试添加 IPNODE 后的数据迁移 ===============");
        chord.join("192.168.56.140");
        show(chord);
    }

    private static void testLeave(Chord chord) {
        System.out.println("=============== 测试移除 IPNODE 后的数据迁移 ===============");
        chord.leave("192.168.56.140");
        show(chord);
    }

    private static void show(Chord chord){
        List<IPNode> nodes = chord.getAllIPNodes();
        for(IPNode ipNode : nodes){
            System.out.println(ipNode.getIp() +"(hash:" + ipNode.getstartHash() +"):" + ipNode.getDatas());
        }
        System.out.println();
    }

}
