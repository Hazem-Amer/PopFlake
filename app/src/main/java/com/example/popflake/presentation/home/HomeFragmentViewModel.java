package com.example.popflake.presentation.home;

import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.popflake.data.remote.dto.ApiResponse;
import com.example.popflake.domain.model.Movie;
import com.example.popflake.domain.usecase.DeleteMovieByIDUseCase;
import com.example.popflake.domain.usecase.GetAllMoviesFromDbUseCase;
import com.example.popflake.domain.usecase.GetComingSoonMoviesUseCase;
import com.example.popflake.domain.usecase.GetOpeningMoviesUseCase;
import com.example.popflake.domain.usecase.InsertMovieToDbUseCase;
import com.example.popflake.domain.usecase.UpdateMoviesInDbUseCase;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

@HiltViewModel
public class HomeFragmentViewModel extends ViewModel {

    GetComingSoonMoviesUseCase mGetComingSoonMoviesUseCase;
    GetOpeningMoviesUseCase mGetOpeningMoviesUseCase;
    InsertMovieToDbUseCase mInsertMovieToDbUseCase;
    GetAllMoviesFromDbUseCase mGetAllMoviesFromDbUseCase;
    UpdateMoviesInDbUseCase mUpdateMoviesInDbUseCase;
    DeleteMovieByIDUseCase mDeleteMovieByIDUseCase;

    MutableLiveData<ArrayList<Movie>> mComingSoonMovies = new MutableLiveData<>();
    MutableLiveData<ArrayList<Movie>> mOpeningMovies = new MutableLiveData<>();

    @Inject
    public HomeFragmentViewModel(GetComingSoonMoviesUseCase getComingSoonMoviesUseCase,
                                 GetOpeningMoviesUseCase getOpeningMoviesUseCase,
                                 InsertMovieToDbUseCase insertMovieToDbUseCase,
                                 GetAllMoviesFromDbUseCase getAllMoviesFromDbUseCase,
                                 UpdateMoviesInDbUseCase updateMoviesInDbUseCase,
                                 DeleteMovieByIDUseCase deleteMovieByIDUseCase) {
        mGetComingSoonMoviesUseCase = getComingSoonMoviesUseCase;
        mGetOpeningMoviesUseCase = getOpeningMoviesUseCase;
        mInsertMovieToDbUseCase = insertMovieToDbUseCase;
        mGetAllMoviesFromDbUseCase = getAllMoviesFromDbUseCase;
        mUpdateMoviesInDbUseCase = updateMoviesInDbUseCase;
        mDeleteMovieByIDUseCase = deleteMovieByIDUseCase;
    }

    @SuppressLint("CheckResult")
    public void getComingSoonMoviesRemote() {
        mGetComingSoonMoviesUseCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(res -> {
                    if (res.code() == 200) {
                        Log.d("Coming soon movies", "success 200");
                        List<Movie> comingSoonMovies = res.body().getData().getUpcoming();
                        mComingSoonMovies.setValue((ArrayList<Movie>) comingSoonMovies);

                    } else {
                        Log.d("Coming soon movies", "code not 200: " + res.code());
                    }
                }, err -> {
                    Log.d("Coming soon movies", "error in response", err);
                });
    }

    @SuppressLint("CheckResult")
    public void getOpeningMoviesRemote() {
        mGetOpeningMoviesUseCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(res -> {
                    if (res.code() == 200) {
                        Log.d("Opening movies", "success 200");
                        List<Movie> openingMovies = res.body().getData().getOpening();
                        mOpeningMovies.setValue((ArrayList<Movie>) openingMovies);

                    } else {
                        Log.d("Opening movies", "code not 200: " + res.code());
                    }
                }, err -> {
                    Log.d("opening movies", "error in response", err);
                });
    }

    public void insertMovie(List<Movie> movies) {
        mInsertMovieToDbUseCase.execute(movies);
    }

    public LiveData<List<Movie>> getAllMovies() {
        return mGetAllMoviesFromDbUseCase.execute();
    }

    public void updateMovies(List<Movie> movies) {
        mUpdateMoviesInDbUseCase.execute(movies);
    }


}