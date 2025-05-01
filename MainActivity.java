package com.example.l3si.conversion;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText edt;
    private TextView resultat;
    private RadioGroup radioGroup;
    private final float taux = 1.47f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt = findViewById(R.id.edit_float);
        resultat = findViewById(R.id.resultat);
        radioGroup = findViewById(R.id.radioGroup);

        registerForContextMenu(radioGroup);
    }

    public void convertir(View v) {
        if (edt.getText().toString().isEmpty()) {
            Toast.makeText(this, "Veuillez entrer une valeur", Toast.LENGTH_SHORT).show();
            return;
        }
        float valeur = Float.parseFloat(edt.getText().toString());
        float result = 0;

        if (radioGroup.getCheckedRadioButtonId() == R.id.radioDinarToEuro) {
            result = dinarsToEuro(valeur);
        } else if (radioGroup.getCheckedRadioButtonId() == R.id.radioEuroToDinar) {
            result = euroToDinar(valeur);
        }

        resultat.setText(String.valueOf(result));
    }

    private float dinarsToEuro(float valeurDinar) {
        return valeurDinar / taux;
    }

    private float euroToDinar(float valeurEuro) {
        return valeurEuro * taux;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(0, 1, 0, "Taux Dinar -> Euro");
        menu.add(0, 2, 0, "Taux Euro -> Dinar");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                Toast.makeText(this, "Taux Dinar -> Euro : 1 DZD = 1 / 1.47 EUR", Toast.LENGTH_LONG).show();
                return true;
            case 2:
                Toast.makeText(this, "Taux Euro -> Dinar : 1 EUR = 1.47 DZD", Toast.LENGTH_LONG).show();
                return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 0, "Conversion C <-> F");
        menu.add(0, 2, 0, "Quitter");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                startActivity(new Intent(this, ConversionTemperature.class));
                return true;
            case 2:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
