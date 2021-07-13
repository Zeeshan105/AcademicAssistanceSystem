package comp3350.AAS.database;

import java.util.ArrayList;
import java.util.List;

import comp3350.AAS.object.FlashCard;
import comp3350.AAS.object.Question;
import comp3350.AAS.object.Quiz;
import comp3350.AAS.object.CardFolder;

public interface DataAccess {
    void open(String string);

    void close();
// from original Card DataBase
    void addCard(String title,String desc, String folderName);
    void getFolderList(ArrayList<CardFolder> folders);
    void deleteFolder(int index);

// from original Quiz Database
    void addQuiz(Question question, String name);
    ArrayList<Quiz> getQuizList();
    ArrayList<String> getAllQuizName();
    ArrayList<String> generateQuizGradesList();
    int getCompletedQuizzes();
    String numberCompletedQuizzes();
    void resetQuizzes();
    void updateQuiz(String quizName, double grade);
}
