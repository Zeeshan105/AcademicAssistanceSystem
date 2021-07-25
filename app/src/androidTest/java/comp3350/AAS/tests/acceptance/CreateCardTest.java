package comp3350.AAS.tests.acceptance;

import android.content.Intent;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
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

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import comp3350.AAS.R;
import comp3350.AAS.presentation.MainActivity;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class CreateCardTest {
    @Rule
    public ActivityTestRule <MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);


    @Before
    public void setUp(){
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
    public void testSecondScreen() {
        onView(withText("QUIZ")).perform(click());
        onView(withId(R.id.CreateMcqQuiz)).check(matches(isDisplayed())).check(matches(isEnabled()));
        onView(withId(R.id.CreateTfQuiz)).check(matches(isDisplayed())).check(matches(isEnabled()));
        onView(withId(R.id.StartQuiz)).check(matches(isDisplayed())).check(matches(isEnabled()));
        onView(withId(R.id.ViewQuizAnswer)).check(matches(isDisplayed())).check(matches(isEnabled()));
        onView(withId(R.id.homePage)).check(matches(isDisplayed())).check(matches(isEnabled()));
        onView(withText("CREATE MULTI-CHOICE QUESTION")).check(matches(isDisplayed())).check(matches(isEnabled()));
        onView(withText("CREATE TRUE/FALSE QUESTION")).check(matches(isDisplayed())).check(matches(isEnabled()));
        onView(withText("START QUIZZES")).check(matches(isDisplayed())).check(matches(isEnabled()));
        onView(withText("VIEW QUIZ ANSWER")).check(matches(isDisplayed())).check(matches(isEnabled()));
        onView(withText("HOME")).check(matches(isDisplayed())).check(matches(isEnabled()));

    }
    @Test
    public void testAddCard() {
        onView(withText("FLASH CARD")).perform(click());
        onView(withId(R.id.editTextTitle)).perform(typeText("New Title"));
        onView(withId(R.id.editTextDescription)).perform(typeText("New Description"));
        onView(withId(R.id.editTextFolderName)).perform(typeText("Folder_1"));
        closeSoftKeyboard();

        onView(withText("SUBMIT")).perform(click());

        onView(withText("VIEW FOLDERS")).perform(click());

        onView(withText("Folder_1")).check(matches(isDisplayed())).perform(click());
        onView(withText("VIEW FOLDER")).perform(click());
        onView(withText("New Title")).check(matches(isDisplayed())).perform(click());
        onView(withText("New Description")).check(matches(isDisplayed())).perform(click());
        onView(withText("DELETE")).perform(click());
        onView(withText("New Title")).check(doesNotExist());
        pressBack();
        onView(withText("Folder_1")).check(doesNotExist());

        pressBack();
        closeSoftKeyboard();
        pressBack();

    }
    @Test
    public void testCreateQuiz(){
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
        onView(withText("QUIZ 1")).perform(click());
        onView(withText("START THIS QUIZ")).perform(click());
        onView(withText("A.Q1 A")).perform(click());
        onView(withText("NEXT QUESTION")).perform(click());
        onView(withText("CLOSE")).perform(click());
        onView(withText("VIEW STATS")).perform(click());
       // onView(withId(R.id.quizListView)).check(matches(isDisplayed())).perform(typeText("QUIZ 1'\n'Mark: 1.0/1"));



    }

}
