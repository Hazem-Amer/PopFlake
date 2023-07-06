package com.example.popflake.data.local.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.popflake.data.local.db.feedBack.FeedBackDao;
import com.example.popflake.domain.model.FeedBack;
import com.example.popflake.domain.model.Movie;

@Database(entities = {Movie.class, FeedBack.class},version = 1)
@TypeConverters(MovieDbConverters.class)
public abstract class MovieDataBase extends RoomDatabase {
    public static final String DATABASE_NAME = "POP_FLAKE";
    public abstract MoviesDao moviesDao();
    public abstract FeedBackDao feedBackDao();

}
