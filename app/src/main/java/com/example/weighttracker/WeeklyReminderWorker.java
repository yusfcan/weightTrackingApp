package com.example.weighttracker;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class WeeklyReminderWorker extends Worker {

    public WeeklyReminderWorker(
            @NonNull Context context,
            @NonNull WorkerParameters params) {
        super(context, params);
    }

    @NonNull
    @Override public Result doWork() {          // ⚠  Result = androidx.work.ListenableWorker.Result
        showNotification();
        return Result.success();
    }

    /* ---------- Notification‑Logik ------------------------------------- */
    private void showNotification() {
        Context c = getApplicationContext();
        String channelId = "reminder";

        NotificationManager nm = c.getSystemService(NotificationManager.class);
        if (Build.VERSION.SDK_INT >= 26 &&
                nm.getNotificationChannel(channelId) == null) {
            nm.createNotificationChannel(
                    new NotificationChannel(
                            channelId,
                            "Wiege‑Erinnerungen",
                            NotificationManager.IMPORTANCE_DEFAULT));
        }

        Notification n = new NotificationCompat.Builder(c, channelId)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Zeit zum Wiegen")
                .setContentText("Trag dein aktuelles Gewicht in WeightTracker ein!")
                .setAutoCancel(true)
                .build();

        nm.notify(1, n);
    }
}
