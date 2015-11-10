package com.sqisland.android.advanced_textview.controller.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.sqisland.android.advanced_textview.R;
import com.sqisland.android.advanced_textview.controller.adapters.ListNotesAdapter;
import com.sqisland.android.advanced_textview.model.entities.Notes;
import com.sqisland.android.advanced_textview.model.entities.persistence.NoteRepository;

import java.util.List;


public class List_notes_activity extends AppCompatActivity {

    private FloatingActionButton fab;
    List<Notes> notes;
    ListView list;
    private Toolbar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_notes);
        bindToolbar();
        bindFloatButton();
        bindList();


    }
    private void bindToolbar() {
        actionBar = (Toolbar) findViewById(R.id.viewToobar1);
        setSupportActionBar(actionBar);
        getSupportActionBar().setTitle("Notas");
    }

    private void bindFloatButton() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToFormHouse = new Intent(List_notes_activity.this, FormNotesActivity.class);
                startActivity(goToFormHouse);
            }
        });
    }

    private void bindList() {
        list = (ListView) findViewById(R.id.list_itens);
        carregaLista();

    }

    @Override
    protected void onResume() {
        carregaLista();
        super.onResume();
    }

    private void carregaLista() {
        notes = NoteRepository.getAll();
        ListAdapter adapter = new ListNotesAdapter(notes, List_notes_activity.this);
        list.setAdapter(adapter);
    }

}