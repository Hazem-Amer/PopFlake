package com.example.popflake.presentation.setting;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.SwitchPreferenceCompat;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.popflake.R;
import com.example.popflake.domain.model.FeedBack;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SettingsFragment extends PreferenceFragmentCompat {
    SettingsFragmentViewModel mSettingsFragmentViewModel;
    private SharedPreferences mDefaultSharedPreferences;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSettingsFragmentViewModel = new ViewModelProvider(this).get(SettingsFragmentViewModel.class);
    }

    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
        addPreferencesFromResource(R.xml.settings);
        mDefaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        handlePrefrencesClicks();
    }

    private void handlePrefrencesClicks() {
        SwitchPreferenceCompat changeMode = findPreference("darkmode");
        if (changeMode != null) {
            changeMode.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(@NonNull Preference preference, Object newValue) {
                    boolean isDarkModeEnabled = (Boolean) newValue;
                    SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(requireContext()).edit();
                    editor.putBoolean("darkmode", isDarkModeEnabled);
                    editor.apply();
                    if (isDarkModeEnabled) {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

                    } else {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    }
                    return true;
                }
            });
        }

        Preference sendFeedBack = findPreference("send_feedback");
        if (sendFeedBack != null) {
            sendFeedBack.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(@NonNull Preference preference) {
                    if (preference.getKey().equals("send_feedback")) {
                        showFeedbackDialog();
                        return true;
                    }
                    return false;
                }
            });
        }
    }
    public void showFeedbackDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Feedback");

        View view = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_feedback, null);
        builder.setView(view);

        EditText nameEditText = view.findViewById(R.id.editTextName);
        EditText feedbackEditText = view.findViewById(R.id.editTextFeedback);

        builder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = nameEditText.getText().toString();
                String feedback = feedbackEditText.getText().toString();
                handleFeedback(name, feedback);
            }
        });

        builder.setNegativeButton("Cancel", null);

        // Create and show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void handleFeedback(String name, String feedback) {
        FeedBack feedBack = new FeedBack(name, feedback);
        mSettingsFragmentViewModel.setFeedBack(feedBack);
        mSettingsFragmentViewModel.addFeedBack(feedBack);
    }

}