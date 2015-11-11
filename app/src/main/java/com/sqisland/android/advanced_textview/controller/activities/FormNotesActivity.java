package com.sqisland.android.advanced_textview.controller.activities;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.sqisland.android.advanced_textview.R;
import com.sqisland.android.advanced_textview.model.entities.Notes;
import com.sqisland.android.advanced_textview.model.entities.persistence.NoteRepository;

public class FormNotesActivity extends AppCompatActivity {

    Notes note;
    EditText text;
    EditText title;
    Toolbar actionBar;
    NumberPicker np;
    Button btnSave;
    private int font = 22;
    Dialog dialog = null;

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
        text.setText(note.getText() == null ? "\n\n\n\n\n\n" : note.getText());
        text.setTextSize(font);
    }

    private void bindEditTextTitle() {
        title = (EditText) findViewById(R.id.title);
        title.setText(note.getTitle() == null ? "" : note.getTitle());
        title.setTextSize(font+4);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add) {
            bindNote();
            NoteRepository.save(note);
            Toast.makeText(FormNotesActivity.this, "Nota Adicionada", Toast.LENGTH_SHORT).show();
            finish();
        }
        if (item.getItemId() == R.id.config){
            LayoutInflater view = getLayoutInflater();
            View dialoglayout = view.inflate(R.layout.config_layout, null);
            dialog = new Dialog(FormNotesActivity.this);
            dialog.setContentView(dialoglayout);
            dialog.setTitle("Configurações");

            binItens(dialog);
            verificaClick(dialog);

        }

        return super.onOptionsItemSelected(item);
    }

    private void verificaClick(Dialog dialog) {
        dialog.show();
    }

    private void binItens(Dialog dialog) {
        final Dialog ndialog = dialog;
        btnSave = (Button) dialog.findViewById(R.id.button);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                font = np.getValue();
                bindEditTextTitle();
                bindEditTextText();
                ndialog.cancel();
            }
        });
        np = (NumberPicker) dialog.findViewById(R.id.numberPicker1);
        np.setMinValue(12);
        np.setMaxValue(36);
        np.setValue(font);
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