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
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ViewQuizAnswerTest {
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {
        activityTestRule.launchActivity(new Intent());
    }

    @Test
    public void testDefaultQuizAnswer(){
        onView(withText("QUIZ")).perform(click());
        onView(withText("VIEW QUIZ ANSWER")).perform(click());

        onView(withText("Historical")).check(matches(isDisplayed())).perform(click());
        onView(withText("Question: Number of Canadians participating in World War II")).check(matches(isDisplayed()));
        onView(withText("Answer: 1 million")).check(matches(isDisplayed()));
        onView(withText("NEXT")).perform(click());
        onView(withText("Question: The capital city of Canada is Vancouver")).check(matches(isDisplayed()));
        onView(withText("Answer: False")).check(matches(isDisplayed()));
        onView(withText("NEXT")).perform(click());
        onView(withText("Question: The year of the founding of Canada is 1867")).check(matches(isDisplayed()));
        onView(withText("Answer: True")).check(matches(isDisplayed()));
        onView(withText("NEXT")).perform(click());
        onView(withText("Question: What year was the founding of Canada?")).check(matches(isDisplayed()));
        onView(withText("Answer: 1867")).check(matches(isDisplayed()));
        onView(withText("NEXT")).perform(click());
        onView(withText("Question: Which aircraft manufacture belong to Canada?")).check(matches(isDisplayed()));
        onView(withText("Answer: Bombardier")).check(matches(isDisplayed()));
        onView(withText("NEXT")).perform(click());
        pressBack();

        onView(withText("Math")).check(matches(isDisplayed())).perform(click());
        onView(withText("Question: The positive result of square root of 16 is 3")).check(matches(isDisplayed()));
        onView(withText("Answer: False")).check(matches(isDisplayed()));
        onView(withText("NEXT")).perform(click());
        onView(withText("Question: The positive result of square root of 25 is 5")).check(matches(isDisplayed()));
        onView(withText("Answer: True")).check(matches(isDisplayed()));
        onView(withText("NEXT")).perform(click());
        onView(withText("Question: What is the positive result of square root of 36?")).check(matches(isDisplayed()));
        onView(withText("Answer: 6")).check(matches(isDisplayed()));
        onView(withText("NEXT")).perform(click());
        onView(withText("Question: What is the result of 1+1?")).check(matches(isDisplayed()));
        onView(withText("Answer: 2")).check(matches(isDisplayed()));
        onView(withText("NEXT")).perform(click());
        pressBack();

        onView(withText("Geographic")).check(matches(isDisplayed())).perform(click());
        onView(withText("Question: What is the area of Canada in square kilometers?")).check(matches(isDisplayed()));
        onView(withText("Answer: 9.98 million")).check(matches(isDisplayed()));
        onView(withText("NEXT")).perform(click());
        onView(withText("Question: What is the capital city of Canada?")).check(matches(isDisplayed()));
        onView(withText("Answer: Ottawa")).check(matches(isDisplayed()));
        onView(withText("NEXT")).perform(click());
        pressBack();

    }

}
