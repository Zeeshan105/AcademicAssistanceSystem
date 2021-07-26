package comp3350.AAS.tests.integration;

import junit.framework.Test;
import junit.framework.TestSuite;

public class IntegrationTest{
    public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("Integration tests");
        suite.addTestSuite(AccessQuizTest.class);
        suite.addTestSuite(DataAccessHSQLDBTest.class);
        return suite;
    }
}
