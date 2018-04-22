package swim.cw3;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class Aktywnosc2 extends Activity implements ActionBar.TabListener, Fragment11.OnButtonClickListener, Fragment12.OnDataChangeListener {
    Fragment11 f11;
    Fragment12 f12;
    FragmentTransaction transakcja;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aktywnosc2);

        f11 = new Fragment11();
        f12 = new Fragment12();
        transakcja = getFragmentManager().beginTransaction();
        transakcja.add(R.id.kontener2, f11);
        transakcja.detach(f11);
        transakcja.add(R.id.kontener2, f12);
        transakcja.detach(f12);
        transakcja.commit();

        actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.Tab tab1 = actionBar.newTab();
        tab1.setText(R.string.opcja1);
        tab1.setTabListener(this);
        actionBar.addTab(tab1);

        ActionBar.Tab tab2 = actionBar.newTab();
        tab2.setText(R.string.opcja2);
        tab2.setTabListener(this);
        actionBar.addTab(tab2);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        int pos = tab.getPosition();

        switch (pos) {
            case 0:
                fragmentTransaction.attach(f11);
                break;
            case 1:
                fragmentTransaction.attach(f12);
                break;
        }
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        int pos = tab.getPosition();

        switch (pos) {
            case 0:
                fragmentTransaction.detach(f11);
                break;
            case 1:
                fragmentTransaction.detach(f12);
                break;
        }
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onButtonClick(int option) {

    }

    @Override
    public void onDataChange(Fragment12.Data data) {

    }
}
