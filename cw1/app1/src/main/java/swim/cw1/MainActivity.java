package swim.cw1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      final Intent intent1 = new Intent(this, Main2Activity.class);
        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(intent1);
            }
        });
    }

    public void runSecond(View view) {
        final Intent intent2 = new Intent(this, Main2Activity.class);
        startActivity(intent2);
    }

    public void runFourth(View view) {
        final Intent intent3 = new Intent(this, Main4Activity.class);
        startActivity(intent3);
    }

    public void runScrolling(View view) {
        final Intent intentScrolling = new Intent(this, ScrollingActivity.class);
        startActivity(intentScrolling);
    }

    public void runRelative(View view) {
        final Intent intentRelative = new Intent(this, RelativeActivity.class);
        startActivity(intentRelative);
    }
}
