package swim.cw1;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Main4Activity extends Activity {
    protected boolean toastEnabled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
    }

    public void toggleToast(View view) {
        toastEnabled = !toastEnabled;
    }

    protected void onStop() {
        super.onStop();

        if (toastEnabled) {
            EditText input = findViewById(R.id.nameInput);
            String name = input.getText().toString();
            RadioGroup radio = findViewById(R.id.radioSex);
            int radioId = radio.getCheckedRadioButtonId();

            String title;

            if (radioId == R.id.radioMale) {
                title = getString(R.string.titleMale);
            }
            else {
                title = getString(R.string.titleFemale);
            }

            Toast.makeText(getApplicationContext(),
                    "Thanks " + title + " " + name,
                    Toast.LENGTH_SHORT).show();
        }
    }
}
