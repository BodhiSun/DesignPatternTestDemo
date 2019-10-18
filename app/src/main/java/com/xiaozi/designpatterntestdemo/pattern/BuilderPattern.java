package com.xiaozi.designpatterntestdemo.pattern;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/10/16 16:35
 * desc :建造者模式-创建型模式
 *
 * 背景：
 * 在软件开发过程中有时需要创建一个复杂的对象，这个复杂对象通常由多个子部件按一定的步骤组合而成。例如，计
 * 算机是由 CPU、主板、内存、硬盘、显卡等部件组装而成的，还有汽车中的方向盘、发动机、车架、轮胎等部件也多
 * 种多样。这些产品都是由多个部件构成的，各个部件可以灵活选择，但其创建步骤都大同小异。这类产品的创建无法
 * 用前面介绍的工厂模式描述，只有建造者模式可以很好地描述该类产品的创建。
 *
 * 模式的定义与特点
 * 建造者模式的定义：指将一个复杂对象的构造与它的表示分离，使同样的构建过程可以创建不同的表示，这样的设计
 * 模式被称为建造者模式。它是将一个复杂的对象分解为多个简单的对象，然后一步一步构建而成。它将变与不变相分
 * 离，即产品的组成部分是不变的，但每一部分是可以灵活选择的。
 * 该模式的主要优点如下：
 * 1.各个具体的建造者相互独立，有利于系统的扩展。
 * 2.客户端不必知道产品内部组成的细节，便于控制细节风险。
 * 其缺点如下：
 * 1.产品的组成部分必须相同，这限制了其使用范围。
 * 2.如果产品的内部变化复杂，该模式会增加很多的建造者类。
 * 建造者模式和工厂模式的关注点不同：
 * 建造者模式注重零部件的组装过程，而工厂方法模式更注重零部件的创建过程，但两者可以结合使用。
 *
 * 模式的结构与实现
 * 建造者模式由产品、抽象建造者、具体建造者、指挥者等 4 个要素构成。
 * 建造者模式的主要角色如下：
 * 1.产品角色：它是包含多个组成部件的复杂对象，由具体建造者来创建其各个部件。
 * 2.抽象建造者：它是一个包含创建产品各个子部件的抽象方法的接口，通常还包含一个返回复杂产品的方法 getResult()。
 * 3.具体建造者：实现抽象建造者，完成复杂产品的各个部件的具体创建方法。
 * 4.它调用建造者对象中的部件构造与装配方法完成复杂对象的创建，在指挥者中不涉及具体产品的信息。
 *
 * 模式的应用场景
 * 建造者模式创建的是复杂对象，其产品的各个部分经常面临着剧烈的变化，但将它们组合在一起的算法却相对稳定，
 * 所以它通常在以下场合使用：
 * 1.创建的对象较复杂，由多个部件构成，各部件面临着复杂的变化，但构件间的建造顺序是稳定的。
 * 2.创建复杂对象的算法独立于该对象的组成部分以及它们的装配方式，即产品的构建过程和最终的表示是独立的。
 *
 * 模式的应用实例
 * 【例1】用建造者模式描述客厅装修。
 * 分析：客厅装修是一个复杂的过程，它包含墙体的装修、电视机的选择、沙发的购买与布局等。客户把装修要求告诉
 * 项目经理，项目经理指挥装修工人一步步装修，最后完成整个客厅的装修与布局，所以用建造者模式实现比较适合。
 * 这里客厅是产品，包括墙、电视和沙发等组成部分；具体装修工人是具体建造者，他们负责装修与墙、电视和沙发的
 * 布局；项目经理是指挥者，他负责指挥装修工人进行装修。客厅类中提供了show()方法，可以将装修效果显示出来。
 *
 * 模式的扩展
 * 建造者模式在应用过程中可以根据需要改变，如果创建的产品种类只有一种，只需要一个具体建造者，这时可以省略
 * 掉抽象建造者，甚至可以省略掉指挥者角色。
 *
 */
public class BuilderPattern {

    public static void testBuildPattern(){
        Decorator d = new ConcreteDecorator1();
        ProjectManager m = new ProjectManager(d);
        Parlour decorate = m.decorate();
        decorate.show();
    }

    //要构建的复杂产品：客厅
    static class Parlour{
        private String wall;
        private String TV;
        private String sofa;

        public void setWall(String wall){
            this.wall=wall;
        }

        public void setTV(String TV){
            this.TV=TV;
        }

        public void setSofa(String sofa){
            this.sofa=sofa;
        }

        public void show(){
            System.out.println("客厅构建完成显示"+" 墙："+wall+"  电视："+TV+"   沙发："+sofa);
        }
    }

    //抽象建造者:装修工人
    static abstract class Decorator{
        //创建产品对象
        protected Parlour parlour = new Parlour();

        public abstract void buildWall();
        public abstract void buildTV();
        public abstract void buildSofa();

        //返回产品对象
        public Parlour getResult(){
            return parlour;
        }
    }

    //具体建造者：具体装修工人1
    static class ConcreteDecorator1 extends Decorator{

        @Override
        public void buildWall() {
            parlour.setWall("白色的墙");
        }

        @Override
        public void buildTV() {
            parlour.setTV("小米智能电视");
        }

        @Override
        public void buildSofa() {
            parlour.setSofa("真皮沙发");
        }
    }

    //具体建造者：具体装修工人2
    static class ConcreteDecorator2 extends Decorator{

        @Override
        public void buildWall() {
            parlour.setWall("金色壁纸墙");
        }

        @Override
        public void buildTV() {
            parlour.setWall("TCL王牌电视");
        }

        @Override
        public void buildSofa() {
            parlour.setWall("欧式沙发");
        }
    }

    //指挥者：项目经理
    static class ProjectManager{
        private Decorator builder;

        public ProjectManager(Decorator builder){
            this.builder=builder;
        }

        //产品构建与组装方法
        public Parlour decorate(){
            builder.buildWall();
            builder.buildTV();
            builder.buildSofa();
            return builder.getResult();
        }
    }





}
