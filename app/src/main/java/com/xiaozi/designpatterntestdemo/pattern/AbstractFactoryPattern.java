package com.xiaozi.designpatterntestdemo.pattern;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/10/16 16:34
 * desc :抽象工厂模式-创建型模式
 *
 * 背景：
 * 工厂方法模式中考虑的是一类产品的生产，如畜牧场只养动物、电视机厂只生产电视机等。同种类称为同等级，也就
 * 是说工厂方法模式只考虑生产同等级的产品，但是在现实生活中许多工厂是综合型的工厂，能生产多等级（种类）的
 * 产品，如农场里既养动物又种植物，电器厂既生产电视机又生产洗衣机或空调等。抽象工厂模式将考虑多等级产品的
 * 生产，将同一个具体工厂所生产的位于不同等级的一组产品称为一个产品族。
 *
 * 模式的定义与特点
 * 抽象工厂模式的定义：
 * 是一种为访问类提供一个创建一组相关或相互依赖对象的接口，且访问类无须指定所要产品的具体类就能得到同族的
 * 不同等级的产品的模式结构。
 * 抽象工厂模式是工厂方法模式的升级版本，工厂方法模式只生产一个等级的产品，而抽象工厂模式可生产多个等级的产品。
 * 使用抽象工厂模式一般要满足以下条件：
 * 1.系统中有多个产品族，每个具体工厂创建同一族但属于不同等级结构的产品。
 * 2.系统一次只可能消费其中某一族产品，即同族的产品一起使用。
 * 抽象工厂模式除了具有工厂方法模式的优点外，其他主要优点如下：
 * 1.可以在类的内部对产品族中相关联的多等级产品共同管理，而不必专门引入多个新的类来进行管理。
 * 2.当增加一个新的产品族时不需要修改原代码，满足开闭原则。
 * 其缺点是：当产品族中需要增加一个新的产品时，所有的工厂类都需要进行修改。
 *
 * 模式的结构与实现
 * 抽象工厂模式同工厂方法模式一样，也是由抽象工厂、具体工厂、抽象产品和具体产品等 4 个要素构成，但抽象工
 * 厂中方法个数不同，抽象产品的个数也不同。
 * 模式的结构：
 * 1.抽象工厂：提供了创建产品的接口，它包含多个创建产品的方法 newProduct()，可以创建多个不同等级的产品。
 * 2.具体工厂：主要是实现抽象工厂中的多个抽象方法，完成具体产品的创建。
 * 3.抽象产品：定义了产品的规范，描述了产品的主要特性和功能，抽象工厂模式有多个抽象产品。
 * 4.具体产品：实现了抽象产品角色所定义的接口，由具体工厂来创建，它 同具体工厂之间是多对一的关系。
 * 模式的实现
 * 抽象工厂模式的结构同工厂方法模式的结构相似，不同的是其产品的种类不止一个，所以创建产品的方法也不止一个。
 *
 * 模式的应用场景
 * 抽象工厂模式最早的应用是用于创建属于不同操作系统的视窗构件。如java的AWT中的Button和Text等构件在Windows
 * 和UNIX中的本地实现是不同的。
 * 抽象工厂模式通常适用于以下场景：
 * 1.当需要创建的对象是一系列相互关联或相互依赖的产品族时，如电器工厂中的电视机、洗衣机、空调等。
 * 2.系统中有多个产品族，但每次只使用其中的某一族产品。如有人只喜欢穿某一个品牌的衣服和鞋。
 * 3.系统中提供了产品的类库，且所有产品的接口相同，客户端不依赖产品实例的创建细节和内部结构。
 *
 * 模式的应用实例
 * 【例1】用抽象工厂模式设计农场类。
 * 分析：农场中除了像畜牧场一样可以养动物，还可以培养植物，如养马、养牛、种菜、种水果等，所以本实例比工厂
 * 方法模式中设计的畜牧场类复杂，必须用抽象工厂模式来实现。
 * 思路：用抽象工厂模式来设计两个农场，一个是朝阳农场用于养牛和种菜，一个是海淀农场用于养马和种水果，可以
 * 在以上两个农场中定义一个生成动物的方法 newAnimal() 和一个培养植物的方法 newPlant()。对马类、牛类、蔬
 * 菜类和水果类等具体产品类，定义一个 show() 方法来显示它们。
 *
 * 模式的扩展
 * 抽象工厂模式的扩展有一定的“开闭原则”倾斜性：
 * 1.当增加一个新的产品族时只需增加一个新的具体工厂，不需要修改原代码，满足开闭原则。
 * 2.当产品族中需要增加一个新种类的产品时，则所有的工厂类都需要进行修改，不满足开闭原则。
 *
 */
public class AbstractFactoryPattern {

    public static void testAbstractFactory(){
        Farm farm = new HDFarm();
        Animal animal = farm.newAnimal();
        Plant plant = farm.newPlant();
        animal.show();
        plant.show();
    }

    //抽象产品：动物类
    interface Animal{
        void show();
    }

    //具体产品：马类
    static class Horse implements Animal{

        @Override
        public void show() {
            System.out.println("具体产品马显示...");
        }
    }

    //具体产品：牛类
    static class Cattle implements Animal{

        @Override
        public void show() {
            System.out.println("具体产品牛显示...");
        }
    }

    //抽象产品：植物类
    interface Plant{
        void show();
    }

    //具体产品：水果类
    static class Fruitage implements Plant{

        @Override
        public void show() {
            System.out.println("具体产品水果显示...");
        }
    }

    //具体产品：蔬菜类
    static class Vegetables implements Plant{

        @Override
        public void show() {
            System.out.println("具体产品蔬菜显示...");
        }
    }

    //抽象工厂：农场类
    interface Farm{
        Animal newAnimal();
        Plant newPlant();
    }

    //具体工厂：朝阳农场类
    static class CYFarm implements Farm{

        @Override
        public Animal newAnimal() {
            System.out.println("新牛出生！");
            return new Cattle();
        }

        @Override
        public Plant newPlant() {
            System.out.println("蔬菜长成！");
            return new Vegetables();
        }
    }

    //具体工厂：海淀农场类
    static class HDFarm implements Farm{

        @Override
        public Animal newAnimal() {
            System.out.println("新马出生！");
            return new Horse();
        }

        @Override
        public Plant newPlant() {
            System.out.println("水果长成！");
            return new Fruitage();
        }
    }








}
