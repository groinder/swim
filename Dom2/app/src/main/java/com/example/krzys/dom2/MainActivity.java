package com.example.krzys.dom2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.Spinner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, RatingBar.OnRatingBarChangeListener {
    public enum Status {
        AKTYWNY("Aktywny"),
        NIEAKTYWNY("Nieaktywny"),
        ZAWIESZONY("Zawieszony");

        private String name;

        Status(String name){
            this.name = name;
        }

        public String getAsString() {
            return super.toString();
        }

        @Override
        public String toString(){
            return name;
        }
    }

    SharedPreferences sp;
    ApplicationFile applicationFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        applicationFile = new ApplicationFile();

        Spinner statusSpinner = findViewById(R.id.statusSpinner);
        statusSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<Status> adapter = new ArrayAdapter<Status>(this, android.R.layout.simple_spinner_item, Status.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusSpinner.setAdapter(adapter);

        sp = getSharedPreferences("ApplicationData", MODE_PRIVATE);
        Status currentStatus = Status.valueOf(sp.getString("status", "AKTYWNY"));

        int idx = -1;

        for (Status s : Status.values()) {
            idx++;
            if (s == currentStatus) {
                break;
            }
        }

        statusSpinner.setSelection(idx);

        RatingBar rb = findViewById(R.id.ratingBar);
        rb.setOnRatingBarChangeListener(this);

        FileInputStream fis = null;
        ApplicationFile fileClass = null;
        try {
            fis = openFileInput("ApplicationFile");
            ObjectInputStream is = new ObjectInputStream(fis);
            fileClass = (ApplicationFile) is.readObject();
            is.close();
            fis.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (fileClass != null) {
            rb.setRating((fileClass.ratingPercent/100)*rb.getNumStars());
        }
    }

    public void runPersonalDataActivity(View view) {
        final Intent intent = new Intent("android.intent.action.RunPersonalData");
        startActivity(intent);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        SharedPreferences.Editor spe = sp.edit();

        Status statusVal = Status.values()[i];

        spe.putString("status", statusVal.getAsString());

        spe.commit();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
        applicationFile.ratingPercent = 100 / v;

        FileOutputStream fos = null;
        try {
            fos = openFileOutput("ApplicationFile", Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(applicationFile);
            os.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
