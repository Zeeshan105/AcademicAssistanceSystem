package comp3350.AAS.tests;

import junit.framework.Test;
import junit.framework.TestSuite;
import comp3350.AAS.tests.integration.DataAccessHSQLDBTest;

public class RunIntegrationTests {
    public static TestSuite suite;

    public static Test suite() {
        suite = new TestSuite("Integration tests");
        suite.addTestSuite(DataAccessHSQLDBTest.class);

        return suite;
    }
}
