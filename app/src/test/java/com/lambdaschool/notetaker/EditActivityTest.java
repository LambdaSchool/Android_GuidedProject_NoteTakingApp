package com.lambdaschool.notetaker;

import android.app.Activity;
import android.content.Intent;
import android.widget.EditText;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.shadows.ShadowActivity;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class EditActivityTest {

    /*@Test
    public void onCreate() {
    }

    @Test
    public void setTheme() {
    }

    @Test
    public void onBackPressed() {
    }*/

    @Test public void shouldReturnNoteToMainActivity() {
        // Setup
        final String title = "title";
        final String content = "content";

        Activity       activity = Robolectric.setupActivity(EditActivity.class);
        ShadowActivity shadowActivity = Shadows.shadowOf(activity);
        ((EditText)activity.findViewById(R.id.edit_title)).setText(title);
        ((EditText)activity.findViewById(R.id.edit_content)).setText(content);

        // Execute
        activity.onBackPressed();
        final Intent resultIntent = shadowActivity.getResultIntent();
        final Note note = (Note) resultIntent.getSerializableExtra(EditActivity.EDIT_NOTE_KEY);

        // Make Assertions
        assertTrue(activity.isFinishing());
        assertEquals(Activity.RESULT_OK, shadowActivity.getResultCode());
        assertEquals(title, note.getTitle());
        assertEquals(content, note.getContent());

    }

    /*@Test public void shouldAddNoteToRepository() {
        Activity       activity = Robolectric.setupActivity(MainActivity.class);

    }*/
}