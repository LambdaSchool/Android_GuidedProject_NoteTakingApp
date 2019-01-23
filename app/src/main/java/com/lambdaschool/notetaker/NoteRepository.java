package com.lambdaschool.notetaker;

import android.app.Activity;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NoteRepository {
//    private ArrayList<Note> notes;
        MutableLiveData<ArrayList<Note>> liveDataList;

    /*public NoteRepository() {
        this.notes = new ArrayList<>();
    }*/

    public MutableLiveData<ArrayList<Note>> getNotes(final Context context) {
        liveDataList = new MutableLiveData<>();
        // retrieve notes from online DB
        new Thread(new Runnable() {
            @Override
            public void run() {
                NotesDbDao.initializeInstance(context);
                // retrieve notes from cache
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        liveDataList.setValue(getNotesFromCache());
                    }
                });

                final ArrayList<Note> notes = NotesFirebaseDao.getNotes();

                final ArrayList<Note> updatedNotes = NotesDbDao.updateCache(notes);

                liveDataList.postValue(updatedNotes);
            }
        }).start();
        return liveDataList;
    }

    private ArrayList<Note> getNotesFromCache() {
        return NotesDbDao.readAllNotes();
    }

    public void addNote(final Note note) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String newId = NotesFirebaseDao.createNote(note);
                note.setId(newId);
                NotesDbDao.createNote(note);
                liveDataList.postValue(getNotesFromCache());
            }
        }).start();
//        return SharedPrefsDao.getAllNotes();
    }
}
