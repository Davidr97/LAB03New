package com.example.david.lab03;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

public class DBMoviesFetchr extends AsyncTask<Context,Void,List<Movie>> {

    private MovieAdapter adapter;

    public DBMoviesFetchr(MovieAdapter adapter){
        this.adapter = adapter;
    }

    @Override
    protected List<Movie> doInBackground(Context... contexts) {
        return MovieDatabase.get(contexts[0]).movieDao().getMovies();
    }

    @Override
    protected void onPostExecute(List<Movie> movies) {
        if(movies != null) {
            adapter.setMovies(movies);
            adapter.notifyDataSetChanged();
        }
    }
}