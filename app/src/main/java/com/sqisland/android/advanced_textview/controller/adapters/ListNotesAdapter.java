package com.sqisland.android.advanced_textview.controller.adapters;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
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

import com.sqisland.android.advanced_textview.R;
import com.sqisland.android.advanced_textview.model.entities.Notes;

import java.util.List;

/**
 * Created by c1284520 on 10/11/2015.
 */
public class ListNotesAdapter extends BaseAdapter {

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
        Notes note = notes.get(i);
        View v = context.getLayoutInflater().inflate(R.layout.item_notes, viewGroup, false);

        TextView edTxtId = (TextView) v.findViewById(R.id.edtxtId);
        TextView edTxtTitulo = (TextView) v.findViewById(R.id.edTxtTitulo);
        ImageView imageDel = (ImageView) v.findViewById(R.id.imageDel);



        imageDel.setImageDrawable(v.getResources().getDrawable(R.drawable.ic_action_x_mark_512));
        edTxtId.setText(note.getId().toString());
        edTxtId.setTextSize(22);
        edTxtTitulo.setText(note.getText());
        edTxtTitulo.setTextSize(22);

        if (i%2==0)
           v.setBackgroundResource(R.color.teste);
        return v;

    }
}
