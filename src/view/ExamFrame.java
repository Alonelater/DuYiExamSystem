package view;


import com.sun.media.sound.MidiOutDeviceProvider;
import domain.Question;
import service.QuestionService;
import util.BaseFrame;
import util.MySpring;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class ExamFrame extends BaseFrame {

    //现在我们需要将登陆界面的一些信息给完善
    //获取QuestionService对象
    private QuestionService service = MySpring.getBean("service.QuestionService");

    //调用getPaper  得到试卷  展示在相应的文本区域

    private ArrayList<Question> paper = service.getPaper(5);
    //对于答题的答案  我们选择字符数组来存取  数组的长度是试卷的长度
    private String[] answer = new String[paper.size()];

    //对于右侧的小文本区域 我们设置一些相应的变量去给其赋值  不能将其写死

    //分别表示当前题号 总题数 已达题数 未答题数
    private int nowNumber = 0;
    private int totalNumber = paper.size();
    private int answerNumber = 0;
    private int unanswerNumber = totalNumber;

    //定义一个线程类的私有属性  用来启动线程
    private ControlTimeThread controlTimeThread = new ControlTimeThread();
    //倒计时的总分钟数
    private int time = 61;


    //添加构造方法
    public ExamFrame() {
        this.init();
    }

    public ExamFrame(String title) {
        super(title);
        this.init();
    }

    //添加三个panel 区域的分割
    private JPanel mainPanel = new JPanel();//负责答题主页面展示
    private JPanel messagePanel = new JPanel();//负责右侧信息展示
    private JPanel buttonPanel = new JPanel();//负责下方按钮的展示
    //添加主要答题的组件
    private JTextArea examArea = new JTextArea();//考试文本域 展示题目
    private JScrollPane scrollPane = new JScrollPane(examArea);//滚动条
    //添加右侧信息的组件
    private JLabel pictureLabel = new JLabel();//展示图片信息
    private JLabel nowNumLabel = new JLabel("当前题号：");//提示当前的题号
    private JLabel totalCountLabel = new JLabel("题目总数：");//提示题目的总数
    private JLabel answerCountLabel = new JLabel("已答题数：");//提示已经答过的题目数量
    private JLabel unanswerCountLabel = new JLabel("未答题数：");//提示未答题数量
    private JTextField nowNumField = new JTextField();//展示题号
    private JTextField totalCountField = new JTextField();//展示总数
    private JTextField answerCountField = new JTextField();//展示已答数
    private JTextField unanswerCountField = new JTextField();//展示未答数
    private JLabel timeLabel = new JLabel("剩余答题时间");//提示剩余时间
    private JLabel realTimeLabel = new JLabel("00:00:00");//倒计时真实时间
    //添加下方按钮的组件
    private JButton aButton = new JButton("A");//a按钮
    private JButton bButton = new JButton("B");//b按钮
    private JButton cButton = new JButton("C");//c按钮
    private JButton dButton = new JButton("D");//d按钮
    private JButton prevButton = new JButton("上一题");//previous题

    private JButton nextButton = new JButton("下一题");//next题
    private JButton submitButton = new JButton("提交试卷");//提交按钮

    @Override
    protected void setFontAndSoOn() {
        //设置panel布局管理---->自定义
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.LIGHT_GRAY);
        //设置message区域的位置
        messagePanel.setLayout(null);
        messagePanel.setBounds(680, 10, 300, 550);
        messagePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        //设置button区域的位置
        buttonPanel.setLayout(null);
        buttonPanel.setBounds(16, 470, 650, 90);
        buttonPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        //手动设置每一个组件的位置 字体 背景
        scrollPane.setBounds(16, 10, 650, 450);
        examArea.setFont(new Font("黑体", Font.BOLD, 34));
        examArea.setEnabled(false);//文本域中的文字不能编辑
        //设置message区域中的每一个组件位置 大小 颜色
        pictureLabel.setBounds(10, 10, 280, 230);
        pictureLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        //pictureLabel.setIcon(null);//展示图片信息
        nowNumLabel.setBounds(40, 270, 100, 30);
        nowNumLabel.setFont(new Font("黑体", Font.PLAIN, 20));
        nowNumField.setBounds(150, 270, 100, 30);
        nowNumField.setFont(new Font("黑体", Font.BOLD, 20));
        nowNumField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        nowNumField.setEnabled(false);
        nowNumField.setHorizontalAlignment(JTextField.CENTER);

        totalCountLabel.setBounds(40, 310, 100, 30);
        totalCountLabel.setFont(new Font("黑体", Font.PLAIN, 20));
        totalCountField.setBounds(150, 310, 100, 30);
        totalCountField.setFont(new Font("黑体", Font.BOLD, 20));
        totalCountField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        totalCountField.setEnabled(false);
        totalCountField.setHorizontalAlignment(JTextField.CENTER);

        answerCountLabel.setBounds(40, 350, 100, 30);
        answerCountLabel.setFont(new Font("黑体", Font.PLAIN, 20));
        answerCountField.setBounds(150, 350, 100, 30);
        answerCountField.setFont(new Font("黑体", Font.BOLD, 20));
        answerCountField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        answerCountField.setEnabled(false);
        answerCountField.setHorizontalAlignment(JTextField.CENTER);

        unanswerCountLabel.setBounds(40, 390, 100, 30);
        unanswerCountLabel.setFont(new Font("黑体", Font.PLAIN, 20));
        unanswerCountField.setBounds(150, 390, 100, 30);
        unanswerCountField.setFont(new Font("黑体", Font.BOLD, 20));
        unanswerCountField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        unanswerCountField.setEnabled(false);
        unanswerCountField.setHorizontalAlignment(JTextField.CENTER);

        timeLabel.setBounds(90, 460, 150, 30);
        timeLabel.setFont(new Font("黑体", Font.PLAIN, 20));
        timeLabel.setForeground(Color.BLUE);
        realTimeLabel.setBounds(108, 490, 150, 30);
        realTimeLabel.setFont(new Font("黑体", Font.BOLD, 20));
        realTimeLabel.setForeground(Color.BLUE);

        aButton.setBounds(40, 10, 120, 30);
        //添加鼠标放上器的手势形状
        aButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bButton.setBounds(190, 10, 120, 30);
        bButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cButton.setBounds(340, 10, 120, 30);
        cButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        dButton.setBounds(490, 10, 120, 30);
        dButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        prevButton.setBounds(40, 50, 100, 30);
        //第一题在初始时必须为不能点击的状态
        prevButton.setEnabled(false);
        prevButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        nextButton.setBounds(510, 50, 100, 30);
        nextButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        submitButton.setBounds(276, 50, 100, 30);
        submitButton.setForeground(Color.RED);
        submitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


        //将相应的文本框赋值

        nowNumField.setText((nowNumber + 1) + "");
        totalCountField.setText(totalNumber + "");
        answerCountField.setText(answerNumber + "");
        unanswerCountField.setText(unanswerNumber + "");
        this.showQuestionAndPicture();

    }

    @Override
    protected void addElement() {
        messagePanel.add(pictureLabel);
        messagePanel.add(nowNumLabel);
        messagePanel.add(nowNumField);
        messagePanel.add(totalCountLabel);
        messagePanel.add(totalCountField);
        messagePanel.add(answerCountLabel);
        messagePanel.add(answerCountField);
        messagePanel.add(unanswerCountLabel);
        messagePanel.add(unanswerCountField);
        messagePanel.add(timeLabel);
        messagePanel.add(realTimeLabel);
        buttonPanel.add(aButton);
        buttonPanel.add(bButton);
        buttonPanel.add(cButton);
        buttonPanel.add(dButton);
        buttonPanel.add(prevButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(submitButton);
        mainPanel.add(scrollPane);
        mainPanel.add(messagePanel);
        mainPanel.add(buttonPanel);
        this.add(mainPanel);
    }

    @Override
    protected void addListener() {

        //现在我们给ABCD四个按钮添加点击监听事件
        ActionListener optionListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //写到这里我们要明白 这个是一个接口的内部实现类 所以我们不能这样用this.clearAllOptionColor
                //因为这个方法是ExamFrame的  所以我们要ExamFrame.this.clearAllOptionColor()  甚至不能直接类名.方法名 因为该方法不是静态的
                ExamFrame.this.clearAllOptionColor();

                //点击按钮让其变颜色  首先我们要获取是谁点击了
                JButton button = (JButton) e.getSource();
                button.setBackground(Color.YELLOW);
                //写到这里 就会面临一个问题当你点击很多按钮的时候  就会出现很多按钮都有颜色 此时就要想 我们可以在每次点击按钮的时候就把之前的按钮的的颜色全部清空

                //然后将选择的题目选项存在数组里面  数组下标就是题目的索引  而不是题号
                answer[nowNumber] = button.getText();
                //于是我们设计一个方法 清除所有的按钮颜色 但是要写在监听程序的最上面 因为一进来就要被清除

            }
        };
        //所以我们给四个按钮添加监听事件  添加同一个事件
        aButton.addActionListener(optionListener);
        bButton.addActionListener(optionListener);
        cButton.addActionListener(optionListener);
        dButton.addActionListener(optionListener);


        /*四个按钮的监听事件添加完了 我们接下来添加的就是上一题和下一题的监听事件*/
        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //现在我们为上一题添加点击事件
                //首先我们要清除所有的颜色 展示之前选择的按钮的颜色
                ExamFrame.this.clearAllOptionColor();
                nextButton.setEnabled(true);
                //将下一题的按钮选项设置成可用
                ExamFrame.this.allOptionEnabled(true);
                nowNumber--;
                if (nowNumber == 0) {
                    prevButton.setEnabled(false);
                }

                //显示之前我们选择的按钮选项  我们再封装一个方法用于还原之前的按钮选项
                ExamFrame.this.restoreOption();
                ExamFrame.this.showQuestionAndPicture();
                nowNumField.setText(nowNumber + 1 + "");
                answerCountField.setText(--answerNumber + "");
                unanswerCountField.setText(++unanswerNumber + "");


            }
        });
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //在这里我们要做的事就是清空所有颜色 临界值的判断 展示下一道题
                //先清空颜色
                ExamFrame.this.clearAllOptionColor();
                //让题目序号自增
                nowNumber++;
                System.out.println(nowNumber);
                if (nowNumber == totalNumber) {
                    examArea.setText("题目已经全部答题完毕，请点击提交按钮");
                    nextButton.setEnabled(false);
                    ExamFrame.this.allOptionEnabled(false);
                } else {
                    ExamFrame.this.restoreOption();
                    prevButton.setEnabled(true);
                    //如果不是最后一题 将选择的选项给数组
                    ExamFrame.this.showQuestionAndPicture();
                    //将右侧得当前题号的改了
                    nowNumField.setText((nowNumber + 1) + "");
                }
                //将右侧的未答题和已答题修改
                answerCountField.setText(++answerNumber + "");
                unanswerCountField.setText(--unanswerNumber + "");
            }
        });


        //最后就是点击提交试卷按钮的事件了  那我们把他完善好

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //首先我们要做如下几件事
                //1.先判断是否是误点造成提交试卷的操作  那就采用确认框 返回值是三个  0表示确认  1表示否 2表示取消
                int val= JOptionPane.showConfirmDialog(ExamFrame.this,"确认提交试卷");

                if (val == 0){
                    //如果确认提交 那我们就让考试时间停止 按钮全部无法用  最后显示成绩
                    //首先时间停止  在api里面有一个方法是能够让线程停止的 但是是废弃的 我们不推荐使用controlTimeThread.stop();

                    //那我们就自己设计方法让线程停止 其实那个线程只要终止死循环就行了 那我们就给个标志让他停止就行了
                    //我们已经扥装好了方法了 接下来只要在调用就行了时间就能停止了
                    controlTimeThread.stopControlTimeThread();
                    //让按钮失效
                    ExamFrame.this.clearAllOptionColor();
                    ExamFrame.this.allOptionEnabled(false);
                    prevButton.setEnabled(false);
                    nextButton.setEnabled(false);
                    //批改试卷
                    float score = ExamFrame.this.checkPaper();
                    examArea.setText("考试结束\n您的最终成绩是"+score);
                }
            }
        });


    }

    /*这个方法主要用来批改试卷*/
    private float checkPaper() {

        int score =100;
        //我们上面已经获取到了整张试卷  我们只要遍历ArrayList就是了
        for (int i=0;i<paper.size();i++){

            Question question = paper.get(i);//此时的paper有三个属性 title answer pictue\re

            String realAnswer = question.getAnswer();
            if (!realAnswer.equals(answer[i])){
                score-=100/(paper.size());
            }
        }
        return  score;
    }


    @Override
    protected void setFrameSelf() {
        this.setBounds(260, 130, 1000, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);//不想让窗体拖拽大小
        this.setVisible(true);//最终展示整个窗体

        //启动线程
        controlTimeThread.start();
    }

    //设计一个方法  用来展示图片信息
    public ImageIcon drawImage(String path) {

        //因为返回值是一个ImageIcon对象 所以我们通过ImageIcon类创建一个对象
        ImageIcon imageIcon = new ImageIcon(path);//此时ImageIcon对象是空的，所以我们要给他设置参数并且得到Image
        Image image = imageIcon.getImage().getScaledInstance(280, 230, Image.SCALE_DEFAULT);//设置好Image参数  接下来就传给ImageIcon就行了
        imageIcon.setImage(image);
        return imageIcon;

    }

    //设计一个方法 让之前选择的按钮选项显示
    private void restoreOption() {
        String answerOption = answer[nowNumber];
        //如果有一个题目没选择任何按钮 就直接结束函数返回 不选择任何颜色
        if (answerOption == null) {
            return;
        }
        switch (answerOption) {
            case "A":
                aButton.setBackground(Color.YELLOW);
                break;
            case "B":
                bButton.setBackground(Color.YELLOW);
                break;
            case "C":
                cButton.setBackground(Color.YELLOW);
                break;
            case "D":
                dButton.setBackground(Color.YELLOW);
                break;
        }

    }

    //设计一个方法 让所有的选项失效
    private void allOptionEnabled(boolean key) {
        aButton.setEnabled(key);
        bButton.setEnabled(key);
        cButton.setEnabled(key);
        dButton.setEnabled(key);

    }

    //设计一个方法  清除所有的按钮颜色
    private void clearAllOptionColor() {
        aButton.setBackground(null);
        bButton.setBackground(null);
        cButton.setBackground(null);
        dButton.setBackground(null);
    }

    //设计一个方法  用来展示一道题目   因为是自己类的方法  所以 我们就设置成私有的
    private void showQuestionAndPicture() {
        //从paper中获取当前的一道的题目
        Question question = paper.get(nowNumber);//三个属性 最后的picture可能是空的
        //此时我们得到的paper对象是已经封装好的Qusetion 对象 所以直接.其成员变量就能得到相应的值
        String picture = question.getPicture();//获得图片的路径
        if (picture != null) {//有图片信息
            //上面设计了一个方法去绘制图片  仔细看 就能看懂
            pictureLabel.setIcon(this.drawImage("src//img//" + picture));
        } else {
            pictureLabel.setIcon(null);
        }
        String title = question.getTitle().replace("<br>", "\n   ");
        examArea.setText((nowNumber + 1) + "." + title);
    }


    //现在所有的方法考试页面的代码已经写得差不多了 剩下的就是将倒计时完善好 那我们就要想了 倒计时和考试页面是同时
    //出现的  那就要通过线程来实现倒计时的功能
    //那就需要一个线程 那我们是写一个外部类还是内部类呢 如果写的是外部类 待会儿就回有很多问题需要解决 所以我们选择用内部类
    //1.创建一个线程内部类
    private class ControlTimeThread extends Thread {

        //我们需要一个标志帮我们控制while死循环的结束  那样就可以自己控制线程的停止了

        private boolean flag = true;
        public void stopControlTimeThread(){
            this.flag = false;
        }
        //现在内部类已经给做好了 接下来就是写run方法用来做事情  那我们就要调用start告诉cpu我们已经准备好了 可以分配时间碎片 执行线程了
        //那我们就需要一个线程的属性放在外部类那里  将调用start的方法放在我们展示整个考试页面的时候一起展示
        @Override
        public void run() {
            //run方法就是告诉cpu这个线程主要执行什么方法
            //那我们就是要一直通过while死循环一直执行我们的倒计时 每执行一次就睡眠一秒钟 这样就达到了我们的倒计时目的
            //首先我们得先对时间进行处理  得到时分秒的形式展示在考试页面控制台上
            int hour = time / 60;
            int minute = time % 60;
            int second = 0;
            while (flag) {
                //为了更加的人性化 在不足10的时候前面应该补0 而且字符串需要一直拼串 所以我们用StringBuilder
                //这里不能将这个字符串的拼串写在外面 那样就会造成之前的拼串影响之后的判断 second会一直有>0的值
                StringBuilder builder = new StringBuilder();
                if (hour >= 0 && hour < 10) {
                    builder.append("0");
                }
                builder.append(hour);
                builder.append(":");
                if (minute >= 0 && minute < 10) {
                    builder.append("0");
                }
                builder.append(minute);
                builder.append(":");
                if (second >= 0 && second < 10) {
                    builder.append("0");
                }
                builder.append(second);
                //拼接完成后就要展示给考试界面
                realTimeLabel.setText(builder.toString());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //现在已经过了一秒了 我们要重新修改时间了 那就要判断当前时间的状态
                if (second > 0) {//秒数够计时了 那我们就让秒数自减
                    second--;
                } else {//表示秒数不够用了 那我们就让分钟借  分钟有就借 没有就跟小时借 都没有时间截止
                    if (minute > 0) {//分钟还有
                        minute--;
                        second = 59;
                    } else {//表示没有分钟了
                        if (hour > 0) {//还有小时
                            hour--;
                            minute = 59;
                            second = 59;
                        } else {//小时也没了  考试时间结束了
                            realTimeLabel.setForeground(Color.red);
                            ExamFrame.this.allOptionEnabled(false);
                            prevButton.setEnabled(false);
                            nextButton.setEnabled(false);
                            JOptionPane.showMessageDialog(ExamFrame.this, "考试时间结束，请提交试卷");
                            break;
                        }
                    }
                }
            }
        }


    }






}
