package com.example.krzys.dom2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class PersonalDataPreviewActivity extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presonal_data_preview);

        Intent intent = getIntent();
        Uri data = intent.getData();

        editText = findViewById(R.id.personalDataPreviewText);
        editText.setText(data.toString());
    }


    public void save(View view) {
        Intent intent = getIntent();
        intent.setData(Uri.parse(editText.getText().toString()));
        setResult(1, intent);
        finish();
    }
}
