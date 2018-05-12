package swim.cw4;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class JavaMenuActivity extends Activity {
    EditText editText;
    EditText editText2;
    EditText editText3;
    Button button;
    View contextView;
    private ActionMode longClickActionMode = null;
    Resources resources;

    boolean textChecked = false;
    boolean bgChecked = false;
    boolean sizeChecked = false;


    ActionMode.Callback actionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            addActionMenu(menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.javaTextCopy:
                    copySelectedText(editText3);
                    actionMode.finish();
                    break;
                case R.id.javaTextSelectAll:
                    editText3.selectAll();
                    break;
                case R.id.javaTextClear:
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
        setContentView(R.layout.activity_java_menu);

        resources = getResources();

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
        addColorMenu(menu);
        SubMenu sm = menu.addSubMenu(Menu.NONE, R.id.javaTextAlign, menu.size(), resources.getString(R.string.textAlign));
        addAlignMenu(sm);

        return super.onCreateOptionsMenu(menu);
    }

    protected void addColorMenu(Menu menu) {
        menu.add(Menu.NONE, R.id.javaRedColor, menu.size(), resources.getString(R.string.redColor));
        menu.add(Menu.NONE, R.id.javaBlueColor, menu.size(), resources.getString(R.string.blueColor));
        menu.add(Menu.NONE, R.id.javaYellowColor, menu.size(), resources.getString(R.string.yellowColor));
    }

    protected void addAlignMenu(Menu menu) {
        menu.add(Menu.NONE, R.id.javaTextLeft, menu.size(), resources.getString(R.string.left));
        menu.add(Menu.NONE, R.id.javaTextCenter, menu.size(), resources.getString(R.string.center));
        menu.add(Menu.NONE, R.id.javaTextRight, menu.size(), resources.getString(R.string.right));
    }

    protected void addActionMenu(Menu menu) {
        menu.add(Menu.NONE, R.id.javaTextCopy, menu.size(), resources.getString(R.string.copy)).setIcon(R.drawable.copy);
        menu.add(Menu.NONE, R.id.javaTextSelectAll, menu.size(), resources.getString(R.string.selectAll)).setIcon(R.drawable.select_all);
        menu.add(Menu.NONE, R.id.javaTextClear, menu.size(), resources.getString(R.string.clear)).setIcon(R.drawable.clear);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.javaRedColor:
                changeTextColor(Color.RED);
                return true;

            case R.id.javaBlueColor:
                changeTextColor(Color.BLUE);
                return true;

            case R.id.javaYellowColor:
                changeTextColor(Color.YELLOW);
                return true;

            case R.id.javaTextLeft:
                changeTextAlignment(EditText.TEXT_ALIGNMENT_TEXT_START);
                return true;

            case R.id.javaTextCenter:
                changeTextAlignment(EditText.TEXT_ALIGNMENT_CENTER);
                return true;

            case R.id.javaTextRight:
                changeTextAlignment(EditText.TEXT_ALIGNMENT_TEXT_END);
                return true;
        }


        return super.onOptionsItemSelected(item);
    }

    protected void changeTextAlignment(int textAlignment) {
        editText.setTextAlignment(textAlignment);
        editText2.setTextAlignment(textAlignment);
    }

    protected void changeTextColor(int color) {
        editText.setTextColor(color);
        editText2.setTextColor(color);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        switch (v.getId()) {
            case R.id.editText:
                addColorMenu(menu);
                break;
            case R.id.editText2:
                addAlignMenu(menu);
                break;
            case R.id.button:
                addButtonMenu(menu);
                break;
        }

        contextView = v;

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    protected void addButtonMenu(Menu menu) {
        MenuItem javaButtonText = menu.add(Menu.NONE, R.id.javaButtonText, menu.size(), resources.getString(R.string.alternativeTextColor));
        javaButtonText.setCheckable(true);
        javaButtonText.setChecked(textChecked);

        MenuItem javaButtonBg = menu.add(Menu.NONE, R.id.javaButtonBg, menu.size(), resources.getString(R.string.alternativeBgColor));
        javaButtonBg.setCheckable(true);
        javaButtonBg.setChecked(bgChecked);

        MenuItem javaButtonSize = menu.add(Menu.NONE, R.id.javaButtonSize, menu.size(), resources.getString(R.string.alternativeSize));
        javaButtonSize.setCheckable(true);
        javaButtonSize.setChecked(sizeChecked);
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
            case R.id.javaButtonText:
                textChecked = item.isChecked();

                if (item.isChecked()) {
                    btn.setTextColor(Color.RED);
                } else {
                    btn.setTextColor(resources.getColor(android.R.color.primary_text_light));
                }
                return true;

            case R.id.javaButtonBg:
                bgChecked = item.isChecked();

                if (item.isChecked()) {
                    btn.getBackground().setColorFilter(Color.BLACK, PorterDuff.Mode.MULTIPLY);
                } else {
                    btn.getBackground().clearColorFilter();
                }
                return true;

            case R.id.javaButtonSize:
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
            case R.id.javaRedColor:
                et.setTextColor(Color.RED);
                return true;

            case R.id.javaBlueColor:
                et.setTextColor(Color.BLUE);
                return true;

            case R.id.javaYellowColor:
                et.setTextColor(Color.YELLOW);
                return true;

            case R.id.javaTextLeft:
                et.setTextAlignment(EditText.TEXT_ALIGNMENT_TEXT_START);
                return true;

            case R.id.javaTextCenter:
                et.setTextAlignment(EditText.TEXT_ALIGNMENT_CENTER);
                return true;

            case R.id.javaTextRight:
                et.setTextAlignment(EditText.TEXT_ALIGNMENT_TEXT_END);
                return true;
        }

        return false;
    }
}
