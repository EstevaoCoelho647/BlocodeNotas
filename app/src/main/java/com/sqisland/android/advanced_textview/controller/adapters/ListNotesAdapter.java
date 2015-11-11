package com.sqisland.android.advanced_textview.controller.adapters;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sqisland.android.advanced_textview.R;
import com.sqisland.android.advanced_textview.controller.activities.List_notes_activity;
import com.sqisland.android.advanced_textview.model.entities.Notes;
import com.sqisland.android.advanced_textview.model.entities.persistence.NoteRepository;

import java.util.List;

/**
 * Created by c1284520 on 10/11/2015.
 */
public abstract class ListNotesAdapter extends BaseAdapter {

    private List<Notes> notes;
    private Activity context;

    public ListNotesAdapter(List<Notes> notes, Activity context) {
        this.notes = notes;
        this.context = context;
    }

    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public Object getItem(int i) {
        return notes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return notes.get(i).getId();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final Notes note = notes.get(i);
        View v = context.getLayoutInflater().inflate(R.layout.item_notes, viewGroup, false);

        TextView edTxtId = (TextView) v.findViewById(R.id.edtxtId);
        TextView edTxtTitulo = (TextView) v.findViewById(R.id.edTxtTitulo);
        ImageView imageDel = (ImageView) v.findViewById(R.id.imageDel);
        imageDel.setImageDrawable(v.getResources().getDrawable(R.drawable.ic_action_x_mark_512));
        imageDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.image_click));
                delete(note);
            }
        });
        ImageView imageEdit = (ImageView) v.findViewById(R.id.imageEdit);
        imageEdit.setImageDrawable(v.getResources().getDrawable(R.drawable.ic_action_edit));
        imageEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.image_click));
                edit(note);
            }
        });

        edTxtId.setText("  " + note.getId().toString() + ".");
        edTxtId.setTextSize(18);
        edTxtTitulo.setText(note.getTitle());
        edTxtTitulo.setTextSize(22);

        if (i % 2 == 0)
            v.setBackgroundResource(R.color.in);
        else
            v.setBackgroundResource(R.color.out);
        return v;
    }

    public abstract void delete(Notes note);
    public abstract void edit(Notes note);
}
