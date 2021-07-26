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

    public AccessQuiz(){
        dataAccess = Services.getDataAccess(Main.dbName);
        quizList = dataAccess.getQuizList();
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

    public String getNumCompletedQuiz(){
        return dataAccess.getNumCompletedQuiz();
    }

    public void updateStatus(String quizName, String status){
        dataAccess.resetQuizStatus(quizName, status);
    }

    public void updateGrade(String quizName, double result) {
        dataAccess.updateQuiz(quizName, result);
    }

    public void deleteQuiz(int index){
        dataAccess.deleteQuiz(index);
    }

    public String getAverageGrade(){
        return dataAccess.getAverageGrade();
    }

    public String getHighestGrade(){
        return dataAccess.getHighestGrade();
    }

    public String getLowestGrade(){
        return dataAccess.getLowestGrade();
    }

}

