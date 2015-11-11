package com.sqisland.android.advanced_textview.controller.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

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
    private Notes itemSelected;

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
        ListAdapter adapter = new ListNotesAdapter(notes, List_notes_activity.this) {
            @Override
            public void delete(Notes note) {
                itemSelected = note;
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(List_notes_activity.this);
                dialogBuilder.setMessage("Deseja realmente deletar este item?");
                dialogBuilder.setTitle("Excluir");
                dialogBuilder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                dialogBuilder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        NoteRepository.delete(itemSelected.getId());
                        Toast.makeText(List_notes_activity.this, "Item excluido com sucesso!", Toast.LENGTH_SHORT).show();
                        bindList();
                    }
                });
                dialogBuilder.show();
            }

            @Override
            public void edit(Notes note) {
                itemSelected = note;
                Intent goToEditForm = new Intent(List_notes_activity.this, FormNotesActivity.class);
                goToEditForm.putExtra("SelectedNote", itemSelected);
                startActivity(goToEditForm);
            }
        };
        list.setAdapter(adapter);
        list.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int event = motionEvent.getAction();
                switch (event) {
                    case MotionEvent.ACTION_DOWN:
                        fab.startAnimation(AnimationUtils.loadAnimation(List_notes_activity.this, R.anim.float_button_down));
                        fab.setVisibility(View.INVISIBLE);
                        break;

                    case MotionEvent.ACTION_UP:
                        fab.startAnimation(AnimationUtils.loadAnimation(List_notes_activity.this, R.anim.float_button_up));
                        fab.setVisibility(View.VISIBLE);
                        break;
                }
                return false;
            }
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                            view.startAnimation(AnimationUtils.loadAnimation(List_notes_activity.this, R.anim.list_click));
                                        }
                                    }
        );
    }

}