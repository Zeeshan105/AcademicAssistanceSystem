package comp3350.AAS.tests.integration;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Iterator;

import comp3350.AAS.application.Main;
import comp3350.AAS.application.Services;
import comp3350.AAS.business.AccessQuiz;
import comp3350.AAS.database.DataAccess;
import comp3350.AAS.object.Question;
import comp3350.AAS.object.Quiz;
import comp3350.AAS.tests.database.DataAccessTest;

public class AccessQuizTest extends TestCase {
    private static String dbName = Main.dbName;
    public AccessQuizTest(String arg0){
        super(arg0);
    }
    public void testAddAndDelete(){
        AccessQuiz aq;
        Services.closeDataAccess();
        Question tempQuestion = new Question("Test Question","A","B","C","A");


        System.out.println("Started Integration test for AccessQuiz on add and delete.");

        Services.createDataAccess(Main.dbName);

        aq = new AccessQuiz();
        aq.addQuiz(tempQuestion,"New Quiz");
        ArrayList<String> quizList = aq.getQuizNames();
        assertEquals(5,quizList.size());
        assertTrue(quizList.contains("New Quiz"));

        aq.deleteQuiz(1);
        quizList = aq.getQuizNames();
        assertEquals(4,quizList.size());
        assertFalse(quizList.contains("New Quiz"));


        Services.closeDataAccess();

    }
}
