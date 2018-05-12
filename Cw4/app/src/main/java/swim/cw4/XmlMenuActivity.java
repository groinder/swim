package swim.cw4;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.TextView;

public class XmlMenuActivity extends Activity {
    EditText editText;
    EditText editText2;
    EditText editText3;
    Button button;
    MenuInflater menuInflater;
    View contextView;
    private ActionMode longClickActionMode = null;

    boolean textChecked = false;
    boolean bgChecked = false;
    boolean sizeChecked = false;

    ActionMode.Callback actionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            menuInflater.inflate(R.menu.action, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.xmlTextCopy:
                    copySelectedText(editText3);
                    actionMode.finish();
                    break;
                case R.id.xmlTextSelectAll:
                    editText3.selectAll();
                    break;
                case R.id.xmlTextClear:
                    editText3.setText("");
                    actionMode.finish();
                    break;
            }

            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {
            longClickActionMode = null;
        }
    };

    protected void copySelectedText(EditText view) {
        String viewText = view.getText().toString();
        int startIndex = view.getSelectionStart();
        int endIndex = view.getSelectionEnd();
        String text = viewText.substring(startIndex, endIndex);
        android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", text);
        clipboard.setPrimaryClip(clip);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml_menu);

        menuInflater = getMenuInflater();

        setViewItems();
    }

    protected void setViewItems() {
        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        button = findViewById(R.id.button);

        registerForContextMenu(editText);
        registerForContextMenu(editText2);
        registerForContextMenu(button);

        editText3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (longClickActionMode != null) {
                    return false;
                }

                longClickActionMode = startActionMode(actionModeCallback);
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menuInflater.inflate(R.menu.text, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.xmlRedColor:
                changeTextColor(Color.RED);
                return true;

            case R.id.xmlBlueColor:
                changeTextColor(Color.BLUE);
                return true;

            case R.id.xmlYellowColor:
                changeTextColor(Color.YELLOW);
                return true;

            case R.id.xmlTextLeft:
                changeTextAlignment(EditText.TEXT_ALIGNMENT_TEXT_START);
                return true;

            case R.id.xmlTextCenter:
                changeTextAlignment(EditText.TEXT_ALIGNMENT_CENTER);
                return true;

            case R.id.xmlTextRight:
                changeTextAlignment(EditText.TEXT_ALIGNMENT_TEXT_END);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        switch (v.getId()) {
            case R.id.editText:
                menuInflater.inflate(R.menu.color, menu);
                break;
            case R.id.editText2:
                menuInflater.inflate(R.menu.align, menu);
                break;
            case R.id.button:
                menuInflater.inflate(R.menu.button, menu);
                MenuItem xmlButtonText = menu.findItem(R.id.xmlButtonText);
                xmlButtonText.setChecked(textChecked);

                MenuItem xmlButtonBg = menu.findItem(R.id.xmlButtonBg);
                xmlButtonBg.setChecked(bgChecked);

                MenuItem xmlButtonSize = menu.findItem(R.id.xmlButtonSize);
                xmlButtonSize.setChecked(sizeChecked);
                break;
        }

        contextView = v;

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        boolean handled = false;
        int itemId = item.getItemId();

        if (contextView instanceof EditText) {
            handled = editTextContext((EditText) contextView, itemId);
        } else if (contextView instanceof Button) {
            handled = buttonContext((Button) contextView, item);
        }

        return handled || super.onContextItemSelected(item);
    }

    protected boolean buttonContext(Button btn, MenuItem item) {
        item.setChecked(!item.isChecked());

        switch (item.getItemId()) {
            case R.id.xmlButtonText:
                textChecked = item.isChecked();

                if (item.isChecked()) {
                    btn.setTextColor(Color.RED);
                } else {
                    btn.setTextColor(getResources().getColor(android.R.color.primary_text_light));
                }
                return true;

            case R.id.xmlButtonBg:
                bgChecked = item.isChecked();

                if (item.isChecked()) {
                    btn.getBackground().setColorFilter(Color.BLACK, PorterDuff.Mode.MULTIPLY);
                } else {
                    btn.getBackground().clearColorFilter();
                }
                return true;

            case R.id.xmlButtonSize:
                sizeChecked = item.isChecked();

                if (item.isChecked()) {
                    btn.setTextSize(20);
                } else {
                    btn.setTextSize(16);
                }
                return true;
        }

        return false;
    }

    protected boolean editTextContext(EditText et, int itemId) {
        switch (itemId) {
            case R.id.xmlRedColor:
                et.setTextColor(Color.RED);
                return true;

            case R.id.xmlBlueColor:
                et.setTextColor(Color.BLUE);
                return true;

            case R.id.xmlYellowColor:
                et.setTextColor(Color.YELLOW);
                return true;

            case R.id.xmlTextLeft:
                et.setTextAlignment(EditText.TEXT_ALIGNMENT_TEXT_START);
                return true;

            case R.id.xmlTextCenter:
                et.setTextAlignment(EditText.TEXT_ALIGNMENT_CENTER);
                return true;

            case R.id.xmlTextRight:
                et.setTextAlignment(EditText.TEXT_ALIGNMENT_TEXT_END);
                return true;
        }

        return false;
    }

    protected void changeTextAlignment(int textAlignment) {
        editText.setTextAlignment(textAlignment);
        editText2.setTextAlignment(textAlignment);
    }

    protected void changeTextColor(int color) {
        editText.setTextColor(color);
        editText2.setTextColor(color);
    }
}
