package comp3350.AAS.object;

import java.util.ArrayList;

public class Quiz {
    private ArrayList<Question> questionList;
    private String quizName;

    public Quiz(String name){
        questionList=new ArrayList<>();
        quizName=name;
    }

    public void addQuestion(Question question){
        questionList.add(question);
    }

    public ArrayList<Question> getQuestionList(){
        return questionList;
    }

    public String getQuizName() {
        return quizName;
    }

}
