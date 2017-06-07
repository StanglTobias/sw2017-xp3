package at.sw2017xp3.regionalo;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;
import android.view.KeyEvent;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertEquals;

/**
 * Created by Ismael on 24.05.2017.
 */

public class OverflowMenuTest {
    @Rule
    public ActivityTestRule<HomeActivity> mActivityRule = new
            ActivityTestRule<>(HomeActivity.class);



   //@Test
    /*public void checkButtonLogin() {

        Instrumentation.ActivityMonitor am = getInstrumentation().addMonitor(HomeActivity.class.getName(), null, false);
        getInstrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_MENU);
        getInstrumentation().invokeMenuActionSync(over, R.id.buttonProducerRegistration, 0);

       Activity a = getInstrumentation().waitForMonitorWithTimeout(am, 1000);
       assertEquals(true, getInstrumentation().checkMonitorHit(am, 1));
       a.finish();

        /*getInstrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_MENU);
        getInstrumentation().invokeMenuActionSync(, R.id.buttonHome, 0);
        onView(withId(R.id.buttonRegister)).perform(click());
    }*/


    @Test
    public void userRegistrationButton(){
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("als Produzent registrieren")).perform(click());
        pressBack();
    }


    @Test
    public void loginAndProductButton(){
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Login")).perform(click());
        onView(withId(R.id.textViewEmail)).perform(typeText("mail"));
        onView(withId(R.id.textViewPassword)).perform(typeText("pass"));
        pressBack();
        onView(withId(R.id.buttonLogin)).perform(click());
        pressBack();
        pressBack();
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Produkt hinzufügen")).perform(click());
        pressBack();
        pressBack();

    }


    @Test
    public void logoutButton(){
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Logout")).perform(click());

    }

    @Test
    public void homeButton() {
        onView(withId(R.id.logo)).perform(click());
    }

}

