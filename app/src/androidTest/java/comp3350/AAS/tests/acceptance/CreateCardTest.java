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
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
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


}
