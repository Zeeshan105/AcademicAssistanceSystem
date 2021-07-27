package comp3350.AAS.tests.acceptance;

import android.content.Intent;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onData;
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
public class FlashCardAcceptanceTest {
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {
        activityTestRule.launchActivity(new Intent());
    }

    @Test
    public void testDeleteFolder(){
        onView(withText("FLASH CARD")).perform(click());
        onView(withId(R.id.editTextTitle)).perform(typeText("Title3"));
        onView(withId(R.id.editTextDescription)).perform(typeText("Description3"));
        onView(withId(R.id.editTextFolderName)).perform(typeText("Folder_2"));
        closeSoftKeyboard();

        onView(withText("SUBMIT")).perform(click());

        onView(withText("VIEW FOLDERS")).perform(click());
        onView(withText("Folder_2")).check(matches(isDisplayed())).perform(click());
        onView(withText("DELETE FOLDER")).perform(click());
        onView(withText("Folder_2")).check(doesNotExist());
    }

    @Test
    public void testCreateAndDeleteCard() {
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
        onView(withText("Description1")).check(matches(isDisplayed())).perform(click());
        onView(withText("DELETE")).perform(click());
        onView(withText("Title2")).check(matches(isDisplayed()));
        onView(withText("Description2")).check(matches(isDisplayed())).perform(click());
        onView(withText("DELETE")).perform(click());

        onView(withText("Folder_1")).check(doesNotExist());
    }

    @Test
    public void testInitialCard() {
        onView(withText("FLASH CARD")).perform(click());
        closeSoftKeyboard();
        onView(withText("VIEW FOLDERS")).perform(click());

        onView(withText("Geographic_Knowledge")).check(matches(isDisplayed())).perform(click());
        onView(withText("VIEW FOLDER")).perform(click());
        onView(withText("National Land Area Descending Order")).check(matches(isDisplayed()));
        onView(withText("Russia>Canada>China>America>Brazil...")).check(matches(isDisplayed()));
        onView(withText("Temperature Distributed")).check(matches(isDisplayed()));
        onView(withText("Decreasing from low latitude to high latitude")).check(matches(isDisplayed()));
        pressBack();

        onView(withText("Historical_Event")).check(matches(isDisplayed())).perform(click());
        onView(withText("VIEW FOLDER")).perform(click());
        onView(withText("First World War")).check(matches(isDisplayed()));
        onView(withText("The World War I break out in 1914")).check(matches(isDisplayed()));
        onView(withText("Second World War")).check(matches(isDisplayed()));
        onView(withText("The World War II break out in 1939")).check(matches(isDisplayed()));
        pressBack();

        onView(withText("Math_Practice")).check(matches(isDisplayed())).perform(click());
        onView(withText("VIEW FOLDER")).perform(click());
        onView(withText("Addition and subtraction within 10")).check(matches(isDisplayed()));
        onView(withText("1+1=2, 2+2=4")).check(matches(isDisplayed()));
        onView(withText("Calculate square root")).check(matches(isDisplayed()));
        onView(withText("sqrt(1)=+-1, sqrt(16)=+-4, sqrt(36)=+-6, sqrt(-1)=undefined")).check(matches(isDisplayed()));

    }

}
