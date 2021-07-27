package comp3350.AAS.tests.acceptance;

import android.content.Intent;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import comp3350.AAS.R;
import comp3350.AAS.presentation.MainActivity;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class QuizAcceptanceTest {
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {
        activityTestRule.launchActivity(new Intent());
    }

    @Test
    public void testCreateMcqQuiz() {
        onView(withText("QUIZ")).perform(click());
        onView(withText("CREATE MULTI-CHOICE QUESTION")).perform(click());

        onView(withId(R.id.question)).perform(typeText("New Question 1"));
        onView(withId(R.id.option_A)).perform(typeText("Q1 A"));
        onView(withId(R.id.option_B)).perform(typeText("Q1 B"));
        onView(withId(R.id.option_C)).perform(typeText("Q1 C"));
        onView(withId(R.id.key)).perform(typeText("Q1 A"));
        closeSoftKeyboard();

        onView(withId(R.id.quiz_index)).perform(typeText("QUIZ 1"));
        closeSoftKeyboard();

        onView(withText("SUBMIT")).perform(click());
        pressBack();

        onView(withText("START QUIZZES")).perform(click());
        onView(withText("QUIZ 1")).check(matches(isDisplayed())).perform(click());
        onView(withText("START")).perform(click());
        onView(withText("New Question 1")).check(matches(isDisplayed()));
        onView(withText("A.Q1 A")).check(matches(isDisplayed())).perform(click());
        onView(withText("NEXT QUESTION")).perform(click());
        onView(withText("CLOSE")).perform(click());

        onView(withText("VIEW STATS")).perform(click());
        onView(withText("QUIZ 1\nMark: 1.0/1")).check(matches(isDisplayed()));

        onView(withText("Number of Completed Quizzes: 1")).check(matches(isDisplayed()));
        onView(withText("Average Quiz Grade: 100.00%")).check(matches(isDisplayed()));
        onView(withText("Highest Quiz Grade: 100.00%")).check(matches(isDisplayed()));
        onView(withText("Lowest Quiz Grade: 100.00%")).check(matches(isDisplayed()));

        onView(withText("END QUIZZES TESTS")).perform(click());

        onView(withText("START QUIZZES")).perform(click());
        onView(withText("QUIZ 1")).check(matches(isDisplayed())).perform(click());
        onView(withText("DELETE")).perform(click());
        onView(withText("QUIZ 1")).check(doesNotExist());


    }

    @Test
    public void testCreateTrueFalseQuiz() {
        onView(withText("QUIZ")).perform(click());
        onView(withText("CREATE \nTRUE/FALSE QUESTION")).perform(click());

        onView(withId(R.id.TFQuestion)).perform(typeText("True/False Question 1"));
        onView(withId(R.id.True)).perform(click());
        onView(withId(R.id.quiz_belong)).perform(typeText("QUIZ 2"));
        closeSoftKeyboard();
        onView(withText("SUBMIT")).perform(click());
        onView(withId(R.id.TFQuestion)).perform(typeText("True/False Question 2"));
        onView(withId(R.id.False)).perform(click());
        onView(withId(R.id.quiz_belong)).perform(typeText("QUIZ 2"));
        closeSoftKeyboard();
        onView(withText("SUBMIT")).perform(click());
        pressBack();

        onView(withText("START QUIZZES")).perform(click());
        onView(withText("QUIZ 2")).check(matches(isDisplayed())).perform(click());
        onView(withText("START")).perform(click());
        onView(withText("True/False Question 1")).check(matches(isDisplayed()));
        onView(withText("A.True")).check(matches(isDisplayed())).perform(click());
        onView(withText("NEXT QUESTION")).perform(click());
        onView(withText("True/False Question 2")).check(matches(isDisplayed()));
        onView(withText("B.False")).check(matches(isDisplayed())).perform(click());
        onView(withText("NEXT QUESTION")).perform(click());
        onView(withText("CLOSE")).perform(click());

        onView(withText("VIEW STATS")).perform(click());
        onView(withText("QUIZ 2\nMark: 2.0/2")).check(matches(isDisplayed()));
        onView(withText("Number of Completed Quizzes: 1")).check(matches(isDisplayed()));
        onView(withText("Average Quiz Grade: 100.00%")).check(matches(isDisplayed()));
        onView(withText("Highest Quiz Grade: 100.00%")).check(matches(isDisplayed()));
        onView(withText("Lowest Quiz Grade: 100.00%")).check(matches(isDisplayed()));

        onView(withText("END QUIZZES TESTS")).perform(click());

        onView(withText("START QUIZZES")).perform(click());
        onView(withText("QUIZ 2")).perform(click());
        onView(withText("DELETE")).perform(click());
        onView(withText("QUIZ 2")).check(doesNotExist());
    }

    @Test
    public void testMultiQuizScoreAndRemainNum() {
        onView(withText("QUIZ")).perform(click());
        onView(withText("START QUIZZES")).perform(click());

        // we make 60% percent of this quiz
        onView(withText("Historical")).check(matches(isDisplayed())).perform(click());
        onView(withText("START")).perform(click());
        onView(withText("Remaining #: 4")).check(matches(isDisplayed()));
        onView(withText("B.1 million")).check(matches(isDisplayed())).perform(click());
        onView(withText("NEXT QUESTION")).perform(click());
        onView(withText("Remaining #: 3")).check(matches(isDisplayed()));
        onView(withText("B.False")).check(matches(isDisplayed())).perform(click());
        onView(withText("NEXT QUESTION")).perform(click());
        onView(withText("Remaining #: 2")).check(matches(isDisplayed()));
        onView(withText("A.True")).check(matches(isDisplayed())).perform(click());
        onView(withText("NEXT QUESTION")).perform(click());
        onView(withText("Remaining #: 1")).check(matches(isDisplayed()));
        onView(withText("C.1787")).check(matches(isDisplayed())).perform(click());
        onView(withText("NEXT QUESTION")).perform(click());
        onView(withText("Remaining #: 0")).check(matches(isDisplayed()));
        onView(withText("B.Boeing")).check(matches(isDisplayed())).perform(click());
        onView(withText("NEXT QUESTION")).perform(click());
        onView(withText("CLOSE")).perform(click());
        onView(withText("Historical")).check(matches(isDisplayed())).perform(click());
        onView(withText("THIS QUIZ IS DONE!")).check(matches(isDisplayed())).perform(click());

        // we make all wrong answer which is 0%
        onView(withText("Math")).check(matches(isDisplayed())).perform(click());
        onView(withText("START")).perform(click());
        onView(withText("Remaining #: 3")).check(matches(isDisplayed()));
        onView(withText("A.True")).check(matches(isDisplayed())).perform(click());
        onView(withText("NEXT QUESTION")).perform(click());
        onView(withText("Remaining #: 2")).check(matches(isDisplayed()));
        onView(withText("B.False")).check(matches(isDisplayed())).perform(click());
        onView(withText("NEXT QUESTION")).perform(click());
        onView(withText("Remaining #: 1")).check(matches(isDisplayed()));
        onView(withText("C.9")).check(matches(isDisplayed())).perform(click());
        onView(withText("NEXT QUESTION")).perform(click());
        onView(withText("Remaining #: 0")).check(matches(isDisplayed()));
        onView(withText("A.0")).check(matches(isDisplayed())).perform(click());
        onView(withText("NEXT QUESTION")).perform(click());
        onView(withText("CLOSE")).perform(click());
        onView(withText("Math")).check(matches(isDisplayed())).perform(click());
        onView(withText("THIS QUIZ IS DONE!")).check(matches(isDisplayed())).perform(click());

        // we make all correct answer which is 100%
        onView(withText("Geographic")).check(matches(isDisplayed())).perform(click());
        onView(withText("START")).perform(click());
        onView(withText("Remaining #: 1")).check(matches(isDisplayed()));
        onView(withText("A.9.98 million")).check(matches(isDisplayed())).perform(click());
        onView(withText("NEXT QUESTION")).perform(click());
        onView(withText("Remaining #: 0")).check(matches(isDisplayed()));
        onView(withText("B.Ottawa")).check(matches(isDisplayed())).perform(click());
        onView(withText("NEXT QUESTION")).perform(click());
        onView(withText("CLOSE")).perform(click());
        onView(withText("Geographic")).check(matches(isDisplayed())).perform(click());
        onView(withText("THIS QUIZ IS DONE!")).check(matches(isDisplayed())).perform(click());

        onView(withText("VIEW STATS")).perform(click());
        onView(withText("Geographic\nMark: 2.0/2")).check(matches(isDisplayed()));   // 100%
        onView(withText("Historical\nMark: 3.0/5")).check(matches(isDisplayed()));   // 60%
        onView(withText("Math\nMark: 0.0/4")).check(matches(isDisplayed()));   // 0%
        onView(withText("Number of Completed Quizzes: 3")).check(matches(isDisplayed()));
        onView(withText("Average Quiz Grade: 53.33%")).check(matches(isDisplayed()));   // (60%+0%+100%)/3=53.33%
        onView(withText("Highest Quiz Grade: 100.00%")).check(matches(isDisplayed()));
        onView(withText("Lowest Quiz Grade: 0.00%")).check(matches(isDisplayed()));

        onView(withText("END QUIZZES TESTS")).perform(click());
    }

    @Test
    public void testCreateAndDeleteQuiz(){
        onView(withText("QUIZ")).perform(click());
        onView(withText("CREATE MULTI-CHOICE QUESTION")).perform(click());

        onView(withId(R.id.question)).perform(typeText("New Question 1"));
        onView(withId(R.id.option_A)).perform(typeText("Q1 A"));
        onView(withId(R.id.option_B)).perform(typeText("Q1 B"));
        onView(withId(R.id.option_C)).perform(typeText("Q1 C"));
        onView(withId(R.id.key)).perform(typeText("Q1 A"));
        closeSoftKeyboard();

        onView(withId(R.id.quiz_index)).perform(typeText("QUIZ"));
        closeSoftKeyboard();

        onView(withText("SUBMIT")).perform(click());
        pressBack();

        onView(withText("START QUIZZES")).perform(click());

        onView(withText("QUIZ")).perform(click());
        onView(withText("DELETE")).perform(click());
        onView(withText("QUIZ")).check(doesNotExist());

        onView(withText("VIEW STATS")).perform(click());
        onView(withText("Number of Completed Quizzes: 0")).check(matches(isDisplayed()));
        onView(withText("Average Quiz Grade: 0.00%")).check(matches(isDisplayed()));   // (60%+0%+100%)/3=53.33%
        onView(withText("Highest Quiz Grade: 0.00%")).check(matches(isDisplayed()));
        onView(withText("Lowest Quiz Grade: 0.00%")).check(matches(isDisplayed()));
        onView(withText("END QUIZZES TESTS")).perform(click());
        onView(withText("HOME")).perform(click());
    }


}
