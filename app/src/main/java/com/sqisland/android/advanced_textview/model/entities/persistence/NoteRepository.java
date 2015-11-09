package com.sqisland.android.advanced_textview.model.entities.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.sqisland.android.advanced_textview.model.entities.Notes;

import java.util.List;

public class NoteRepository {

    public static void save(Notes house) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = NoteContract.getContentValues(house);

        if (house.getId() == null) {
            db.insert(NoteContract.TABLE, null, values);
        } else {
            String where = NoteContract.ID + " = ?";
            String[] params = {house.getId().toString()};
            db.update(NoteContract.TABLE, values, where, params);
        }
        db.close();
        databaseHelper.close();
    }

    public static List<Notes> getAll() {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query(NoteContract.TABLE, NoteContract.COLUNS, null, null, null, null, NoteContract.ID);
        List<Notes> values = NoteContract.getNotes(cursor);

        db.close();
        databaseHelper.close();
        return values;
    }

    public static void delete(long id) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String where = NoteContract.ID + " = ? ";
        String[] params = {String.valueOf(id)};
        db.delete(NoteContract.TABLE, where, params);

        db.close();
        databaseHelper.close();
    }

//    public static List<Notes> findByFilterItens(int nBanheiros, int nQuartos, int ehVenda, int ehAluguel, Double preco) {
//        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
//        SQLiteDatabase db = databaseHelper.getReadableDatabase();
//
//        String where = NoteContract.NBANHEIROS + " >= ? AND " + NoteContract.NQUARTOS + " >= ? AND " +
//                NoteContract.EHVENDA + " = ? AND " + NoteContract.EHALUGUEL + " = ? AND " + NoteContract.PRECO + " <= ? ;";
//
//        String[] params = {String.valueOf(nBanheiros), String.valueOf(nQuartos), String.valueOf(ehVenda), String.valueOf(ehAluguel), String.valueOf(preco)};
//
//        Cursor cursor = db.query(NoteContract.TABLE, NoteContract.COLUNS, where, params, null, null, null);
//        List<House> values = NoteContract.getHouses(cursor);
//
//        db.close();
//        databaseHelper.close();
//        return values;
//    }
}
