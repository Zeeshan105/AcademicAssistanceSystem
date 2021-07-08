package comp3350.ASS.tests.persistance;

import junit.framework.TestCase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import comp3350.AAS.application.Main;
import comp3350.AAS.object.Question;
import comp3350.AAS.object.FlashCard;
import comp3350.AAS.object.CardFolder;
import comp3350.AAS.object.Quiz;
import comp3350.AAS.database.DataAccess;
import comp3350.AAS.database.DataAccessObject;

public class DataAccessTest extends TestCase {
    private DataAccess dataAccess;

    public DataAccessTest(String arg0) {
        super(arg0);
    }

    public void setUp() {
        //System.out.println("\nStarting Persistence test DataAccess (using stub)");
        //dataAccess = new DataAccessStub();
        //dataAccess.open("Stub");

        System.out.println("\nStarting Persistence test DataAccess (using HSQLDB)");
         dataAccess = new DataAccessObject(Main.dbName);
         dataAccess.open(Main.getDBPathName());
    }

    public void tearDown() {
        //System.out.println("Finished Persistence test DataAccess (using stub)");

        dataAccess.close();
        System.out.println("Finished Persistence test DataAccess (using HSQLDB)");
    }

//    INSERT INTO QUESTION VALUES('this is a test, working?','Yes','No','Maybe','Yes')
//    INSERT INTO QUIZ VALUES('Historical','this is a test, working?')

    public void testQuizName(){
        System.out.println("\tStart test quiz name...");
        ArrayList<String> quizNameList = dataAccess.getAllQuizName();

        assertEquals("Historical", quizNameList.get(0));
        assertEquals("Geographic", quizNameList.get(1));
        assertEquals("Math", quizNameList.get(2));

        System.out.println("\tPASS!");
    }

    public void testQuestionContent() {
        System.out.println("\tStart test all question content...");
        ArrayList<Quiz> quizList = dataAccess.getQuizList();
        ArrayList<String> questionContent = new ArrayList<>();

        // First quiz
        ArrayList<Question> firstQuestionList = quizList.get(0).getQuestionList();

        for (int i = 0; i < firstQuestionList.size(); i++) {
            questionContent.add(firstQuestionList.get(i).getQuestion());
        }

        assertEquals("Number of Canadians participating in World War II", questionContent.get(0));
        assertEquals("What year was the founding of Canada?", questionContent.get(1));
        assertEquals("Which aircraft manufacture belong to Canada?", questionContent.get(2));
        questionContent.clear();

        // Second quiz
        ArrayList<Question> secondQuestionList = quizList.get(1).getQuestionList();

        for (int i = 0; i < secondQuestionList.size(); i++) {
            questionContent.add(secondQuestionList.get(i).getQuestion());
        }

        assertEquals("What is the area of Canada in square kilometers?", questionContent.get(0));
        assertEquals("What is the capital city of Canada?", questionContent.get(1));
        questionContent.clear();

        // Third quiz
        ArrayList<Question> thirdQuestionList = quizList.get(2).getQuestionList();

        for (int i = 0; i < thirdQuestionList.size(); i++) {
            questionContent.add(thirdQuestionList.get(i).getQuestion());
        }

        assertEquals("What is the positive result of square root of 36?", questionContent.get(0));
        assertEquals("What is the result of 1+1?", questionContent.get(1));
        questionContent.clear();

        System.out.println("\tPASS!");
    }

    public void testQuestionOptionInFirstQuiz() {
        System.out.println("\tStart test 1st quiz with its questions' options and key...");
        ArrayList<Quiz> quizList = dataAccess.getQuizList();
        ArrayList<String> option = new ArrayList<>();
        int index=0;

        ArrayList<Question> questionList = quizList.get(0).getQuestionList();

        for (int i = 0; i < questionList.size(); i++) {
            option.add(questionList.get(i).getOption1());
            option.add(questionList.get(i).getOption2());
            option.add(questionList.get(i).getOption3());
            option.add(questionList.get(i).getKey());
        }

        // First question options and key
        assertEquals("0.5 million", option.get(index++));
        assertEquals("1 million", option.get(index++));
        assertEquals("2 million", option.get(index++));
        assertEquals("1 million", option.get(index++));

        // Second question options and key
        assertEquals("1867", option.get(index++));
        assertEquals("1887", option.get(index++));
        assertEquals("1787", option.get(index++));
        assertEquals("1867", option.get(index++));

        // Third question options and key
        assertEquals("Airbus", option.get(index++));
        assertEquals("Boeing", option.get(index++));
        assertEquals("Bombardier", option.get(index++));
        assertEquals("Bombardier", option.get(index));

        System.out.println("\tPASS!");
    }

    public void testQuestionOptionInSecondQuiz() {
        System.out.println("\tStart test 2nd quiz with its questions' options and key...");
        ArrayList<Quiz> quizList = dataAccess.getQuizList();
        ArrayList<String> option = new ArrayList<>();
        int index=0;

        ArrayList<Question> questionList = quizList.get(1).getQuestionList();

        for (int i = 0; i < questionList.size(); i++) {
            option.add(questionList.get(i).getOption1());
            option.add(questionList.get(i).getOption2());
            option.add(questionList.get(i).getOption3());
            option.add(questionList.get(i).getKey());
        }

        // First question options and key
        assertEquals("9.98 million", option.get(index++));
        assertEquals("9.60 million", option.get(index++));
        assertEquals("9.37 million", option.get(index++));
        assertEquals("9.98 million", option.get(index++));

        // Second question options and key
        assertEquals("Vancouver", option.get(index++));
        assertEquals("Ottawa", option.get(index++));
        assertEquals("Toronto", option.get(index++));
        assertEquals("Ottawa", option.get(index));

        System.out.println("\tPASS!");
    }

    public void testQuestionOptionInThirdQuiz() {
        System.out.println("\tStart test 3rd quiz with its questions' options and key...");
        ArrayList<Quiz> quizList = dataAccess.getQuizList();
        ArrayList<String> option = new ArrayList<>();
        int index=0;

        ArrayList<Question> questionList = quizList.get(2).getQuestionList();

        for (int i = 0; i < questionList.size(); i++) {
            option.add(questionList.get(i).getOption1());
            option.add(questionList.get(i).getOption2());
            option.add(questionList.get(i).getOption3());
            option.add(questionList.get(i).getKey());
        }

        // First question options and key
        assertEquals("3", option.get(index++));
        assertEquals("6", option.get(index++));
        assertEquals("9", option.get(index++));
        assertEquals("6", option.get(index++));

        // Second question options and key
        assertEquals("0", option.get(index++));
        assertEquals("1", option.get(index++));
        assertEquals("2", option.get(index++));
        assertEquals("2", option.get(index));

        System.out.println("\tPASS!");
    }


}
