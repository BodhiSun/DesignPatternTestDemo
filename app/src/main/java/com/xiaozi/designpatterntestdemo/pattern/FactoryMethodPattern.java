package com.xiaozi.designpatterntestdemo.pattern;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/10/16 16:33
 * desc :工厂方法模式-创建型模式
 *
 * 背景：
 * 在软件开发中能否做到软件对象的生产和使用相分离呢？能否在满足“开闭原则”的前提下，客户随意增删或改变对
 * 软件相关对象的使用呢？
 *
 * 模式的定义与特点
 * 工厂方法模式的定义：
 * 定义一个创建产品对象的工厂接口，将产品对象的实际创建工作推迟到具体子工厂类当中。这满足创建型模式中所要
 * 求的“创建与使用相分离”的特点。我们把被创建的对象称为“产品”，把创建产品的对象称为“工厂”。
 * 如果要创建的产品不多，只要一个工厂类就可以完成，这种模式叫“简单工厂模式”，它不属于 GoF 的 23 种经典
 * 设计模式，它的缺点是增加新产品时会违背“开闭原则”。“工厂方法模式”是对简单工厂模式的进一步抽象化，其
 * 好处是可以使系统在不修改原来代码的情况下引进新的产品，即满足开闭原则。
 * 工厂方法模式的主要优点有：
 * 1).用户只需要知道具体工厂的名称就可得到所要的产品，无须知道产品的具体创建过程；
 * 2).在系统增加新的产品时只需要添加具体产品类和对应的具体工厂类，无须对原工厂进行任何修改，满足开闭原则；
 * 其缺点是:每增加一个产品就要增加一个具体产品类和一个对应的具体工厂类，这增加了系统的复杂度。
 *
 * 模式的结构与实现
 * 工厂方法模式由抽象工厂、具体工厂、抽象产品和具体产品等4个要素构成。
 * 1.模式的结构
 * 1).抽象工厂:提供了创建产品的接口，调用者通过它访问具体工厂的工厂方法 newProduct() 来创建产品。
 * 2).具体工厂:主要是实现抽象工厂中的抽象方法，完成具体产品的创建。
 * 3).抽象产品:定义了产品的规范，描述了产品的主要特性和功能。
 * 4).具体产品:实现了抽象产品角色所定义的接口，由具体工厂来创建，它同具体工厂之间一一对应。
 *
 * 模式的应用场景
 * 工厂方法模式通常适用于以下场景:
 * 1).客户只知道创建产品的工厂名，而不知道具体的产品名。如 TCL 电视工厂、海信电视工厂等。
 * 2).创建对象的任务由多个具体子工厂中的某一个完成，而抽象工厂只提供创建产品的接口。
 * 3).客户不关心创建产品的细节，只关心产品的品牌。
 *
 * 模式的应用实例
 * 【例1】用工厂方法模式设计畜牧场。
 * 分析：有很多种类的畜牧场，如养马场用于养马，养牛场用于养牛，所以该实例用工厂方法模式比较适合。
 * 对养马场和养牛场等具体工厂类，只要定义一个生成动物的方法 newAnimal() 即可。由于要显示马类和牛类等具体
 * 产品类,所以定义一个 show() 方法来显示它们。
 *
 * 模式的扩展
 * 当需要生成的产品不多且不会增加，一个具体工厂类就可以完成任务时，可删除抽象工厂类。这时工厂方法模式将退化到简单工厂模式。
 *
 */
public class FactoryMethodPattern {

    public static void testFactoryMethodPattern(){
        System.out.println("新类名：HorseFarm");
        AnimalFarm farm = new HorseFarm();
        Animal animal = farm.newAnimal();
        animal.show();
    }

    public static void testSimpleFactoryPatter(){
        SimpleFactoryAnimalFarm simpleFactoryAnimalFarm = new SimpleFactoryAnimalFarm();
        Animal cattle = simpleFactoryAnimalFarm.newCattle();
        Animal horse = simpleFactoryAnimalFarm.newHorse();
        cattle.show();
    }

    //抽象产品：提供了产品的接口
    interface Animal{
        void show();
    }

    //具体产品马类：实现抽象产品中的抽象方法
    static class Horse implements Animal{

        @Override
        public void show() {
            System.out.println("具体产品马显示...");
        }
    }

    //具体产品牛类：实现抽象产品中的抽象方法
    static class Cattle implements Animal{
        @Override
        public void show() {
            System.out.println("具体产品牛显示...");
        }
    }

    //抽象工厂畜牧场：提供了产品的生成方法
    interface AnimalFarm{
        Animal newAnimal();
    }

    //具体工厂养马场：实现了产品的生成方法
    static class HorseFarm implements AnimalFarm{

        @Override
        public Animal newAnimal() {
            System.out.println("具体工厂养马场-->新马出生！...");
            return new Horse();
        }
    }

    //具体工厂养牛场：实现了产品的生成方法
    static class CattleFarm implements AnimalFarm{
        @Override
        public Animal newAnimal() {
            System.out.println("具体工厂养牛场-->新牛出生！...");
            return new Cattle();
        }
    }

    //--------------------------------我是华丽的分割线-------------------------------------------

    //简单工厂模式 畜牧场
    static class SimpleFactoryAnimalFarm{

        public Animal newHorse() {
            System.out.println("简单工厂模式-->新马出生！...");
            return new Horse();
        }

        public Animal newCattle() {
            System.out.println("简单工厂模式-->新牛出生！...");
            return new Cattle();
        }

    }



}
