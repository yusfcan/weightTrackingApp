package com.example.weighttracker;

import android.content.Context;
import android.content.SharedPreferences;

public class UserProfileManager {

    private static final String PREF      = "profile";
    private static final String KEY_FIRST = "first";
    private static final String KEY_LAST  = "last";
    private static final String KEY_AGE   = "age";
    private static final String KEY_HEIGHT= "height";   //cm
    private static final String KEY_GENDER= "gender";

    private final SharedPreferences sp;

    public UserProfileManager(Context c) {
        sp = c.getSharedPreferences(PREF, Context.MODE_PRIVATE);
    }

// Speichern
    public void save(String first, String last, int age,
                     float heightCm, String gender) {
        sp.edit()
                .putString(KEY_FIRST, first)
                .putString(KEY_LAST,  last)
                .putInt   (KEY_AGE,   age)
                .putFloat (KEY_HEIGHT,heightCm)
                .putString(KEY_GENDER,gender)
                .apply();
    }


    public boolean isComplete() {
        return sp.contains(KEY_HEIGHT)
                && sp.getFloat(KEY_HEIGHT, 0f) > 0f          // sinnvolle Größe?
                && !sp.getString(KEY_FIRST, "").isEmpty();    // Vorname gesetzt?
    }
    public float  getHeightCm() { return sp.getFloat(KEY_HEIGHT, 0f); }
    public String getGender()  { return sp.getString(KEY_GENDER, ""); }
}
