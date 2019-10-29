package com.xiaozi.designpatterntestdemo;

import com.xiaozi.designpatterntestdemo.pattern.AbstractFactoryPattern;
import com.xiaozi.designpatterntestdemo.pattern.AdapterPattern;
import com.xiaozi.designpatterntestdemo.pattern.BridgePattern;
import com.xiaozi.designpatterntestdemo.pattern.BuilderPattern;
import com.xiaozi.designpatterntestdemo.pattern.CompositePattern;
import com.xiaozi.designpatterntestdemo.pattern.DecoratorPattern;
import com.xiaozi.designpatterntestdemo.pattern.FacadePattern;
import com.xiaozi.designpatterntestdemo.pattern.FactoryMethodPattern;
import com.xiaozi.designpatterntestdemo.pattern.FlyweightPattern;
import com.xiaozi.designpatterntestdemo.pattern.PrototypePattern;
import com.xiaozi.designpatterntestdemo.pattern.ProxyPattern;
import com.xiaozi.designpatterntestdemo.pattern.SingletonPattern;
import com.xiaozi.designpatterntestdemo.principle.CompositeReusePrinciple;
import com.xiaozi.designpatterntestdemo.principle.DependenceInversionPrinciple;
import com.xiaozi.designpatterntestdemo.principle.InterfaceSegregationPrinciple;
import com.xiaozi.designpatterntestdemo.principle.LawOfDemeter;
import com.xiaozi.designpatterntestdemo.principle.LiskovSubstitutionPrinciple;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/10/14 17:04
 * desc :23种设计模式简述以及设计模式中经常用到的类图，类之间的关系和面向对象7个设计原则
 *
 * 软件设计模式的概念与意义：
 * 1).软件设计模式的概念
 * 软件设计模式（Software Design Pattern），又称设计模式，是一套被反复使用、多数人知晓的、经过分类编目的、
 * 代码设计经验的总结。它描述了在软件设计过程中的一些不断重复发生的问题，以及该问题的解决方案。也就是说，
 * 它是解决特定问题的一系列套路，是前辈们的代码设计经验的总结，具有一定的普遍性，可以反复使用。其目的是为了
 * 提高代码的可重用性、代码的可读性和代码的可靠性。
 * 2).学习设计模式的意义
 * 设计模式的本质是面向对象设计原则的实际运用，是对类的封装性、继承性和多态性以及类的关联关系和组合关系的
 * 充分理解。正确使用设计模式具有以下优点:
 * 可以提高程序员的思维能力、编程能力和设计能力。
 * 使程序设计更加标准化、代码编制更加工程化，使软件开发效率大大提高，从而缩短软件的开发周期。
 * 使设计的代码可重用性高、可读性强、可靠性高、灵活性好、可维护性强。
 * 总结：软件设计模式只是一个引导。在具体的软件幵发中，必须根据设计的应用系统的特点和要求来恰当选择。对于简单的程
 * 序开发，苛能写一个简单的算法要比引入某种设计模式更加容易。但对大项目的开发或者框架设计，用设计模式来组织代码显然更好。
 *
 *
 * 设计模式有两种分类方法,即根据模式的目的来分和根据模式的作用的范围来分。
 * 1. 根据目的来分
 * 根据模式是用来完成什么工作来划分，这种方式可分为创建型模式、结构型模式和行为型模式 3 种。
 * 1)创建型模式：用于描述“怎样创建对象”，它的主要特点是“将对象的创建与使用分离” 包括单例、原型、工厂方法、
 * 抽象工厂、建造者等 5 种创建型模式
 * 2)结构型模式：用于描述如何将类或对象按某种布局组成更大的结构,包括代理、适配器、桥接、装饰、外观、享元、
 * 组合等 7 种结构型模式。
 * 3)行为型模式：用于描述类或对象之间怎样相互协作共同完成单个对象都无法单独完成的任务，以及怎样分配职责。
 * 包括模板方法、策略、命令、职责链、状态、观察者、中介者、迭代器、访问者、备忘录、解释器等 11 种行为型模式。
 *
 * 2. 根据作用范围来分
 * 根据模式是主要用于类上还是主要用于对象上来分，这种方式可分为类模式和对象模式两种。
 * 1)类模式：用于处理类与子类之间的关系，这些关系通过继承来建立，是静态的，在编译时刻便确定下来了。包括工
 * 厂方法、（类）适配器、模板方法、解释器等4种模式
 * 2)对象模式：用于处理对象之间的关系，这些关系可以通过组合或聚合来实现，在运行时刻是可以变化的，更具动态
 * 性。除了以上 4 种，其他的都是对象模式。
 *
 *
 * 23种设计模式的功能:
 * 1).单例（Singleton）模式：某个类只能生成一个实例，该类提供了一个全局访问点供外部获取该实例，其拓展是有限多例模式
 * 2).原型（Prototype）模式：将一个对象作为原型，通过对其进行复制而克隆出多个和原型类似的新实例。
 * 3).工厂方法（Factory Method）模式：定义一个用于创建产品的接口，由子类决定生产什么产品。
 * 4).抽象工厂（AbstractFactory）模式：提供一个创建产品族的接口，其每个子类可以生产一系列相关的产品
 * 5).建造者（Builder）模式：将一个复杂对象分解成多个相对简单的部分，然后根据不同需要分别创建他们，最后构建成该负责对象。
 * 6).代理（Proxy）模式：为某对象提供一种代理以控制对该对象的访问，即客户端通过代理间接的访问该对象，从而限制、增强或修改该对象一些特性
 * 7).适配器（Adapter）模式：将一个类的接口转换成客户希望的另外一个接口，使得原本由于接口不兼容而不能一起工作的那些类能一起工作。
 * 8).桥接（Bridge）模式:将抽象与实现分离，使它们可以独立变化。它是用组合关系代替继承关系来实现，从而降低了抽象和实现这两个可变维度的偶尔度。
 * 9).装饰（Decorator）模式：动态的给对象增加一些职责，即增加其额外的功能。
 * 10).外观（Facade）模式：为多个复杂的子系统提供一个一致的接口，使这些子系统更加容易被访问。
 * 11).享元（Flyweight）模式：运用共享技术来有效地支持大量细粒度对象的复用。
 * 12).组合（Composite）模式：将对象组合成树状层次结构，使用户对单个对象和组合对象具有一致的访问性。
 * 13).模板方法（TemplateMethod）模式：定义一个操作中的算法骨架，而将算法的一些步骤延迟到子类中，使得子类
 * 可以不改变该算法结构的情况下重定义该算法的某些特定步骤。
 * 14).策略（Strategy）模式：定义一系列算法，并将每个算法封装起来，是它们可以相互替换，且算法的改变不会影响使用算法的客户。
 * 15).命令（Command）模式：将一个请求封装为一个对象,使发出请求的责任和执行请求的责任分割开。
 * 16).职责链（Chain of Responsibility）模式：把请求从链中的一个对象传到下一个对象，直到请求被响应为止。通过这种方式去除对象之间的耦合。
 * 17).状态（State）模式：允许一个对象在其内部状态发生改变时改变其行为能力。
 * 18).观察者（Observer）模式：多个对象间存在一对多关系，当一个对象发生改变时，把这种改变通知给其他多个对象，从而影响其他对象的行为。
 * 19).中介者（Mediator）模式：定义一个中介对象来简化原有对象之间的交互关系，降低系统中对象间的耦合度，使原有对象之间不必相互了解。
 * 20).迭代器（Iterator）模式：提供一种方法来顺序访问聚合对象中的一系列数据，而不暴露聚合对象的内部表示。
 * 21).访问者（Visitor）模式：在不改变集合元素的前提下，为一个集合中的每个元素提供多种访问方式，即每个元素有多个访问者对象访问。
 * 22).备忘录（Memento）模式：在不破坏封装性的前提下，获取并保存一个对象的内部状态，以便以后恢复它。
 * 23).解释器（Interpreter）模式：提供如何定义语言的文法，以及对语言句子的解释方法，即解释器。
 *
 *
 * 类之间的关系：
 * 在软件系统中，类不是孤立存在的，类与类之间存在各种关系。根据类与类之间的耦合度从弱到强排列，UML 中的类图
 * 有以下几种关系：依赖关系、关联关系、聚合关系、组合关系、泛化关系和实现关系。其中泛化和实现的耦合度相等，它们是最强的。
 * 1).依赖关系:依赖（Dependency）关系是一种使用关系，它是对象之间耦合度最弱的一种关联方式，是临时性的关联。在代码中，
 * 某个类的方法通过局部变量、方法的参数或者对静态方法的调用来访问另一个类（被依赖类）中的某些方法来完成一些职责。
 * 2).关联（Association）关系是对象之间的一种引用关系，用于表示一类对象与另一类对象之间的联系，如老师和学生、丈夫和妻
 * 子等关联关系是类与类之间最常用的一种关系，分为一般关联关系、聚合关系和组合关系
 * 一般关联关系:在代码中通常将一个类的对象作为另一个类的成员变量来实现关联关系
 * 3).聚合（Aggregation）关系是关联关系的一种，是强关联关系，是整体和部分之间的关系，是 has-a 的关系。聚合关系
 * 也是通过成员对象来实现的，其中成员对象是整体对象的一部分，但是成员对象可以脱离整体对象而独立存在。例如，学校与
 * 老师的关系，学校包含老师，但如果学校停办了，老师依然存在。
 * 4).组合（Composition）关系也是关联关系的一种，也表示类之间的整体与部分的关系，但它是一种更强烈的聚合关系，是 contains-a
 * 关系。在组合关系中，整体对象可以控制部分对象的生命周期，一旦整体对象不存在，部分对象也将不存在，部分对象不能脱离整体对象
 * 而存在。例如，头和嘴的关系，没有了头，嘴也就不存在了。
 * 5).泛化（Generalization）关系是对象之间耦合度最大的一种关系，表示一般与特殊的关系，是父类与子类之间的关系，是一种
 * 继承关系，是 is-a 的关系。例如，Student 类和 Teacher 类都是 Person 类的子类。
 * 6).实现（Realization）关系是接口与实现类之间的关系。在这种关系中，类实现了接口，类中的操作实现了接口中所声明的所
 * 有的抽象操作。例如，汽车和船实现了交通工具。
 *
 *
 * 在软件开发中，为了提高软件系统的可维护性和可复用性，增加软件的可扩展性和灵活性，程序员要尽量根据 7 条原则来
 * 开发程序，从而提高软件开发效率、节约软件开发成本和维护成本。面向对象的7个设计原则如下：
 * 1).开闭原则（Open Closed Principle，OCP）:软件实体应当对扩展开放，对修改关闭,这就是开闭原则的经典定义。
 * 2).里氏替换原则（Liskov Substitution Principle，LSP）:继承必须确保超类所拥有的性质在子类中仍然成立
 * 3).依赖倒置原则（Dependence Inversion Principle，DIP）:高层模块不应该依赖低层模块，两者都应该依赖其抽象；抽象不应该依赖细节，细节应该依赖抽象
 * 4).单一职责原则（Single Responsibility Principle，SRP）:一个类应该有且仅有一个引起它变化的原因，否则类应该被拆分
 * 5).接口隔离原则（Interface Segregation Principle，ISP）:将臃肿庞大的接口拆分成更小的和更具体的接口,让接口中只包含客户感兴趣的方法
 * 6).迪米特法则（Law of Demeter，LoD）:只与你的直接朋友交谈，不跟“陌生人”说话
 * 7).合成复用原则（Composite Reuse Principle，CRP）:软件复用时，要尽量先使用组合或者聚合等关联关系来实现，其次才考虑使用继承关系来实现
 *
 */
