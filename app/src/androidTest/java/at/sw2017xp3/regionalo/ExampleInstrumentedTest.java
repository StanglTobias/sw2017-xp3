package at.sw2017xp3.regionalo;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("at.sw2017xp3.regionalo", appContext.getPackageName());
    }

    @Rule
    public ActivityTestRule<HomeActivity> menuActivityTestRule =
            new ActivityTestRule<>(HomeActivity.class, true, true);

    @Test
    public void testMenuButtonsFirstScreen () throws Exception {
        onView(withText("Fleisch")).perform(click());
        onView(withText("Gemüse")).perform(click());
        onView(withText("Getreide")).perform(click());
        onView(withText("Obst")).perform(click());
        onView(withText("Milch")).perform(click());
        onView(withId(R.id.buttonOthers)).perform(scrollTo(), click());
    }

    @Ignore
    @Test
    public void testSearchFunction() {
        onView(withId(R.id.searchViewHome)).perform(click());
        onView(withId(R.id.searchViewHome)).perform(typeText("Hallo"), click());
    }

    @Test
    public void testLoginButton(){
        onView(withId(R.id.buttonLogin)).perform(click());
    }

    @Test
    public void onClick(){
        onView(withId(R.id.buttonLogin)).perform(click());
        onView(withId(R.id.textViewEmail)).perform(typeText("Hallo Welt!"));
        onView(withId(R.id.textViewEmail)).check(matches(withText("Hallo Welt!")));
    }
}