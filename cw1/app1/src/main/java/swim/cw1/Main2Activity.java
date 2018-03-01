package swim.cw1;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Main2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        View myWindow2 = findViewById(R.id.myWindow2);
        myWindow2.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                finish();
                return false;
            }
        });
    }
}
