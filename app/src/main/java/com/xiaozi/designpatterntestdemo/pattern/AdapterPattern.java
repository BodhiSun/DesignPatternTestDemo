package com.xiaozi.designpatterntestdemo.pattern;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/10/17 14:57
 * desc :适配器模式-结构型模式
 *
 * 背景：
 * 在现实生活中，经常出现两个对象因接口不兼容而不能在一起工作的实例，这时需要第三者进行适配。例如，讲中文
 * 的人同讲英文的人对话时需要一个翻译，用直流电的笔记本电脑接交流电源时需要一个电源适配器等。
 * 在软件设计中也可能出现：需要开发的具有某种业务功能的组件在现有的组件库中已经存在，但它们与当前系统的接
 * 口规范不兼容，如果重新开发这些组件成本又很高，这时用适配器模式能很好地解决这些问题。
 *
 * 模式的定义与特点：
 * 适配器模式的定义如下：将一个类的接口转换成客户希望的另外一个接口，使得原本由于接口不兼容而不能一起工作
 * 的那些类能一起工作。适配器模式分为类结构型模式和对象结构型模式两种，前者类之间的耦合度比后者高，且要求
 * 程序员了解现有组件库中的相关组件的内部结构，所以应用相对较少些。
 * 该模式的主要优点如下：
 * 1.客户端通过适配器可以透明地调用目标接口。
 * 2.复用了现存的类，程序员不需要修改原有代码而重用现有的适配者类。
 * 3.将目标类和适配者类解耦，解决了目标类和适配者类接口不一致的问题。
 * 其缺点是：对类适配器来说，更换适配器的实现过程比较复杂。
 *
 * 模式的结构与实现
 * 类适配器模式可采用多重继承方式实现，但Java 不支持多继承，可以定义一个适配器类来实现当前系统的业务接口，
 * 同时又继承现有组件库中已经存在的组件。
 * 对象适配器模式可釆用将现有组件库中已经实现的组件引入适配器类中，该类同时实现当前系统的业务接口。
 * 模式的结构
 * 适配器模式包含以下主要角色：
 * 1.目标接口：当前系统业务所期待的接口，它可以是抽象类或接口。
 * 2.适配者类：它是被访问和适配的现存组件库中的组件接口。
 * 3.适配器类：它是一个转换器，通过继承或引用适配者的对象，把适配者接口转换成目标接口，让客户按目标接口的
 * 格式访问适配者。
 *
 * 模式的应用场景
 * 适配器模式通常适用于以下场景：
 * 1.以前开发的系统存在满足新系统功能需求的类，但其接口同新系统的接口不一致。
 * 2.使用第三方提供的组件，但组件接口定义和自己要求的接口定义不同。
 *
 * 模式的应用实例
 * 【例1】用适配器模式（Adapter）模拟新能源汽车的发动机。
 * 分析：新能源汽车的发动机有电能发动机，和光能发动机等，各种发动机的驱动方法不同，电能发动机的驱动方法
 * electricDrive() 是用电能驱动，而光能发动机的驱动方法 opticalDrive() 是用光能驱动，它们是适配器模式中
 * 被访问的适配者。客户端希望用统一的发动机驱动方法 drive() 访问这两种发动机，所以必须定义一个统一的目标
 * 接口Motor，然后再定义电能适配器（Electric Adapter）和光能适配器（Optical Adapter）去适配这两种发动机。
 *
 * 模式的扩展
 * 适配器模式可扩展为双向适配器模式，双向适配器类既可以把适配者接口转换成目标接口，也可以把目标接口转换成
 * 适配者接口。
 *
 *
 */
public class AdapterPattern {

    public static void testAdapterPattern(){
        System.out.println("适配器模式测试：");
        Motor motor = new ElectricAdapter();
        motor.drive();

        Motor motor2 =new OpticalAdapter();
        motor2.drive();
    }


    public static void testTwoWayAdapter(){
        System.out.println("目标通过双向适配器访问适配者：");
        TwoWayAdapter adaptee = new AdapterRealize();
        TwoWayTarget  target = new TWAdapter(adaptee);
        target.request();
        System.out.println("适配者通过双向适配器访问目标：");
        target=new TargetRealize();
        adaptee = new TWAdapter(target);
        adaptee.specificRequest();
    }

    //目标：发动机
    interface Motor{
        void drive();
    }

    //适配者1：电能发动机
    static class ElectricMotor{
        public void electricDrive(){
            System.out.println("电能发动机驱动汽车！");
        }
    }

    //适配者2：光能发动机
    static class OpticalMotor{
        public void opticalDrive(){
            System.out.println("光能发动机驱动汽车！");
        }
    }

    //电能适配器，类适配器模式
    static class ElectricAdapter extends ElectricMotor implements Motor{

        @Override
        public void drive() {
            electricDrive();
        }
    }

    //光能适配器，对象适配器模式
    static class OpticalAdapter implements Motor{

        private OpticalMotor opticalMotor;

        public OpticalAdapter(){
            opticalMotor = new OpticalMotor();
        }

        @Override
        public void drive() {
            opticalMotor.opticalDrive();
        }
    }

    //--------------------------------我是华丽的分割线-------------------------------------------

    //目标接口
    interface TwoWayTarget{
        void request();
    }

    //适配者接口
    interface  TwoWayAdapter{
        void specificRequest();
    }

    //目标实现
    static class TargetRealize implements TwoWayTarget{

        @Override
        public void request() {
            System.out.println("目标代码被调用！");
        }
    }

    //适配者实现
    static class AdapterRealize implements TwoWayAdapter{

        @Override
        public void specificRequest() {
            System.out.println("适配者代码被调用！");
        }
    }

    //双向适配器
    static class TWAdapter implements TwoWayAdapter,TwoWayTarget {
        private TwoWayTarget target;
        private TwoWayAdapter adapter;

        public TWAdapter(TwoWayTarget target)
        {
            this.target=target;
        }
        public TWAdapter(TwoWayAdapter adapter)
        {
            this.adapter=adapter;
        }


        @Override
        public void request() {
            adapter.specificRequest();
        }

        @Override
        public void specificRequest() {
            target.request();
        }
    }





}
