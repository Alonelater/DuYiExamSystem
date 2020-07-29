package test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImpInterface implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {

        // System.out.println("你点我干嘛");
        //在这个接口中我们需要实现的是登录的逻辑判定
        //我们需要知道从登录页面输入的账号和密码
        //然后调用service层的登录方法


        //UserService service = new UserService();
        /**
         *        那么问题就来了   由于组件里面的文本框输入的账号和密码是其他类的
         *        而且我们是重写了接口的方法 不能将账号和密码通过参数传进来
         *        所以我们就想到了匿名内部类去解决
         *
         */

        //service.login(account,password);



        /**
         * 思想的演变才是最重要的
         */


    }
}
