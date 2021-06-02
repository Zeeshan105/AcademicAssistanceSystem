package comp3350.AAS.database;

import java.util.ArrayList;
import comp3350.AAS.object.Quiz;

public class QuizDatabase {
    private ArrayList<Quiz> quizList;

    public QuizDatabase(){

    }

    public void open(){
        Quiz quiz;

        quizList=new ArrayList<Quiz>();
        quiz=new Quiz("1+1=?", "0", "1", "2", "2");
        quizList.add(quiz);
        quiz=new Quiz("1+2=?", "0", "3", "6", "3");
        quizList.add(quiz);
        quiz=new Quiz("1+0=?", "0", "1", "2", "1");
        quizList.add(quiz);
        quiz=new Quiz("Which city is the capital of Canada?", "Ottawa", "Vancouver", "Toronto", "Ottawa");
        quizList.add(quiz);
        quiz=new Quiz("What is the coldest degree Celsius in Winnipeg in winter?", "-30", "-50", "-70", "-50");
        quizList.add(quiz);

        System.out.println("Opened quizzes database");
    }

    
}

