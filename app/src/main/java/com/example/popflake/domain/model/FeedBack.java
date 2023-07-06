package com.example.popflake.domain.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "FeedBack_Table")
public class FeedBack {
    @PrimaryKey(autoGenerate = true)
    public int id;
    String name;
    String feedBack;

    public FeedBack(String name, String feedBack) {
        this.name = name;
        this.feedBack = feedBack;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFeedBack() {
        return feedBack;
    }

    public void setFeedBack(String feedBack) {
        this.feedBack = feedBack;
    }
}
