package com.example.krzys.dom2;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Set;

public class PersonalDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_data);

        loadPersonalData();
    }

    private void loadPersonalData() {
        SharedPreferences sp = getSharedPreferences("personalData", MODE_PRIVATE);

        String sex = sp.getString("sex", "");

        if (sex.equals("male")) {
            RadioButton sexBtn = findViewById(R.id.sexMale);
            sexBtn.setChecked(true);
        } else if (sex.equals("female")) {
            RadioButton sexBtn = findViewById(R.id.sexFemale);
            sexBtn.setChecked(true);
        }

        String firstName = sp.getString("firstName", "");
        EditText firstNameView = findViewById(R.id.firstName);
        firstNameView.setText(firstName);

        String lastName = sp.getString("lastName", "");
        EditText lastNameView = findViewById(R.id.lastName);
        lastNameView.setText(lastName);
    }

    public void savePersonalData(View view) {
        SharedPreferences sp = getSharedPreferences("personalData", MODE_PRIVATE);
        SharedPreferences.Editor spe = sp.edit();

        RadioGroup sexGroup = findViewById(R.id.sex);
        int sex = sexGroup.getCheckedRadioButtonId();

        if (sex == R.id.sexMale) {
            spe.putString("sex", "male");
        } else if (sex == R.id.sexFemale) {
            spe.putString("sex", "female");
        }

        spe.putString("firstName", getStringFromEditText(R.id.firstName));

        spe.putString("lastName", getStringFromEditText(R.id.lastName));

        spe.commit();

        navigateToMainActivity(view);
    }

    public void navigateToMainActivity(View view) {
        final Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private String getStringFromEditText(int id) {
        EditText editText = findViewById(id);
        return editText.getText().toString();
    }

    public void exportPersonalDataToFile(View view) {
        savePersonalDataToFile(getPersonalDataString());
    }

    private void savePersonalDataToFile(String data) {
        FileOutputStream os;

        try {
            os = openFileOutput("personalData", Context.MODE_PRIVATE);
            os.write(data.getBytes());
            os.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getPersonalDataString() {
        RadioGroup sexGroup = findViewById(R.id.sex);
        int sexId = sexGroup.getCheckedRadioButtonId();

        String sex;

        if (sexId == R.id.sexMale) {
            sex = getString(R.string.male);
        } else {
            sex = getString(R.string.female);
        }

        return getString(R.string.sex) + ": " + sex + "\n" +
                getString(R.string.firstName) + ": " + getStringFromEditText(R.id.firstName) + "\n" +
                getString(R.string.lastName) + ": " + getStringFromEditText(R.id.lastName);
    }

    public void navigateToPersonalDataPreviewActivity(View view) {
        final Intent intent = new Intent(this, PersonalDataPreviewActivity.class);
        String personalData = readPersonalDataFromFile();
        intent.setData(Uri.parse(personalData));
        startActivityForResult(intent, 3333);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            String dataString = data.getDataString();
            savePersonalDataToFile(dataString);

            Toast toast = Toast.makeText(getApplicationContext(), R.string.changesSaved, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void sendPersonalDataByTextMessage(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        EditText phoneView = findViewById(R.id.phoneToSend);
        String phone = phoneView.getText().toString();

        intent.setData(Uri.parse("sms:" + phone));
        intent.putExtra("sms_body", getPersonalDataString());

        startActivity(intent);
    }

    private String readPersonalDataFromFile() {
        FileInputStream is;
        String data = "";

        try {
            is = openFileInput("personalData");

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            int bytesRead;
            byte[] b = new byte[1024];
            while ((bytesRead = is.read(b)) != -1) {
                baos.write(b, 0, bytesRead);
            }

            data = baos.toString();
            is.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }
}
