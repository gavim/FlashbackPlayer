package com.example.liam.flashbackplayer;

import android.*;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.GrantPermissionRule;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiSelector;
import android.util.Log;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

/**
 * Created by liam on 3/18/18.
 */

public class VibeModeTests {
    @Rule
    public ActivityTestRule<MainActivity> mainAct = new ActivityTestRule<MainActivity>(MainActivity.class);
    @Rule
    public GrantPermissionRule permissionRule = GrantPermissionRule.grant(android.Manifest.permission.ACCESS_FINE_LOCATION);
    @Rule
    public GrantPermissionRule permissionRule2 = GrantPermissionRule.grant(android.Manifest.permission.READ_EXTERNAL_STORAGE);
    @Rule
    public GrantPermissionRule permissionRule3 = GrantPermissionRule.grant(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);

    @Before
    public void loginAndEnsureSongMode() {
        try {
            onView(withId(R.id.btnSignIn)).perform(click());
            onView(withId(R.id.sign_in_button)).perform(click());
            UiDevice mUiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
            UiObject account = mUiDevice.findObject(new UiSelector().index(0));
            account.click();
        } catch(Exception e) {
            Log.e("TEST SIGN IN", e.getMessage());
        }

        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ViewInteraction sortBtn = onView(withId(R.id.btn_sortby));
        sortBtn.perform(click());
        onView(withText("Names")).perform(click());
    }


    @Test
    public void vibeModeTest() {
        onView(withId(R.id.btnFlashback)).perform(click());
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(true, mainAct.getActivity().masterList != null);
        assertEquals(true, mainAct.getActivity().masterList.size() > 0);
        assertEquals(true, mainAct.getActivity().musicController.isPlaying());
    }
}
