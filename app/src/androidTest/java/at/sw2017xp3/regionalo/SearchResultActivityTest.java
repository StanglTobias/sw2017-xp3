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
import static android.support.test.espresso.action.ViewActions.doubleClick;
import static android.support.test.espresso.action.ViewActions.longClick;
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
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.Spinner_ID_ExtendedSearch)).perform(click());

        onView(withText("Alphabetisch")).perform(click());

        onView(withId(R.id.checkBox_ID_BiologischerAnbau)).perform(scrollTo(),click());
        onView(withId(R.id.checkBox_ID_KategorieObst)).perform(scrollTo(),click());
        onView(withId(R.id.checkBox_ID_KategorieGemüse)).perform(scrollTo(),click());
        onView(withId(R.id.checkBox_ID_KategoriePilze)).perform(scrollTo(),click());
        onView(withId(R.id.checkBox_ID_KategoriePlanzenUndSamen)).perform(scrollTo(),click());
        onView(withId(R.id.checkBox_ID_KategorieHolz)).perform(scrollTo(),click());
        onView(withId(R.id.checkBox_ID_WeitereGartenprodukte)).perform(scrollTo(),click());
        onView(withId(R.id.checkBox_ID_Burgenland)).perform(scrollTo(), click());
        onView(withId(R.id.checkBox_ID_Kaernten)).perform(scrollTo(), click());
        onView(withId(R.id.checkBox_ID_Niederoesterreich)).perform(scrollTo(), click());
        onView(withId(R.id.checkBox_ID_Oberoesterreich)).perform(scrollTo(), click());
        onView(withId(R.id.checkBox_ID_Salzburg)).perform(scrollTo(), click());
        onView(withId(R.id.checkBox_ID_Steiermark)).perform(scrollTo(), click());
        onView(withId(R.id.checkBox_ID_Tirol)).perform(scrollTo(), click());
        onView(withId(R.id.checkBox_ID_Vorarlberg)).perform(scrollTo(), click());
        onView(withId(R.id.checkBox_ID_Wien)).perform(scrollTo(), click());
        onView(withId(R.id.checkBox_ID_Privat)).perform(scrollTo(), click());
        onView(withId(R.id.checkBox_ID_Firma)).perform(scrollTo(), click());
        onView(withId(R.id.checkBox_ID_Zustellung)).perform(scrollTo(), click());
        onView(withId(R.id.checkBox_ID_Selbstabholung)).perform(scrollTo(), click());
        onView(withId(R.id.checkBox_ID_NichtBenoetigt)).perform(scrollTo(), click());
        onView(withId(R.id.checkBox_ID_BereitsGeerntet)).perform(scrollTo(), click());
        onView(withId(R.id.checkBox_ID_SelbstErnten)).perform(scrollTo(), click());
        onView(withId(R.id.Button_ID_ResetFilterExtendedSearch)).perform(scrollTo(), click());


        onView(withId(R.id.checkBox_ID_BiologischerAnbau)).perform(scrollTo());
        onView(withId(R.id.checkBox_ID_BiologischerAnbau)).check(matches(isNotChecked()));
        onView(withId(R.id.checkBox_ID_KategorieObst)).perform(scrollTo());
        onView(withId(R.id.checkBox_ID_KategorieObst)).check(matches(isNotChecked()));
        onView(withId(R.id.checkBox_ID_KategorieGemüse)).perform(scrollTo());
        onView(withId(R.id.checkBox_ID_KategorieGemüse)).check(matches(isNotChecked()));
        onView(withId(R.id.checkBox_ID_KategoriePilze)).perform(scrollTo());
        onView(withId(R.id.checkBox_ID_KategoriePilze)).check(matches(isNotChecked()));
        onView(withId(R.id.checkBox_ID_KategoriePlanzenUndSamen)).perform(scrollTo());
        onView(withId(R.id.checkBox_ID_KategoriePlanzenUndSamen)).check(matches(isNotChecked()));
        onView(withId(R.id.checkBox_ID_KategorieHolz)).perform(scrollTo());
        onView(withId(R.id.checkBox_ID_KategorieHolz)).check(matches(isNotChecked()));
        onView(withId(R.id.checkBox_ID_WeitereGartenprodukte)).perform(scrollTo());
        onView(withId(R.id.checkBox_ID_WeitereGartenprodukte)).check(matches(isNotChecked()));
        onView(withId(R.id.checkBox_ID_Burgenland)).perform(scrollTo());
        onView(withId(R.id.checkBox_ID_Burgenland)).check(matches(isNotChecked()));
        onView(withId(R.id.checkBox_ID_Kaernten)).perform(scrollTo());
        onView(withId(R.id.checkBox_ID_Kaernten)).check(matches(isNotChecked()));
        onView(withId(R.id.checkBox_ID_Niederoesterreich)).perform(scrollTo());
        onView(withId(R.id.checkBox_ID_Niederoesterreich)).check(matches(isNotChecked()));
        onView(withId(R.id.checkBox_ID_Oberoesterreich)).perform(scrollTo());
        onView(withId(R.id.checkBox_ID_Oberoesterreich)).check(matches(isNotChecked()));
        onView(withId(R.id.checkBox_ID_Salzburg)).perform(scrollTo());
        onView(withId(R.id.checkBox_ID_Salzburg)).check(matches(isNotChecked()));
        onView(withId(R.id.checkBox_ID_Steiermark)).perform(scrollTo());
        onView(withId(R.id.checkBox_ID_Steiermark)).check(matches(isNotChecked()));
        onView(withId(R.id.checkBox_ID_Tirol)).perform(scrollTo());
        onView(withId(R.id.checkBox_ID_Tirol)).check(matches(isNotChecked()));
        onView(withId(R.id.checkBox_ID_Vorarlberg)).perform(scrollTo());
        onView(withId(R.id.checkBox_ID_Vorarlberg)).check(matches(isNotChecked()));
        onView(withId(R.id.checkBox_ID_Wien)).perform(scrollTo());
        onView(withId(R.id.checkBox_ID_Wien)).check(matches(isNotChecked()));
        onView(withId(R.id.checkBox_ID_Privat)).perform(scrollTo());
        onView(withId(R.id.checkBox_ID_Privat)).check(matches(isNotChecked()));
        onView(withId(R.id.checkBox_ID_Firma)).perform(scrollTo());
        onView(withId(R.id.checkBox_ID_Firma)).check(matches(isNotChecked()));
        onView(withId(R.id.checkBox_ID_Zustellung)).perform(scrollTo());
        onView(withId(R.id.checkBox_ID_Zustellung)).check(matches(isNotChecked()));
        onView(withId(R.id.checkBox_ID_Selbstabholung)).perform(scrollTo());
        onView(withId(R.id.checkBox_ID_Selbstabholung)).check(matches(isNotChecked()));
        onView(withId(R.id.checkBox_ID_NichtBenoetigt)).perform(scrollTo());
        onView(withId(R.id.checkBox_ID_NichtBenoetigt)).check(matches(isNotChecked()));
        onView(withId(R.id.checkBox_ID_BereitsGeerntet)).perform(scrollTo());
        onView(withId(R.id.checkBox_ID_BereitsGeerntet)).check(matches(isNotChecked()));
        onView(withId(R.id.checkBox_ID_SelbstErnten)).perform(scrollTo());
        onView(withId(R.id.checkBox_ID_SelbstErnten)).check(matches(isNotChecked()));

        onView(withId(R.id.Button_ID_ExtendedSearchStart)).perform(scrollTo(), click());
        onView(withId(R.id.Button_ID_ResetFilterExtendedSearch)).perform(scrollTo(), click());


    }









}
