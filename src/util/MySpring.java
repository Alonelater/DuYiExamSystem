package util;

import javafx.beans.property.adapter.JavaBeanBooleanProperty;

import java.util.HashMap;

public class MySpring {

    //设计一个类  获取任何一个类的对象
    //返回值对象  甚至是泛型  参数 String类的全名

    /**
     * 现在我们要做的将对象设计成单例的  因为我们要想   现在我们做的是单机版的  如果是网页版的
     * 那就是服务器给我们做操作  如果一直创建对象  服务器都受不了  所以我们要将创建对象设计成单例  那我们就要在创建对象的
     * 时候做判断  先将所有创建出来的集合 放在HashMap里面   有这个对象就不创建  直接从集合里面拿  没有就创建
     * 那我们就先创建一个HashMap集合   键就是类名  值就是对象
     * @param className
     * @return
     */

    private static HashMap<String,Object> beanBox = new HashMap<>();
    //现在解决返回值类型的问题 因为每次通过getBean方法产生对象  都要造型才能接收对象
    //我们现在将getBean方法前面加上<T>T  后面的T表示返回值类型 前面的尖括号里面的表示你用什么接收我就帮你自动造型成你接收的形式
    public static <T>T getBean(String className){
    //public static Object getBean(String className){
        T obj = null;
        //Object obj = null;


        try {

            //所以上来先取集合里面的值  如果没有就创建  有的话就直接返回
            obj =(T)beanBox.get(className);
            if(obj==null){
                //通过类名获取Class
                Class clazz = Class.forName(className);
                //通过反射产生一个对象
                obj=(T)clazz.newInstance();
                //新的对象存入集合
                beanBox.put(className,obj);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;


    }
}
