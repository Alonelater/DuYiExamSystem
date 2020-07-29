package dao;

import domain.User;
import util.UserFileReader;

import java.io.*;

//持久层  数据的持久化
    //只负责我数据的读和写  不负责处理逻辑
    //现阶段看到的方法内部  通常都是包含了I/O
    //以后这个层次的方法看到的内部代码都是JDBC
public class Userdao {

    //负责查询一个人的信息  参数的账号  返回值是一个对象


    //dao层就是去底层去操作文件  数据库  等等
    public User selectOne(String account) {


        //现在我们有了缓存  我们要得到一个对象就不用去文件里面去得到一个user了 只要
            User user = UserFileReader.getUser(account);
            return user;

            /*
            User user = null;
            FileReader fileReader = null;
            BufferedReader bufferedReader = null;
            try {
                //先创建一个文件对象 通过字符流(FileReader)进行读取
                //BufferedReader 是字符流的高级体现  需要低级流的支持 那就是fileReader
                File file = new File("src//dbfile//User.txt");
                fileReader = new FileReader(file);
                bufferedReader = new BufferedReader(fileReader);

                //高级流里面有一个方法 就读取一行的操作  返回的是字符串  通过方法将字符串拆分后将匹配正确的信息存入User对象中
                //最后关闭流
                String message= bufferedReader.readLine();
                while (message!=null){
                    String[] values=message.split("-");
                    if(values[0].equals(account)){
                       user= new User(values[0],values[1]);
                       break;
                    }
                    message = bufferedReader.readLine();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                try {
                    if(fileReader!=null){
                        fileReader.close();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    if(bufferedReader!=null){
                        fileReader.close();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return user;
        }
*/

}
}