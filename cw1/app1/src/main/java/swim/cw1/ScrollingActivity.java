package swim.cw1;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class ScrollingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        TableLayout tableLayout = findViewById(R.id.table);

        TextView textView;
        TableRow tableRow;
        CheckBox checkBox;

        for (int i = 1; i <= 50; i++) {
            tableRow = new TableRow(this);

            checkBox = new CheckBox(this);
            checkBox.setOnClickListener(new CheckBox.OnClickListener(){

                @Override
                public void onClick(View view) {
                    CheckBox cView = (CheckBox) view;
                    String txt = "";

                    TableRow parent = (TableRow) view.getParent();

                    TextView textV = parent.findViewWithTag("checkbox-label");

                    txt += textV.getText() + " ";

                    if (cView.isChecked()) {
                        txt += "checked!";
                    }
                    else {
                        txt += "unchecked!";
                    }

                    Toast.makeText(getApplicationContext(),
                            txt,
                            Toast.LENGTH_SHORT).show();
                }
            });

            tableRow.addView(checkBox);

            textView = new TextView(this);
            textView.setText(Integer.toString(i));
            textView.setTag("checkbox-label");

            tableRow.addView(textView);

            tableLayout.addView(tableRow);
        }
    }
}
