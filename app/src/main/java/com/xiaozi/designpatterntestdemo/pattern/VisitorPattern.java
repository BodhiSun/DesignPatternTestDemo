package com.xiaozi.designpatterntestdemo.pattern;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author : Sun
 * @version : 1.0
 * @time : 2019/11/5
 * desc :访问者模式-行为型模式
 *
 * 背景：
 * 在现实生活中，有些集合对象中存在多种不同的元素，且每种元素也存在多种不同的访问者和处理方式。例如，医院医
 * 生开的处方单中包含多种药元素，査看它的划价员和药房工作人员对它的处理方式也不同，划价员根据处方单上面的药
 * 品名和数量进行划价，药房工作人员根据处方单的内容进行抓药。还有顾客在商场购物时放在“购物车”中的商品，顾
 * 客主要关心所选商品的性价比，而收银员关心的是商品的价格和数量。
 * 这些被处理的数据元素相对稳定而访问方式多种多样的数据结构，如果用“访问者模式”来处理比较方便。访问者模式
 * 能把处理方法从数据结构中分离出来，并可以根据需要增加新的处理方法，且不用修改原来的程序代码与数据结构，这
 * 提高了程序的扩展性和灵活性。
 *
 * 模式的定义与特点
 * 访问者（Visitor）模式的定义：
 * 将作用于某种数据结构中的各元素的操作分离出来封装成独立的类，使其在不改变数据结构的前提下可以添加作用于这
 * 些元素的新的操作，为数据结构中的每个元素提供多种访问方式。它将对数据的操作与数据结构进行分离，是行为类模
 * 式中最复杂的一种模式。
 * 访问者（Visitor）模式是一种对象行为型模式，其主要优点如下：
 * 1.扩展性好。能够在不修改对象结构中的元素的情况下，为对象结构中的元素添加新的功能。
 * 2.复用性好。可以通过访问者来定义整个对象结构通用的功能，从而提高系统的复用程度。
 * 3.灵活性好。访问者模式将数据结构与作用于结构上的操作解耦，使得操作集合可相对自由地演化而不影响系统的数据结构。
 * 4.符合单一职责原则。访问者模式把相关的行为封装在一起，构成一个访问者，使每一个访问者的功能都比较单一。
 * 访问者（Visitor）模式的主要缺点如下：
 * 1.增加新的元素类很困难。在访问者模式中，每增加一个新的元素类，都要在每一个具体访问者类中增加相应的具体操作，这违背了“开闭原则”。
 * 2.破坏封装。访问者模式中具体元素对访问者公布细节，这破坏了对象的封装性。
 * 3.违反了依赖倒置原则。访问者模式依赖了具体类，而没有依赖抽象类。
 *
 * 模式的结构与实现
 * 访问者（Visitor）模式实现的关键是如何将作用于元素的操作分离出来封装成独立的类。
 * 访问者模式包含以下主要角色：
 * 1.抽象访问者（Visitor）角色：定义一个访问具体元素的接口，为每个具体元素类对应一个访问操作 visit() ，该
 * 操作中的参数类型标识了被访问的具体元素。
 * 2.具体访问者（ConcreteVisitor）角色：实现抽象访问者角色中声明的各个访问操作，确定访问者访问一个元素时该做什么。
 * 3.抽象元素（Element）角色：声明一个包含接受操作 accept() 的接口，被接受的访问者对象作为 accept() 方法的参数。
 * 4.具体元素（ConcreteElement）角色：实现抽象元素角色提供的 accept() 操作，其方法体通常都是 visitor.visit(this) ，
 * 另外具体元素中可能还包含本身业务逻辑的相关操作。
 * 5.对象结构（Object Structure）角色：是一个包含元素角色的容器，提供让访问者对象遍历容器中的所有元素的方法，
 * 通常由 List、Set、Map 等聚合类实现。
 *
 * 模式的应用场景
 * 通常在以下情况可以考虑使用访问者（Visitor）模式:
 * 1.对象结构相对稳定，但其操作算法经常变化的程序。
 * 2.对象结构中的对象需要提供多种不同且不相关的操作，而且要避免让这些操作的变化影响对象的结构。
 * 3.对象结构包含很多类型的对象，希望对这些对象实施一些依赖于其具体类型的操作。
 *
 * 模式的扩展
 * 访问者（Visitor）模式是使用频率较高的一种设计模式，它常常同以下两种设计模式联用:
 * 1.与“迭代器模式”联用。
 * 因为访问者模式中的“对象结构”是一个包含元素角色的容器，当访问者遍历容器中的所有元素时，常常要用迭代器。
 * 2.访问者（Visitor）模式同“组合模式”联用。
 * 因为访问者（Visitor）模式中的“元素对象”可能是叶子对象或者是容器对象，如果元素对象包含容器对象，就必须用到组合模式
 *
 *
 */
public class VisitorPattern {

    //访问者模式实现
    public static void testVisitorPattern(){
        ObjectStructure objectStructure = new ObjectStructure();
        objectStructure.add(new ConcreteElementA());
        objectStructure.add(new ConcreteElementB());

        Visitor  visitor = new ConcreteVisitorA();
        objectStructure.accept(visitor);
        System.out.println("------------------------");
        visitor = new ConcreteVisitorB();
        objectStructure.accept(visitor);
    }

    //抽象访问者
    interface Visitor{
        void visit(ConcreteElementA elementA);
        void visit(ConcreteElementB elementB);
    }

    //具体访问者A类
    static class ConcreteVisitorA implements Visitor{

        @Override
        public void visit(ConcreteElementA elementA) {
            System.out.println("具体访问者A访问-->"+elementA.operationA());
        }

        @Override
        public void visit(ConcreteElementB elementB) {
            System.out.println("具体访问者A访问-->"+elementB.operationB());
        }
    }

    //具体访问者B类
    static class ConcreteVisitorB implements Visitor{

        @Override
        public void visit(ConcreteElementA elementA) {
            System.out.println("具体访问者B访问-->"+elementA.operationA());
        }

        @Override
        public void visit(ConcreteElementB elementB) {
            System.out.println("具体访问者B访问-->"+elementB.operationB());
        }
    }


    //抽象元素类
    interface Element{
        void accept(Visitor visitor);
    }

    //具体元素A类
    static class ConcreteElementA implements Element{

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }

        public String operationA(){
            return "具体元素A的操作。";
        }
    }

    //具体元素B类
    static class ConcreteElementB implements Element{

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }

        public String operationB(){
            return "具体元素B的操作。";
        }
    }

    //对象结构角色
    static class ObjectStructure{
        private List<Element> list = new ArrayList<>();

        public void accept(Visitor visitor){
            Iterator<Element> iterator = list.iterator();
            while (iterator.hasNext()) {
                iterator.next().accept(visitor);
            }
        }

        public void add(Element element){
            list.add(element);
        }

        public void remove(Element element){
            list.remove(element);
        }

    }






}
