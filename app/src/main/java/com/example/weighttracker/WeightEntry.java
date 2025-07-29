package com.example.weighttracker;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "weights")
public class WeightEntry {
    @PrimaryKey(autoGenerate = true) public long id;
    public long timestamp;
    public double weightKg;
}
