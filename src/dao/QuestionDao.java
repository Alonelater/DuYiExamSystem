package dao;

import domain.Question;

import util.MySpring;
import util.QuestionFileReader;


import java.util.ArrayList;

import java.util.HashSet;
import java.util.Random;

public class QuestionDao {
    /**
     * 1.负责读文件
     * 2.随机生成一套试卷  题库里面有10道题目 随机选五道
     */


    //获取试卷的缓存机制对象
     private  QuestionFileReader  reader = MySpring.getBean("util.QuestionFileReader");
    //首先在QuestionFileReader 里面用了一个HashSet集合  有两个好处
    //1.去掉Question.txt 里面题干相同的题 这样再加入缓存中就能保证所有的题目都不一样 因为我们重写了equals 和hashCode方法
    //2.但是我们调用QuestionFileReader.getQuestionBox方法是这个得到的questionBox是无序的  所以我们在随机抽取的时候就面临了问题了
    // 我们这时候就要想到HashSet和ArrayList是同一个Collection接口下的  就可以相互转化先将其构造成有序的ArrayList

    //将缓存中的集合转化为list集合  目的就是有索引号  那样就可以随机找寻元素的时候存在位置了
    private  ArrayList<Question> hasIndexQuestion = new ArrayList<>(reader.getQuestionBox());
    /***
     * 这时候就要思考  我们对于试卷就是要循环遍历，那么这次是五道题目，下次是10道题目
     *  那用什么作为存储题目的容器呢 因为长度不可变的特性 所以我们不能用数组 我们选择长度可变的集合
     *  那arrayList  linkedList hashMap 等等用什么好呢
     *  因为我们试卷是只要遍历  那么就用效率高的arrayList
     *  分析完了 我们就要用方法全实现了
     *
     */

     public ArrayList<Question> getPaper(int num){


         //用来存最后的题目
         HashSet<Question> paper = new HashSet<>();


        while (paper.size()!=num){
            Random r =new Random();
            int index = r.nextInt(this.hasIndexQuestion.size());
            paper.add(this.hasIndexQuestion.get(index));
        }
        //由于我们既要保证paper里面的题目不重复所以利用了HashSet存最后的试卷 又要保证学生遍历试卷的速度快
         //所以返回值是arraylist 我们就需要将已经有5道题但类型是HashSet的paper构建回ArrayList
         return new ArrayList<Question>(paper);
     }

}
