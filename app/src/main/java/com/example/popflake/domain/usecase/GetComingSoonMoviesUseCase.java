package com.example.popflake.domain.usecase;

import com.example.popflake.data.remote.dto.ApiResponse;
import com.example.popflake.domain.repository.MoviesRepository;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Response;

public class GetComingSoonMoviesUseCase {
    MoviesRepository mMoviesRepository;
    @Inject
    public GetComingSoonMoviesUseCase(MoviesRepository moviesRepository) {
        mMoviesRepository = moviesRepository;
    }

    public Observable<Response<ApiResponse>> execute() {
        return mMoviesRepository.getComingSoonMovies();
    }
}
