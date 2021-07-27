package comp3350.AAS.tests.acceptance;

import android.content.Intent;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import comp3350.AAS.R;
import comp3350.AAS.presentation.MainActivity;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class CreateFlashCardTest {
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {
        activityTestRule.launchActivity(new Intent());
    }

    @Test
    public void testCreateCard() {
        onView(withText("FLASH CARD")).perform(click());
        onView(withId(R.id.editTextTitle)).perform(typeText("Title1"));
        onView(withId(R.id.editTextDescription)).perform(typeText("Description1"));
        onView(withId(R.id.editTextFolderName)).perform(typeText("Folder_1"));
        closeSoftKeyboard();
        onView(withText("SUBMIT")).perform(click());

        onView(withId(R.id.editTextTitle)).perform(typeText("Title2"));
        onView(withId(R.id.editTextDescription)).perform(typeText("Description2"));
        onView(withId(R.id.editTextFolderName)).perform(typeText("Folder_1"));
        closeSoftKeyboard();
        onView(withText("SUBMIT")).perform(click());

        onView(withText("VIEW FOLDERS")).perform(click());

        onView(withText("Folder_1")).check(matches(isDisplayed())).perform(click());
        onView(withText("VIEW FOLDER")).perform(click());
        onView(withText("Title1")).check(matches(isDisplayed()));
        onView(withText("Description1")).check(matches(isDisplayed()));
        onView(withText("Title2")).check(matches(isDisplayed()));
        onView(withText("Description2")).check(matches(isDisplayed()));
    }

    @After
    public void tearDown(){
        onView(withText("Description1")).check(matches(isDisplayed())).perform(click());
        onView(withText("DELETE")).perform(click());
        onView(withText("Description2")).check(matches(isDisplayed())).perform(click());
        onView(withText("DELETE")).perform(click());
        onView(withText("Folder_1")).check(doesNotExist());
    }

}
