package util;

//类的目的就是为了增加一个个缓存机制
//程序启动的时候将User.txt全部一次性读出来
//以后做查询的时候直接读取缓存的数据  其实缓存有很多好处
//因为我们操作文件不好改动一行里面的某一部分 所以我们可以先将缓存里面改动数据全部写会文件里面实现文件的更新

import domain.User;

import java.io.*;
import java.util.HashMap;

public class UserFileReader {

    //现在想要 将文件里面的信息全部读出来 那就不止一行了
    //那存在哪里呢  集合  选什么集合好呢  HashMap  因为查看方便啊
    //键值用什么呢？  键用账号  值最好用整个对象 因为以后存取的不仅仅是密码呢  还有其他东西
     /**
      *
      * 此时我们要考虑到  缓存只要一份 不要每个UserFileReader 一创建就有一个缓存  所以我们将 HashMap添加一个static
      * 那就要将块也要添加static  让他们同时加载
      *
      */
    private static HashMap<String, User> userBox = new HashMap<String, User>();
    //现在集合里面没东西  所以我们以后要在程序加载的时候就有东西  所以就应该在块里面将集合加上元素

    //现在缓存已经创建好了 就是块里面的东西 现在只要我们提供一个方法就能使用集合里面的东西了  由于这个集合里面的值是User对象
    //所以返回值是一个User



    public static User getUser(String account){
        User user =userBox.get(account);
        return user;
    }



    //在类加载的时候先加载
    static {

        FileReader fileReader = null;
        BufferedReader bufferedReader= null;
        try {
            File file = new File("src//dbfile//User.txt");
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);


            String message=bufferedReader.readLine();
            while (message!=null){
               String[] values= message.split("-");
               User user = new User(values[0],values[1]);
               userBox.put(values[0],user);
               message = bufferedReader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {if (fileReader!=null){
                    fileReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (bufferedReader!=null){
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
