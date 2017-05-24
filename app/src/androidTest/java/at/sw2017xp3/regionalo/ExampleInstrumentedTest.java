package at.sw2017xp3.regionalo;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.KeyEvent;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressKey;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.core.IsNot.not;

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
    public void testMenuButtonsFirstScreen (){
        onView(withId(R.id.buttonMeat)).perform( click());
        pressBack();
        onView(withId(R.id.buttonVegetables)).perform( click());
        pressBack();
        onView(withId(R.id.buttonFruit)).perform( click());
        pressBack();
        onView(withId(R.id.buttonMilk)).perform( click());
        pressBack();
        onView(withId(R.id.buttonCereals)).perform( click());
        pressBack();
        onView(withId(R.id.buttonOthers)).perform( click());
    }


    @Ignore
    @Test
    public void testSearchGouda(){
        onView(withId(R.id.searchViewHome)).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.searchViewHome)).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.searchViewHome)).perform(typeText("Gouda"));
        onView(withId(R.id.searchViewHome)).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(16)).perform(click());
    }

    @Ignore
    @Test
    public void testSearchFleisch(){
        onView(withId(R.id.searchViewHome)).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.searchViewHome)).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.searchViewHome)).perform(typeText("Spe"));
        onView(withId(R.id.searchViewHome)).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(1)).perform(click());
    }

    @Ignore
    @Test
    public void testSearchObst(){
        onView(withId(R.id.searchViewHome)).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.searchViewHome)).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.searchViewHome)).perform(typeText("Birne"));
        onView(withId(R.id.searchViewHome)).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(8)).perform(click());
    }

    @Ignore
    @Test
    public void testSearchGemüse(){
        onView(withId(R.id.searchViewHome)).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.searchViewHome)).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.searchViewHome)).perform(typeText("Tomaten"));
        onView(withId(R.id.searchViewHome)).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(10)).perform(click());
    }

    @Ignore
    @Test
    public void testSearchSonstiges(){
        onView(withId(R.id.searchViewHome)).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.searchViewHome)).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.searchViewHome)).perform(typeText("Honig"));
        onView(withId(R.id.searchViewHome)).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(26)).perform(click());
    }

    @Ignore
    @Test
    public void testSearchGetreide(){
        onView(withId(R.id.searchViewHome)).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.searchViewHome)).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.searchViewHome)).perform(typeText("Weizen"));
        onView(withId(R.id.searchViewHome)).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(20)).perform(click());
    }



    @Ignore
    @Test
    public void testSearchFunction() {
        onView(withId(R.id.searchViewHome)).perform(click());
        onView(withId(R.id.searchViewHome)).perform(typeText("Hallo"), click());
    }

    @Test
    public void testLoginButton(){
        onView(withId(R.id.buttonMenuLogin)).perform(click());
    }

    @Test
    public void onClick(){
        onView(withId(R.id.buttonMenuLogin)).perform(click());
        onView(withId(R.id.textViewEmail)).perform(typeText("Hallo Welt!"));
        onView(withId(R.id.textViewEmail)).check(matches(withText("Hallo Welt!")));
    }

    @Ignore
    @Test
    public void testSearchSpeckDetail(){
        onView(withId(R.id.searchViewHome)).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.searchViewHome)).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.searchViewHome)).perform(typeText("Speck"));
        onView(withId(R.id.searchViewHome)).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(1)).perform(click());
        //onView(withId(R.id.ButtonContact)).perform(scrollTo(), click());

    }


    @Ignore
    @Test
    public void testSpeckDetail(){
        onView(withId(1)).perform(click());
        onView(withId(R.id.textViewProductName)).check(matches(withText("Speck")));

    }

    @Ignore
    @Test
    public void testLikeButtonSearchBirneNotLikedAndLiked()
    {
        onView(withId(R.id.searchViewHome)).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.searchViewHome)).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.searchViewHome)).perform(typeText("Birne"));
        onView(withId(R.id.searchViewHome)).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(8)).perform(click());
        onView(withId(R.id.textViewProductName)).check(matches(withText("Birnen")));


        if(!onView(withId(R.id.buttonLike)).equals(isEnabled())) {
            onView(withId(R.id.buttonLike)).perform(click());
        } else {
            onView(withId(R.id.buttonLike)).check(matches((isEnabled())));
            onView(withId(R.id.buttonLike)).perform(click());
            onView(withId(R.id.buttonLike)).check(matches(not(isEnabled())));
        }

        pressBack();

        onView(withId(8)).perform(click());
        onView(withId(R.id.textViewProductName)).check(matches(withText("Birnen")));

        if(!onView(withId(R.id.buttonLike)).equals(isEnabled())) {
            onView(withId(R.id.buttonLike)).perform(click());
        } else {
            onView(withId(R.id.buttonLike)).check(matches((isEnabled())));
            onView(withId(R.id.buttonLike)).perform(click());
            onView(withId(R.id.buttonLike)).check(matches(not(isEnabled())));
        }

    }


    @Test
    public void testLikeButtonSpeckNotLikedAndLiked() {



        onView(withId(1)).perform(click());
        onView(withId(R.id.textViewProductName)).check(matches(withText("Speck")));

        if(!onView(withId(R.id.buttonLike)).equals(isEnabled())) {
            onView(withId(R.id.buttonLike)).perform(click());
        } else {
            onView(withId(R.id.buttonLike)).check(matches((isEnabled())));
            onView(withId(R.id.buttonLike)).perform(click());
            onView(withId(R.id.buttonLike)).check(matches(not(isEnabled())));
        }

        pressBack();

        onView(withId(1)).perform(click());
        onView(withId(R.id.textViewProductName)).check(matches(withText("Speck")));

        if(!onView(withId(R.id.buttonLike)).equals(isEnabled())) {
            onView(withId(R.id.buttonLike)).perform(click());
        } else {
            onView(withId(R.id.buttonLike)).check(matches((isEnabled())));
            onView(withId(R.id.buttonLike)).perform(click());
            onView(withId(R.id.buttonLike)).check(matches(not(isEnabled())));
        }

    }

    @Ignore
    @Test
    public void testSearchSpeckDetailLogin(){
        onView(withId(R.id.searchViewHome)).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.searchViewHome)).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.searchViewHome)).perform(typeText("Speck"));
        onView(withId(R.id.searchViewHome)).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(1)).perform(click());
        //onView(withId(R.id.ButtonContact)).perform(scrollTo(), click());

    }
}