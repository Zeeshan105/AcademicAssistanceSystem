package comp3350.AAS.tests.business;

import junit.framework.Test;
import junit.framework.TestSuite;

public class BusinessTests {

    public static TestSuite suite;

    public static Test suite() {
        suite = new TestSuite("Business tests");
        suite.addTestSuite(AccessFolderTest.class);
        suite.addTestSuite(AccessQuizTest.class);
        suite.addTestSuite(CalculateTest.class);
        suite.addTestSuite(ValidateTest.class);
        return suite;
    }
}
