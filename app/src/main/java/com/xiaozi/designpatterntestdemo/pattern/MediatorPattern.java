package com.xiaozi.designpatterntestdemo.pattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Sun
 * @version : 1.0
 * @time : 2019/10/30
 * desc :中介者模式-行为型模式
 *
 * 背景：
 * 在现实生活中，常常会出现好多对象之间存在复杂的交互关系，这种交互关系常常是“网状结构”，它要求每个对象都
 * 必须知道它需要交互的对象。例如，每个人必须记住所有朋友的电话；朋友中如果有人的电话修改了，他必须告诉其他
 * 所有的朋友修改，这叫作“牵一发而动全身”，非常复杂。
 * 如果把这种“网状结构”改为“星形结构”的话，将大大降低它们之间的“耦合性”，这时只要找一个“中介者”就可
 * 以了。只要在网上建立一个每个朋友都可以访问的“通信录”就可以解决上面的问题。租房可以找“房屋中介”等。
 * 在软件的开发过程中，这样的例子也很多，例如，在 MVC 框架中，控制器（C）就是模型（M）和视图（V）的中介者；
 * 中介者模式将大大降低对象之间的耦合性，提高系统的灵活性。
 *
 * 模式的定义与特点
 * 中介者（Mediator）模式的定义：
 * 定义一个中介对象来封装一系列对象之间的交互，使原有对象之间的耦合松散，且可以独立地改变它们之间的交互。中
 * 介者模式又叫调停模式，它是迪米特法则的典型应用。
 * 中介者模式是一种对象行为型模式，其主要优点如下：
 * 1.降低了对象之间的耦合性，使得对象易于独立地被复用。
 * 2.将对象间的一对多关联转变为一对一的关联，提高系统的灵活性，使得系统易于维护和扩展。
 * 其主要缺点是：当同事类太多时，中介者的职责将很大，它会变得复杂而庞大，以至于系统难以维护。
 *
 * 模式的结构与实现
 * 中介者模式包含以下主要角色：
 * 1.抽象中介者（Mediator）角色：它是中介者的接口，提供了同事对象注册与转发同事对象信息的抽象方法。
 * 2.具体中介者（ConcreteMediator）角色：实现中介者接口，定义一个 List 来管理同事对象，协调各个同事角色之
 * 间的交互关系，因此它依赖于同事角色。
 * 3.抽象同事类（Colleague）角色：定义同事类的接口，保存中介者对象，提供同事对象交互的抽象方法，实现所有相
 * 互影响的同事类的公共功能。
 * 4.具体同事类（Concrete Colleague）角色：是抽象同事类的实现者，当需要与其他同事对象交互时，由中介者对象负责后续的交互。
 *
 * 模式的应用场景
 * 1.当对象之间存在复杂的网状结构关系而导致依赖关系混乱且难以复用时。
 * 2.当想创建一个运行于多个类之间的对象，又不想生成新的子类时。
 *
 * 模式的扩展
 * 在实际开发中，通常采用以下两种方法来简化中介者模式，使开发变得更简单:
 * 1.不定义中介者接口，把具体中介者对象实现成为单例。
 * 2.同事对象不持有中介者，而是在需要的时直接获取中介者对象并调用。
 *
 */
public class MediatorPattern {

    public static void testMediatorPattern(){
        Mediator md = new ConcreteMediator();
        Colleague c1,c2;
        c1 = new ConcreteColleague1();
        c2 = new ConcreteColleague2();
        md.register(c1);
        md.register(c2);
        c1.send();
        System.out.println("-------------");
        c2.send();
    }

    public static void testSimpleMediatorPattern(){
        SimpleColleague c1,c2;
        c1 = new SimpleConcreteColleague1();
        c2 = new SimpleConcreteColleague2();
        c1.send();
        System.out.println("-----------------");
        c2.send();
    }

    //抽象中介者
    static abstract class Mediator{
        public abstract void register(Colleague colleague);//注册双方
        public abstract void relay(Colleague cl);//转发
    }

    //具体中介者
    static class ConcreteMediator extends Mediator{
        private List<Colleague> colleagueList = new ArrayList<>();

        @Override
        public void register(Colleague colleague) {
            if (!colleagueList.contains(colleague)) {
                colleagueList.add(colleague);
                colleague.setMediator(this);
            }
        }

        @Override
        public void relay(Colleague cl) {
            for (Colleague colleague : colleagueList) {
                if (!colleague.equals(cl)) {
                    colleague.receive();
                }
            }
        }
    }

    //抽象同事类
    static abstract class Colleague{
        protected Mediator mediator;
        public void setMediator(Mediator mediator){
            this.mediator=mediator;
        }
        public abstract void receive();
        public abstract void send();
    }

    //具体同事类1
    static class ConcreteColleague1 extends Colleague{

        @Override
        public void receive() {
            System.out.println("具体同事类1收到请求。");
        }

        @Override
        public void send() {
            System.out.println("具体同事类1发出请求。");
            mediator.relay(this);//请中介者转发
        }
    }

    //具体同事类2
    static class ConcreteColleague2 extends Colleague{

        @Override
        public void receive() {
            System.out.println("具体同事类2收到请求。");
        }

        @Override
        public void send() {
            System.out.println("具体同事类2发出请求。");
            mediator.relay(this);//请中介者转发
        }
    }

    //--------------------------------我是华丽的分割线-------------------------------------------


    //简单单例中介者
    static class SimpleMediator{
        private static SimpleMediator smd = new SimpleMediator();
        private List<SimpleColleague> colleagues =new ArrayList<>();

        private SimpleMediator(){};
        public static SimpleMediator getMediator(){
            return smd;
        }
        public void register(SimpleColleague colleague){
            if (!colleagues.contains(colleague)) {
                colleagues.add(colleague);
            }
        }
        public void relay(SimpleColleague scl){
            for (SimpleColleague colleague : colleagues) {
                if (!colleague.equals(scl)) {
                    colleague.receive();
                }
            }
        }
    }

    //抽象同事类
    interface SimpleColleague{
        void receive();
        void send();
    }

    //具体同事类1
    static class SimpleConcreteColleague1 implements SimpleColleague{

        SimpleConcreteColleague1(){
            SimpleMediator mediator = SimpleMediator.getMediator();
            mediator.register(this);
        }

        @Override
        public void receive() {
            System.out.println("具体同事类1：收到请求。");
        }

        @Override
        public void send() {
            System.out.println("具体同事类1：发出请求...");
            SimpleMediator smd=SimpleMediator.getMediator();
            smd.relay(this);//请中介者转发
        }
    }

    //具体同事类2
    static class SimpleConcreteColleague2 implements SimpleColleague{

        SimpleConcreteColleague2(){
            SimpleMediator mediator = SimpleMediator.getMediator();
            mediator.register(this);
        }

        @Override
        public void receive() {
            System.out.println("具体同事类2：收到请求。");
        }

        @Override
        public void send() {
            System.out.println("具体同事类2：发出请求...");
            SimpleMediator mediator = SimpleMediator.getMediator();
            mediator.relay(this);//请中介者转发
        }
    }



}
