package com.example.krzys.cw2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class InitialActivity extends Activity implements AdapterView.OnItemSelectedListener {

    String lista[] = {"Pozycja 1", "Pozycja 2", "Pozycja 3"};
    String[] p = {"1", "2", "3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        Spinner opcje = findViewById(R.id.spinner1);

        if (opcje != null) {
            opcje.setOnItemSelectedListener(this);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_spinner_item,
                    lista
            );

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            opcje.setAdapter(adapter);
        }

        SharedPreferences sp = getSharedPreferences("dane_apki", MODE_PRIVATE);
        SharedPreferences.Editor spe = sp.edit();
        int nru = sp.getInt("nr_uruchomienia", 0);
        nru++;
        Toast.makeText(getApplicationContext(), "Uruchomienie nr " + nru, Toast.LENGTH_SHORT).show();
        spe.putInt("nr_uruchomienia", nru);
        spe.commit();

    }

    public void runLista1(View view) {
        final Intent intent = new Intent(this, Lista1.class);
        startActivity(intent);
    }

    public void runLista2(View view) {
        final Intent intent = new Intent(this, Lista2.class);
        startActivity(intent);
    }

    public void runGrid1(View view) {
        final Intent intent = new Intent(this, Grid1.class);
        startActivity(intent);
    }

    public void runLista3(View view) {
        final Intent intent = new Intent(this, Lista3.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(
                getApplicationContext(),
                "Wybrałeś: " + p[i],
                Toast.LENGTH_LONG
        ).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
