package comp3350.AAS.tests.object;

import junit.framework.Test;
import junit.framework.TestSuite;


public class ObjectTests {
    public static TestSuite suite;

    public static Test suite() {
        suite = new TestSuite("Object tests");
        suite.addTestSuite(CardFolderTest.class);
        suite.addTestSuite(FlashCardTest.class);
        suite.addTestSuite(QuestionTest.class);
        suite.addTestSuite(QuizTest.class);
        return suite;
    }
}
