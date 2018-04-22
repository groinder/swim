package swim.cw3;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment11 extends Fragment implements Button.OnClickListener {
    Activity onButtonClickActivity;
    OnButtonClickListener onButtonClickListener;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                onButtonClickListener.onButtonClick(1);
                break;
            case R.id.button2:
                onButtonClickListener.onButtonClick(2);
                break;

        }
    }

    public interface OnButtonClickListener {
        void onButtonClick(int option);
    }

    public Fragment11() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setRetainInstance(true);

        View inflateView = inflater.inflate(R.layout.fragment_fragment11, container, false);

        Button button1 = inflateView.findViewById(R.id.button1);
        button1.setOnClickListener(this);

        Button button2 = inflateView.findViewById(R.id.button2);
        button2.setOnClickListener(this);

        // Inflate the layout for this fragment
        return inflateView;
    }

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);

        try {
            onButtonClickActivity = context;
            onButtonClickListener = (OnButtonClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException((onButtonClickActivity.toString() + " have to implement OnButtonClickListener"));
        }
    }
}
