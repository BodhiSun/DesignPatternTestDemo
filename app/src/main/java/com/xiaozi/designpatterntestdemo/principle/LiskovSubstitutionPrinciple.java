package com.xiaozi.designpatterntestdemo.principle;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/10/15 12:49
 * desc :里氏替换原则(LSP)——面向对象设计原则
 *
 * 1.里氏替换原则的定义
 * 里氏替换原则由里斯科夫（Liskov）女士提出：
 * 继承必须确保超类所拥有的性质在子类中仍然成立（Inheritance should ensure that any property proved
 * about supertype objects also holds for subtype objects）。
 * 里氏替换原则主要阐述了有关继承的一些原则，也就是什么时候应该使用继承，什么时候不应该使用继承，以及其中蕴含的原理。
 * 里氏替换原则是继承复用的基础，它反映了基类与子类之间的关系，是对开闭原则的补充，是对实现抽象化的具体步骤的规范。
 *
 * 2.里氏替换原则的作用
 * 里氏替换原则的主要作用如下:
 * 1).里氏替换原则是实现开闭原则的重要方式之一。
 * 2).它克服了继承中重写父类造成的可复用性变差的缺点。
 * 3).它是动作正确性的保证。即类的扩展不会给已有的系统引入新的错误，降低了代码出错的可能性。
 *
 * 3.里氏替换原则的实现方法
 * 里氏替换原则通俗来讲就是：子类可以扩展父类的功能，但不能改变父类原有的功能。也就是说：子类继承父类时，
 * 除添加新的方法完成新增功能外，尽量不要重写父类的方法。
 * 如果通过重写父类的方法来完成新的功能，这样写起来虽然简单，但是整个继承体系的可复用性会比较差，特别是
 * 运用多态比较频繁时，程序运行出错的概率会非常大。
 * 如果程序违背了里氏替换原则，则继承类的对象在基类出现的地方会出现运行错误。这时其修正方法是：取消原来
 * 的继承关系，重新设计它们之间的关系。
 *
 * 【例1】“正方形不是长方形”
 * 当然，生活中也有很多类似的例子，例如，企鹅、鸵鸟和几维鸟从生物学的角度来划分，它们属于鸟类；但从类的
 * 继承关系来看，由于它们不能继承“鸟”会飞的功能，所以它们不能定义成“鸟”的子类。“玩具炮”炸不了敌人，
 * 所以不能定义成“炮”的子类等。
 *
 * 【例2】里氏替换原则在“几维鸟不是鸟”实例中的应用。
 * 分析：鸟一般都会飞行，如燕子的飞行速度大概是每小时 120 千米。但是新西兰的几维鸟由于翅膀退化无法飞行。
 * 假如要设计一个实例，计算这两种鸟飞行 300 千米要花费的时间。显然，拿燕子来测试这段代码，结果正确，能计
 * 算出所需要的时间；但拿几维鸟来测试，结果会发生“除零异常”或是“无穷大”，明显不符合预期。
 *
 * 程序运行错误的原因是：几维鸟类重写了鸟类的 setSpeed(double speed) 方法，这违背了里氏替换原则。正确的
 * 做法是：取消几维鸟原来的继承关系，定义鸟和几维鸟的更一般的父类，如动物类，它们都有奔跑的能力。几维鸟的
 * 行速度虽然为 0，但奔跑速度不为 0，可以计算出其奔跑 300 千米所要花费的时间。
 *
 */
public class LiskovSubstitutionPrinciple {

    public static void testLSP(){
        Bird  swallow = new Swallow();
        Bird  brownKiwi = new BrownKiwi();
        swallow.setSpeed(120);
        brownKiwi.setSpeed(120);
        System.out.println("如果飞行300公里：");
        try {
            System.out.println("燕子将飞行"+swallow.getFlyTime(300)+"小时.");
            System.out.println("几维鸟将飞行"+brownKiwi.getFlyTime(300)+"小时.");
        }catch (Exception e){
            System.out.println("发生错误了!");
        }

        Animal kiwi = new Kiwi();
        kiwi.setRunSpeed(30);
        System.out.println("重新设计程序的继承关系后，如果行进300公里：");
        try {
            System.out.println("燕子将飞"+swallow.getFlyTime(300)+"小时.");
            System.out.println("几维鸟将跑"+kiwi.getRunTime(300)+"小时.");
        }catch (Exception e){
            System.out.println("发生错误了!");
        }
    }

    //鸟类
    static class Bird extends Animal{
        double flySpeed;
        public void setSpeed(double speed){
            flySpeed=speed;
        }
        public double getFlyTime(double distance){
            return (distance/flySpeed);
        }
    }

    //燕子类
    static class Swallow extends Bird{};

    //几维鸟
    static class BrownKiwi extends Bird{
        @Override
        public void setSpeed(double speed) {
            flySpeed=0;
        }
    }

    //定义鸟和几维鸟的更一般的父类-动物类
    static class Animal{
        double runSpeed;

        public void setRunSpeed(double speed){
            runSpeed=speed;
        }

        public double getRunTime(double distance){
            return (distance/runSpeed);
        }
    }

    //新的几维鸟类
    static class Kiwi extends Animal{}


}
