package com.example.popflake.di;

import com.example.popflake.data.local.db.MoviesDao;
import com.example.popflake.data.local.db.feedBack.FeedBackDao;
import com.example.popflake.data.remote.MoviesApi;
import com.example.popflake.data.repository.FeedBackImpl;
import com.example.popflake.data.repository.MoviesRepositoryImpl;
import com.example.popflake.domain.repository.FeedBackRepo;
import com.example.popflake.domain.repository.MoviesRepository;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class RepoModule {
    @Provides
    MoviesRepository provideMoviesRepo(MoviesApi moviesApi, MoviesDao moviesDao){
        return new MoviesRepositoryImpl(moviesApi,moviesDao);
    }
    @Provides
    FeedBackRepo provideFeedBackRepo(FeedBackDao feedBackDao){
        return new FeedBackImpl(feedBackDao);
    }
}
