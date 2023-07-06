package com.example.popflake.domain.usecase;

import com.example.popflake.domain.model.Movie;
import com.example.popflake.domain.repository.MoviesRepository;

import javax.inject.Inject;

public class DeleteMovieByIDUseCase {
    MoviesRepository mMoviesRepository;
    @Inject
    public DeleteMovieByIDUseCase(MoviesRepository moviesRepository) {
        mMoviesRepository = moviesRepository;
    }

    public Movie execute(int movieId) {
        return mMoviesRepository.getMovieById(movieId);
    }
}
