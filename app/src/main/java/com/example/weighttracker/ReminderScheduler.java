package com.example.weighttracker;

import android.content.Context;

import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import java.util.concurrent.TimeUnit;

public class ReminderScheduler {
    private static final String TAG = "weekly_weight_reminder";

    public static void scheduleWeeklyReminder(Context ctx) {
        PeriodicWorkRequest req = new PeriodicWorkRequest.Builder(
                WeeklyReminderWorker.class,
                7, TimeUnit.DAYS)
                .setInitialDelay(1, TimeUnit.DAYS)
                .build();

        WorkManager.getInstance(ctx).enqueueUniquePeriodicWork(
                TAG, ExistingPeriodicWorkPolicy.KEEP, req);
    }
}
