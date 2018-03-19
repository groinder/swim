package swim.cw1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class OperationActivity extends AppCompatActivity {
    public enum Operacja {
        DODAJ,
        USUN
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operation);
    }

    @SuppressLint("RestrictedApi")
    public void calculate(View view) {
        final Intent intent = new Intent(this, ResultActivity.class);

        EditText first = findViewById(R.id.first);
        EditText second = findViewById(R.id.second);

        RadioGroup radio = findViewById(R.id.radioOperacja);

        int radioId = radio.getCheckedRadioButtonId();

        Operacja operacja = Operacja.DODAJ;

        switch (radioId) {
            case R.id.radioDodaj:
                operacja = Operacja.DODAJ;
                break;
            case R.id.radioUsun:
                operacja = Operacja.USUN;
                break;
        }

        Bundle bundle = new Bundle();
        bundle.putInt("first", Integer.parseInt(first.getText().toString()));
        bundle.putInt("second", Integer.parseInt(second.getText().toString()));
        bundle.putString("operacja", operacja.toString());

        intent.putExtra("bundle", bundle);

        startActivityForResult(intent, 200);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Uri _result = data.getData();
        String result = _result.getQueryParameter("result");

        TextView textView = findViewById(R.id.result2);
        textView.setText(result);
    }
}
