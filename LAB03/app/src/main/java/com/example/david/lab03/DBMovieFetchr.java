package com.example.david.lab03;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

public class DBMovieFetchr extends AsyncTask<Context,Void,Movie>{

    private String title;
    private ImageView moviePoster;
    private TextView movieTitle;
    private TextView movieYear;

    public DBMovieFetchr(String title,ImageView i,TextView t1,TextView t2){
        this.title = title;
        moviePoster = i;
        movieTitle = t1;
        movieYear = t2;
    }


    @Override
    protected void onPostExecute(Movie movie) {
        Picasso.with(moviePoster.getContext()).load(movie.getPoster()).into(moviePoster);
        movieTitle.setText(movie.getTitle());
        movieYear.setText(movie.getYear());
    }

    @Override
    protected Movie doInBackground(Context... contexts) {
        return MovieDatabase.get(contexts[0]).movieDao().findByTitle(title);
    }
}