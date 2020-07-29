package util;

import domain.Question;

import java.io.*;

import java.util.HashSet;

public class QuestionFileReader {

    //创建HashSet就是为了保证将所有的题目加载到缓存里面
    private HashSet<Question> questionBox = new HashSet<>();

    {

        FileReader fileReader = null;
        BufferedReader bufferedReader = null;

        try {
            //我们需要从题库里面去读取题目  那就涉及到文件输入流
            //那就需要一行一行的读取文件  那就要创建高级文件输入流BufferReader
            File file = new File("src//dbfile//Question.txt");
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            //读取一行
            String message = bufferedReader.readLine();
            while (message != null) {
                String[] values = message.split("#");
                if(values.length==2){
                    Question question = new Question(values[0],values[1]);
                    // 让抽取的题目没有重复 -->HashSet(重写了equals  hashCode 方法)
                    questionBox.add(question);
                }else if(values.length==3){
                    Question question = new Question(values[0],values[1],values[2]);
                    // 让抽取的题目没有重复 -->HashSet(重写了equals  hashCode 方法)
                    questionBox.add(question);
                }

                message = bufferedReader.readLine();
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }

        }


    }



    public  HashSet<Question> getQuestionBox(){
        return questionBox;
    }
}
