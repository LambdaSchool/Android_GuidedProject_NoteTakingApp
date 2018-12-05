package com.lambdaschool.notetaker;

import android.arch.lifecycle.MutableLiveData;

import java.util.ArrayList;

public class NoteRepository {
//    private ArrayList<Note> notes;

    /*public NoteRepository() {
        this.notes = new ArrayList<>();
    }*/

    public MutableLiveData<ArrayList<Note>> getNotes() {
        final MutableLiveData<ArrayList<Note>> liveDataList = new MutableLiveData<>();
        // retrieve notes from online DB
//        liveDataList.setValue(SharedPrefsDao.getAllNotes());
        new Thread(new Runnable() {
            @Override
            public void run() {
                final ArrayList<Note> notes = NotesFirebaseDao.getNotes();
                liveDataList.postValue(notes);
            }
        }).start();
        return liveDataList;
    }

    public void addNote(final Note note) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String newId = NotesFirebaseDao.createNote(note);
                note.setId(newId);
                SharedPrefsDao.setNote(note);
            }
        }).start();
//        return SharedPrefsDao.getAllNotes();
    }
}
