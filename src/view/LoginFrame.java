package view;

import service.UserService;
import util.BaseFrame;
import util.MySpring;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends BaseFrame {
    /**
     * 这个类是通过分析得到的
     *
     *
     * 这个窗体是我们自己的  所以那些组件就是属性我们就可以剪切过来
     */

    public LoginFrame(){
        this.init();
    }
    //我们注意到一个细节  就是抽象出来的类不能再构造方法里面提供title  所以在提供一个带String参数的一个构造调用继承过来的JFrame
    //来给title赋值

    public LoginFrame(String title){
        super(title);
        this.init();
    }





    //因为当前类就是窗体  所以我们不用new了
    //创建窗体
    //JFrame frame = new JFrame("登录页面");
    //创建面板
    private JPanel mainPanel = new JPanel();
    //创建三个标签
    private JLabel titleLabel = new JLabel("在 线 考 试 系 统");
     private JLabel accountLabel = new JLabel("账 户:");
     private JLabel passwordLabel = new JLabel("密 码:");
    //创建两个文本框
     private JTextField accountField = new JTextField();
     private JPasswordField passwordField = new JPasswordField();

    //创建两个按钮
     private JButton loginButton =new JButton("登 录");
     private JButton exitButton =new JButton("退 出");






    protected void setFontAndSoOn(){

         //设置自定义布局 去除默认样式
        mainPanel.setLayout(null);
//         panel.setLayout(null);
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

     }


    protected void addElement(){
        //将组件加入到面板里面
        mainPanel.add(titleLabel);
        mainPanel.add(accountLabel);
        mainPanel.add(accountField);
        mainPanel.add(passwordLabel);
        mainPanel.add(passwordField);
        mainPanel.add(loginButton);
        mainPanel.add(exitButton);
         //此时就是当前对象了
         this.add(mainPanel);
     }

    protected void addListener(){


         loginButton.addActionListener(
                 new ActionListener() {
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
                 //UserService service = new UserService();
                 UserService service = MySpring.getBean("service.UserService");
                 String result=service.login(account,password);
                 //3.判断最终结果
                 if(result.equals("登录成功")){
                     //弹出考试界面
                     new ExamFrame(account+"的考试界面");
                     /**
                      * 写到这里我们已经能够将初步登录的逻辑实现了
                      * 但我们发现了一个问题
                      * 那就是90多行的考试登录代码写在了注释里面 现在我们又要开始画一个新的考试页面 可能不止90行
                      * 不可能写在下面的判断语句里面吧
                      * 于是我们就想能不能将窗体的构建抽象为对象要的时候new一下就行了
                      * 这就是万物皆对象  这就是面向对象的思想  所以我们将上面的面板代码注释了 将其抽出一个面板类
                      */
                     //System.out.println(result);
                 }else {
                     //System.out.println(result);
                     //如果登录失败就弹出警告框
                     //此处不是this 因为这是内部类 所以应该是LoginFrame.this
                     JOptionPane.showMessageDialog(LoginFrame.this,result);
                     accountField.setText("");
                     passwordField.setText("");
                 }
             }
         });
     }


    protected void setFrameSelf(){
         this.setBounds(350,200,560,340);
         this.setResizable(false);
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         this.setVisible(true);
     }




}
