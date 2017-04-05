package at.sw2017xp3.regionalo;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by lukas on 05.04.2017.
 */

public class ProductDetailActivityInstrumentedTest {

    @Rule
    public ActivityTestRule<ProductDetailActivity> mActivityRule = new
            ActivityTestRule<>(ProductDetailActivity.class);

    @Test
    public void checkButtonClicks() {
        onView(withId(R.id.ButtonContact)).perform(scrollTo(),click());
        onView(withId(R.id.buttonLike)).perform(click());
    }


}
