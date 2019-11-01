package com.xiaozi.designpatterntestdemo.pattern;

import java.util.HashMap;

/**
 * @author : Sun
 * @version : 1.0
 * @time : 2019/10/30
 * desc :状态模式--行为型模式
 *
 * 背景：
 * 在软件开发过程中，应用程序中的有些对象可能会根据不同的情况做出不同的行为，我们把这种对象称为有状态的对象，
 * 我们把这种对象称为有状态的对象，而把影响对象行为的一个或多个动态变化的属性称为状态。。当有状态的对象与外
 * 部事件产生互动时，其内部状态会发生改变，从而使得其行为也随之发生改变。
 * 对这种有状态的对象编程，传统的解决方案是：将这些所有可能发生的情况全都考虑到，然后使用 if-else 语句来做
 * 状态判断，再进行不同情况的处理。但当对象的状态很多时，程序会变得很复杂。而且增加新的状态要添加新的 if-else
 * 语句，这违背了“开闭原则”，不利于程序的扩展。如果采用“状态模式”就能很好地得到解决。
 * 状态模式的解决思想是：当控制一个对象状态转换的条件表达式过于复杂时，把相关“判断逻辑”提取出来，放到一系
 * 列的状态类当中，这样可以把原来复杂的逻辑判断简单化。
 *
 * 状态模式的定义与特点
 * 状态（State）模式的定义：
 * 对有状态的对象，把复杂的“判断逻辑”提取到不同的状态对象中，允许状态对象在其内部状态发生改变时改变其行为。
 * 状态模式是一种对象行为型模式，其主要优点如下：
 * 1.状态模式将与特定状态相关的行为局部化到一个状态中，并且将不同状态的行为分割开来，满足“单一职责原则”。
 * 2.减少对象间的相互依赖。将不同的状态引入独立的对象中会使得状态转换变得更加明确，且减少对象间的相互依赖。
 * 3.有利于程序的扩展。通过定义新的子类很容易地增加新的状态和转换。
 * 状态模式的主要缺点如下：
 * 1.状态模式的使用必然会增加系统的类与对象的个数。
 * 2.状态模式的结构与实现都较为复杂，如果使用不当会导致程序结构和代码的混乱。
 *
 * 状态模式的结构与实现
 * 状态模式把受环境改变的对象行为包装在不同的状态对象里，其意图是让一个对象在其内部状态改变的时候，其行为也随之改变。
 * 状态模式包含以下主要角色：
 * 1.环境（Context）角色：也称为上下文，它定义了客户感兴趣的接口，维护一个当前状态，并将与状态相关的操作委托给当前状态对象来处理。
 * 2.抽象状态（State）角色：定义一个接口，用以封装环境对象中的特定状态所对应的行为。
 * 3.具体状态（Concrete    State）角色：实现抽象状态所对应的行为。
 *
 * 状态模式的应用场景
 * 通常在以下情况下可以考虑使用状态模式：
 * 1.当一个对象的行为取决于它的状态，并且它必须在运行时根据状态改变它的行为时，就可以考虑使用状态模式。
 * 2.一个操作中含有庞大的分支结构，并且这些分支决定于对象的状态时。
 *
 * 状态模式的应用实例
 * 【例1】用“状态模式”设计一个学生成绩的状态转换程序。
 * 分析：本实例包含了“不及格”“中等”和“优秀” 3 种状态，当学生的分数小于 60 分时为“不及格”状态，当分数
 * 大于等于60分且小于90分时为“中等”状态，当分数大于等于90分时为“优秀”状态，我们用状态模式来实现这个程序。
 * 首先，定义一个抽象状态类（AbstractState），其中包含了环境属性、状态名属性和当前分数属性，以及加减分方法
 * addScore(intx) 和检查当前状态的抽象方法 checkState()；然后，定义“不及格”状态类 LowState、“中等”状
 * 态类 MiddleState 和“优秀”状态类 HighState，它们是具体状态类，实现 checkState() 方法，负责检査自己的
 * 状态，并根据情况转换；最后，定义环境类（ScoreContext），其中包含了当前状态对象和加减分的方法 add(int score)，
 * 客户类通过该方法来改变成绩状态。
 *
 * 【例2】用“状态模式”设计一个多线程的状态转换程序。
 * 分析：多线程存在 5 种状态，分别为新建状态、就绪状态、运行状态、阻塞状态和死亡状态，各个状态当遇到相关方
 * 法调用或事件触发时会转换到其他状态
 * 现在先定义一个抽象状态类（TheadState），然后为每个状态设计一个具体状态类，它们是新建状态（New）、就绪状态（Runnable ）、
 * 运行状态（Running）、阻塞状态（Blocked）和死亡状态（Dead），每个状态中有触发它们转变状态的方法，环境类（ThreadContext）
 * 中先生成一个初始状态（New），并提供相关触发方法。
 *
 * 状态模式的扩展
 * 在有些情况下，可能有多个环境对象需要共享一组状态，这时需要引入享元模式，将这些具体状态对象放在集合中供程序共享。
 * 分析：共享状态模式的不同之处是在环境类中增加了一个 HashMap 来保存相关状态，，当需要某种状态时可以从中获取。
 *
 */
