package com.example.popflake.data.local.db.feedBack;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.popflake.domain.model.FeedBack;

@Dao
public interface FeedBackDao {
    @Insert
    void insertFeedBack(FeedBack feedBack);
}
