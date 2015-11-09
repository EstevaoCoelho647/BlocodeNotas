package com.sqisland.android.advanced_textview.model.entities.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import com.sqisland.android.advanced_textview.model.entities.Notes;

import java.util.ArrayList;
import java.util.List;

public class NoteContract {

    public static String TABLE = "house";
    public static String ID = "id";
    public static String TITLE = "title";
    public static String TEXT = "text";

    public static final String[] COLUNS = {ID, TITLE, TEXT};

    public static String getCreateTableScript() {
        final StringBuilder create = new StringBuilder();

        create.append(" CREATE TABLE " + TABLE);
        create.append(" ( ");
        create.append(ID + " INTEGER PRIMARY KEY, ");
        create.append(TITLE + " TEXT, ");
        create.append(TEXT + " TEXT ");
        create.append(" ); ");
        return create.toString();
    }

    public static ContentValues getContentValues(Notes note) {
        ContentValues values = new ContentValues();
        values.put(NoteContract.ID, note.getId());
        values.put(NoteContract.TITLE, note.getTitle());
        values.put(NoteContract.TEXT, note.getText());
        return values;
    }

    static Notes getNote(Cursor cursor) {
        Notes notes = new Notes();
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            notes.setId(cursor.getLong(cursor.getColumnIndex(NoteContract.ID)));
            notes.setTitle(cursor.getString(cursor.getColumnIndex(NoteContract.TITLE)));
            notes.setText(cursor.getString(cursor.getColumnIndex(NoteContract.TEXT)));
            return notes;
        }
        return null;
    }

    public static List getNotes(Cursor cursor) {
        ArrayList<Notes> notes = new ArrayList();
        while (cursor.moveToNext()) {
            notes.add(getNote(cursor));
        }
        return notes;
    }
}
