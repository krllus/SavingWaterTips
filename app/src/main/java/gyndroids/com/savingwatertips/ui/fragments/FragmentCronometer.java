package gyndroids.com.savingwatertips.ui.fragments;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import gyndroids.com.savingwatertips.R;
import gyndroids.com.savingwatertips.utils.Configs;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentCronometer#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentCronometer extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private int mParam1;

    private CountDownTimer mCountDownTimer;

    private View mView;
    private TextView mTimer;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FragmentCronometer.
     */

    public static FragmentCronometer newInstance() {
        FragmentCronometer fragment = new FragmentCronometer();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, Configs.DEFAULT_TIMER_CHRONOMETER);
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentCronometer() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_cronometer, container, false);

        mTimer = (TextView) mView.findViewById(R.id.textview_cronometer);

        int timer = Configs.DEFAULT_TIMER_CHRONOMETER;

        mCountDownTimer = new CountDownTimer(timer, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimer.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                mTimer.setText("done!");
            }
        }.start();

        return mView;
    }

}
