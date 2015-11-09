package com.sqisland.android.advanced_textview.model.entities;

/**
 * Created by c1284520 on 09/11/2015.
 */
public class Notes {

    Long id;
    String title;
    String text;

    public String getTitle() {
        return title;
    }

    public Notes() {
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Notes(Long id, String title, String text) {
        this.id = id;
        this.title = title;
        this.text = text;
    }
}
