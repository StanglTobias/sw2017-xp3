package at.sw2017xp3.regionalo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.core.deps.guava.collect.Iterables;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.support.test.runner.lifecycle.Stage;
import android.widget.EditText;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.HashMap;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.getIdlingResources;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.is;


/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */


@RunWith(AndroidJUnit4.class)
public class RegistryInstrumentedTest {

    @Rule
    public ActivityTestRule<RegisterActivity> mActivityRule = new
            ActivityTestRule<>(RegisterActivity.class);

    @Test
    public void checkButtonClicks() {
        closeSoftKeyboard();
        onView(withText("Registrieren")).perform(scrollTo(), click());
        onView(withText("Registrieren")).perform(scrollTo(), click());
        onView(withText(R.string.enterComulsoryFields)).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));

    }

    private void enterEverythingWithoutPasswordsAndCheck() {
        onView(withId(R.id.et_register_ID_0)).perform(scrollTo(), typeText("Testhofname"));
        onView(withId(R.id.et_register_ID_1)).perform(scrollTo(), typeText("Vorname1"));
        onView(withId(R.id.et_register_ID_2)).perform(scrollTo(), typeText("Nachname1"));
        onView(withId(R.id.et_register_ID_3)).perform(scrollTo(), typeText("test@testmail.com"));
        onView(withId(R.id.et_register_ID_4)).perform(scrollTo(), typeText("0900666666"));
        onView(withId(R.id.et_register_ID_5)).perform(scrollTo(), typeText("Testort"));
        onView(withId(R.id.et_register_ID_6)).perform(scrollTo(), typeText("1234"));
        onView(withId(R.id.et_register_ID_7)).perform(scrollTo(), typeText("Testaddresse"));

        onView(withId(R.id.et_register_ID_0)).check(matches(withText("Testhofname")));
        onView(withId(R.id.et_register_ID_1)).check(matches(withText("Vorname1")));
        onView(withId(R.id.et_register_ID_2)).check(matches(withText("Nachname1")));
        onView(withId(R.id.et_register_ID_3)).check(matches(withText("test@testmail.com")));
        onView(withId(R.id.et_register_ID_4)).check(matches(withText("0900666666")));
        onView(withId(R.id.et_register_ID_5)).check(matches(withText("Testort")));
        onView(withId(R.id.et_register_ID_6)).check(matches(withText("1234")));
        onView(withId(R.id.et_register_ID_7)).check(matches(withText("Testaddresse")));
    }

    @Test
    public void checkTextViews() {
        closeSoftKeyboard();
        enterEverythingWithoutPasswordsAndCheck();

        onView(withId(R.id.et_register_ID_8)).perform(scrollTo(), typeText("passwort1234"));
        onView(withId(R.id.et_register_ID_9)).perform(scrollTo(), typeText("passwort1235"));
        onView(withId(R.id.et_register_ID_8)).check(matches(withText("passwort1234")));
        onView(withId(R.id.et_register_ID_9)).check(matches(withText("passwort1235")));
    }


    //   private static final RegisterActivity class2 = mock(RegisterActivity.class);

    @Test
    public void checkWrongDatabaseInsert() {

        closeSoftKeyboard();
        onView(withText("Registrieren")).perform(scrollTo(), click());

        HashMap<String, String> registerFields = new HashMap<>();
        registerFields.put("email", "sööööör" + new Date().getTime());
        mActivityRule.getActivity().createRegisterUser(registerFields);
    }

    @Test
    public void checkWrongDatabaseInsertEasy() {

        HashMap<String, String> registerFields = new HashMap<>();
        registerFields.put("email", "sööööör" + new Date().getTime());
        mActivityRule.getActivity().createRegisterUser(registerFields);
    }


    @Test
    public void checkEmptyFields() {

        closeSoftKeyboard();
        onView(withId(R.id.Button_ID_ConfirmRegistration)).perform(scrollTo(), click());
        // onView(withText(R.string.enterComulsoryFields)).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));

        checkTextViews();
        closeSoftKeyboard();
        onView(withId(R.id.Button_ID_ConfirmRegistration)).perform(scrollTo(), click());
        onView(withText(R.string.passwordNotMatching)).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void checkPasswordFields() {

        closeSoftKeyboard();
        enterEverythingWithoutPasswordsAndCheck();

        onView(withId(R.id.et_register_ID_8)).perform(scrollTo(), typeText("passwort1234"));
        onView(withId(R.id.et_register_ID_9)).perform(scrollTo(), typeText("passwort1235"));
        onView(withId(R.id.et_register_ID_8)).check(matches(withText("passwort1234")));
        onView(withId(R.id.et_register_ID_9)).check(matches(withText("passwort1235")));

        closeSoftKeyboard();
        onView(withId(R.id.Button_ID_ConfirmRegistration)).perform(scrollTo(), click());
        onView(withId(R.id.Button_ID_ConfirmRegistration)).perform(scrollTo(), click());
        onView(withText(R.string.passwordNotMatching)).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void checkUserAlreadyInDB() {

        closeSoftKeyboard();

        onView(withId(R.id.et_register_ID_0)).perform(scrollTo(), typeText("Testhofname"));
        onView(withId(R.id.et_register_ID_1)).perform(scrollTo(), typeText("Vorname1"));
        onView(withId(R.id.et_register_ID_2)).perform(scrollTo(), typeText("Nachname1"));
        onView(withId(R.id.et_register_ID_3)).perform(scrollTo(), typeText("nixemail"));
        onView(withId(R.id.et_register_ID_4)).perform(scrollTo(), typeText("0900666666"));
        onView(withId(R.id.et_register_ID_5)).perform(scrollTo(), typeText("Testort"));
        onView(withId(R.id.et_register_ID_6)).perform(scrollTo(), typeText("1234"));
        onView(withId(R.id.et_register_ID_7)).perform(scrollTo(), typeText("Testaddresse"));

        onView(withId(R.id.et_register_ID_0)).check(matches(withText("Testhofname")));
        onView(withId(R.id.et_register_ID_1)).check(matches(withText("Vorname1")));
        onView(withId(R.id.et_register_ID_2)).check(matches(withText("Nachname1")));
        onView(withId(R.id.et_register_ID_3)).check(matches(withText("nixemail")));
        onView(withId(R.id.et_register_ID_4)).check(matches(withText("0900666666")));
        onView(withId(R.id.et_register_ID_5)).check(matches(withText("Testort")));
        onView(withId(R.id.et_register_ID_6)).check(matches(withText("1234")));
        onView(withId(R.id.et_register_ID_7)).check(matches(withText("Testaddresse")));

        onView(withId(R.id.et_register_ID_8)).perform(scrollTo(), typeText("passwort1235"));
        onView(withId(R.id.et_register_ID_9)).perform(scrollTo(), typeText("passwort1235"));
        onView(withId(R.id.et_register_ID_8)).check(matches(withText("passwort1235")));
        onView(withId(R.id.et_register_ID_9)).check(matches(withText("passwort1235")));

        closeSoftKeyboard();
        onView(withId(R.id.Button_ID_ConfirmRegistration)).perform(scrollTo(), click());
        onView(withText(R.string.emailAlreadyUsed)).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void checkFieldClick() {

        closeSoftKeyboard();
        onView(withId(R.id.et_register_ID_0)).perform(scrollTo(), click());

    }

    @Test
    public void checkCorrectRegister() {

        closeSoftKeyboard();

        onView(withId(R.id.et_register_ID_0)).perform(scrollTo(), typeText("Testhofname"));
        onView(withId(R.id.et_register_ID_1)).perform(scrollTo(), typeText("Vorname1"));
        onView(withId(R.id.et_register_ID_2)).perform(scrollTo(), typeText("Nachname1"));
        long date = new Date().getTime();
        onView(withId(R.id.et_register_ID_3)).perform(scrollTo(), typeText("testmail" + String.valueOf(date)));
        onView(withId(R.id.et_register_ID_4)).perform(scrollTo(), typeText("0900666666"));
        onView(withId(R.id.et_register_ID_5)).perform(scrollTo(), typeText("Testort"));
        onView(withId(R.id.et_register_ID_6)).perform(scrollTo(), typeText("1234"));
        onView(withId(R.id.et_register_ID_7)).perform(scrollTo(), typeText("Testaddresse"));
        onView(withId(R.id.et_register_ID_8)).perform(scrollTo(), typeText("passwort1234"));
        onView(withId(R.id.et_register_ID_9)).perform(scrollTo(), typeText("passwort1234"));

        onView(withId(R.id.et_register_ID_0)).check(matches(withText("Testhofname")));
        onView(withId(R.id.et_register_ID_1)).check(matches(withText("Vorname1")));
        onView(withId(R.id.et_register_ID_2)).check(matches(withText("Nachname1")));
        onView(withId(R.id.et_register_ID_3)).check(matches(withText("testmail" + String.valueOf(date))));
        onView(withId(R.id.et_register_ID_4)).check(matches(withText("0900666666")));
        onView(withId(R.id.et_register_ID_5)).check(matches(withText("Testort")));
        onView(withId(R.id.et_register_ID_6)).check(matches(withText("1234")));
        onView(withId(R.id.et_register_ID_7)).check(matches(withText("Testaddresse")));
        onView(withId(R.id.et_register_ID_8)).check(matches(withText("passwort1234")));
        onView(withId(R.id.et_register_ID_9)).check(matches(withText("passwort1234")));

        onView(withId(R.id.Button_ID_ConfirmRegistration)).perform(scrollTo(), click());
        onView(withText(R.string.userSucessfullyRegistered)).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));

    }
}