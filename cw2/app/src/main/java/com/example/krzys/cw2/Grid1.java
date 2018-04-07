package com.example.krzys.cw2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

public class Grid1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid1);

        GridView gridView = findViewById(R.id.gridView1);
        gridView.setAdapter(new MyAdapter(this));
    }
}
