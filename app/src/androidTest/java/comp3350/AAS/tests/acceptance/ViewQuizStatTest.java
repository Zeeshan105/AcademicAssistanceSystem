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
public class ViewQuizStatTest {
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {
        activityTestRule.launchActivity(new Intent());
    }

    @Test
    public void testSingleQuizFullScore() {
        onView(withText("QUIZ")).perform(click());
        onView(withText("START QUIZZES")).perform(click());

        // we make 100% percent of this quiz
        onView(withText("Historical")).check(matches(isDisplayed())).perform(click());
        onView(withText("START")).perform(click());
        onView(withText("B.1 million")).check(matches(isDisplayed())).perform(click());
        onView(withText("NEXT QUESTION")).perform(click());
        onView(withText("B.False")).check(matches(isDisplayed())).perform(click());
        onView(withText("NEXT QUESTION")).perform(click());
        onView(withText("A.True")).check(matches(isDisplayed())).perform(click());
        onView(withText("NEXT QUESTION")).perform(click());
        onView(withText("A.1867")).check(matches(isDisplayed())).perform(click());
        onView(withText("NEXT QUESTION")).perform(click());
        onView(withText("C.Bombardier")).check(matches(isDisplayed())).perform(click());
        onView(withText("NEXT QUESTION")).perform(click());
        onView(withText("CLOSE")).perform(click());

        onView(withText("VIEW STATS")).perform(click());
        onView(withText("Historical\nMark: 5.0/5")).check(matches(isDisplayed()));
        onView(withText("Number of Completed Quizzes: 1")).check(matches(isDisplayed()));
        onView(withText("Average Quiz Grade: 100.00%")).check(matches(isDisplayed()));
        onView(withText("Highest Quiz Grade: 100.00%")).check(matches(isDisplayed()));
        onView(withText("Lowest Quiz Grade: 100.00%")).check(matches(isDisplayed()));

        onView(withText("END QUIZZES TESTS")).perform(click());
    }

    @Test
    public void testSingleQuizZeroScore() {
        onView(withText("QUIZ")).perform(click());
        onView(withText("START QUIZZES")).perform(click());

        // we make all correct answer which is 0%
        onView(withText("Geographic")).check(matches(isDisplayed())).perform(click());
        onView(withText("START")).perform(click());
        onView(withText("B.9.60 million")).check(matches(isDisplayed())).perform(click());
        onView(withText("NEXT QUESTION")).perform(click());
        onView(withText("A.Vancouver")).check(matches(isDisplayed())).perform(click());
        onView(withText("NEXT QUESTION")).perform(click());
        onView(withText("CLOSE")).perform(click());

        onView(withText("VIEW STATS")).perform(click());
        onView(withText("Geographic\nMark: 0.0/2")).check(matches(isDisplayed()));
        onView(withText("Number of Completed Quizzes: 1")).check(matches(isDisplayed()));
        onView(withText("Average Quiz Grade: 0.00%")).check(matches(isDisplayed()));
        onView(withText("Highest Quiz Grade: 0.00%")).check(matches(isDisplayed()));
        onView(withText("Lowest Quiz Grade: 0.00%")).check(matches(isDisplayed()));

        onView(withText("END QUIZZES TESTS")).perform(click());
    }

    @Test
    public void testMultiQuizScore() {
        onView(withText("QUIZ")).perform(click());
        onView(withText("START QUIZZES")).perform(click());

        // we make 60% percent of this quiz
        onView(withText("Historical")).check(matches(isDisplayed())).perform(click());
        onView(withText("START")).perform(click());
        onView(withText("B.1 million")).check(matches(isDisplayed())).perform(click());
        onView(withText("NEXT QUESTION")).perform(click());
        onView(withText("B.False")).check(matches(isDisplayed())).perform(click());
        onView(withText("NEXT QUESTION")).perform(click());
        onView(withText("A.True")).check(matches(isDisplayed())).perform(click());
        onView(withText("NEXT QUESTION")).perform(click());
        onView(withText("C.1787")).check(matches(isDisplayed())).perform(click());
        onView(withText("NEXT QUESTION")).perform(click());
        onView(withText("B.Boeing")).check(matches(isDisplayed())).perform(click());
        onView(withText("NEXT QUESTION")).perform(click());
        onView(withText("CLOSE")).perform(click());
        onView(withText("Historical")).check(matches(isDisplayed())).perform(click());
        onView(withText("THIS QUIZ IS DONE!")).check(matches(isDisplayed())).perform(click());

        // we make all wrong answer which is 0%
        onView(withText("Math")).check(matches(isDisplayed())).perform(click());
        onView(withText("START")).perform(click());
        onView(withText("A.True")).check(matches(isDisplayed())).perform(click());
        onView(withText("NEXT QUESTION")).perform(click());
        onView(withText("B.False")).check(matches(isDisplayed())).perform(click());
        onView(withText("NEXT QUESTION")).perform(click());
        onView(withText("C.9")).check(matches(isDisplayed())).perform(click());
        onView(withText("NEXT QUESTION")).perform(click());
        onView(withText("A.0")).check(matches(isDisplayed())).perform(click());
        onView(withText("NEXT QUESTION")).perform(click());
        onView(withText("CLOSE")).perform(click());
        onView(withText("Math")).check(matches(isDisplayed())).perform(click());
        onView(withText("THIS QUIZ IS DONE!")).check(matches(isDisplayed())).perform(click());

        // we make all correct answer which is 100%
        onView(withText("Geographic")).check(matches(isDisplayed())).perform(click());
        onView(withText("START")).perform(click());
        onView(withText("A.9.98 million")).check(matches(isDisplayed())).perform(click());
        onView(withText("NEXT QUESTION")).perform(click());
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

}
