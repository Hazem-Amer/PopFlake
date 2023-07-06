package com.example.popflake.presentation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.popflake.R;
import com.example.popflake.presentation.home.HomeFragment;
import com.example.popflake.presentation.search.SearchFragment;
import com.example.popflake.presentation.setting.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    private static final String SELECTED_ITEM = "selected_item";
    private int selectedItem;
    private BottomNavigationView mBottomNavigationView;

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SELECTED_ITEM, mBottomNavigationView.getSelectedItemId());

    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        selectedItem = savedInstanceState.getInt(SELECTED_ITEM, 0);
        mBottomNavigationView.setSelectedItemId(selectedItem);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);
        initViews();
        initializeFragments();
        setUpPrefrences();
    }

    private void setUpPrefrences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isDarkModeEnabled = sharedPreferences.getBoolean("darkmode", false);
        if (isDarkModeEnabled) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    private void initializeFragments() {
        getSupportFragmentManager().beginTransaction().replace(R.id.homepage_frameLayout, new HomeFragment()).commit();
        mBottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                handleBottomNavBarSelection(item);
                return true;
            }
        });
    }
    private void handleBottomNavBarSelection(MenuItem item) {
        if (item.getItemId() == R.id.home) {
            getSupportFragmentManager().beginTransaction().replace(R.id.homepage_frameLayout, new HomeFragment())
                    .commit();
        } else if (item.getItemId() == R.id.search) {
            getSupportFragmentManager().beginTransaction().replace(R.id.homepage_frameLayout,new SearchFragment())
                    .commit();
        }
        else {
            getSupportFragmentManager().beginTransaction().replace(R.id.homepage_frameLayout, new SettingsFragment())
                    .commit();
        }
        item.setChecked(true);
    }

    private void initViews() {
        mBottomNavigationView = findViewById(R.id.main_bottomNavBar);

    }



}