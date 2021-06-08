package comp3350.AAS.database;

import java.util.ArrayList;
import comp3350.AAS.object.Quiz;

public class QuizDatabase {
    private ArrayList<Quiz> quizList;

    public QuizDatabase(){
        quizList=new ArrayList<Quiz>();
    }

    public void addQuiz(String question, String A, String B, String C, String key){
        quizList.add(new Quiz(question, A, B, C, key));
    }

    public ArrayList<Quiz> getQuizList(){
        return quizList;
    }

}

