package com.sqisland.android.advanced_textview.controller.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.sqisland.android.advanced_textview.R;
import com.sqisland.android.advanced_textview.model.entities.Notes;
import com.sqisland.android.advanced_textview.model.entities.persistence.NoteRepository;

public class FormNotesActivity extends AppCompatActivity {

    Notes note;
    EditText text;
    EditText title;
    Toolbar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lined_paper);
        bindToolbar();
        initNote();
        bindEditTextTitle();
        bindEditTextText();
    }

    private void bindToolbar() {
        actionBar = (Toolbar) findViewById(R.id.viewToobar);
        setSupportActionBar(actionBar);
        getSupportActionBar().setTitle("Nova Nota");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_form_note, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void bindEditTextText() {
        text = (EditText) findViewById(R.id.text);
    }

    private void bindEditTextTitle() {
        title = (EditText) findViewById(R.id.title);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add) {
            bindNote();
            NoteRepository.save(note);
            Toast.makeText(FormNotesActivity.this, "Nota Adicionada", Toast.LENGTH_SHORT).show();

            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initNote() {
        Bundle values = getIntent().getExtras();
        if (values != null) {
            this.note = values.getParcelable("SelectedNote");
        } else
            this.note = new Notes();
    }

    private void bindNote() {
        note.setText(text.getText().toString());
        note.setTitle(title.getText().toString());
    }
}