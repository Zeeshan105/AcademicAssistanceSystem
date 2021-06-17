package comp3350.AAS.database;

import java.util.ArrayList;
import comp3350.AAS.object.Question;
import comp3350.AAS.object.Quiz;

public class QuizDatabase {

    private ArrayList<Quiz> quizList;
    private int completedQuizzes;

    public QuizDatabase(){
        quizList=new ArrayList<Quiz>();
        completedQuizzes = 0;
    }

    public void addQuiz(Question question, String name){

        int index=-1; // Place holder index to check if the question belongs to an existing or new quiz.

        for (int i = 0; i < quizList.size(); i++) {
            Quiz selectedQuiz = quizList.get(i);
            if (selectedQuiz.getQuizName().compareTo(name)==0) {
                index=i;
                break;
            }
        }

        if (index==-1) {
            Quiz quiz = new Quiz(name);
            quiz.addQuestion(question);
            quizList.add(quiz);
        }
        else {
            Quiz quiz = quizList.get(index);
            quiz.addQuestion(question);
        }

    }

    public ArrayList<Quiz> getQuizList() {
        return quizList;
    }

    public ArrayList<String> getAllQuizName(){
        ArrayList<String> quizNames = new ArrayList<>();
        for (int i = 0; i < quizList.size(); i++) {
            quizNames.add(quizList.get(i).getQuizName());
        }
        return quizNames;
    }

    public ArrayList<String> generateQuizGradesList(){
        ArrayList<String> completedQuizList = new ArrayList<String>();
        for (int i = 0; i < quizList.size(); i++) {
            if (quizList.get(i).isComplete()) {
                completedQuizList.add( quizList.get(i).getQuizName() + "\nMark: " + (int)quizList.get(i).getQuizResult() + "/" + quizList.get(i).getQuestionCount() );
            }
        }
        return completedQuizList;
    }

    public int getCompletedQuizzes() { return completedQuizzes; }

    public void setCompletedQuizzes(int numCompleted) { completedQuizzes = numCompleted; }

}

