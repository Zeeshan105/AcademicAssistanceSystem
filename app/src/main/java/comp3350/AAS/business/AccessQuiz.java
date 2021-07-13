package comp3350.AAS.business;

import java.util.ArrayList;

import comp3350.AAS.application.Main;
import comp3350.AAS.application.Services;
import comp3350.AAS.database.DataAccess;
import comp3350.AAS.object.Question;
import comp3350.AAS.object.Quiz;

public class AccessQuiz {
    private DataAccess dataAccess;
    private ArrayList<Quiz> quizList;
    private Quiz quiz;

    public AccessQuiz(){
        dataAccess = Services.getDataAccess(Main.dbName);
        quizList = dataAccess.getQuizList();
        quiz = null;
    }

    public void addQuiz(Question question, String name){
        dataAccess.addQuiz(question, name);
    }

    public ArrayList<Quiz> getQuizList(){
        return quizList;
    }

    public ArrayList<String> getQuizNames(){
        return dataAccess.getAllQuizName();
    }

    public ArrayList<String> getGradeQuizList(){
        return dataAccess.generateQuizGradesList();
    }

    public int getCompletedQuizzes(){
        return dataAccess.getCompletedQuizzes();
    }

    public String numberCompletedQuizzes(){
        return dataAccess.numberCompletedQuizzes();
    }

    public void resetQuizzes(){
        dataAccess.resetQuizzes();
    }

    public void updateGrade(String quizName, double result) { dataAccess.updateQuiz(quizName, result); }
}
