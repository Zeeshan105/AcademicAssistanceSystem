package comp3350.AAS.tests.acceptance;

import android.content.Intent;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import comp3350.AAS.R;
import comp3350.AAS.presentation.MainActivity;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class DeleteQuizTest {
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {
        activityTestRule.launchActivity(new Intent());
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
    }

    @Test
    public void testCreateAndDeleteQuiz(){
        onView(withText("START QUIZZES")).perform(click());
        onView(withText("QUIZ")).perform(click());
        onView(withText("DELETE")).perform(click());
        onView(withText("QUIZ")).check(doesNotExist());
    }

}
