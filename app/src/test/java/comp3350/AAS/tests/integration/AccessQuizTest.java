package comp3350.AAS.tests.integration;

import junit.framework.TestCase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import comp3350.AAS.application.Main;
import comp3350.AAS.application.Services;
import comp3350.AAS.business.AccessQuiz;
import comp3350.AAS.database.DataAccess;
import comp3350.AAS.object.Question;
import comp3350.AAS.object.Quiz;
import comp3350.AAS.tests.database.DataAccessStub;
import comp3350.AAS.tests.database.DataAccessTest;

public class AccessQuizTest extends TestCase {
    private static String dbName = Main.dbName;

    public AccessQuizTest(String arg0){
        super(arg0);
    }

    public void testAddAndDelete(){
        AccessQuiz aq;
        ArrayList<String> quizNameList;
        Question tempQuestion = new Question("Test Question","A","B","C","A");

        Services.closeDataAccess();
        System.out.println("Started Integration test for AccessQuiz on add and delete.");

        Services.createDataAccess(dbName);
        aq = new AccessQuiz();
        aq.addQuiz(tempQuestion,"New Quiz");
        quizNameList = aq.getQuizNames();
        assertEquals(5, quizNameList.size());
        assertTrue(quizNameList.contains("New Quiz"));
        aq.deleteQuiz(1);
        quizNameList = aq.getQuizNames();
        assertEquals(4, quizNameList.size());
        assertFalse(quizNameList.contains("New Quiz"));
        Services.closeDataAccess();

        Services.createDataAccess(new DataAccessStub());
        aq = new AccessQuiz();
        aq.addQuiz(tempQuestion,"New Quiz");
        quizNameList = aq.getQuizNames();
        assertEquals(5, quizNameList.size());
        assertTrue(quizNameList.contains("New Quiz"));
        aq.deleteQuiz(4);
        quizNameList = aq.getQuizNames();
        assertEquals(4, quizNameList.size());
        assertFalse(quizNameList.contains("New Quiz"));
        Services.closeDataAccess();
    }

    public void testChangingCompleteStatus(){
        AccessQuiz aq;
        int count;
        Services.closeDataAccess();
        System.out.println("Started Integration test for changing complete status.");

        Services.createDataAccess(dbName);
        aq = new AccessQuiz();
        count = Integer.parseInt(aq.getNumCompletedQuiz());
        assertEquals(0,count);
        aq.updateStatus("Math","TRUE");
        count = Integer.parseInt(aq.getNumCompletedQuiz());
        assertEquals(1,count);
        aq.updateStatus("Historical","TRUE");
        count = Integer.parseInt(aq.getNumCompletedQuiz());
        assertEquals(2,count);
        aq.updateStatus("Math","FALSE");
        aq.updateStatus("Historical","FALSE");
        count = Integer.parseInt(aq.getNumCompletedQuiz());
        assertEquals(0,count);
        Services.closeDataAccess();

        Services.createDataAccess(new DataAccessStub());
        aq = new AccessQuiz();
        count = Integer.parseInt(aq.getNumCompletedQuiz());
        assertEquals(0, count);
        aq.updateStatus("Math","TRUE");
        count = Integer.parseInt(aq.getNumCompletedQuiz());
        assertEquals(1, count);
        aq.updateStatus("Historical","TRUE");
        count = Integer.parseInt(aq.getNumCompletedQuiz());
        assertEquals(2, count);
        aq.updateStatus("Math","FALSE");
        aq.updateStatus("Historical","FALSE");
        count = Integer.parseInt(aq.getNumCompletedQuiz());
        assertEquals(0, count);
        Services.closeDataAccess();
    }

    public void testChangingGrades(){
        AccessQuiz aq;
        ArrayList<String> gradeList;
        Services.closeDataAccess();
        System.out.println("Started Integration test for changing complete status.");

        Services.createDataAccess(dbName);
        aq = new AccessQuiz();
        gradeList = aq.getGradeQuizList();
        assertEquals(0,gradeList.size());
        aq.updateGrade("Math",4.0);
        aq.updateStatus("Math","TRUE");
        aq.updateGrade("Historical",3.0);
        aq.updateStatus("Historical","TRUE");
        gradeList = aq.getGradeQuizList();

        assertEquals(2,gradeList.size());
        assertTrue(gradeList.contains("Math\nMark: 4.0/4"));
        assertTrue(gradeList.contains("Historical\nMark: 3.0/5"));

        aq.updateGrade("Math",0.0);
        aq.updateStatus("Math","FALSE");
        aq.updateGrade("Historical",0.0);
        aq.updateStatus("Historical","FALSE");
        Services.closeDataAccess();


        Services.createDataAccess(new DataAccessStub());
        aq = new AccessQuiz();
        gradeList = aq.getGradeQuizList();
        assertEquals(0, gradeList.size());
        aq.updateGrade("Math",4.0);
        aq.updateStatus("Math","TRUE");
        aq.updateGrade("Historical",3.0);
        aq.updateStatus("Historical","TRUE");
        gradeList = aq.getGradeQuizList();

        assertEquals(2, gradeList.size());
        assertTrue(gradeList.contains("Math\nMark: 4.0/4"));
        assertTrue(gradeList.contains("Historical\nMark: 3.0/5"));

        aq.updateGrade("Math",0.0);
        aq.updateStatus("Math","FALSE");
        aq.updateGrade("Historical",0.0);
        aq.updateStatus("Historical","FALSE");
        Services.closeDataAccess();
    }


}
