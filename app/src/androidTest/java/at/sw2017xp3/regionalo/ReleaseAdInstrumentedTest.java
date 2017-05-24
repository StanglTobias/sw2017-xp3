package at.sw2017xp3.regionalo;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


/**
 * Created by lukas on 24.05.2017.
 */

@RunWith(AndroidJUnit4.class)
public class ReleaseAdInstrumentedTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule = new
            ActivityTestRule<>(LoginActivity.class);

    @Test
    public void checkTextClicks() {
        moveToRightAcitivity();
        onView(withId(R.id.TextView_ID_Angebotstitel)).perform(scrollTo(), typeText("Supertomaten"));
        onView(withId(R.id.editText2)).perform(scrollTo(), typeText("Diese Tomaten sind super mega toll!!!"));
        onView(withId(R.id.spinner_ID_ReleaseAdKategorie)).perform(scrollTo(),click());
        pressBack();
        onView(withId(R.id.button_ID_Bildauswaehlen)).perform(scrollTo(),click());
        onView(withId(R.id.textView_ID_Preis)).perform(scrollTo(), typeText("1000"));
        onView(withId(R.id.spinner_ID_Einheit)).perform(scrollTo(),click());
        pressBack();
        onView(withId(R.id.checkBox_ID_ReleaseAd_BiologischerAnbau)).perform(scrollTo(),click());
        onView(withId(R.id.radioButton_ID_ReleaseAd_SelbstErnten)).perform(scrollTo(),click());
        onView(withId(R.id.radioButton_ID_ReleaseAd_BereitsGeerntet)).perform(scrollTo(),click());
        onView(withId(R.id.button_ID_ReleaseAd_Abbrechen)).perform(scrollTo(),click());
        onView(withId(R.id.button_ID_ReleaseAdSpeichern)).perform(scrollTo(),click());
    }

    public void moveToRightAcitivity() {
        onView(withId(R.id.textViewEmail)).perform(typeText("Test@gmx.at"));
        onView(withId(R.id.textViewPassword)).perform(typeText("Test"));
        closeSoftKeyboard();
        onView(withId(R.id.buttonLogin)).perform(click());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
