package swim.cw3;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.View;

public class MainActivity extends Activity implements Fragment1.OnWyborOpcjiListener, Fragment11.OnButtonClickListener, Fragment12.OnDataChangeListener {
    Fragment11 f11;
    Fragment12 f12;
    FragmentTransaction transakcja;
    private static final String TAG_F11 = "Fragment11";
    private static final String TAG_F12 = "Fragment12";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            f11 = new Fragment11();
            f12 = new Fragment12();

            SharedPreferences sp = getSharedPreferences("ApplicationData", MODE_PRIVATE);
            Bundle bundle = new Bundle();
            bundle.putString("text", sp.getString("text", ""));
            bundle.putInt("radio", sp.getInt("radio", 1));

            f12.setArguments(bundle);

            transakcja = getFragmentManager().beginTransaction();
            transakcja.add(R.id.kontener, f11, TAG_F11);
            transakcja.detach(f11);
            transakcja.add(R.id.kontener, f12, TAG_F12);
            transakcja.detach(f12);
            transakcja.commit();
        } else {
            f11 = (Fragment11) getFragmentManager().findFragmentByTag(TAG_F11);
            f12 = (Fragment12) getFragmentManager().findFragmentByTag(TAG_F12);
        }
    }

    @Override
    public void onWyborOpcji(int opcja) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        switch (opcja) {
            case 1:
                transaction.detach(f12);
                transaction.attach(f11);
                break;
            case 2:
                transaction.detach(f11);
                transaction.attach(f12);
                break;
        }

        transaction.commit();
    }

    public void runSecond(View view) {
        final Intent intent = new Intent(this, Aktywnosc2.class);
        startActivity(intent);
    }

    @Override
    public void onButtonClick(int option) {
        switch (option) {
            case 1:
                final Intent intent = new Intent(Intent.ACTION_INSERT)
                        .setData(CalendarContract.Events.CONTENT_URI);
                startActivity(intent);
                break;
            case 2:
                Intent intent2 = new Intent(Intent.ACTION_DIAL);
                intent2.setData(Uri.parse("tel:"));
                startActivity(intent2);
                break;
        }
    }

    @Override
    public void onDataChange(Fragment12.Data data) {
        SharedPreferences sp = getSharedPreferences("ApplicationData", MODE_PRIVATE);
        SharedPreferences.Editor spe = sp.edit();

        spe.putString("text", data.text);
        spe.putInt("radio", data.radio);

        spe.commit();
    }
}
