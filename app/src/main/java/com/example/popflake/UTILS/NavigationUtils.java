package com.example.popflake.UTILS;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.popflake.R;
import com.example.popflake.domain.model.Movie;
import com.example.popflake.presentation.AppAdapter;
import com.example.popflake.presentation.movieDetails.MovieDetailsFragment;

import java.util.List;

public class NavigationUtils {
    public static void openMovieDetailsFragment(AppAdapter<Movie> appAdapter, int position, FragmentActivity fragmentActivity) {
        MovieDetailsFragment movieDetailsFragment = new MovieDetailsFragment();
        List<Movie> movies = appAdapter.getDataList();
        if (movies != null) {
            Bundle bundle = new Bundle();
            String emsVersionId = movies.get(position).getEmsVersionId();
            bundle.putString("id", emsVersionId);
            movieDetailsFragment.setArguments(bundle);
        }
        FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.homepage_frameLayout, movieDetailsFragment, "MovieDetailsFragment");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
