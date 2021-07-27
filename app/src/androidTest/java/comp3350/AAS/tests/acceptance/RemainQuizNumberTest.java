package comp3350.AAS.tests.acceptance;

import android.content.Intent;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import comp3350.AAS.presentation.MainActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class RemainQuizNumberTest {
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {
        activityTestRule.launchActivity(new Intent());
    }

    @Test
    public void testRemainNum() {
        onView(withText("QUIZ")).perform(click());
        onView(withText("START QUIZZES")).perform(click());

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

    }

}
