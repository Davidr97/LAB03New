package com.example.david.lab03;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieDetails implements Parcelable {
    public String Title;
    public String Year;
    public String Genre;
    public String Actors;
    public String Plot;

    protected MovieDetails(Parcel in) {
        Title = in.readString();
        Year = in.readString();
        Genre = in.readString();
        Actors = in.readString();
        Plot = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Title);
        dest.writeString(Year);
        dest.writeString(Genre);
        dest.writeString(Actors);
        dest.writeString(Plot);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<MovieDetails> CREATOR = new Parcelable.Creator<MovieDetails>() {
        @Override
        public MovieDetails createFromParcel(Parcel in) {
            return new MovieDetails(in);
        }

        @Override
        public MovieDetails[] newArray(int size) {
            return new MovieDetails[size];
        }
    };
}