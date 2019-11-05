package com.xiaozi.designpatterntestdemo.pattern;

/**
 * @author : Sun
 * @version : 1.0
 * @time : 2019/11/5
 * desc :备忘录模式-行为型模式
 *
 * 背景：
 * 很多应用软件如 Word、记事本、Photoshop、Eclipse 等软件在编辑时按 Ctrl+Z 组合键时能撤销当前操作，数据库
 * 事务管理中的回滚操作、棋类游戏中的悔棋功能等，在计算机应用中客户同样会常常犯错误，提供“后悔药”给他们是
 * 有必要的。这个功能由“备忘录模式”来实现。
 * 备忘录模式能记录一个对象的内部状态，当用户后悔时能撤销当前操作，使数据恢复到它原先的状态。
 *
 * 模式的定义与特点
 * 备忘录（Memento）模式的定义：
 * 在不破坏封装性的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态，以便以后当需要时能将该对象恢
 * 复到原先保存的状态。该模式又叫快照模式。
 * 备忘录模式是一种对象行为型模式，其主要优点如下：
 * 1.提供了一种可以恢复状态的机制。当用户需要时能够比较方便地将数据恢复到某个历史的状态。
 * 2.实现了内部状态的封装。除了创建它的发起人之外，其他对象都不能够访问这些状态信息。
 * 3.简化了发起人类。发起人不需要管理和保存其内部状态的各个备份，所有状态信息都保存在备忘录中，并由管理者进
 * 行管理，这符合单一职责原则。
 * 其主要缺点是：资源消耗大。如果要保存的内部状态信息过多或者特别频繁，将会占用比较大的内存资源。
 *
 * 模式的结构与实现：
 * 备忘录模式的核心是设计备忘录类以及用于管理备忘录的管理者类。
 * 备忘录模式的主要角色如下：
 * 1.发起人（Originator）角色：记录当前时刻的内部状态信息，提供创建备忘录和恢复备忘录数据的功能，实现其他
 * 业务功能，它可以访问备忘录里的所有信息。
 * 2.备忘录（Memento）角色：负责存储发起人的内部状态，在需要的时候提供这些内部状态给发起人。
 * 3.管理者（Caretaker）角色：对备忘录进行管理，提供保存与获取备忘录的功能，但其不能对备忘录的内容进行访问与修改。
 *
 * 模式的应用场景
 * 该模式适用于以下应用场景:
 * 1.需要保存与恢复数据的场景，如玩游戏时的中间结果的存档功能。
 * 2.需要提供一个可回滚操作的场景，如 Word、记事本、还有数据库中事务操作。
 *
 * 模式的扩展
 * 备忘录模式如何同原型模式混合使用，在备忘录模式中，通过定义“备忘录”来备份“发起人”的信息，而原型模式的
 * clone() 方法具有自备份功能，所以，如果让发起人实现 Cloneable 接口就有备份自己的功能，这时可以删除备忘录类。
 *
 */
public class MementoPattern {

    //备忘录模式实现
    public static void testMementoPattern(){
        Originator originator = new Originator();//发起人
        Caretaker caretaker = new Caretaker();//管理者
        originator.setState("SO");
        System.out.println("初始状态:"+originator.getState());
        caretaker.setMemento(originator.createMemento());//保存状态
        originator.setState("S1");
        System.out.println("新的状态:"+originator.getState()); //恢复状态
        originator.restoreMemento(caretaker.getMemento());
        System.out.println("恢复状态:"+originator.getState());

    }

    //备忘录原型模式混合使用
    public static void testMementoPrototype(){
        OriginatorPrototype op = new OriginatorPrototype();
        PrototypeCaretaker pc = new PrototypeCaretaker();
        op.setState("S0");
        System.out.println("初始状态:"+op.getState());
        pc.setMemento(op.createMemento());//保存状态
        op.setState("S1");
        System.out.println("新的状态:"+op.getState());
        op.restoreMemento(pc.getMemento());//恢复状态
        System.out.println("恢复状态:"+op.getState());
    }


    //备忘录
    static class Memento{
        private String state;

        public Memento(String state){
            this.state =state;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }

    //发起人
    static class Originator{
        private String state;

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public Memento createMemento(){
            return new Memento(state);
        }

        public void restoreMemento(Memento m){
            setState(m.getState());
        }
    }

    //管理者
    static class Caretaker{
        private Memento memento;

        public Memento getMemento() {
            return memento;
        }

        public void setMemento(Memento memento) {
            this.memento = memento;
        }
    }

    //--------------------------------我是华丽的分割线-------------------------------------------

    //发起人原型
    static class OriginatorPrototype implements Cloneable{
        private String state;
        public void setState(String state){
            this.state = state;
        }

        public String getState(){
            return state;
        }

        public OriginatorPrototype createMemento(){
                return this.clone();
        }

        public void  restoreMemento(OriginatorPrototype opt){
            this.setState(opt.getState());
        }

        public OriginatorPrototype clone(){
            try {
                return (OriginatorPrototype) super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return null;
        }

    }

    //原型管理者
    static class PrototypeCaretaker{
        private OriginatorPrototype opt;
        public void setMemento(OriginatorPrototype opt){
            this.opt = opt;
        }

        public OriginatorPrototype getMemento(){
            return opt;
        }

    }






}
