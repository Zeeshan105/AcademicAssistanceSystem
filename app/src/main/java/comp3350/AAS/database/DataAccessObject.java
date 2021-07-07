package comp3350.AAS.database;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLWarning;
import java.sql.DatabaseMetaData;
import java.util.ArrayList;

import comp3350.AAS.object.*;

public class DataAccessObject implements DataAccess {
    private Statement st1, st2, st3;
    private Connection c1;
    private ResultSet rs2, rs3, rs4, rs5;

    private String dbName;
    private String dbType;

//    private ArrayList<Quiz> students;
//    private ArrayList<Question> courses;
//    private ArrayList<FlashCard> scs;

    private String cmdString;
    private int updateCount;
    private String result;
    private static String EOF = "  ";

    public DataAccessObject(String dbName) {
        this.dbName = dbName;
    }

    public void open(String dbPath) {
        String url;
        try {
            // Setup for HSQL
            dbType = "HSQL";
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
            url = "jdbc:hsqldb:file:" + dbPath; // stored on disk mode
            c1 = DriverManager.getConnection(url, "SA", "");
            st1 = c1.createStatement();
            st2 = c1.createStatement();
            st3 = c1.createStatement();

        } catch (Exception e) {
            processSQLError(e);
        }
        System.out.println("Opened " + dbType + " database " + dbPath);
    }

    public void close() {
        try {    // commit all changes to the database
            cmdString = "shutdown compact";
            rs2 = st1.executeQuery(cmdString);
            c1.close();
        } catch (Exception e) {
            processSQLError(e);
        }
        System.out.println("Closed " + dbType + " database " + dbName);
    }


    public void addCard(String title, String desc, String folderName){

    }

    public ArrayList<String> getFolderNames(){
        return null;
    }

    public ArrayList<CardFolder> getFolders(){
        return null;
    }

    public void deleteFolder(int index){

    }

    public void addQuiz(Question question, String name) {
        String values;

        result = null;
        try {            // add question to db
            values = "'" + question.getQuestion()
                    + "', '" + question.getOption1()
                    + "', '" + question.getOption2()
                    + "', '" + question.getOption3()
                    + "', '" + question.getKey()
                    + "'";
            cmdString = "INSERT INTO QUESTION" + " Values(" + values + ")";
            System.out.println(cmdString);
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        } catch (Exception e) {
            result = processSQLError(e);
        }
        try {         // link with quiz
            cmdString = "INSERT INTO QUIZ VALUES('" + name + "','" + question.getQuestion() + "')";
            System.out.println(cmdString);
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        } catch (Exception e) {
            result = processSQLError(e);
        }
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

    public String checkWarning(Statement st, int updateCount)
    {
        String result;

        result = null;
        try
        {
            SQLWarning warning = st.getWarnings();
            if (warning != null)
            {
                result = warning.getMessage();
            }
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
        if (updateCount != 1)
        {
            result = "Tuple not inserted correctly.";
        }
        return result;
    }

    public String processSQLError(Exception e) {
        String result = "*** SQL Error: " + e.getMessage();

        // Remember, this will NOT be seen by the user!
        e.printStackTrace();

        return result;
    }
}