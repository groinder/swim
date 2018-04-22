package swim.cw3;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment12 extends Fragment implements TextWatcher, RadioGroup.OnCheckedChangeListener {
    class Data {
        String text;
        int radio;
    }

    Activity onDataChangeActivity;
    OnDataChangeListener onDataChangeListener;

    public interface OnDataChangeListener {
        void onDataChange(Data data);
    }

    public Fragment12() {
        // Required empty public constructor
    }

    Data data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        data = new Data();
        Bundle bundle = getArguments();

        setRetainInstance(true);
        View inflateView = inflater.inflate(R.layout.fragment_fragment12, container, false);

        EditText text = inflateView.findViewById(R.id.editText);
        text.addTextChangedListener(this);

        RadioGroup radioGroup = inflateView.findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(this);

        if (bundle != null) {
            switch (bundle.getInt("radio")) {
                case 1:
                    ((RadioButton)inflateView.findViewById(R.id.radio1)).setChecked(true);
                    break;
                case 2:
                    ((RadioButton)inflateView.findViewById(R.id.radio2)).setChecked(true);
                    break;
            }

            text.setText(bundle.getString("text"));
        }

        // Inflate the layout for this fragment
        return inflateView;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        data.text = editable.toString();
        onDataChangeListener.onDataChange(data);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.radio1:
                data.radio = 1;
                break;
            case R.id.radio2:
                data.radio = 2;
                break;
        }

        onDataChangeListener.onDataChange(data);
    }

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);

        try {
            onDataChangeActivity = context;
            onDataChangeListener = (OnDataChangeListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException((onDataChangeActivity.toString() + " have to implement OnButtonClickListener"));
        }
    }
}
