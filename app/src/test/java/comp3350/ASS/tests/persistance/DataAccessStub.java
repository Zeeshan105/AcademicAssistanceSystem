package comp3350.ASS.tests.persistance;

import java.util.ArrayList;
import java.util.List;
import comp3350.AAS.application.Main;
import comp3350.AAS.object.Quiz;
import comp3350.AAS.object.CardFolder;
import comp3350.AAS.object.FlashCard;
import comp3350.AAS.object.Question;
import comp3350.AAS.database.DataAccess;

public class DataAccessStub implements DataAccess {
    private String dbName;
    private String dbType = "stub";
    private ArrayList<Quiz> quizzes;
    private ArrayList<CardFolder> cardFolders;
    private ArrayList<FlashCard> flashCards;
    private ArrayList<Question> questions;

    public DataAccessStub(String dbName)
    {
        this.dbName = dbName;
    }

    public DataAccessStub()
    {
        this(Main.dbName);
    }

    public void open(String string) {
        Quiz quiz;
        CardFolder cardFolder;
        Question question;
        FlashCard flashCard;

        quizzes = new ArrayList<Quiz>();
        quiz = new Quiz("Quiz 1");
        quizzes.add(quiz);
        quiz = new Quiz("Quiz 2");
        quizzes.add(quiz);
        quiz = new Quiz("Quiz 3");
        quizzes.add(quiz);
        quiz = new Quiz("Quiz 4");
        quizzes.add(quiz);
        quiz = new Quiz("Quiz 5");
        quizzes.add(quiz);

        questions = new ArrayList<Question>();
        question = new Question("Q1","1","2","3","1");
        questions.add(question);
        question = new Question("Q2","2","2","1","2");
        questions.add(question);
        question = new Question("Q3","3","2","1","1");
        questions.add(question);
        question = new Question("Q4","1","2","3","4");
        questions.add(question);
        question = new Question("Q5","1","1","3","1");
        questions.add(question);


    }
    public void close() {

    }
    public ArrayList<CardFolder> getFolders() {
        return null;
    }
    public ArrayList<String> getFolderNames() {
        return null;
    }
    public void deleteFolder(int index) {

    }
    public void addCard(String title, String desc, String folderName) {

    }
    public void addQuiz(Question question, String name) {

    }
    public ArrayList<Quiz> getQuizList() {
        return null;
    }
    public ArrayList<String> getAllQuizName() {
        return null;
    }
    public ArrayList<String> generateQuizGradesList() {
        return null;
    }
    public int getCompletedQuizzes() {
        return 0;
    }
    public void setCompletedQuizzes(int numCompleted) {

    }
}
