package com.xiaozi.designpatterntestdemo.principle;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/10/15 12:54
 * desc :迪米特法则(LoD)——面向对象设计原则
 *
 * 1.迪米特法则的定义
 * 迪米特法则又叫作最少知识原则产生于一个名为迪米特（Demeter）的研究项目由伊恩·荷兰（Ian Holland）提出。
 * 迪米特法则的定义是：只与你的直接朋友交谈，不跟“陌生人”说话（Talk only to your immediate friends and
 * not to strangers）其含义是：如果两个软件实体无须直接通信，那么就不应当发生直接的相互调用，可以通过第三
 * 方转发该调用。其目的是降低类之间的耦合度，提高模块的相对独立性。
 * 迪米特法则中的“朋友”是指：当前对象本身、当前对象的成员对象、当前对象所创建的对象、当前对象的方法参数
 * 等，这些对象同当前对象存在关联、聚合或组合关系，可以直接访问这些对象的方法。
 *
 * 2.迪米特法则的优点
 * 迪米特法则要求限制软件实体之间通信的宽度和深度，正确使用迪米特法则将有以下两个优点：
 * 1).降低了类之间的耦合度，提高了模块的相对独立性。
 * 2).由于亲合度降低，从而提高了类的可复用率和系统的扩展性。
 * 但是，过度使用迪米特法则会使系统产生大量的中介类，从而增加系统的复杂性，使模块之间的通信效率降低。所以，
 * 在釆用迪米特法则时需要反复权衡，确保高内聚和低耦合的同时，保证系统的结构清晰。
 *
 * 3.迪米特法则的实现方法
 * 从迪米特法则的定义和特点可知，它强调以下两点：
 * 1).从依赖者的角度来说，只依赖应该依赖的对象。
 * 2).从被依赖者的角度说，只暴露应该暴露的方法。
 * 所以，在运用迪米特法则时要注意以下 6 点:
 * 1).在类的划分上，应该创建弱耦合的类。类与类之间的耦合越弱，就越有利于实现可复用的目标。
 * 2).在类的结构设计上，尽量降低类成员的访问权限。
 * 3).在类的设计上，优先考虑将一个类设置成不变类。
 * 4).在对其他类的引用上，将引用其他对象的次数降到最低。
 * 5).不暴露类的属性成员，而应该提供相应的访问器（set 和 get 方法）。
 * 6).谨慎使用序列化（Serializable）功能。
 *
 * 【例1】明星与经纪人的关系实例。
 * 分析：明星由于全身心投入艺术，所以许多日常事务由经纪人负责处理，如与粉丝的见面会，与媒体公司的业务洽淡
 * 等。这里的经纪人是明星的朋友，而粉丝和媒体公司是明星的陌生人，所以适合使用迪米特法则。
 *
 */
public class LawOfDemeter {

    public static void testLoD(){
        Agent agent = new Agent();
        agent.setStar(new Star("林心如"));
        agent.setFans(new Fans("粉丝Sun"));
        agent.setCompany(new Company("中国传媒有限公司"));
        System.out.println("经纪人安排一场粉丝见面会：");
        agent.meeting();
        System.out.println("经纪人又安排与媒体公司谈新剧合作:");
        agent.bussiness();

    }

    //经纪人
    static class Agent{
        private Star myStar;
        private Fans myFans;
        private Company myCompany;

        public void setStar(Star myStar)
        {
            this.myStar=myStar;
        }
        public void setFans(Fans myFans)
        {
            this.myFans=myFans;
        }
        public void setCompany(Company myCompany)
        {
            this.myCompany=myCompany;
        }

        public void meeting(){
            System.out.println(myFans.getName()+"与明星"+myStar.getName()+"见面了。");
        }

        public void bussiness(){
            System.out.println(myCompany.getName()+"与明星"+myStar.getName()+"洽淡业务。");
        }
    }

    //明星
    static class Star{
        private String name;

        public Star(String name){
            this.name=name;
        }

        public String getName() {
            return name == null ? "" : name;
        }
    }

    //粉丝
    static class Fans{
        private String name;

        public Fans(String name){
            this.name=name;
        }

        public String getName() {
            return name == null ? "" : name;
        }
    }

    //媒体公司
    static class Company{
        private String name;

        public Company(String name){
            this.name=name;
        }

        public String getName() {
            return name == null ? "" : name;
        }
    }



}
