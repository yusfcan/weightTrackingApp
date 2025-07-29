package com.example.weighttracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;


import android.widget.EditText;

public class ProfileActivity extends AppCompatActivity {

    private UserProfileManager pm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        pm = new UserProfileManager(this);

        findViewById(R.id.btn_save).setOnClickListener(v -> {
            String first  = text(R.id.et_first);
            String last   = text(R.id.et_last);
            int    age    = parseIntSafe (text(R.id.et_age));
            float  height = parseFloatSafe(text(R.id.et_height));

            RadioGroup rg = findViewById(R.id.rg_gender);
            String gender = (rg.getCheckedRadioButtonId()==R.id.rb_male) ? "m" : "f";

            pm.save(first, last, age, height, gender);

            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
    }

    //HilfsMethoden
    private String text(int id){ return ((EditText) findViewById(id))
            .getText().toString().trim(); }
    private int  parseIntSafe (String s){
        try { return Integer.parseInt(s); } catch(Exception e){ return 0; }}
    private float parseFloatSafe(String s){
        try { return Float.parseFloat(s.replace(',','.')); }
        catch(Exception e){ return 0f;}}
}
