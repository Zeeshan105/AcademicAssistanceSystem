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
    private ResultSet rs1,rs2, rs3, rs4;

    private String dbName;
    private String dbType;

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
        String values;
        result = null;

        try {
            // add flash card to db
            values = "'" + title
                    + "', '" + desc
                    + "'";
            cmdString = "INSERT INTO FLASHCARD VALUES(" + values + ")";
            System.out.println(cmdString);
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        } catch (Exception e) {
            System.out.println(processSQLError(e));
        }

        try {
            // link with Folder
            cmdString = "INSERT INTO FOLDER VALUES('" + folderName + "','" + title + "')";
            System.out.println(cmdString);
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        } catch (Exception e) {
            System.out.println(processSQLError(e));
        }
    }

    public ArrayList<String> getFolderNames(){
        ArrayList<String> folderNameList = new ArrayList<>();

        try {
            cmdString = "SELECT FOLDERNAME FROM FLASHCARD JOIN FOLDER ON FLASHCARD.TITLE = FOLDER.TITLE";
            rs1 = st1.executeQuery(cmdString);

            while (rs1.next()){
                String folderName = rs1.getString("FOLDERNAME");

                if(!folderNameList.contains(folderName)){
                    folderNameList.add(folderName);
                }
            }
        }catch (Exception e){
            System.out.println(processSQLError(e));
        }
        return folderNameList;
    }

    public ArrayList<CardFolder> getFolders(){
        ArrayList<CardFolder> folderList = new ArrayList<>();
        ArrayList<String> existingFolder = new ArrayList<>();

        try {   // create all folders objects
            cmdString = "SELECT * FROM FOLDER JOIN FLASHCARD ON FLASHCARD.TITLE = FOLDER.TITLE";
            rs1 = st1.executeQuery(cmdString);

            while (rs1.next()){
                String folderName = rs1.getString("FOLDERNAME");
                String title = rs1.getString("TITLE");
                String description = rs1.getString("DESCRIPTION");

                if(existingFolder.contains(folderName)){
                    for(CardFolder folder : folderList) {
                        if(folder.getFolderName().equals(folderName)){
                            folder.addCard(title, description);
                        }
                    }
                } else {
                    CardFolder newFolder = new CardFolder(folderName);
                    newFolder.addCard(title, description);
                    folderList.add(newFolder);
                    existingFolder.add(folderName);
                }
            }
        }catch (Exception e){
            System.out.println(processSQLError(e));
        }
        return folderList;
    }

    public void deleteFolder(int index){
        String folderName;
        result = null;

        try {
            folderName = getFolderNames().get(index);
            cmdString = "DELETE FROM FOLDER WHERE FOLDERNAME=" +folderName;
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        } catch (Exception e) {
            System.out.println(processSQLError(e));
            System.out.println(result);
        }
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
            System.out.println(processSQLError(e));
            System.out.println(result);
        }

        try {         // link with quiz
            cmdString = "INSERT INTO QUIZ VALUES('" + name + "','" + question.getQuestion() + "')";
            System.out.println(cmdString);
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        } catch (Exception e) {
            System.out.println(processSQLError(e));
            System.out.println(result);
        }
    }

    public ArrayList<Quiz> getQuizList() {
        ArrayList<Quiz> quizList = new ArrayList<>();
        ArrayList<String> existingQuiz = new ArrayList<>();

        try {   // create all question objects
            cmdString = "SELECT * FROM QUESTION JOIN QUIZ ON QUESTION.QUESTIONCONTENT = QUIZ.QUESTIONCONTENT";
            rs1 = st1.executeQuery(cmdString);

            while (rs1.next()){
                String content = rs1.getString("QUESTIONCONTENT");
                String option1 = rs1.getString("OPTION1");
                String option2 = rs1.getString("OPTION2");
                String option3 = rs1.getString("OPTION3");
                String key = rs1.getString("ANSWER");
                String quizName = rs1.getString("QUIZNAME");
                Question newQuestion = new Question(content,option1,option2,option3,key);

                if( existingQuiz.contains(quizName)){
                    for( Quiz quiz : quizList) {
                        if( quiz.getQuizName().equals(quizName)){
                           quiz.addQuestion(newQuestion);
                        }
                    }
                } else {
                    Quiz newQuiz = new Quiz(quizName);
                    newQuiz.addQuestion(newQuestion);
                    quizList.add(newQuiz);
                    existingQuiz.add(quizName);
                }
            }

        }catch (Exception e){
            System.out.println(processSQLError(e));
        }
        return quizList;
    }

    public ArrayList<String> getAllQuizName() {
        ArrayList<String> quizNameList = new ArrayList<>();

        try {
            cmdString = "SELECT QUIZNAME FROM QUESTION JOIN QUIZ ON QUESTION.QUESTIONCONTENT = QUIZ.QUESTIONCONTENT";
            rs1 = st1.executeQuery(cmdString);

            while (rs1.next()){
                String quizName = rs1.getString("QUIZNAME");

                if(!quizNameList.contains(quizName)){
                    quizNameList.add(quizName);
                }
            }

        }catch (Exception e){
            System.out.println(processSQLError(e));
        }
        return quizNameList;
    }

    public ArrayList<String> generateQuizGradesList() {
        ArrayList<String> gradeList = new ArrayList<>();

        try {
            cmdString = "SELECT COUNT(QUESTIONCONTENT) AS COUNTS, QUIZNAME, COMPLETE, RESULT FROM QUIZ GROUP BY QUIZNAME, COMPLETE, RESULT HAVING COMPLETE = TRUE";
            rs1 = st1.executeQuery(cmdString);

            while(rs1.next()){
                String count = rs1.getString("COUNTS");
                String name = rs1.getString("QUIZNAME");
                String result = rs1.getString("RESULT");
                gradeList.add(name + "\nMark: " + result + "/" + count);
            }
        }catch(Exception e){
            System.out.println(processSQLError(e));
        }

        return gradeList;
    }

    public int getCompletedQuizzes() {
        int numCompleted = 0;

        try{
            cmdString = "SELECT COUNT(*) AS ANSWER FROM (SELECT COUNT(*) AS COUNTS, COUNT(QUIZNAME) AS QUIZNAME, COMPLETE, RESULT FROM QUIZ GROUP BY COMPLETE, RESULT HAVING COMPLETE = TRUE)";
            rs1 = st1.executeQuery(cmdString);

            while(rs1.next()){
                numCompleted = rs1.getInt("ANSWER");
            }
        }catch(Exception e){
            System.out.println(processSQLError(e));
        }

        return numCompleted;
    }

    public void setCompletedQuizzes(int numCompleted) {

    }

    public String checkWarning(Statement st, int updateCount) {
        String result = null;

        try {
            SQLWarning warning = st.getWarnings();

            if (warning != null) {
                result = warning.getMessage();
            }
        } catch (Exception e) {
            result = processSQLError(e);
        }

        if (updateCount != 1) {
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