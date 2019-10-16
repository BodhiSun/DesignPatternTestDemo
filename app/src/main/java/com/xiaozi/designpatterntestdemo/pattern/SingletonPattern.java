package com.xiaozi.designpatterntestdemo.pattern;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/10/16 11:32
 * desc :单例模式-创建型模式
 *
 * 背景：
 * 在有些系统中，为了节省内存资源、保证数据内容的一致性，对某些类要求只能创建一个实例，这就是所谓的单例模式。
 *
 * 单例模式的定义与特点
 * 单例模式的定义：指一个类只有一个实例，且该类能自行创建这个实例的一种模式。
 * 单例模式有 3 个特点：
 * 1.单例类只有一个实例对象；
 * 2.该单例对象必须由单例类自行创建；
 * 3.单例类对外提供一个访问该单例的全局访问点；
 *
 * 单例模式的结构与实现
 * 通常，普通类的构造函数是公有的，外部类可以通过“new 构造函数()”来生成多个实例。但是，如果将类的构造函
 * 数设为私有的，外部类就无法调用该构造函数，也就无法生成多个实例。这时该类自身必须定义一个静态私有实例，
 * 并向外提供一个静态的公有函数用于创建或获取该静态私有实例。
 * 1.单例模式的结构
 * 单例模式的主要角色如下:
 * 1).单例类：包含一个实例且能自行创建这个实例的类。
 * 2).访问类：使用单例的类。
 * 2.单例模式的实现
 * Singleton模式通常有两种实现形式:
 * 1).懒汉式单例:该模式的特点是类加载时没有生成单例，只有当第一次调用 getlnstance 方法时才去创建这个单例。
 * 注意：如果编写的是多线程程序，则要代码中加关键字 volatile 和 synchronized，否则将存在线程非安全的问题。加
 * 上这两个关键字就能保证线程安全，但是每次访问时都要同步，会影响性能，且消耗更多的资源，这是懒汉式单例的缺点。
 * 2).饿汉式单例:该模式的特点是类一旦加载就创建一个单例，保证在调用 getInstance 方法之前单例已经存在了。
 * 注意:饿汉式单例在类创建的同时就已经创建好一个静态的对象供系统使用，以后不再改变，所以是线程安全的，可以
 * 直接用于多线程而不会出现问题。
 *
 * 单例模式的应用场景
 * 前面分析了单例模式的结构与特点，以下是它通常适用的场景的特点：
 * 1.在应用场景中，某类只要求生成一个对象的时候，如一个班中的班长、每个人的身份证号等。
 * 2.当对象需要被共享的场合。由于单例模式只允许创建一个对象，共享该对象可以节省内存，并加快对象访问速度。
 * 3.当某类需要频繁实例化，而创建的对象又频繁被销毁的时候，如多线程的线程池、网络连接池等。
 *
 * 单例模式的应用实例:
 * 【例1】别分用懒汉式和饿汉式单例模式模拟产生美国当今总统对象。
 * 分析：在每一届任期内，美国的总统只有一人，所以本实例适合用单例模式实现
 *
 * 单例模式的扩展
 * 单例模式可扩展为有限的多例(Multitcm)模式，这种模式可生成有限个实例并保存在ArmyList中,客户需要时可随机获取.
 * 【例2】用有限的多例模式模拟产生美国部长对象。
 *
 *
 */
public class SingletonPattern {

    public static void testSingleton(){
        PresidentLazy presidentLazy1 = PresidentLazy.getInstance();
        presidentLazy1.getName();
        PresidentLazy presidentLazy2 = PresidentLazy.getInstance();
        presidentLazy2.getName();
        if(presidentLazy1.equals(presidentLazy2)){
            System.out.println("他们是同一人！");
        }else{
            System.out.println("他们不是同一人！");
        }
    }

    public static void testMultiton(){
        MinisterMultiton multiton1 = MinisterMultiton.getRandomInstance();
        multiton1.getNum();
        MinisterMultiton multiton2 = MinisterMultiton.getRandomInstance();
        multiton2.getNum();
        MinisterMultiton multiton3 = MinisterMultiton.getRandomInstance();
        multiton3.getNum();
    }

    //懒汉式单例模式总统对象
    static class PresidentLazy{
        private static volatile PresidentLazy instance=null;//保证instance在多线程中同步

        private PresidentLazy(){
            System.out.println("产生一个总统！");
        }

        //在getInstance方法上加关键字 保证instance在多线程中同步
        public static synchronized PresidentLazy getInstance(){
            if(instance==null){
                instance=new PresidentLazy();
            }else{
                System.out.println("已经有一个总统，不能产生新总统！");
            }
            return instance;
        }

        public void getName(){
            System.out.println("我是美国总统：Trump!");
        }

    }

    //饿汉式单例模式总统对象
    static class PresidentHungry{
        private static PresidentHungry instance = new PresidentHungry();

        private PresidentHungry(){
            System.out.println("产生一个总统！");
        }

        public static PresidentHungry getInstance(){
            return instance;
        }

        public void getName(){
            System.out.println("我是美国总统：Trump!");
        }
    }

    //有限的多例模式部长对象
    static class MinisterMultiton{
        private static final int TOTAL_NUM=3;
        private static List<MinisterMultiton> list;
        private int num;

        static {
            list = new ArrayList<>();
            for (int i = 0; i < TOTAL_NUM; i++) {
                list.add(new MinisterMultiton(i));
            }
        }

        private MinisterMultiton(){};

        private MinisterMultiton(int i){
            System.out.println("产生一个部长实例 编号:"+i);
            this.num=i;
        }

        public static MinisterMultiton getRandomInstance(){
            int index=(int)(Math.random()*TOTAL_NUM);
            return list.get(index);
        }

        public void getNum(){
            System.out.println("我是美国部长 编号:"+num);
        }

    }



}
