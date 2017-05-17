package at.sw2017xp3.regionalo;

import android.support.test.rule.ActivityTestRule;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by lukas on 05.04.2017.
 */

public class ProductDetailActivityInstrumentedTest{

    @Rule
    public ActivityTestRule<ProductDetailActivity> mActivityRule = new
            ActivityTestRule<>(ProductDetailActivity.class);

    @Ignore
    @Test
    public void checkButtonContact() {
        onView(withId(R.id.ButtonContact)).perform(scrollTo(),click());
    }

    @Test
    public void testLikeButton() {
        if(!onView(withId(R.id.buttonLike)).equals(isEnabled())) {
            onView(withId(R.id.buttonLike)).perform(click());
        } else {
            onView(withId(R.id.buttonLike)).check(matches((isEnabled())));
            onView(withId(R.id.buttonLike)).perform(click());
            onView(withId(R.id.buttonLike)).check(matches(not(isEnabled())));
        }
    }

    @Test
    public void pressLoginButton(){
        onView(withId(R.id.buttonMenuLogin)).perform(click());
    }
}
