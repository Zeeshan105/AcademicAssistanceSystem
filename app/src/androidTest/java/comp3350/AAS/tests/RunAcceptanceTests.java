package comp3350.AAS.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import comp3350.AAS.tests.acceptance.FlashCardAcceptanceTest;
import comp3350.AAS.tests.acceptance.HomeScreenAcceptanceTest;
import comp3350.AAS.tests.acceptance.QuizAcceptanceTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        HomeScreenAcceptanceTest.class,
        FlashCardAcceptanceTest.class,
        QuizAcceptanceTest.class
})
public class RunAcceptanceTests {
    public RunAcceptanceTests() {
        System.out.println("Pass all acceptance tests!");
    }
}