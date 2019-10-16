package com.xiaozi.designpatterntestdemo.pattern;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/10/16 16:32
 * desc :原型模式-创建型模式
 *
 * 背景：
 * 在有些系统中，存在大量相同或相似对象的创建问题，如果用传统的构造函数来创建对象，会比较复杂且耗时耗资源，
 * 用原型模式生成对象就很高效。
 *
 * 原型模式的定义与特点
 * 原型模式的定义如下：
 * 用一个已经创建的实例作为原型，通过复制该原型对象来创建一个和原型相同或相似的新对象。在这里，原型实例指
 * 定了要创建的对象的种类。用这种方式创建对象非常高效，根本无须知道对象创建的细节。在生活中复制的例子非常多。
 *
 * 原型模式的结构与实现
 * 由于 Java 提供了对象的 clone() 方法，所以用 Java 实现原型模式很简单。
 * 1.模式的结构
 * 原型模式包含以下主要角色：
 * 1).抽象原型类：规定了具体原型对象必须实现的接口。
 * 2).具体原型类：实现抽象原型类的 clone() 方法，它是可被复制的对象。
 * 3).访问类：使用具体原型类中的 clone() 方法来复制新的对象。
 * 2.模式的实现
 * 原型模式的克隆分为浅克隆和深克隆，Java 中的 Object 类提供了浅克隆的 clone() 方法，具体原型类只要实现
 * Cloneable 接口就可实现对象的浅克隆，这里的 Cloneable 接口就是抽象原型类。
 *
 * 原型模式的应用场景
 * 原型模式通常适用于以下场景:
 * 1.对象之间相同或相似，即只是个别的几个属性不同的时候。
 * 2.对象的创建过程比较麻烦，但复制比较简单的时候。
 *
 * 原型模式的应用实例
 * 用原型模式除了可以生成相同的对象，还可以生成相似的对象。
 *【例1】用原型模式生成“三好学生”奖状。
 * 分析：同一学校的“三好学生”奖状除了获奖人姓名不同，其他都相同，属于相似对象的复制，同样可以用原型模式
 * 创建，然后再做简单修改就可以了。
 *
 * 原型模式的扩展
 * 原型模式可扩展为带原型管理器的原型模式，它在原型模式的基础上增加了一个原型管理器 PrototypeManager 类。
 * 该类用 HashMap 保存多个复制的原型，Client 类可以通过管理器的 get(String id) 方法从中获取复制的原型。
 *【例2】用带原型管理器的原型模式来生成包含“圆”和“正方形”等图形的原型，并计算其面积。
 * 分析：本实例中由于存在不同的图形类，例如，“圆”和“正方形”，它们计算面积的方法不一样，所以需要用一个
 * 原型管理器来管理它们。
 *
 */
public class PrototypePattern {

    public static void testPrototypePattern(){
        System.out.println("------原型模式生成相同对象------");
        Realizetype obj1 = new Realizetype();
        Realizetype obj2 = null;
        try {
            obj2 = (Realizetype) obj1.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        System.out.println("obj1==obj2?"+(obj1.equals(obj2)));

        System.out.println("------原型模式生成相似对象------");
        Award award1=new Award("祢豆子","同学：在2016学年第一学期中表现优秀，被评为三好学生。","设计模式大学");
        award1.display();
        Award award2=null;
        try {
            award2= (Award) award1.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        System.out.println("award1==award2?"+(obj1.equals(obj2)));
        award2.setName("宝儿姐");
        award2.display();
    }

    //原型模式生成相同对象的具体原型类
    static class Realizetype implements Cloneable{
        public Realizetype(){
            System.out.println("具体原型创建成功！");
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            System.out.println("具体原型复制成功！");
            return super.clone();
        }
    }

    //原型模式生成相似对象的具体原型类 奖状类
    static class Award implements Cloneable{
        private String name;
        private String info;
        private String college;

        public Award() {}

        public Award(String name, String info, String college) {
            this.name = name;
            this.info = info;
            this.college = college;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void display(){
            System.out.println(name+info+college);
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            System.out.println("奖状拷贝成功！");
            return super.clone();
        }
    }

    //--------------------------------我是华丽的分割线-------------------------------------------

    public static void testProtoTypeManager(){
        ProtoTypeManager ptm = new ProtoTypeManager();
        Shape circle = ptm.getShape("Circle");
        circle.countArea();
        Shape square = ptm.getShape("Square");
        square.countArea();
    }

    //原型模式扩展 图形接口
    interface Shape extends Cloneable{
        Object clone();//拷贝
        void countArea();//计算面积
    }

    //原型模式扩展 圆形
    static class Circle implements Shape{

        @Override
        public Object clone() {
            Circle w = null;
            try {
                w= (Circle) super.clone();
            } catch (CloneNotSupportedException e) {
                System.out.println("拷贝圆失败!");
                e.printStackTrace();
            }
            return w;
        }

        @Override
        public void countArea() {
            int r=3;
            System.out.print("这是一个圆，假如半径是3");
            System.out.println("该圆的面积="+3.1415*r*r+"\n");
        }
    }

    //原型模式扩展 正方形
    static class Square  implements Shape{

        @Override
        public Object clone() {
            Square b=null;
            try {
                b=(Square)super.clone();
            } catch (CloneNotSupportedException e) {
                System.out.println("拷贝正方形失败!");
                e.printStackTrace();
            }
            return b;
        }

        @Override
        public void countArea() {
            int a=3;
            System.out.print("这是一个正方形，假如变成是3");
            System.out.println("该正方形的面积="+a*a+"\n");
        }
    }

    //原型模式扩展 原型管理器
    static class ProtoTypeManager{
        private HashMap<String,Shape> hm = new HashMap<>();

        public ProtoTypeManager(){
            hm.put("Circle",new Circle());
            hm.put("Square",new Square());
        }

        public void addShape(String key,Shape shape){
            hm.put(key,shape);
        }

        public Shape getShape(String key){
            Shape shape = hm.get(key);
            return (Shape) shape.clone();
        }
    }






}
