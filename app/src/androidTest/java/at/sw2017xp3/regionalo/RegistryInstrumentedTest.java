package at.sw2017xp3.regionalo;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.EditText;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;



/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */


@RunWith(AndroidJUnit4.class)
public class RegistryInstrumentedTest {

    @Rule
    public ActivityTestRule<RegisterActivity> mActivityRule = new
            ActivityTestRule<>(RegisterActivity.class);

    @Test
    public void checkButtonClicks() {
        onView(withText("Registrieren")).perform(scrollTo(), click());
    }

    @Test
    public void checkTextViews() {

        onView(withId(R.id.et_register_ID_0)).perform(scrollTo(), typeText("Testhofname"));
        onView(withId(R.id.et_register_ID_1)).perform(scrollTo(), typeText("Vorname1"));
        onView(withId(R.id.et_register_ID_2)).perform(scrollTo(), typeText("Nachname1"));
        onView(withId(R.id.et_register_ID_3)).perform(scrollTo(), typeText("test@testmail.com"));
        onView(withId(R.id.et_register_ID_4)).perform(scrollTo(), typeText("0900666666"));
        onView(withId(R.id.et_register_ID_5)).perform(scrollTo(), typeText("Testort"));
        onView(withId(R.id.et_register_ID_6)).perform(scrollTo(), typeText("1234"));
        onView(withId(R.id.et_register_ID_7)).perform(scrollTo(), typeText("Testaddresse"));
        onView(withId(R.id.et_register_ID_8)).perform(scrollTo(), typeText("passwort1234"));
        onView(withId(R.id.et_register_ID_9)).perform(scrollTo(), typeText("passwort1235"));

        onView(withId(R.id.et_register_ID_0)).check(matches(withText("Testhofname")));
        onView(withId(R.id.et_register_ID_1)).check(matches(withText("Vorname1")));
        onView(withId(R.id.et_register_ID_2)).check(matches(withText("Nachname1")));
        onView(withId(R.id.et_register_ID_3)).check(matches(withText("test@testmail.com")));
        onView(withId(R.id.et_register_ID_4)).check(matches(withText("0900666666")));
        onView(withId(R.id.et_register_ID_5)).check(matches(withText("Testort")));
        onView(withId(R.id.et_register_ID_6)).check(matches(withText("1234")));
        onView(withId(R.id.et_register_ID_7)).check(matches(withText("Testaddresse")));
        onView(withId(R.id.et_register_ID_8)).check(matches(withText("passwort1234")));
        onView(withId(R.id.et_register_ID_9)).check(matches(withText("passwort1235")));
    }
}
