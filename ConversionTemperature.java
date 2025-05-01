package com.example.l3si.conversion;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ConversionTemperature extends AppCompatActivity {

    EditText inputTemperature;
    RadioGroup conversionGroup;
    RadioButton toCelsius, toFahrenheit;
    TextView resultTextView;
    Button convertirButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion_temperature);


        inputTemperature = findViewById(R.id.inputTemperature);
        conversionGroup = findViewById(R.id.conversionGroup);
        toCelsius = findViewById(R.id.radioCelsius);
        toFahrenheit = findViewById(R.id.radioFahrenheit);
        resultTextView = findViewById(R.id.resultTextView);
        convertirButton = findViewById(R.id.btnConvertir);

        // زر التحويل
        convertirButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertirTemperature();
            }
        });


        registerForContextMenu(conversionGroup);
    }

    private void convertirTemperature() {
        if (inputTemperature.getText().toString().isEmpty()) {
            Toast.makeText(this, "Veuillez entrer une température", Toast.LENGTH_SHORT).show();
            return;
        }

        double temperature = Double.parseDouble(inputTemperature.getText().toString());
        double result = 0;
        String conversionType = "";

        if (toCelsius.isChecked()) {
            result = (temperature - 32) * 5 / 9;
            conversionType = "Fahrenheit to Celsius";
        } else if (toFahrenheit.isChecked()) {
            result = (temperature * 9 / 5) + 32;
            conversionType = "Celsius to Fahrenheit";
        } else {
            Toast.makeText(this, "Veuillez choisir une conversion", Toast.LENGTH_SHORT).show();
            return;
        }

        String resultText = String.format("Résultat: %.2f", result);
        resultTextView.setText(resultText);
        Toast.makeText(this, conversionType + ": " + resultText, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 0, "Retour Conversion Devise");
        menu.add(0, 2, 0, "Quitter");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 1) {
            finish();
            return true;
        } else if (item.getItemId() == 2) {
            finishAffinity();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
