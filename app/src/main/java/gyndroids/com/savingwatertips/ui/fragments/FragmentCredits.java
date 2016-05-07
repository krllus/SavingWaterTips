package gyndroids.com.savingwatertips.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import gyndroids.com.savingwatertips.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentCredits extends Fragment {

    private View mView;
    private TextView mTextFonts;
    private TextView mTextLink;

    public FragmentCredits() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_credits, container, false);

        mTextFonts = (TextView) mView.findViewById(R.id.credits_fonts);
        mTextLink = (TextView) mView.findViewById(R.id.credits_link);

        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();

        setmTextFonts();
        setLink();
    }

    private void setLink() {

        String link = getString(R.string.font_link);

        mTextLink.setClickable(true);
        mTextLink.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "<a href='" + link + "'> " + link + " </a>";
        mTextLink.setText(Html.fromHtml(text));
    }

    public void setmTextFonts() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(getString(R.string.font_01)).append(", ");
        stringBuilder.append(getString(R.string.font_02)).append(", ");
        stringBuilder.append(getString(R.string.font_03)).append(", ");
        stringBuilder.append(getString(R.string.font_04));

        mTextFonts.setText(stringBuilder.toString());
    }
}
