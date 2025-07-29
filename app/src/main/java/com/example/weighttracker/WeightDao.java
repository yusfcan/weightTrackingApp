package com.example.weighttracker;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WeightDao {
    @Insert void insert(WeightEntry e);
    @Query("SELECT * FROM weights ORDER BY timestamp DESC")
    LiveData<List<WeightEntry>> getAll();
    @Query("SELECT * FROM weights ORDER BY timestamp DESC LIMIT 1")
    WeightEntry getLatestSync();
}
