package com.example.popflake.presentation.setting;

import androidx.lifecycle.ViewModel;

import com.example.popflake.data.local.db.feedBack.FeedBackDao;
import com.example.popflake.domain.model.FeedBack;
import com.example.popflake.domain.repository.FeedBackRepo;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class SettingsFragmentViewModel extends ViewModel {
    FeedBackRepo mFeedBackRepo;
    FeedBack mFeedBack;

    public FeedBack getFeedBack() {
        return mFeedBack;
    }

    public void setFeedBack(FeedBack feedBack) {
        mFeedBack = feedBack;
    }

    @Inject
    public SettingsFragmentViewModel(FeedBackRepo feedBackRepo) {
        mFeedBackRepo = feedBackRepo;
    }
    public void addFeedBack(FeedBack feedBack){
        mFeedBackRepo.insertFeedBack(feedBack);
    }
}