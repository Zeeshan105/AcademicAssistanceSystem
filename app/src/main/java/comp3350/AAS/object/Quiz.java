package comp3350.AAS.object;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Quiz {

    private ArrayList<Question> questionList;
    private String quizName;
    private double quizResult;
    private boolean quizCompleted;

    public Quiz(String name){
        questionList=new ArrayList<>();
        quizName=name;
        quizResult = 0;
        quizCompleted = false;
    }

    public void addQuestion(Question question){
        questionList.add(question);
    }

    public ArrayList<Question> getQuestionList(){
        return questionList;
    }

    public int getQuestionCount() { return questionList.size(); }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizResult(double result) {

        if (result<0){
            result=0;
        }
        quizResult = result;

    }

    public double getQuizResult() { return quizResult; }

    public void setCompleteStatus(boolean completed) { quizCompleted = completed; }

    public boolean isComplete() { return quizCompleted; }

    public String toString(){
        String result = quizName;
       for( Question quest : questionList){
          result = result.concat(" " + quest.toString());
       }
       return result;
    }
}
