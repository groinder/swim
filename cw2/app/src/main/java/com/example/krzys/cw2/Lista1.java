package com.example.krzys.cw2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Lista1 extends AppCompatActivity implements AdapterView.OnItemClickListener {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista1);

        ListView lista1 = findViewById(R.id.viewListaProsta);
        lista1.setOnItemClickListener(this);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                texts
        );

        lista1.setAdapter(adapter2);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        TextView textView = (TextView) view;
        String text = textView.getText().toString();

        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }
}
