package swim.cw1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    protected int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_result);

        Bundle bundle = this.getIntent().getBundleExtra("bundle");

        int first = bundle.getInt("first");
        int second = bundle.getInt("second");

        OperationActivity.Operacja operacja = OperationActivity.Operacja.valueOf(bundle.getString("operacja"));

        switch (operacja) {
            case DODAJ:
                result = first + second;
                break;
            case USUN:
                result = first - second;
                break;
        }

        TextView textView = findViewById(R.id.result);

        textView.setText(Integer.toString(result));

        Intent _result = new Intent();
        Uri.Builder builder = new Uri.Builder();
        builder.appendQueryParameter("result", Integer.toString(result));
        Uri data = builder.build();
        _result.setData(data);

        setResult(200, _result);
        super.onCreate(savedInstanceState);
    }
}
