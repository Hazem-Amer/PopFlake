package com.example.popflake.presentation.search;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.popflake.data.remote.dto.ApiResponse;
import com.example.popflake.domain.model.Movie;
import com.example.popflake.domain.usecase.SearchMoviesUseCase;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

@HiltViewModel
public class SearchViewModel extends ViewModel {
    private SearchMoviesUseCase mSearchMoviesUseCase;
    private MutableLiveData<ArrayList<Movie>> searchedMovies = new MutableLiveData<>();
    @Inject
    public SearchViewModel(SearchMoviesUseCase searchMoviesUseCase) {
        mSearchMoviesUseCase = searchMoviesUseCase;
    }
    public void searchMoviesByName(String movieName){
        mSearchMoviesUseCase.execute(movieName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(res -> {
                    if (res.code() == 200) {
                        List<Movie> movies = res.body().getData().getSearch().getMovies();
                        searchedMovies.setValue((ArrayList<Movie>) movies);
                    } else {
                        Log.d("Coming soon movies", "code not 200: " + res.code());
                    }
                }, err -> {
                    Log.d("Coming soon movies", "error in response", err);
                });
    }

    public SearchMoviesUseCase getSearchMoviesUseCase() {
        return mSearchMoviesUseCase;
    }

    public void setSearchMoviesUseCase(SearchMoviesUseCase searchMoviesUseCase) {
        mSearchMoviesUseCase = searchMoviesUseCase;
    }

    public MutableLiveData<ArrayList<Movie>> getSearchedMovies() {
        return searchedMovies;
    }

    public void setSearchedMovies(MutableLiveData<ArrayList<Movie>> searchedMovies) {
        this.searchedMovies = searchedMovies;
    }
}