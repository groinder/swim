package com.example.krzys.cw2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Lista2 extends AppCompatActivity implements AdapterView.OnItemClickListener {
    String[] texts = {
            "Jaś",
            "Fasola",
            "jakiś tekst",
            "Jaś",
            "Fasola",
            "jakiś tekst",
            "Jaś",
            "Fasola",
            "jakiś tekst",
            "Jaś",
            "Fasola",
            "jakiś tekst",
    };

    ListView lista2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista2);

        lista2 = findViewById(R.id.viewListaMultichoice);
        lista2.setOnItemClickListener(this);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_multiple_choice,
                texts
        );

        lista2.setAdapter(adapter2);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String napis = "";
        SparseBooleanArray zaznaczone = lista2.getCheckedItemPositions();

        for (int ii = 0; ii < zaznaczone.size(); ii++) {
            if (zaznaczone.valueAt(ii)) {
                int indeks = zaznaczone.keyAt(ii);
                napis += (" " + String.valueOf(indeks+1));
            }
        }

        Toast.makeText(getApplicationContext(),
                "Wybrałeś: " + napis,
                Toast.LENGTH_SHORT
        ).show();
    }
}
