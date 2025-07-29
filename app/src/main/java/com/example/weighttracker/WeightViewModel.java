package com.example.weighttracker;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class WeightViewModel extends AndroidViewModel {
    private final WeightRepository repo;
    public final LiveData<List<WeightEntry>> weights;

    public WeightViewModel(@NonNull Application app) {
        super(app);
        repo = new WeightRepository(app);
        weights = repo.all();
    }
    public void addWeight(double kg) { repo.add(kg); }
}
