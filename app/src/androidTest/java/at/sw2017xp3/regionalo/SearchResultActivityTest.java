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
import static android.support.test.espresso.action.ViewActions.doubleClick;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.action.ViewActions.pressKey;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isNotChecked;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class SearchResultActivityTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("at.sw2017xp3.regionalo", appContext.getPackageName());
    }

    @Rule
    public ActivityTestRule<SearchResultActivity> menuActivityTestRule =
            new ActivityTestRule<>(SearchResultActivity.class, true, true);




    @Test
    public void testButtons() {
        onView(withId(R.id.expand)).perform(longClick());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.Spinner_ID_ExtendedSearch)).perform(click());

        onView(withText("Alphabetisch")).perform(click());

        onView(withId(R.id.checkBox_ID_BiologischerAnbau)).perform(scrollTo(),click());
        onView(withId(R.id.cb_category_0)).perform(scrollTo(),click());
        onView(withId(R.id.cb_category_1)).perform(scrollTo(),click());
        onView(withId(R.id.cb_category_2)).perform(scrollTo(),click());
        onView(withId(R.id.cb_category_3)).perform(scrollTo(),click());
        onView(withId(R.id.cb_category_4)).perform(scrollTo(),click());
        onView(withId(R.id.cb_category_5)).perform(scrollTo(),click());
        onView(withId(R.id.cb_seller_0)).perform(scrollTo(), click());
        onView(withId(R.id.cb_seller_1)).perform(scrollTo(), click());
        onView(withId(R.id.cb_transfer_0)).perform(scrollTo(), click());
        onView(withId(R.id.cb_transfer_1)).perform(scrollTo(), click());
        onView(withId(R.id.cb_transfer_2)).perform(scrollTo(), click());
        onView(withId(R.id.Button_ID_ResetFilterExtendedSearch)).perform(scrollTo(), click());


        onView(withId(R.id.checkBox_ID_BiologischerAnbau)).perform(scrollTo());
        onView(withId(R.id.checkBox_ID_BiologischerAnbau)).check(matches(isNotChecked()));
        onView(withId(R.id.cb_category_0)).perform(scrollTo());
        onView(withId(R.id.cb_category_0)).check(matches(isNotChecked()));
        onView(withId(R.id.cb_category_1)).perform(scrollTo());
        onView(withId(R.id.cb_category_1)).check(matches(isNotChecked()));
        onView(withId(R.id.cb_category_2)).perform(scrollTo());
        onView(withId(R.id.cb_category_2)).check(matches(isNotChecked()));
        onView(withId(R.id.cb_category_3)).perform(scrollTo());
        onView(withId(R.id.cb_category_3)).check(matches(isNotChecked()));
        onView(withId(R.id.cb_category_4)).perform(scrollTo());
        onView(withId(R.id.cb_category_4)).check(matches(isNotChecked()));
        onView(withId(R.id.cb_category_5)).perform(scrollTo());
        onView(withId(R.id.cb_category_5)).check(matches(isNotChecked()));
        onView(withId(R.id.cb_seller_0)).perform(scrollTo());
        onView(withId(R.id.cb_seller_0)).check(matches(isNotChecked()));
        onView(withId(R.id.cb_seller_1)).perform(scrollTo());
        onView(withId(R.id.cb_seller_1)).check(matches(isNotChecked()));
        onView(withId(R.id.cb_transfer_0)).perform(scrollTo());
        onView(withId(R.id.cb_transfer_0)).check(matches(isNotChecked()));
        onView(withId(R.id.cb_transfer_1)).perform(scrollTo());
        onView(withId(R.id.cb_transfer_1)).check(matches(isNotChecked()));
        onView(withId(R.id.cb_transfer_2)).perform(scrollTo());
        onView(withId(R.id.cb_transfer_2)).check(matches(isNotChecked()));


        onView(withId(R.id.Button_ID_ResetFilterExtendedSearch)).perform(scrollTo(), click());
        onView(withId(R.id.Button_ID_ExtendedSearchStart)).perform(scrollTo(), click());




    }

    @Test
    public void testProgressBar()
    {
        onView(withId(R.id.expand)).perform(longClick());
        onView(withId(R.id.seekBar_ID_Entfernung)).perform(click());
    }

    @Test
    public void testFilterSearch()
    {
        onView(withId(R.id.expand)).perform(longClick());
        onView(withId(R.id.seekBar_ID_Entfernung)).perform(click());
        onView(withId(R.id.checkBox_ID_BiologischerAnbau)).perform(scrollTo(),click());
        onView(withId(R.id.cb_category_0)).perform(scrollTo(),click());
        onView(withId(R.id.cb_category_1)).perform(scrollTo(),click());
        onView(withId(R.id.cb_category_2)).perform(scrollTo(),click());
        onView(withId(R.id.cb_category_3)).perform(scrollTo(),click());
        onView(withId(R.id.cb_category_4)).perform(scrollTo(),click());
        onView(withId(R.id.cb_category_5)).perform(scrollTo(),click());
        onView(withId(R.id.cb_seller_0)).perform(scrollTo(), click());
        onView(withId(R.id.cb_seller_1)).perform(scrollTo(), click());
        onView(withId(R.id.cb_transfer_0)).perform(scrollTo(), click());
        onView(withId(R.id.cb_transfer_1)).perform(scrollTo(), click());
        onView(withId(R.id.cb_transfer_2)).perform(scrollTo(), click());
        onView(withId(R.id.Button_ID_ExtendedSearchStart2)).perform(scrollTo(),click());

    }


    @Test
    public void testSearchSpeckDetailLogin(){
        onView(withId(R.id.searchViewResult)).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.searchViewResult)).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.searchViewResult)).perform(typeText("Speck"));
        onView(withId(R.id.searchViewResult)).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.searchViewResult)).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.searchViewResult)).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.searchViewResult)).perform(typeText("Speck"));
        onView(withId(R.id.searchViewResult)).perform(pressKey(KeyEvent.KEYCODE_ENTER));

        onView(withId(R.id.buttonMenuLogin)).perform(click());
    }





}


