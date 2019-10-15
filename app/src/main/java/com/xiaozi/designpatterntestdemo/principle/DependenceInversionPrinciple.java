package com.xiaozi.designpatterntestdemo.principle;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/10/15 12:51
 * desc :依赖倒置原则(DIP)——面向对象设计原则
 *
 * 1.依赖倒置原则的定义
 * 依赖倒置原则是罗伯特·马丁（Robert C.Martin）发表的文章。
 * 依赖倒置原则的原始定义为：高层模块不应该依赖低层模块，两者都应该依赖其抽象；抽象不应该依赖细节，细节
 * 应该依赖抽象(High level modules shouldnot depend upon low level modules.Both should depend upon
 * abstractions.Abstractions should not depend upon details. Details should depend upon abstractions)
 * 其核心思想是：要面向接口编程，不要面向实现编程。
 * 依赖倒置原则是实现开闭原则的重要途径之一，它降低了客户与实现模块之间的耦合。
 * 由于在软件设计中，细节具有多变性，而抽象层则相对稳定，因此以抽象为基础搭建起来的架构要比以细节为基础
 * 搭建起来的架构要稳定得多。这里的抽象指的是接口或者抽象类，而细节是指具体的实现类。
 * 使用接口或者抽象类的目的是制定好规范和契约，而不去涉及任何具体的操作，把展现细节的任务交给它们的实现类去完成。
 *
 * 2.依赖、倒置原则的作用
 * 依赖倒置原则的主要作用如下：
 * 1).依赖倒置原则可以降低类间的耦合性。
 * 2).依赖倒置原则可以提高系统的稳定性。
 * 3).依赖倒置原则可以减少并行开发引起的风险。
 * 4).依赖倒置原则可以提高代码的可读性和可维护性。
 *
 * 3.依赖倒置原则的实现方法
 * 依赖倒置原则的目的是要通过面向接口的编程来降低类间的耦合性，所以我们在实际编程中只要遵循以下4点，就能
 * 在项目中满足这个规则。
 * 1).每个类尽量提供接口或抽象类，或者两者都具备。
 * 2).变量的声明类型尽量是接口或者是抽象类。
 * 3).任何类都不应该从具体类派生。
 * 4).使用继承时尽量遵循里氏替换原则。
 *
 * 【例1】依赖倒置原则在“顾客购物程序”中的应用。
 * 分析：本程序反映了 “顾客类”与“商店类”的关系。商店类中有 sell() 方法，顾客类通过该方法购物。如果顾
 * 客购物方法直接写具体的网店，顾客每更换一家商店，都要修改一次代码，这明显违背了开闭原则。原因是顾客类设
 * 计时同具体的商店类绑定了，这违背了依赖倒置原则。解决方法就是定义“京东网店”和“天猫超市”的共同接口Shop，
 * 顾客类面向该接口编程。
 *
 */
public class DependenceInversionPrinciple {

    public static void testDIP(){
        Customer customer = new Customer();
        System.out.println("顾客从以下网店购买了商品：");
        customer.shopping(new JdShop());
        customer.shopping(new TmShop());
    }

    //商店
    interface Shop{
        //定义抽象卖物品方法
        String sell();
    }

    //京东网店
    static class JdShop implements Shop{

        @Override
        public String sell() {
            return "京东到家为您服务";
        }
    }

    //天猫超时网店
    static class TmShop implements Shop{

        @Override
        public String sell() {
            return "天猫超市足不出户为您准时送达";
        }
    }

    static class Customer{
        public void shopping(Shop shop){
            //购物
            System.out.println(shop.sell());
        }
    }

}
