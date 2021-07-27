package comp3350.AAS.tests.acceptance;

import android.content.Intent;
import androidx.test.rule.ActivityTestRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import comp3350.AAS.R;
import comp3350.AAS.presentation.MainActivity;
import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class HomeScreenTest {
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {
        activityTestRule.launchActivity(new Intent());
    }

    @Test
    public void testHomeScreen() {
        onView(withText("Academic Assistance System")).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.Button1)).check(matches(isDisplayed())).check(matches(isEnabled()));
        onView(withId(R.id.Button2)).check(matches(isDisplayed())).check(matches(isEnabled()));
        onView(withText("FLASH CARD")).check(matches(isDisplayed())).check(matches(isEnabled()));
        onView(withText("QUIZ")).check(matches(isDisplayed())).check(matches(isEnabled()));
    }

    @Test
    public void testFlashCardHomeScreen() {
        onView(withText("FLASH CARD")).perform(click());
        onView(withId(R.id.editTextTitle)).check(matches(isDisplayed())).check(matches(isEnabled()));
        onView(withId(R.id.editTextDescription)).check(matches(isDisplayed())).check(matches(isEnabled()));
        onView(withId(R.id.editTextFolderName)).check(matches(isDisplayed())).check(matches(isEnabled()));
        onView(withId(R.id.submitButton)).check(matches(isDisplayed())).check(matches(isEnabled()));
        onView(withId(R.id.viewFoldersButton)).check(matches(isDisplayed())).check(matches(isEnabled()));
        onView(withText("SUBMIT")).check(matches(isDisplayed())).check(matches(isEnabled()));
        onView(withText("VIEW FOLDERS")).check(matches(isDisplayed())).check(matches(isEnabled()));
    }

    @Test
    public void testQuizHomeScreen() {
        onView(withText("QUIZ")).perform(click());
        onView(withId(R.id.CreateMcqQuiz)).check(matches(isDisplayed())).check(matches(isEnabled()));
        onView(withId(R.id.CreateTfQuiz)).check(matches(isDisplayed())).check(matches(isEnabled()));
        onView(withId(R.id.StartQuiz)).check(matches(isDisplayed())).check(matches(isEnabled()));
        onView(withId(R.id.ViewQuizAnswer)).check(matches(isDisplayed())).check(matches(isEnabled()));
        onView(withText("CREATE MULTI-CHOICE QUESTION")).check(matches(isDisplayed())).check(matches(isEnabled()));
        onView(withText("CREATE \nTRUE/FALSE QUESTION")).check(matches(isDisplayed())).check(matches(isEnabled()));
        onView(withText("START QUIZZES")).check(matches(isDisplayed())).check(matches(isEnabled()));
        onView(withText("VIEW QUIZ ANSWER")).check(matches(isDisplayed())).check(matches(isEnabled()));
    }

    @Test
    public void testFlashCardReturnHome() {
        onView(withText("FLASH CARD")).perform(click());
        closeSoftKeyboard();
        pressBack();
        onView(withText("Academic Assistance System")).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.Button1)).check(matches(isDisplayed())).check(matches(isEnabled()));
        onView(withId(R.id.Button2)).check(matches(isDisplayed())).check(matches(isEnabled()));
        onView(withText("FLASH CARD")).check(matches(isDisplayed())).check(matches(isEnabled()));
        onView(withText("QUIZ")).check(matches(isDisplayed())).check(matches(isEnabled()));
    }

    @Test
    public void testQuizReturnHome() {
        onView(withText("QUIZ")).perform(click());
        pressBack();
        onView(withText("Academic Assistance System")).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.Button1)).check(matches(isDisplayed())).check(matches(isEnabled()));
        onView(withId(R.id.Button2)).check(matches(isDisplayed())).check(matches(isEnabled()));
        onView(withText("FLASH CARD")).check(matches(isDisplayed())).check(matches(isEnabled()));
        onView(withText("QUIZ")).check(matches(isDisplayed())).check(matches(isEnabled()));
    }

}
