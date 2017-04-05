package at.sw2017xp3.regionalo;


import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class ExtendedSearchActivityTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("at.sw2017xp3.regionalo", appContext.getPackageName());
    }

    @Rule
    public ActivityTestRule<ExtendedSearchActivity> extendedSearchActivityActivityTestRule =
            new ActivityTestRule<>(ExtendedSearchActivity.class);


    @Test
    public void testButtons() {
        onView(withId(R.id.Button_ID_ExtendedSearchStart)).perform(click());
        onView(withId(R.id.Button_ID_ResetFilterExtendedSearch)).perform(click());
    }

    @Test
    public void testFields() {
        onView(withId(R.id.Spinner_ID_ExtendedSearch)).perform(click());
        onView(withText("Alphabetisch")).perform(click());

        onView(withId(R.id.checkBox_ID_BiologischerAnbau)).perform(click());

        onView(withId(R.id.checkBox_ID_KategorieObst)).perform(click());
        onView(withId(R.id.checkBox_ID_KategorieGemüse)).perform(click());
        onView(withId(R.id.checkBox_ID_KategoriePilze)).perform(click());
        onView(withId(R.id.checkBox_ID_KategoriePlanzenUndSamen)).perform(click());
        onView(withId(R.id.checkBox_ID_KategorieHolz)).perform(click());
        onView(withId(R.id.checkBox_ID_WeitereGartenprodukte)).perform(click());












    }

}