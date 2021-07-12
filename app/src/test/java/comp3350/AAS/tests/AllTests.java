package comp3350.AAS.tests;

import junit.framework.Test;
import junit.framework.TestSuite;
import comp3350.AAS.tests.business.CalculateTest;
import comp3350.AAS.tests.object.CardFolderTest;
import comp3350.AAS.tests.object.FlashCardTest;
import comp3350.AAS.tests.object.QuestionTest;
import comp3350.AAS.tests.object.QuizTest;
import comp3350.AAS.tests.database.DataAccessTest;

public class AllTests {
    public static TestSuite suite;

    public static Test suite() {
        suite = new TestSuite("All tests");
        testObjects();
        testBusiness();
        testDatabase();
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

    private static void testDatabase() {
        suite.addTestSuite(DataAccessTest.class);
    }
}

