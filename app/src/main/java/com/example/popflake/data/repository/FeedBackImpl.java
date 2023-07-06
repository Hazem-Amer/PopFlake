package com.example.popflake.data.repository;

import com.example.popflake.data.local.db.feedBack.FeedBackDao;
import com.example.popflake.domain.model.FeedBack;
import com.example.popflake.domain.repository.FeedBackRepo;

import javax.inject.Inject;

public class FeedBackImpl implements FeedBackRepo {
    FeedBackDao mFeedBackDao;
    @Inject
    public FeedBackImpl(FeedBackDao feedBackDao) {
        mFeedBackDao = feedBackDao;
    }

    @Override
    public void insertFeedBack(FeedBack feedBack) {
        mFeedBackDao.insertFeedBack(feedBack);
    }
}