public class StatePattern {

    public static void testStatePattern(){
        ScoreContext account = new ScoreContext();
        System.out.println("学生成绩状态测试：");
        account.add(30);
        account.add(40);
        account.add(25);
        account.add(-15);
        account.add(-25);
    }

    public static void testThreadState(){
        System.out.println("线程5种状态转换测试：");
        ThreadContext context = new ThreadContext();
        context.start();
        context.getCPU();
        context.suspend();
        context.resume();
        context.getCPU();
        context.stop();
    }

    public static void testShareStatePattern(){
        ShareContext shareContext = new ShareContext(); //创建环境
        shareContext.handle();//处理请求
        shareContext.handle();
        shareContext.handle();
        shareContext.handle();
    }

    static class ScoreContext{
        private AbstractState state;

        ScoreContext(){
            state = new LowState(this);
        }

        public AbstractState getState() {
            return state;
        }

        public void setState(AbstractState state) {
            this.state = state;
        }

        public void add(int score){
            state.addScore(score);
        }
    }

    //抽象状态类
    static abstract class AbstractState{
        protected ScoreContext hj;//环境
        protected String stateName;//状态名
        protected int score;//分数

        public abstract void checkState();//检查当前状态

        //加减分数
        public void addScore(int x){
            score +=x;
            System.out.print("加上："+x+"分，\t当前分数："+score );
            checkState();
            System.out.println("分，\t当前状态："+hj.getState().stateName);
        }
    }

    //具体状态类：不及格
    static class LowState extends AbstractState{

        public LowState(ScoreContext scoreContext){
            hj=scoreContext;
            stateName = "不及格";
            score=0;
        }

        public LowState(AbstractState state){
            hj = state.hj;
            stateName="不及格";
            score =state.score;
        }

        @Override
        public void checkState() {
            if(score>=90){
                hj.setState(new HighState(this));
            }else if(score>=60){
                hj.setState(new MiddleState(this));
            }
        }
    }

    //具体状态类：中等
    static class MiddleState extends AbstractState{

        public MiddleState(AbstractState state){
            hj=state.hj;
            stateName="中等";
            score=state.score;
        }

        @Override
        public void checkState() {
            if(score<60){
                hj.setState(new LowState(this));
            }else if(score>=90){
                hj.setState(new HighState(this));
            }
        }
    }

    //具体状态类：优秀
    static class HighState extends AbstractState{

        public HighState(AbstractState state){
            hj = state.hj;
            stateName="优秀";
            score = state.score;
        }

        @Override
        public void checkState() {
            if(score<60){
                hj.setState(new LowState(this));
            }else if(score<90){
                hj.setState(new MiddleState(this));
            }
        }
    }

    //--------------------------------我是华丽的分割线-------------------------------------------

    //环境类
    static class ThreadContext{
        private ThreadState state;

        ThreadContext(){
            state = new NewState();
        }

        public ThreadState getState() {
            return state;
        }

        public void setState(ThreadState state) {
            this.state = state;
        }

        public void start(){
            ((NewState) state).start(this);
        }

