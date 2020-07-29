package test;



import view.ExamFrame;
import view.LoginFrame;




public class TestMain {
    public static void main(String[] args) {




     /*   QuestionService service = MySpring.getBean("service.QuestionService");
        ArrayList<Question> paper = service.getPaper(5);
        for (int i=0;i<paper.size();i++){
            Question p = paper.get(i);

           String title = p.getTitle().replace("<br>","\n   ");
            System.out.println((i+1)+"."+title);
        }
*/


        //问题的描述
       /* String question = "以下哪个是Java的基本数据类型？\n\tA.String\n\tB.Integer\n\tC.boolean\n\tD.Math";
        System.out.println(question);*/


       //测试我们自己写的hashCode 和equals 方法是否满足条件  经过测试 是满足条件的
       /*String message ="以下哪个是Java的基本数据类型?<br>   A.String<br>   B.Integer<br>   C.boolean<br>   D.Math#C";
       String message1 ="以下哪个是Java的基本数据类型?<br>   A.String<br>   B.Integer<br>   C.boolean<br>   D.Math#C";
       String []value =message.split("#");
       message1.split("#");
        String []value1 =message.split("#");

        Question question1= new Question(value[0],value[1]);
        Question question2= new Question(value1[0],value1[1]);
        System.out.println(question1.equals(question2));
        System.out.println(question1.hashCode());
        System.out.println(question2.hashCode());*/





        /**
         * 接下来面板类已经创建好了 只要new 一下就行了就能创建登录窗口了  问题就来了 new后你就要依次调用类中的每个方法
         * 不仅要记住调用的方法名 还有顺序
         * 那可不可以一调用构造方法就直接调用这些方法  有啊  只要将构造方法里面加入如下方法就行了  这就是思想的演变
         *  既然这样我们也可以将原来设置为共有的方法变成私有的 自己类调用就行了
         *
         *在操作的过程中我们又发现在创建窗口的时候都是添加组件  设置背景  设置监听事件  所以我们就想  那就设立一套规则
         * 只要创建窗体就要实现这个规则  于是一个工具类util就产生了
         */
        /*LoginFrame lf=new LoginFrame();
        lf.setFontAndSoOn();
        lf.addElement();
        lf.addListener();
        lf.setFrameSelf();*/
        //new LoginFrame("登录窗口");
        new ExamFrame();


/*
        //创建窗体
        JFrame frame = new JFrame("登录页面");
        //创建面板
        JPanel panel = new JPanel();
        //创建三个标签
        JLabel titleLabel = new JLabel("在 线 考 试 系 统");
        JLabel accountLabel = new JLabel("账 户:");
        JLabel passwordLabel = new JLabel("密 码:");
        //创建两个文本框
        JTextField accountField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        //创建两个按钮
        JButton loginButton =new JButton("登 录");
        JButton exitButton =new JButton("退 出");*/



/*

        //设置自定义布局 去除默认样式
        panel.setLayout(null);
        titleLabel.setBounds(140,40,340,35);
        titleLabel.setFont(new Font("微软雅黑",Font.BOLD,34));


        accountLabel.setBounds(100,100,90,30);
        accountLabel.setFont(new Font("微软雅黑",Font.BOLD,24));


        accountField.setBounds(200,100,260,30);
        accountField.setFont(new Font("微软雅黑",Font.BOLD,20));


        passwordLabel.setBounds(100,150,90,30);
        passwordLabel.setFont(new Font("微软雅黑",Font.BOLD,24));


        passwordField.setBounds(200,150,260,30);
        passwordField.setFont(new Font("微软雅黑",Font.BOLD,20));

        loginButton.setBounds(200,200,90,30);
        loginButton.setFont(new Font("微软雅黑",Font.BOLD,20));


        exitButton.setBounds(370,200,90,30);
        exitButton.setFont(new Font("微软雅黑",Font.BOLD,20));
*/






/*

        panel.add(titleLabel);
        panel.add(accountLabel);
        panel.add(accountField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(exitButton);
        frame.add(panel);

*/
/*


        frame.setVisible(true);
        frame.setBounds(350,200,560,340);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

*/

        //现在主页面已经设计好了  现在就是添加事件点击了  由于ActionListener是个接口  所以要实现接口里面的抽象方法  所以写了一个类ImpInterface
        //重写了actionPerformed方法  此时就可以将对象绑定在事件源上面
        /**
         *
         * 本来我们是将ActionListener接口中的方法具体化  实现时间的监听 在写着的过程中我们发现无法将当前类的
         * 文本框里面的密码和账号传入ImpInterface类里面 所以采用匿名内部类的方式  所以我们将下面的代码注释
         *
         * 使用匿名内部类是实现接口的一种简便方法
         * 在调用方法时将new ActionListener(){}相当于创建了一个实例对象  并将对象传给了方法
         * new ActionListener()后面接了一个{}表示创建的是ActionListener的子类实例  并且在大括号中编写匿名子类的实例
         *
         */
        /*ActionListener actionListener = new ImpInterface();
        loginButton.addActionListener(actionListener);*/


/*
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //1.我们需要或获取当前类里面的输入的密码和账号
                String account = accountField.getText();
                //此方法看起来是废弃的  我们要去思考为什么废弃  那是因为密码是需要加密的
                //我们获得了一串字符串要进行加密就是要将字符串转化为数组挨个遍历去加密
                //于是就有一个新的方法省略了我们转化为字符数组的过程  那就是getPassword  返回值类型就是数组
                //你如果要加密就是遍历数组挨个去加密就行
                //String password = passwordField.getText();
                char[] pas = passwordField.getPassword();
                //我们不加密  所以将数组转化为字符串 调用带数组参数的String构造器
                String password = new String(pas);
                //2.调用service里面写好的方法
                UserService service = new UserService();
                String result=service.login(account,password);
                //3.判断最终结果
                if(result.equals("登录成功")){
                    //弹出考试界面
                    *//**
                     * 写到这里我们已经能够将初步登录的逻辑实现了
                     * 但我们发现了一个问题
                     * 那就是90多行的考试登录代码写在了主函数里面 现在我们又要开始画一个新的考试页面 可能不止90行
                     * 不可能写在下面的判断语句里面吧
                     * 于是我们就想能不能将窗体的构建抽象为对象要的时候new一下就行了
                     * 这就是万物皆对象  这就是面向对象的思想  所以我们将上面的面板代码注释了 将其抽出一个面板类
                     *//*
                    //System.out.println(result);
                }else {
                    //System.out.println(result);
                    //如果登录失败就弹出警告框
                    JOptionPane.showMessageDialog(frame,result);
                    accountField.setText("");
                    passwordField.setText("");
                }
            }
        });*/


    }
}
