package service;


import dao.Userdao;
import domain.User;
import util.MySpring;

public class UserService {

    //由于我们现在有了dao层里面的方法selectOne方法作为支持  我们只要拿到对象去匹配文本框输入的信息就行了

    //所以我们现在需要一个dao层的对象去操作相应的方法  也就是在其他类里面创建另外一个对象那个
    //private Userdao dao = new Userdao();
    // 现在我们已经创建了一个方法 所以就不用自己new对象了 只要调getBean方法去获取就行了
    private  Userdao dao = (Userdao) MySpring.getBean("dao.Userdao");
    //设计一个方法---负责登录

    //现在我们要想一个问题  虽然已经实现了登录功能  那每次登录都要去创建流  就显得很麻烦
    //所以我们可以先创建一个缓存  有效的提高效率  如果同一时间有很多人访问那就意义不一样了
    public String login(String account,String password){
        //这里要明白一个点  不用再去验证账号是否相等 因为传进来的参数本身就是文本框 只要不等于空 就等于输入的名字存在
        //又因为selectOne 得到的就是账号的对象 只要将密码匹配成功就可以了
        User user = dao.selectOne(account);
        if(user!=null){
            if(user.getPassword().equals(password)){
                return "登录成功";
            }
        }
        return "用户名或密码错误";
    }
}