        public void getCPU(){
            ((RunnableState) state).getCPU(this);
        }

        public void suspend(){
            ((RunningState)state).suspend(this);
        }

        public void stop(){
            ((RunningState)state).stop(this);
        }

        public void resume(){
            ((BlockedState)state).resume(this);
        }

    }



    //抽象状态类：线程状态
    static abstract class ThreadState{
        protected String stateName;//状态名
    }

    //具体状态类：新建状态
    static class NewState extends ThreadState{
        public NewState(){
            stateName="新建状态";
            System.out.println("当前线程处于：新建状态.");
        }

        public void start(ThreadContext hj){
            System.out.print("调用start()方法-->");
            if(stateName.endsWith("新建状态")){
                hj.setState(new RunnableState());
            }else{
                System.out.println("当前线程不是新建状态，不能调用start()方法.");
            }
        }

    }

    //具体状态类：就绪状态
    static class RunnableState extends ThreadState{
        public RunnableState(){
            stateName ="就绪状态";
            System.out.println("当前线程处于：就绪状态.");
        }

        public void getCPU(ThreadContext hj){
            System.out.print("获得CPU时间-->");
            if(stateName.endsWith("就绪状态")){
                hj.setState(new RunningState());
            }else{
                System.out.println("当前线程不是就绪状态，不能获取CPU.");
            }
        }

    }

    //具体状态类：运行状态
    static class RunningState extends ThreadState{
        public RunningState(){
            stateName="运行状态";
            System.out.println("当前线程处于：运行状态.");
        }

        public void suspend(ThreadContext hj){
            System.out.print("调用suspend()方法-->");
            if(stateName.endsWith("运行状态")){
                hj.setState(new BlockedState());
            }else{
                System.out.println("当前线程不是运行状态，不能调用suspend()方法.");
            }
        }

        public void stop(ThreadContext hj){
            System.out.print("调用stop()方法-->");
            if(stateName.endsWith("运行状态")){
                hj.setState(new DeadState());
            }else{
                System.out.println("当前线程不是运行状态，不能调用stop()方法.");
            }
        }

    }

    //具体状态类：阻塞状态
    static class BlockedState extends ThreadState{
        public BlockedState(){
            stateName = "阻塞状态";
            System.out.println("当前线程处于：阻塞状态.");
        }

        public void resume(ThreadContext hj){
            System.out.print("调用resume()方法-->");
            if(stateName.endsWith("阻塞状态")){
                hj.setState(new RunnableState());
            }else{
                System.out.println("当前线程不是阻塞状态，不能调用resume()方法.");
            }
        }
    }

    //具体状态类：死亡状态
    static class DeadState extends ThreadState{
        public DeadState(){
            stateName="死亡状态";
            System.out.println("当前线程处于：死亡状态.");
        }
    }


    //--------------------------------我是华丽的分割线-------------------------------------------

    //环境类
    static class ShareContext{
        private ShareState shareState;
        private HashMap<String,ShareState> shareStateHashMap = new HashMap<>();

        public ShareContext(){
            shareState = new ConcreteState1();
            shareStateHashMap.put("1",shareState);
            shareState = new ConcreteState2();
            shareStateHashMap.put("2",shareState);
            shareState=getShareState("1");
        }

        //设置新状态
        public void setShareState(ShareState shareState){
            this.shareState = shareState;
        }

        //读取状态
        public ShareState getShareState(String key){
            return shareStateHashMap.get(key);
        }

        //对请求做处理
        public void handle(){
            shareState.handle(this);
        }


    }

    //抽象状态类
    static abstract class ShareState{
        public abstract void handle(ShareContext shareContext);
    }

    //具体状态1类
    static class ConcreteState1 extends ShareState{

        @Override
        public void handle(ShareContext context) {
            System.out.println("当前状态是： 状态1");
            context.setShareState(context.getShareState("2"));
        }
    }

    //具体状态2类
    static class ConcreteState2 extends ShareState{

        @Override
        public void handle(ShareContext context) {
            System.out.println("当前状态是： 状态2");
            context.setShareState(context.getShareState("1"));
        }
    }






}
