package com.example.popflake.presentation.search;

import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.popflake.R;
import com.example.popflake.UTILS.NavigationUtils;
import com.example.popflake.domain.model.Movie;
import com.example.popflake.presentation.AppAdapter;
import com.example.popflake.presentation.MoviesRecyclerViewListener;
import com.google.android.material.progressindicator.LinearProgressIndicator;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SearchFragment extends Fragment implements MoviesRecyclerViewListener {
    private SearchView mSearchView;
    private RecyclerView searchMoviesRecyclerView;
    private AppAdapter mAppAdapter;

    private SearchViewModel mSearchViewModel;
    LinearProgressIndicator mLinearProgressIndicator;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSearchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeViews(view);
        handleSearchQuery();
        listenToSearchedMoviesUpdates();
    }

    private void listenToSearchedMoviesUpdates() {
        mSearchViewModel.getSearchedMovies().observe(getViewLifecycleOwner(), new Observer<ArrayList<Movie>>() {
            @Override
            public void onChanged(ArrayList<Movie> movies) {
                mLinearProgressIndicator.setVisibility(View.GONE);
                setQueriedMoviesRecyclerViewAdapter(movies);
            }
        });
    }

    private void handleSearchQuery() {
        mSearchView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    mAppAdapter.clearAdapter();
                }
            }
        });
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!query.equals("")) {
                    mLinearProgressIndicator.setVisibility(View.VISIBLE);
                    performSearch(query);

                }
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                    performSearch(newText);
                return true;
            }
        });
    }

    private void performSearch(String query) {
            mSearchViewModel.searchMoviesByName(query);

    }
    private void setQueriedMoviesRecyclerViewAdapter(ArrayList<Movie> movies) {
        if (movies != null) {
            mAppAdapter = new AppAdapter(movies, R.layout.opening_movie_list_item, this);
            searchMoviesRecyclerView.setLayoutManager(new GridLayoutManager(requireContext(),2));
            searchMoviesRecyclerView.setAdapter(mAppAdapter);
            mAppAdapter.notifyDataSetChanged();
        }
    }

    private void initializeViews(View view) {
        mSearchView =view.findViewById(R.id.searchView);
        searchMoviesRecyclerView = view.findViewById(R.id.search_RV);
        mLinearProgressIndicator = view.findViewById(R.id.search_progressBar);
    }


    @Override
    public void onMoviePosterClick(int position, int layoutResource) {
        if(layoutResource == R.layout.opening_movie_list_item)
            NavigationUtils.openMovieDetailsFragment(mAppAdapter, position, requireActivity());
    }
}