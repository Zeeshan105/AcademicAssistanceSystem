package comp3350.ASS.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import comp3350.ASS.tests.business.temp;
import comp3350.ASS.tests.object.FlashCardTest;
import comp3350.ASS.tests.object.QuestionTest;
import comp3350.ASS.tests.object.QuizTest;

public class AllTests {
    public static TestSuite suite;

    public static Test suite() {
        suite = new TestSuite("All tests");
        testObjects();
        //testBusiness();
        return suite;
    }

    private static void testObjects() {
        suite.addTestSuite(FlashCardTest.class);
        suite.addTestSuite(QuestionTest.class);
        suite.addTestSuite(QuizTest.class);
    }

    private static void testBusiness() {
        suite.addTestSuite(temp.class);
    }

}
