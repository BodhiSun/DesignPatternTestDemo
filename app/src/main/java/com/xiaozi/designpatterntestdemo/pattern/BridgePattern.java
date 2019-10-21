package com.xiaozi.designpatterntestdemo.pattern;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/10/17 14:58
 * desc :桥接模式-结构型模式
 *
 * 背景：
 * 在现实生活中，某些类具有两个或多个维度的变化，如图形既可按形状分又可按颜色分，如果用继承方式，m种形状
 * 和n种颜色的图形就有m×n 种，不但对应的子类很多，而且扩展困难，如果用桥接模式就能很好地解决这些问题。
 *
 * 桥接模式的定义与特点
 * 桥接模式的定义如下：将抽象与实现分离，使它们可以独立变化。它是用组合关系代替继承关系来实现，从而降低了
 * 抽象和实现这两个可变维度的耦合度。
 * 桥接模式的优点是：
 * 1.由于抽象与实现分离，所以扩展能力强；
 * 2.其实现细节对客户透明。
 * 缺点是：由于聚合关系建立在抽象层，要求开发者针对抽象化进行设计与编程，这增加了系统的理解与设计难度。
 *
 * 桥接模式的结构与实现
 * 可以将抽象化部分与实现化部分分开，取消二者的继承关系，改用组合关系。
 * 桥接模式包含以下主要角色：
 * 1.抽象化角色：定义抽象类，并包含一个对实现化对象的引用。
 * 2.扩展抽象化角色：是抽象化角色的子类，实现父类中的业务方法，并通过组合关系调用实现化角色中的业务方法。
 * 3.实现化角色：定义实现化角色的接口，供扩展抽象化角色调用。
 * 4.具体实现化角色：给出实现化角色接口的具体实现。
 *
 * 桥接模式的应用场景
 * 1.当一个类存在两个独立变化的维度，且这两个维度都需要进行扩展时。
 * 2.当一个系统不希望使用继承或因为多层次继承导致系统类的个数急剧增加时。
 * 3.当一个系统需要在构件的抽象化角色和具体化角色之间增加更多的灵活性时。
 *
 * 桥接模式的应用实例
 * 【例1】用桥接模式模拟女士皮包的选购。
 * 分析：女士皮包有很多种，可以按用途分、按皮质分、按品牌分、按颜色分、按大小分等，存在多个维度的变化，所
 * 以采用桥接模式来实现女士皮包的选购比较合适。按用途分可选钱包（Wallet）和挎包（HandBag），按颜色分可选
 * 黄色（Yellow）和红色（Red）。可以按两个维度定义为颜色类和包类。颜色类（Color）是一个维度，定义为实现
 * 化角色，它有两个具体实现化角色：黄色和红色，通过 getColor() 方法可以选择颜色；包类（Bag）是另一个维度，
 * 定义为抽象化角色，它有两个扩展抽象化角色：挎包和钱包，它包含了颜色类对象，通过 getName() 方法可以选择
 * 相关颜色的挎包和钱包。
 *
 * 桥接模式模式的扩展
 * 在软件开发中，有时桥接模式可与适配器模式联合使用。当桥接模式的实现化角色的接口与现有类的接口不一致时，
 * 可以在二者中间定义一个适配器将二者连接起来。
 *
 *
 *
 */
public class BridgePattern {

    public static void testBridgePattern(){
        Color yellow = new Yellow();
        Bag bag = new HandBag();
        bag.setColor(yellow);
        System.out.println(bag.getName());


    }

    //实现化角色
    interface Color{
        String getColor();
    }

    //具体实现化角色：黄色
    static class Yellow implements Color{

        @Override
        public String getColor() {
            return "yellow";
        }
    }

    //具体实现化角色：红色
    static class Red implements Color{

        @Override
        public String getColor() {
            return "red";
        }
    }

    //抽象化角色：包
    static abstract class Bag{
        protected Color color;
        public void setColor(Color color){
            this.color=color;
        }

        public abstract String getName();
    }

    //扩展抽象化角色：挎包
    static class HandBag extends Bag{

        @Override
        public String getName() {
            return color.getColor()+"   HandBag";
        }
    }

    //扩展抽象化角色：钱包
    static class Wallet extends Bag{

        @Override
        public String getName() {
            return color.getColor()+"   Wallet";
        }
    }




}
