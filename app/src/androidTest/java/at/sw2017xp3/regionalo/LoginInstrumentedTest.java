package at.sw2017xp3.regionalo;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by lukas on 29.03.2017.
 */

public class LoginInstrumentedTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule = new
            ActivityTestRule<>(LoginActivity.class);

    @Test
    public void checkButtonClicks() {
        onView(withText("Login")).perform(click());
        onView(withText("Registrieren")).perform(click());
    }

    @Test
    public void checkTextViews() {

        onView(withId(R.id.textViewEmail)).perform(typeText("test@testmail.com"));
        onView(withId(R.id.textViewPassword)).perform(typeText("passwort1234"));

        onView(withId(R.id.textViewEmail)).check(matches(withText("test@testmail.com")));
        onView(withId(R.id.textViewPassword)).check(matches(withText("passwort1234")));
    }

    @Test
    public void checkGoToRegister(){
        onView(withId(R.id.buttonRegister)).perform(click());
        onView(withId(R.id.textView_Register_ID_Hofname)).perform(typeText("Testhofname"));
        onView(withId(R.id.textView_Register_ID_Hofname)).check(matches(withText("Testhofname")));
    }
}
