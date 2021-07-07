package comp3350.ASS.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import comp3350.ASS.tests.business.CalculateTest;
import comp3350.ASS.tests.object.CardFolderTest;
import comp3350.ASS.tests.object.FlashCardTest;
import comp3350.ASS.tests.object.QuestionTest;
import comp3350.ASS.tests.object.QuizTest;
import comp3350.ASS.tests.persistance.DataAccessTest;

public class AllTests {
    public static TestSuite suite;

    public static Test suite() {
        suite = new TestSuite("All tests");
        testObjects();
        testBusiness();
        testPersistence();
        return suite;
    }

    private static void testObjects() {
        suite.addTestSuite(CardFolderTest.class);
        suite.addTestSuite(FlashCardTest.class);
        suite.addTestSuite(QuestionTest.class);
        suite.addTestSuite(QuizTest.class);
    }

    private static void testBusiness() {
        suite.addTestSuite(CalculateTest.class);
    }

    private static void testPersistence() {
        suite.addTestSuite(DataAccessTest.class);
    }
}

