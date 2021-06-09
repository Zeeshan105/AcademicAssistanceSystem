package comp3350.AAS.database;

import java.util.ArrayList;

import comp3350.AAS.object.Question;
import comp3350.AAS.object.Quiz;

public class QuizDatabase {
    private ArrayList<Quiz> quizList;

    public QuizDatabase(){
        quizList=new ArrayList<Quiz>();
    }

    public void addQuiz(Question question, String name){
        int index=-1;
        for (int i = 0; i < quizList.size(); i++) {
            if (quizList.get(i).getQuizName().compareTo(name)==0) {
                index=i;
                break;
            }
        }

        if (index==-1){
            Quiz quiz = new Quiz(name);
            quiz.addQuestion(question);
            quizList.add(quiz);
        }else {
            Quiz quiz = quizList.get(index);
            quiz.addQuestion(question);
        }
    }

    public ArrayList<Quiz> getQuizList() {
        return quizList;
    }

    public String[] getAllQuizName(){
        String [] names=new String[quizList.size()];
        for (int i = 0; i < quizList.size(); i++) {
            names[i] = quizList.get(i).getQuizName();
        }
        return names;
    }

}

