package swim.cw1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Main3Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }

    public void backTo1(View view) {
        onBackPressed();
    }

    protected void onStop() {
        super.onStop();

        Toast.makeText(getApplicationContext(),
                "Aktywność 3 - Zatrzymana",
                Toast.LENGTH_SHORT).show();
    }
}
