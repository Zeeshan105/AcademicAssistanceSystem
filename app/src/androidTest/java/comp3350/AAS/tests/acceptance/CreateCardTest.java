package comp3350.AAS.tests.acceptance;

import android.app.Activity;

import androidx.test.espresso.Espresso;
//import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.runner.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import comp3350.AAS.R;
import comp3350.AAS.presentation.CardListActivity;


@RunWith(AndroidJUnit4.class)

public class CreateCardTest {
    @Rule
    public ActivityTestRule <CardListActivity> activityTestRule = new ActivityTestRule<>(CardListActivity.class);

    @Test
    public void testAddCard() {
        onView(withText("FLASH CARD")).perform(click());

        onView(withText(R.id.editTextTitle)).perform(typeText("New Title"));
        onView(withText(R.id.editTextDescription)).perform(typeText("New Description"));
        onView(withText(R.id.editTextFolderName)).perform(typeText("Folder_1"));

        Espresso.closeSoftKeyboard();

        onView(withText("SUBMIT")).perform(click());

    }



}
