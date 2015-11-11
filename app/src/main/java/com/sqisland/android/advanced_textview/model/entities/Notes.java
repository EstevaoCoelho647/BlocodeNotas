package com.sqisland.android.advanced_textview.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by c1284520 on 09/11/2015.
 */
public class Notes implements Parcelable {

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
        return id + " " + title;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id == null ? -1 : this.id);
        dest.writeString(this.title == null ? "" : this.title);
        dest.writeString(this.text == null ? "" : this.text);
    }

    protected Notes(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.title = in.readString();
        this.text = in.readString();
    }

    public static final Parcelable.Creator<Notes> CREATOR = new Parcelable.Creator<Notes>() {
        public Notes createFromParcel(Parcel source) {
            return new Notes(source);
        }

        public Notes[] newArray(int size) {
            return new Notes[size];
        }
    };
}
