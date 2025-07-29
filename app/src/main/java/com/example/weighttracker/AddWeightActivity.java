package com.example.weighttracker;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Locale;

public class AddWeightActivity extends AppCompatActivity {
    private WeightViewModel vm;
    private UserProfileManager pm;

    @Override protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_add_weight);

        vm = new ViewModelProvider(this).get(WeightViewModel.class);
        pm = new UserProfileManager(this);

        TextInputEditText et = findViewById(R.id.et_weight);
        findViewById(R.id.btn_save_weight).setOnClickListener(v -> {
            double kg = Double.parseDouble(et.getText().toString());
            vm.addWeight(kg);

            double bmi = calcBmi(kg, pm.getHeightCm());
            String cat = bmiCategory(bmi);
            Toast.makeText(this, String.format(Locale.getDefault(),
                    "BMI %.1f (%s)", bmi, cat), Toast.LENGTH_LONG).show();
            finish();
        });
    }

    private double calcBmi(double kg, double cm) {
        return kg / Math.pow(cm / 100.0, 2);
    }
    public static String bmiCategory(double bmi) {
        if (bmi < 18.5)      return "Untergewicht";
        else if (bmi < 25)   return "Normal";
        else if (bmi < 30)   return "Übergewicht";
        else                 return "Adipositas";
    }
}
