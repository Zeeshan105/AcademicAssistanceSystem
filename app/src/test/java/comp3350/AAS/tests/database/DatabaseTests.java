package comp3350.AAS.tests.database;

import junit.framework.Test;
import junit.framework.TestSuite;

public class DatabaseTests {
    public static TestSuite suite;

    public static Test suite() {
        suite = new TestSuite("Database tests");
        suite.addTestSuite(DataAccessTest.class);
        return suite;
    }
}
