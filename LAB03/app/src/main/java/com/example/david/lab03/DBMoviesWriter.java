package com.example.david.lab03;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

public class DBMoviesWriter extends AsyncTask<Context,Void,Void> {

    private List<Movie> movies;

    public DBMoviesWriter(List<Movie> movies){
        this.movies = movies;
    }
    @Override
    protected Void doInBackground(Context... contexts) {
        List<Movie> m = MovieDatabase.get(contexts[0]).movieDao().getMovies();
        if(m!=null){
            if(!m.containsAll(movies)){
                MovieDatabase.get(contexts[0]).movieDao().insertMovies(movies);
            }
        }
        else{
            MovieDatabase.get(contexts[0]).movieDao().insertMovies(movies);
        }
        return null;
    }
}