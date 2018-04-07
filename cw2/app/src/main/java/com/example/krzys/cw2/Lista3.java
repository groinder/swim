package com.example.krzys.cw2;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Lista3 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista3);

        myAdapter  adapter  =  new  myAdapter(ltxt1);
        ListView  lista3  =  (ListView)  findViewById(R.id.viewListaDost); lista3.setAdapter(adapter);

    }

    private class LVitem {
        TextView tv1;
        TextView tv2;
        ImageView img;
        CheckBox cBox;
    }

    private LayoutInflater inflater = null;
    boolean[] zazn_pozycje;

    LVitem myLVitem;

    String ltxt1[] = {
            "Pozycja nr 1",
            "Pozycja nr 2",
            "Pozycja nr 3",
            "Pozycja nr 4",
            "Pozycja nr 5",
            "Pozycja nr 6",
            "Pozycja nr 7",
            "Pozycja nr 8",
            "Pozycja nr 9",
            "Pozycja nr 10",
            "Pozycja nr 11"
    };

    String ltxt2[] = {
            "Tekst 1",
            "Tekst 2",
            "Tekst 3",
            "Tekst 4",
            "Tekst 5",
            "Tekst 6",
            "Tekst 7",
            "Tekst 8",
            "Tekst 9",
            "Tekst 10",
            "Tekst 11"
    };

    public class myAdapter extends BaseAdapter {
        private LayoutInflater inflater = null;
        boolean[] zazn_pozycje;
        LVitem myLVitem;
        String[] ltext;

        myAdapter(String[] lista) {
            super();
            ltext = lista;
            zazn_pozycje = new boolean[ltext.length];
            inflater = (LayoutInflater)
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public int getCount() {
            return ltext.length;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(final int position,
                            View listItemView, ViewGroup parent) {
            if (listItemView == null) {
                listItemView = inflater.inflate(R.layout.list_row, null);
                myLVitem = new LVitem();
                myLVitem.tv1 = (TextView)
                        listItemView.findViewById(R.id.row_tv1);
                myLVitem.tv2 = (TextView)
                        listItemView.findViewById(R.id.row_tv2);
                myLVitem.img = (ImageView)
                        listItemView.findViewById(R.id.row_image);
                myLVitem.cBox = (CheckBox)
                        listItemView.findViewById(R.id.lrow_checkBox);
                listItemView.setTag(myLVitem);
            } else
                myLVitem = (LVitem) listItemView.getTag();
            myLVitem.cBox.setChecked(zazn_pozycje[position]);
            myLVitem.cBox.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (((CheckBox) v).isChecked()) zazn_pozycje[position] = true;
                    else
                        zazn_pozycje[position] = false;
                    Toast.makeText(getApplicationContext(),
                            "Zanaczyłeś/odznaczyłeś:  " + (position + 1), Toast.LENGTH_SHORT).show();
                }
            });
            return listItemView;
        }
    }

}
