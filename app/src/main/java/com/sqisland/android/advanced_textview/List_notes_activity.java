package com.sqisland.android.advanced_textview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sqisland.android.advanced_textview.model.entities.Notes;
import com.sqisland.android.advanced_textview.model.entities.persistence.NoteRepository;

import java.util.ArrayList;
import java.util.List;


public class List_notes_activity extends Activity {

    private FloatingActionButton fab;
    List<Notes> notes;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_notes);
        bindFloatButton();
        bindList();
    }

    private void bindFloatButton() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToFormHouse = new Intent(List_notes_activity.this, LinedPaperActivity.class);
                startActivity(goToFormHouse);
            }
        });
    }

    private void bindList() {
        list = (ListView) findViewById(R.id.list_itens);

        String[] strings = {"oi", "oi", "oi"};

    }

    @Override
    protected void onResume() {
        notes = NoteRepository.getAll();
        setAdapter(notes);
        super.onResume();
    }

    public void setAdapter(List<Notes> notes) {
        final ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(List_notes_activity.this,
                android.R.layout.simple_list_item_activated_1);

        for(Notes n : notes) {
            mAdapter.add(n.toString());
        }
        list.setAdapter(mAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
    }
}