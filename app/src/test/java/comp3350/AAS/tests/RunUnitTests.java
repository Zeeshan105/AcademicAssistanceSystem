package comp3350.AAS.tests;

import junit.framework.Test;
import junit.framework.TestSuite;
import comp3350.AAS.tests.business.BusinessTests;
import comp3350.AAS.tests.database.DatabaseTests;
import comp3350.AAS.tests.object.ObjectTests;

public class RunUnitTests {
    public static TestSuite suite;

    public static Test suite() {
        suite = new TestSuite("Unit tests");
        suite.addTest(BusinessTests.suite());
        suite.addTest(DatabaseTests.suite());
        suite.addTest(ObjectTests.suite());
        return suite;
    }
}
