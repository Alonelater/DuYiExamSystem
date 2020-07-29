package util;

import javax.swing.*;

public  abstract  class BaseFrame extends JFrame {
    /**
     * 由于我们只要制订一套规则  谁要创建窗体只要继承的类就行了
     *  所以我们设置一套规则 由于我们这个基本窗体是给子类用的 所以我们可以将方法的权限修饰改成protected
     *
     *
     *  然后我们还将四个抽出来的方法放在了一个init()函数里面了  那样只要在子类的构造方法调用init()方法就行了
     */
    public BaseFrame(){

    }
    public BaseFrame(String title){
        super(title);

    }

    protected void init(){
        this.setFontAndSoOn();
        this.addElement();
        this.addListener();
        this.setFrameSelf();
    }
    protected abstract void setFontAndSoOn();
    protected abstract void addElement();
    protected abstract void addListener();
    protected abstract void setFrameSelf();


        //模板模式
        //设计一个规则  任何窗口想要画出来  执行流程固定










}
