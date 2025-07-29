package com.example.weighttracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private WeightViewModel vm;
    private UserProfileManager pm;
    private TextView tvBmi;
    private WeightAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pm = new UserProfileManager(this);
        if (!pm.isComplete()) {               // Profil noch nicht vorhanden
            openProfileAndFinish();
            return;                            // Main‑UI NICHT laden
        }

        setContentView(R.layout.activity_main);



        //Toolbar + Profil icon
        MaterialToolbar bar = findViewById(R.id.topAppBar);
        setSupportActionBar(bar);
        bar.setTitle(R.string.app_name);

        ImageButton btnProfile = findViewById(R.id.btnProfile);
        btnProfile.setOnClickListener(
                v -> startActivity(new Intent(this, ProfileActivity.class)));

        // RecyclerView
        tvBmi = findViewById(R.id.tvBmi);
        RecyclerView rv = findViewById(R.id.rvWeights);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new WeightAdapter();
        rv.setAdapter(adapter);

        //ViewModel
        vm = new ViewModelProvider(this).get(WeightViewModel.class);
        vm.weights.observe(this, list -> {
            adapter.submitList(list);
            if (!list.isEmpty()) {
                showBmi(list.get(0).weightKg);
            }
        });

        //neues Gewicht
        findViewById(R.id.fabAdd).setOnClickListener(
                v -> startActivity(new Intent(this, AddWeightActivity.class)));

        //Errinerung
        ReminderScheduler.scheduleWeeklyReminder(this);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_edit_profile) {
            startActivity(new Intent(this, ProfileActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (!adapter.getCurrentList().isEmpty()) {
            showBmi(adapter.getCurrentList().get(0).weightKg);
        }
    }


    private void showBmi(double kg) {
        double bmi = kg / Math.pow(pm.getHeightCm() / 100.0, 2);
        String cat = AddWeightActivity.bmiCategory(bmi);
        tvBmi.setText(String.format(Locale.getDefault(),
                "BMI %.1f – %s", bmi, cat));
    }

    private void openProfileAndFinish() {
        Intent i = new Intent(this, ProfileActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
        finish();
    }
}
