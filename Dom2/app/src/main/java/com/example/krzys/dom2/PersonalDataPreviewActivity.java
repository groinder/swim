package com.example.krzys.dom2;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class PersonalDataPreviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presonal_data_preview);

        readPersonalDataFromFile();
    }

    private void readPersonalDataFromFile() {
        FileInputStream is;

        try {
            is = openFileInput("personalData");

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            int bytesRead;
            byte[] b = new byte[1024];
            while ((bytesRead = is.read(b)) != -1) {
                baos.write(b, 0, bytesRead);
            }

            String data = baos.toString();

            EditText editText = findViewById(R.id.personalDataPreviewText);
            editText.setText(data);

            is.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
