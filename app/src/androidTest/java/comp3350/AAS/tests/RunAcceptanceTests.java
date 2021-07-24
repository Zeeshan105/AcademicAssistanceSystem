package comp3350.AAS.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import comp3350.AAS.tests.acceptance.CreateCardTest;


@RunWith(Suite.class)
@Suite.SuiteClasses({CreateCardTest.class})

public class RunAcceptanceTests {
    public RunAcceptanceTests() {
        System.out.println("");
    }
}