package comp3350.AAS.object;

import java.util.ArrayList;

public class Quiz {
    private ArrayList<Question> questionList;
    private String quizName;
    private int quizResult;

    public Quiz(String name){
        questionList=new ArrayList<>();
        quizName=name;
        quizResult = 0;
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

    public int getQuizSize() { return questionList.size(); }

    public int setQuizResult(int result) { return quizResult = result; }

    public int getQuizResult() { return quizResult; }



}
