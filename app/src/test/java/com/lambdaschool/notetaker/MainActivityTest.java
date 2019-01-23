package com.lambdaschool.notetaker;

import android.app.Activity;
import android.content.Intent;

import org.apache.tools.ant.Main;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    @Test
    public void shouldStartEditActivity() {
        // Set up
        MainActivity activity = Robolectric.setupActivity(MainActivity.class);
        activity.findViewById(R.id.add_button).performClick();

        // Execute
        Intent expected = new Intent(activity, EditActivity.class);
        Intent actual = Shadows.shadowOf(activity).getNextStartedActivity();

        // Assert
        assertTrue(actual.filterEquals(expected));

    }



    /*@Test
    public void clickingLogin_shouldStartLoginActivity() {
        WelcomeActivity activity = Robolectric.setupActivity(WelcomeActivity.class);
        activity.findViewById(R.id.login).performClick();

        Intent expectedIntent = new Intent(activity, LoginActivity.class);
        Intent actual = shadowOf(RuntimeEnvironment.application).getNextStartedActivity();
        assertEquals(expectedIntent.getComponent(), actual.getComponent());
    }*/

}