package com.xiaozi.designpatterntestdemo.pattern;

import java.util.ArrayList;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/10/17 15:01
 * desc :享元模式-结构型模式
 *
 * 背景：
 * 在面向对象程序设计过程中，有时会面临要创建大量相同或相似对象实例的问题。创建那么多的对象将会耗费很多的系
 * 统资源，它是系统性能提高的一个瓶颈。这些对象有很多相似的地方，如果能把它们相同的部分提取出来共享，则能节
 * 省大量的系统资源，这就是享元模式的产生背景。
 *
 * 享元模式的定义与特点
 * 享元模式的定义：
 * 运用共享技术来有効地支持大量细粒度对象的复用。它通过共享已经存在的对象来大幅度减少需要创建的对象数量、避
 * 免大量相似类的开销，从而提高系统资源的利用率。
 * 享元模式的主要优点是：
 * 相同对象只要保存一份，这降低了系统中对象的数量，从而降低了系统中细粒度对象给内存带来的压力。
 * 其主要缺点是：
 * 1.为了使对象可以共享，需要将一些不能共享的状态外部化，这将增加程序的复杂性。
 * 2.读取享元模式的外部状态会使得运行时间稍微变长。
 *
 * 享元模式的结构与实现
 * 享元模式中存在以下两种状态：
 * 1.内部状态，即不会随着环境的改变而改变的可共享部分；
 * 2.外部状态，指随环境改变而改变的不可以共享的部分。享元模式的实现要领就是区分应用中的这两种状态，并将外部状态外部化。
 * 享元模式的主要角色有如下：
 * 1.抽象享元角色:是所有的具体享元类的基类，为具体享元规范需要实现的公共接口，非享元的外部状态以参数的形式通过方法传入。
 * 2.具体享元角色：实现抽象享元角色中所规定的接口。
 * 3.非享元角色：是不可以共享的外部状态，它以参数的形式注入具体享元的相关方法中。
 * 4.享元工厂角色：负责创建和管理享元角色。当客户对象请求一个享元对象时，享元工厂检査系统中是否存在符合要求
 * 的享元对象，如果存在则提供给客户；如果不存在的话，则创建一个新的享元对象。
 *
 * 享元模式的应用场景
 * 前面分析了享元模式的结构与特点，下面分析它适用的应用场景。享元模式是通过减少内存中对象的数量来节省内存空
 * 间的，所以以下几种情形适合采用享元模式：
 * 1.系统中存在大量相同或相似的对象，这些对象耗费大量的内存资源。
 * 2.大部分的对象可以按照内部状态进行分组，且可将不同部分外部化，这样每一个组只需保存一个内部状态。
 * 3.由于享元模式需要额外维护一个保存享元的数据结构，所以应当在有足够多的享元实例时才值得使用享元模式。
 *
 * 享元模式的应用实例
 * 【例1】享元模式在五子棋游戏中的应用。
 * 分析：五子棋同围棋一样，包含多个“黑”或“白”颜色的棋子，所以用享元模式比较好。本实例中的棋子ChessPieces）
 * 类是抽象享元角色，它包含了一个落子的 downPieces()方法；白子（WhitePieces）和黑子（BlackPieces）类是具体
 * 享元角色，它实现了落子方法；Point 是非享元角色，它指定了落子的位置；WeiqiFactory 是享元工厂角色，它通过
 * ArrayList来管理棋子，并且提供了获取白子或者黑子的 getChessPieces(String type) 方法；
 *
 * 享元模式的扩展
 * 享元模式中通常包含可以共享的部分和不可以共享的部分。在实际使用过程中，有时候会稍加改变，即存在两种特殊的
 * 享元模式：单纯享元模式和复合享元模式.
 * 1.单纯享元模式，这种享元模式中的所有的具体享元类都是可以共享的，不存在非共享的具体享元类.
 * 2.复合享元模式，这种享元模式中的有些享元对象是由一些单纯享元对象组合而成的，它们就是复合享元对象。虽然复
 * 合享元对象本身不能共享，但它们可以分解成单纯享元对象再被共享
 *
 *
 */
public class FlyweightPattern {

    public static void testFlyweightPattern(){
        WeiqiFactory weiqiFactory = new WeiqiFactory();
        ChessPieces w = weiqiFactory.getChessPieces("w");
        w.downPieces("5,6");
        ChessPieces b = weiqiFactory.getChessPieces("b");
        b.downPieces("3,3");
    }


    //抽象享元角色：棋子
    interface ChessPieces{
        void downPieces(String point);    //下子
    }

    //具体享元角色：白子
    static class WhitePieces implements ChessPieces{

        @Override
        public void downPieces(String point) {
            System.out.printf("白子下到了%s的位置",point);
            System.out.println("");
        }
    }

    //具体享元角色：白子
    static class BlackPieces  implements ChessPieces{

        @Override
        public void downPieces(String point) {
            System.out.printf("黑子下到了%s的位置",point);
            System.out.println("");

        }
    }

    //享元工厂角色
    static class WeiqiFactory{
        private ArrayList<ChessPieces> qz;

        public WeiqiFactory(){
            qz=new ArrayList<ChessPieces>();
            ChessPieces w=new WhitePieces();
            qz.add(w);
            ChessPieces b=new BlackPieces();
            qz.add(b);
        }

        public ChessPieces getChessPieces(String type)
        {
            if(type.equalsIgnoreCase("w"))
            {
                return (ChessPieces)qz.get(0);
            }
            else if(type.equalsIgnoreCase("b"))
            {
                return (ChessPieces)qz.get(1);
            }
            else
            {
                return null;
            }
        }
    }


}
