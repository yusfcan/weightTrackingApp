package com.example.weighttracker;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class WeightRepository {
    private final WeightDao dao;
    private final Executor io = Executors.newSingleThreadExecutor();

    public WeightRepository(Context ctx) {
        dao = WeightDatabase.get(ctx).dao();
    }
    public LiveData<List<WeightEntry>> all() { return dao.getAll(); }

    public void add(double kg) {
        io.execute(() -> {
            WeightEntry e = new WeightEntry();
            e.timestamp = System.currentTimeMillis();
            e.weightKg  = kg;
            dao.insert(e);
        });
    }

    public WeightEntry latestSync() { return dao.getLatestSync(); }
}
