package com.xiaozi.designpatterntestdemo.pattern;

/**
 * @author : Sun
 * @version : 1.0
 * @time : 2019/10/29
 * desc :策略模式-行为型模式
 *
 * 背景：
 * 在现实生活中常常遇到实现某种目标存在多种策略可供选择的情况，例如，出行旅游可以乘坐飞机、乘坐火车、骑自行
 * 车或自己开私家车等，超市促销可以釆用打折、送商品、送积分等方法。
 * 在软件开发中也常常遇到类似的情况，当实现某一个功能存在多种算法或者策略，我们可以根据环境或者条件的不同选
 * 择不同的算法或者策略来完成该功能，如数据排序策略有冒泡排序、选择排序、插入排序、二叉树排序等。
 * 如果使用多重条件语句实现（即硬编码），不但使条件语句变得很复杂，而且增加、删除或更换算法要修改原代码，
 * 不易维护，违背开闭原则。如果采用策略模式就能很好解决该问题。
 *
 * 策略模式的定义与特点
 * 策略（Strategy）模式的定义：
 * 该模式定义了一系列算法，并将每个算法封装起来，使它们可以相互替换，且算法的变化不会影响使用算法的客户。策
 * 略模式属于对象行为模式，它通过对算法进行封装，把使用算法的责任和算法的实现分割开来，并委派给不同的对象对
 * 这些算法进行管理。
 * 策略模式的主要优点如下：
 * 1.多重条件语句不易维护，而使用策略模式可以避免使用多重条件语句。
 * 2.策略模式提供了一系列的可供重用的算法族，恰当使用继承可以把算法族的公共代码转移到父类里面，从而避免重复的代码。
 * 3.策略模式可以提供相同行为的不同实现，客户可以根据不同时间或空间要求选择不同的。
 * 4.策略模式提供了对开闭原则的完美支持，可以在不修改原代码的情况下，灵活增加新算法。
 * 5.策略模式把算法的使用放到环境类中，而算法的实现移到具体策略类中，实现了二者的分离。
 * 其主要缺点如下：
 * 1.客户端必须理解所有策略算法的区别，以便适时选择恰当的算法类。
 * 2.策略模式造成很多的策略类。
 *
 * 策略模式的结构与实现
 * 策略模式的主要角色如下：
 * 1.抽象策略（Strategy）类：定义了一个公共接口，各种不同的算法以不同的方式实现这个接口，环境角色使用这个接
 * 口调用不同的算法，一般使用接口或抽象类实现。
 * 2.具体策略（Concrete Strategy）类：实现了抽象策略定义的接口，提供具体的算法实现。
 * 3.环境（Context）类：持有一个策略类的引用，最终给客户端调用。
 *
 * 策略模式的应用场景
 * 在程序设计中，通常在以下几种情况中使用策略模式较多：
 * 1.一个系统需要动态地在几种算法中选择一种时，可将每个算法封装到策略类中。
 * 2.一个类定义了多种行为，并且这些行为在这个类的操作中以多个条件语句的形式出现，可将每个条件分支移入它们各
 * 自的策略类中以代替这些条件语句。
 * 3.系统中各算法彼此完全独立，且要求对客户隐藏具体算法的实现细节时。
 * 4.系统要求使用算法的客户不应该知道其操作的数据时，可使用策略模式来隐藏与算法相关的数据结构。
 * 5.多个类只区别在表现行为不同，可以使用策略模式，在运行时动态选择具体要执行的行为。
 *
 * 策略模式的应用实例
 * 【例1】策略模式在“大闸蟹”做菜中的应用。
 * 分析：关于大闸蟹的做法有很多种，我们以清蒸大闸蟹和红烧大闸蟹两种方法为例，介绍策略模式的应用。
 * 首先，定义一个大闸蟹加工的抽象策略类（CrabCooking），里面包含了一个做菜的抽象方法 CookingMethod()；然后，
 * 定义清蒸大闸蟹（SteamedCrabs）和红烧大闸蟹（BraisedCrabs）的具体策略类，它们实现了抽象策略类中的抽象方法；
 * 最后，定义一个厨房（Kitchen）环境类，它具有设置和选择做菜策略的方法；客户类通过厨房类获取做菜策略。
 *
 * 策略模式的扩展
 * 在一个使用策略模式的系统中，当存在的策略很多时，客户端管理所有策略算法将变得很复杂，如果在环境类中使用策
 * 略工厂模式来管理这些策略类将大大减少客户端的工作复杂度。
 *
 */
public class StrategyPattern {

    public static void testStrategyPattern(){
        CrabCooking strategy = new SteameCrabs();
        Kitchen kitchen = new Kitchen();
        kitchen.setStrategy(strategy);
        kitchen.cookingMethod();
        strategy = new BraisedCrabs();
        kitchen.setStrategy(strategy);
        kitchen.cookingMethod();

    }


    //抽象策略类：大闸蟹加工类
    interface CrabCooking{
        void cookingMethod();//做菜方法
    }

    //具体策略类：清蒸大闸蟹
    static class SteameCrabs implements CrabCooking{

        @Override
        public void cookingMethod() {
            System.out.println("做法1：清蒸大闸蟹");
        }
    }

    //具体策略类：红烧大闸蟹
    static class BraisedCrabs implements CrabCooking{

        @Override
        public void cookingMethod() {
            System.out.println("做法2：红烧大闸蟹");
        }
    }

    //环境类：厨房
    static class Kitchen{
        private CrabCooking strategy;//抽象策略

        public CrabCooking getStrategy() {
            return strategy;
        }

        public void setStrategy(CrabCooking strategy) {
            this.strategy = strategy;
        }

        public void cookingMethod(){
            strategy.cookingMethod();//做菜
        }
    }

}
