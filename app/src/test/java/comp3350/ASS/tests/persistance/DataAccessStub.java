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
    private ArrayList<Quiz> quiz;
    private ArrayList<CardFolder> cardFolder;
    private ArrayList<FlashCard> flashCard;
    private ArrayList<Question> question;

    public DataAccessStub(String dbName)
    {
        this.dbName = dbName;
    }

    public DataAccessStub()
    {
        this(Main.dbName);
    }

    public void open(String string) {

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
