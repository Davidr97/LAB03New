package com.example.david.lab03;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;


public class MoviesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MovieAdapter adapter;
    private BroadcastReceiver onShowMoviesNotification = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            List<Movie> movies = intent.getExtras().getParcelableArrayList("movies");
            DBMoviesWriter writer = new DBMoviesWriter(movies);
            writer.execute(getApplicationContext());
            adapter.setMovies(movies);
            adapter.notifyDataSetChanged();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        recyclerView = findViewById(R.id.activity_movies_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MovieAdapter(getApplicationContext());
        recyclerView.setAdapter(adapter);
        DBMoviesFetchr fetchr = new DBMoviesFetchr(adapter);
        fetchr.execute(getApplicationContext());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.activity_movies, menu);
        MenuItem searchItem = menu.findItem(R.id.menu_item_search);
        final SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent i = MoviesIntentService.newIntent(getApplicationContext(), query);
                startService(i);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter(MoviesIntentService.ACTION_SHOW_NOTIFICATION);
        registerReceiver(onShowMoviesNotification,filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(onShowMoviesNotification);
    }
}
