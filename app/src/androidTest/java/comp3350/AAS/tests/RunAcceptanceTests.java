package comp3350.AAS.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.AAS.tests.acceptance.CreateFlashCardTest;
import comp3350.AAS.tests.acceptance.CreateMcqQuizTest;
import comp3350.AAS.tests.acceptance.CreateTfQuizTest;
import comp3350.AAS.tests.acceptance.DeleteFlashCardTest;
import comp3350.AAS.tests.acceptance.DeleteQuizTest;
import comp3350.AAS.tests.acceptance.HomeScreenTest;
import comp3350.AAS.tests.acceptance.RemainQuizNumberTest;
import comp3350.AAS.tests.acceptance.ViewQuizAnswerTest;
import comp3350.AAS.tests.acceptance.ViewQuizStatTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CreateFlashCardTest.class,
        CreateMcqQuizTest.class,
        CreateTfQuizTest.class,
        DeleteFlashCardTest.class,
        DeleteQuizTest.class,
        HomeScreenTest.class,
        RemainQuizNumberTest.class,
        ViewQuizAnswerTest.class,
        ViewQuizStatTest.class
})
public class RunAcceptanceTests {
    public RunAcceptanceTests() {
        System.out.println("Pass all acceptance tests!");
    }
}