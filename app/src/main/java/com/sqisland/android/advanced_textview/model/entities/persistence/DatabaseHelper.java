package com.sqisland.android.advanced_textview.model.entities.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.Override;
import java.lang.String;

import com.sqisland.android.advanced_textview.ApplicationUtil;

/**
 * Created by c1284520 on 16/10/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "notesDb";
    private static final int DATABASE_VERSION = 1;

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DatabaseHelper getInstance() {
        return new DatabaseHelper(ApplicationUtil.getContext());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(NoteContract.getCreateTableScript());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}