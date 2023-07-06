package com.example.popflake.di;

import android.app.Application;

import androidx.room.Room;

import com.example.popflake.data.local.db.MovieDataBase;
import com.example.popflake.data.local.db.MoviesDao;
import com.example.popflake.data.local.db.feedBack.FeedBackDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class DatabaseModule {
    @Provides
    @Singleton
    public static MovieDataBase provideMovieDb(Application application){
        return Room.databaseBuilder(application,MovieDataBase.class,MovieDataBase.DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }
    @Provides
    @Singleton
    public static MoviesDao provideMoviesDao(MovieDataBase movieDataBase){
        return movieDataBase.moviesDao();
    }
    @Provides
    @Singleton
    public static FeedBackDao provideFeedBackDao(MovieDataBase movieDataBase){
        return movieDataBase.feedBackDao();
    }
}
