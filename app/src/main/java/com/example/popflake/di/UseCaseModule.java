package com.example.popflake.di;

import com.example.popflake.domain.repository.MoviesRepository;
import com.example.popflake.domain.usecase.DeleteMovieByIDUseCase;
import com.example.popflake.domain.usecase.GetAllMoviesFromDbUseCase;
import com.example.popflake.domain.usecase.GetComingSoonMoviesUseCase;
import com.example.popflake.domain.usecase.GetMovieDetailsUseCase;
import com.example.popflake.domain.usecase.GetOpeningMoviesUseCase;
import com.example.popflake.domain.usecase.InsertMovieToDbUseCase;
import com.example.popflake.domain.usecase.SearchMoviesUseCase;
import com.example.popflake.domain.usecase.UpdateMoviesInDbUseCase;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class UseCaseModule {
    @Provides
    GetComingSoonMoviesUseCase provideGetComingSoonUseCase(MoviesRepository moviesRepository){
        return new GetComingSoonMoviesUseCase(moviesRepository);
    }
    @Provides
    GetOpeningMoviesUseCase provideGetOpeningMoviesUseCase(MoviesRepository moviesRepository){
        return new GetOpeningMoviesUseCase(moviesRepository);
    }
    @Provides
    SearchMoviesUseCase provideSearchMoviesUseCase(MoviesRepository moviesRepository){
        return new SearchMoviesUseCase(moviesRepository);
    }
    @Provides
    GetMovieDetailsUseCase provideGetMovieDetailsUseCase(MoviesRepository moviesRepository){
        return new GetMovieDetailsUseCase(moviesRepository);
    }

    @Provides
    DeleteMovieByIDUseCase provideDeleteMovieByIDUseCase(MoviesRepository moviesRepository) {
        return new DeleteMovieByIDUseCase(moviesRepository);
    }

    @Provides
    GetAllMoviesFromDbUseCase provideGetAllMoviesFromDbUseCase(MoviesRepository moviesRepository) {
        return new GetAllMoviesFromDbUseCase(moviesRepository);
    }

    @Provides
    UpdateMoviesInDbUseCase provideUpdateMoviesInDbUseCase(MoviesRepository moviesRepository) {
        return new UpdateMoviesInDbUseCase(moviesRepository);
    }

    @Provides
    InsertMovieToDbUseCase provideInsertMovieToDbUseCase(MoviesRepository moviesRepository) {
        return new InsertMovieToDbUseCase(moviesRepository);
    }
}
