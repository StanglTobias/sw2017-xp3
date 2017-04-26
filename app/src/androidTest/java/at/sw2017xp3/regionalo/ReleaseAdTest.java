package at.sw2017xp3.regionalo;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;


/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */


@RunWith(AndroidJUnit4.class)
public class ReleaseAdTest {

    @Rule
    public ActivityTestRule<ReleaseAdActivity> mActivityRule = new
            ActivityTestRule<>(ReleaseAdActivity.class);


    @Test
    public void checkTextViews() {

        onView(withId(R.id.TextView_ID_ReleaseAdName)).perform(scrollTo(), typeText("Speck"));
        onView(withId(R.id.TextView_ID_ReleaseAdName)).check(matches(withText("Speck")));

        onView(withId(R.id.radioButton_ID_Privat)).perform(scrollTo(), click());
        onView(withId(R.id.radioButton_ID_Firma)).check(matches(not(isChecked())));
        onView(withId(R.id.radioButton_ID_Privat)).check(matches((isChecked())));

        onView(withId(R.id.editText_ID_Beschreibung)).perform(scrollTo(), typeText("ein seeeeeeeeeeeeeeeeeeeehr langer Text"));
        onView(withId(R.id.editText_ID_Beschreibung)).check(matches(withText("ein seeeeeeeeeeeeeeeeeeeehr langer Text")));

        onView(withId(R.id.spinner_ID_ReleaseAdKategorie)).perform(scrollTo(), click());
        onData(allOf(is(instanceOf(String.class)), is("Kategorie 2"))).perform(click());
        onView(withId(R.id.spinner_ID_ReleaseAdKategorie)).check(matches(withSpinnerText(containsString("Kategorie 2"))));

        onView(withId(R.id.spinner_ID_ReleaseAdUnterkategorie)).perform(scrollTo(), click());
        onData(allOf(is(instanceOf(String.class)), is("Unterkategorie 2"))).perform(click());
        onView(withId(R.id.spinner_ID_ReleaseAdUnterkategorie)).check(matches(withSpinnerText(containsString("Unterkategorie 2"))));









        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }



}
