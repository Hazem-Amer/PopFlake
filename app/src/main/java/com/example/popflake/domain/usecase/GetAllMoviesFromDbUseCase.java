package com.example.popflake.domain.usecase;

import androidx.lifecycle.LiveData;

import com.example.popflake.domain.model.Movie;
import com.example.popflake.domain.repository.MoviesRepository;

import java.util.List;

import javax.inject.Inject;

public class GetAllMoviesFromDbUseCase {

    MoviesRepository mMoviesRepository;
    @Inject
    public GetAllMoviesFromDbUseCase(MoviesRepository moviesRepository) {
        mMoviesRepository = moviesRepository;
    }

    public LiveData<List<Movie>> execute() {
        return mMoviesRepository.getAllMovies();
    }


}
