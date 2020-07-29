package domain;

public class Question {
    private String title;
    private String answer;
    private String picture;


    public int hashCode(){
        String thisTitle = this.title.substring(0,this.title.indexOf("<br>"));
        return thisTitle.hashCode();
    }


    //想要将question对象存入HashSet集合内  让set帮我们去掉重复元素
    //因为要保证按照自己定义的规则去比较两个对象是否是相同的就要重写equals和hashCode
    public boolean equals(Object obj){

        if (this==obj){
            return true;
        }
        if (obj instanceof Question){

            Question anotherQuestion = (Question)obj;
            String thisTitle = this.title.substring(0,this.title.indexOf("<br>"));
            String anotherTitle =anotherQuestion.title.substring(0,anotherQuestion.title.indexOf("<br>"));
            if (thisTitle.equals(anotherTitle)){
                return true;
            }
        }

        return false;
    }


    public  Question(){}
    public  Question(String title,String answer){
        this.title = title;
        this.answer = answer;
    }
    public  Question(String title,String answer,String picture){
        this.title = title;
        this.answer = answer;
        this.picture = picture;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public  void setPicture(){
        this.picture = picture;
    }
    public String getPicture(){
        return picture;
    }

}
