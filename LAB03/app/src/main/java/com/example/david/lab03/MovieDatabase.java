package com.example.david.lab03;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Movie.class}, version = 1)
public abstract class MovieDatabase extends RoomDatabase {

        private static MovieDatabase sMovieDatabase;
        public static MovieDatabase get(Context context){
            if(sMovieDatabase == null){
                sMovieDatabase = Room.databaseBuilder(context,
                        MovieDatabase.class, "database-name").build();
            }
            return sMovieDatabase;
        }
        public abstract MovieDao movieDao();
}