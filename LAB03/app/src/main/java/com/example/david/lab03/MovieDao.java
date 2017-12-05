package com.example.david.lab03;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertMovies(List<Movie> movies);

    @Query("SELECT * FROM Movie")
    public List<Movie> getMovies();

    @Query("SELECT * FROM Movie WHERE Title LIKE :title")
    public Movie findByTitle(String title);

    @Query("DELETE FROM Movie")
    public void nukeTable();
}