package swim.cw4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setButtonsListeners();
    }

    protected void setButtonsListeners() {
        Button xmlBtn = findViewById(R.id.xmlBtn);
        xmlBtn.setOnClickListener(this);

        Button javaBtn = findViewById(R.id.javaBtn);
        javaBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Class activityClass = null;

        switch (view.getId()) {
            case R.id.xmlBtn:
                activityClass = XmlMenuActivity.class;
                break;
            case R.id.javaBtn:
                activityClass = JavaMenuActivity.class;
                break;
        }

        if (activityClass != null) {
            final Intent intent = new Intent(this, activityClass);
            startActivity(intent);
        }
    }
}