public class DesignPatternProfile {

    public void principleTest(){
        System.out.println("------里氏替换原则范例------");
        LiskovSubstitutionPrinciple.testLSP();
        System.out.println("------依赖倒置原则范例------");
        DependenceInversionPrinciple.testDIP();
        System.out.println("------接口隔离原则范例------");
        InterfaceSegregationPrinciple.testISP();
        System.out.println("------迪米特法则范例------");
        LawOfDemeter.testLoD();
        System.out.println("------合成复用原则范例------");
        CompositeReusePrinciple.testCRP();
    }

    public void patternTest(){
        System.out.println("------单例模式范例------");
        SingletonPattern.testSingleton();
        System.out.println("------单例模式范例扩展------");
        SingletonPattern.testMultiton();
        System.out.println("------原型模式范例------");
        PrototypePattern.testPrototypePattern();
        System.out.println("------原型模式范例扩展------");
        PrototypePattern.testProtoTypeManager();
        System.out.println("------工厂方法模式范例------");
        FactoryMethodPattern.testFactoryMethodPattern();
        System.out.println("------工厂方法模式范例扩展------");
        FactoryMethodPattern.testSimpleFactoryPatter();
        System.out.println("------抽象工厂模式范例------");
        AbstractFactoryPattern.testAbstractFactory();
        System.out.println("------建造者模式范例------");
        BuilderPattern.testBuildPattern();
        System.out.println("------代理模式范例------");
        ProxyPattern.testProxyPattern();
        System.out.println("------适配器模式范例------");
        AdapterPattern.testAdapterPattern();
        System.out.println("------适配器模式范例扩展------");
        AdapterPattern.testTwoWayAdapter();
        System.out.println("------桥接模式范例------");
        BridgePattern.testBridgePattern();
        System.out.println("------装饰模式范例------");
        DecoratorPattern.DecoratorPatternTest();
        System.out.println("------外观模式范例------");
        FacadePattern.testFacadePattern();
        System.out.println("------享元模式范例------");
        FlyweightPattern.testFlyweightPattern();
        System.out.println("------组合模式-范例------");
        CompositePattern.testCompositePattern();

    }

}
