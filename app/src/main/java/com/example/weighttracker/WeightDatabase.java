package com.example.weighttracker;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {WeightEntry.class}, version = 1, exportSchema = false)
public abstract class WeightDatabase extends RoomDatabase {
    public abstract WeightDao dao();
    private static volatile WeightDatabase INSTANCE;

    public static WeightDatabase get(Context ctx) {
        if (INSTANCE == null) {
            synchronized (WeightDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(ctx.getApplicationContext(),
                            WeightDatabase.class, "weights.db").build();
                }
            }
        }
        return INSTANCE;
    }
}
