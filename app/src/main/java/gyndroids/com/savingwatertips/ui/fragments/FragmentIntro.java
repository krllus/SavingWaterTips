package gyndroids.com.savingwatertips.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import gyndroids.com.savingwatertips.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentIntro#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentIntro extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_POSITION = "position";

    private int mPosition;

    private View mView;
    private TextView mTextContent;

    public FragmentIntro() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param position Parameter 1.
     * @return A new instance of fragment FragmentIntro.
     */
    public static FragmentIntro newInstance(int position) {
        FragmentIntro fragment = new FragmentIntro();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setting a default value if nothing is passed
        mPosition = getArguments().getInt(ARG_POSITION, 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_introduction, container, false);

        loadViews();
        loadContent();

        return mView;
    }

    private void loadContent() {
        switch (mPosition) {
            case 0:
                mTextContent.setText(getString(R.string.introduction_paragraph_01));
                break;
            case 1:
                mTextContent.setText(getString(R.string.introduction_paragraph_02));
                break;
            case 2:
                mTextContent.setText(getString(R.string.introduction_paragraph_03));
                break;
            case 3:
                mTextContent.setText(getString(R.string.introduction_paragraph_04));
                break;
        }

    }

    private void loadViews() {
        mTextContent = (TextView) mView.findViewById(R.id.motivation_textview_content);
    }

}
