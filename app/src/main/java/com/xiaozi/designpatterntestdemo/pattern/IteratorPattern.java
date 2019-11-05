package com.xiaozi.designpatterntestdemo.pattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Sun
 * @version : 1.0
 * @time : 2019/11/5
 * desc :迭代器模式-行为型模式
 *
 * 背景：
 * 在现实生活以及程序设计中，经常要访问一个聚合对象中的各个元素，如“数据结构”中的链表遍历，通常的做法是将
 * 链表的创建和遍历都放在同一个类中，但这种方式不利于程序的扩展，如果要更换遍历方法就必须修改程序源代码，这
 * 违背了 “开闭原则”。
 * 既然将遍历方法封装在聚合类中不可取，那么聚合类中不提供遍历方法，将遍历方法由用户自己实现同样是不可取，因
 * 为这种方式会存在两个缺点：
 * 1.暴露了聚合类的内部表示，使其数据不安全；
 * 2.增加了客户的负担。
 * “迭代器模式”能较好地克服以上缺点，它在客户访问类与聚合类之间插入一个迭代器，这分离了聚合对象与其遍历行
 * 为，对客户也隐藏了其内部细节，且满足“单一职责原则”和“开闭原则”。
 *
 * 模式的定义与特点
 * 迭代器（Iterator）模式的定义：
 * 提供一个对象来顺序访问聚合对象中的一系列数据，而不暴露聚合对象的内部表示。迭代器模式是一种对象行为型模式，
 * 其主要优点如下：
 * 1.访问一个聚合对象的内容而无须暴露它的内部表示。
 * 2.遍历任务交由迭代器完成，这简化了聚合类。
 * 3.它支持以不同方式遍历一个聚合，甚至可以自定义迭代器的子类以支持新的遍历。
 * 4.增加新的聚合类和迭代器类都很方便，无须修改原有代码。
 * 5.封装性良好，为遍历不同的聚合结构提供一个统一的接口。
 * 其主要缺点是：增加了类的个数，这在一定程度上增加了系统的复杂性。
 *
 * 模式的结构与实现
 * 迭代器模式是通过将聚合对象的遍历行为分离出来，抽象成迭代器类来实现的，其目的是在不暴露聚合对象的内部结构
 * 的情况下，让外部代码透明地访问聚合的内部数据。
 * 迭代器模式主要包含以下角色：
 * 1.抽象聚合（Aggregate）角色：定义存储、添加、删除聚合对象以及创建迭代器对象的接口。
 * 2.具体聚合（ConcreteAggregate）角色：实现抽象聚合类，返回一个具体迭代器的实例。
 * 3.抽象迭代器（Iterator）角色：定义访问和遍历聚合元素的接口，通常包含 hasNext()、first()、next() 等方法。
 * 4.具体迭代器（Concretelterator）角色：实现抽象迭代器接口中所定义的方法，完成对聚合对象的遍历，记录遍历的当前位置。
 *
 * 模式的应用场景
 * 迭代器模式通常在以下几种情况使用:
 * 1.当需要为聚合对象提供多种遍历方式时。
 * 2.当需要为遍历不同的聚合结构提供一个统一的接口时。
 * 3.当访问一个聚合对象的内容而无须暴露其内部细节的表示时。
 * 由于聚合与迭代器的关系非常密切，所以大多数语言在实现聚合类时都提供了迭代器类，因此大数情况下使用语言中已
 * 有的聚合类的迭代器就已经够了。
 *
 * 模式的扩展
 * 迭代器模式常常与组合模式结合起来使用，在对组合模式中的容器构件进行访问时，经常将迭代器潜藏在组合模式的容
 * 器构成类中。当然，也可以构造一个外部迭代器来对容器构件进行访问。
 *
 *
 */
public class IteratorPattern {

    //迭代器模式实现
    public static void testIteratorPattern(){
        Aggregate ag = new ConcreteAggregate();
        ag.add("中山大学");
        ag.add("华南理工");
        ag.add("韶关学院");
        System.out.print("聚合的内容有：");
        Iterator iterator = ag.getIterator();
        while (iterator.hasNext()) {
            Object next = iterator.next();
            System.out.print(next.toString()+"\t");
        }
        Object first = iterator.first();
        System.out.println("\nFirst："+first.toString());

    }

    //抽象聚合
    interface Aggregate{
        void add(Object obj);
        void remove(Object obj);
        Iterator getIterator();
    }

    //具体聚合
    static class ConcreteAggregate implements Aggregate{
        private List<Object> list = new ArrayList<>();

        @Override
        public void add(Object obj) {
            list.add(obj);
        }

        @Override
        public void remove(Object obj) {
            list.remove(obj);
        }

        @Override
        public Iterator getIterator() {
            return new ConcreteIterator(list);
        }
    }

    //抽象迭代器
    interface Iterator {
        Object first();
        Object next();
        boolean hasNext();
    }

    //具体迭代器
    static class ConcreteIterator implements Iterator{
        private List<Object> list =null;
        private int index = -1;
        public ConcreteIterator(List<Object> list){
            this.list=list;
        }

        @Override
        public Object first() {
            index=0;
            return list.get(index);
        }

        @Override
        public Object next() {
            Object obj = null;
            if(this.hasNext()){
                obj = list.get(++index);
            }
            return obj;
        }

        @Override
        public boolean hasNext() {
            if(index<list.size()-1){
                return true;
            }else{
                return false;
            }
        }
    }




}
