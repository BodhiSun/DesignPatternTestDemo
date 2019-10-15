package com.xiaozi.designpatterntestdemo.principle;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/10/15 12:55
 * desc :合成复用原则(CRP)——面向对象设计原则
 *
 * 1.合成复用原则的定义
 * 合成复用原则又叫组合/聚合复用原则（Composition/Aggregate Reuse Principle，CARP）。它要求在软件复用时，
 * 要尽量先使用组合或者聚合等关联关系来实现，其次才考虑使用继承关系来实现。
 * 如果要使用继承关系，则必须严格遵循里氏替换原则。合成复用原则同里氏替换原则相辅相成的，两者都是开闭原则的具体实现规范。
 *
 * 2.合成复用原则的重要性
 * 通常类的复用分为继承复用和合成复用两种，继承复用虽然有简单和易实现的优点，但它也存在以下缺点：
 * 1).继承复用破坏了类的封装性。因为继承会将父类的实现细节暴露给子类，父类对子类是透明的，所以这种复用又称为“白箱”复用。
 * 2).子类与父类的耦合度高。父类的实现的任何改变都会导致子类的实现发生变化，这不利于类的扩展与维护。
 * 3).它限制了复用的灵活性。从父类继承而来的实现是静态的，在编译时已经定义，所以在运行时不可能发生变化。
 * 采用组合或聚合复用时，可以将已有对象纳入新对象中，使之成为新对象的一部分，新对象可以调用已有对象的功能，它有以下优点:
 * 1).它维持了类的封装性。因为成分对象的内部细节是新对象看不见的，所以这种复用又称为“黑箱”复用。
 * 2).新旧类之间的耦合度低。这种复用所需的依赖较少，新对象存取成分对象的唯一方法是通过成分对象的接口。
 * 3).复用的灵活性高。这种复用可以在运行时动态进行，新对象可以动态地引用与成分对象类型相同的对象。
 *
 * 3.合成复用原则的实现方法
 * 合成复用原则是通过将已有的对象纳入新对象中，作为新对象的成员对象来实现的，新对象可以调用已有对象的功能，从而达到复用。
 *
 * 【例1】合成复用原则在"汽车分类管理程序"中的应用。
 * 分析：汽车按“动力源”划分可分为汽油汽车、电动汽车等；按“颜色”划分可分为白色汽车、黑色汽车和红色汽车
 * 等。如果同时考虑这两种分类，其组合就很多。
 *
 * 如果按照继承关系实现，汽车：汽油汽车、电动汽车，然后汽油汽车：白色汽油汽车、黑色汽油汽车、红色汽油汽车，
 * 电动汽车：白色电动汽车、黑色电动汽车、红色电动汽车，会产生很多子类，而且增加新的“动力源”或者增加新的
 * “颜色”都要修改源代码，这违背了开闭原则，显然不可取。但如果改用组合关系实现就能很好地解决以上问题。
 *
 */
public class CompositeReusePrinciple {

    public static void testCRP(){
        GasolineCar gasolineCar = new GasolineCar(new White());
        ElectricCar electricCar = new ElectricCar(new Black());
        gasolineCar.move();
        electricCar.move();
    }

    //抽象颜色接口
    interface Color{
        String color();
    }

    //白颜色实现类
    static class White implements Color{

        @Override
        public String color() {
            return "白颜色";
        }
    }

    //黑颜色实现类
    static class Black implements Color{

        @Override
        public String color() {
            return "黑颜色";
        }
    }

    //红颜色实现类
    static class Red implements Color{

        @Override
        public String color() {
            return "红颜色";
        }
    }

    //车类
    static class Car{
        Color color;

        public Car(Color color){
            this.color=color;
        }

        public void move(){}
    }

    //汽油车
    static class GasolineCar extends Car{

        public GasolineCar(Color color) {
            super(color);
        }

        @Override
        public void move() {
            System.out.println("我是"+color.color()+"的汽油动力车,我开始移动了");
        }
    }

    //汽油车
    static class ElectricCar extends Car{

        public ElectricCar(Color color) {
            super(color);
        }

        @Override
        public void move() {
            System.out.println("我是"+color.color()+"的电动力车,我开始移动了");
        }
    }

}
