package gyndroids.com.savingwatertips;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String PARAM_POSITION = "position";

    private TextView mTextDetails;
    private ImageView mImageBackdrop;
    private int mPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        setUpToolbar();

        loadViews();

        //transition if version is lolipop+
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            /*
            Slide slide = new Slide(Gravity.BOTTOM);
            slide.addTarget(R.id.details_nestedscrollview);
            slide.setInterpolator(
                    AnimationUtils.loadInterpolator(this,
                            android.R.interpolator.linear_out_slow_in));
            slide.setDuration(getResources().getInteger(R.integer.anim_duration_medium));
            getWindow().setEnterTransition(slide);
            */
        }

    }

    private void loadViews() {
        mTextDetails = (TextView) findViewById(R.id.details_text);
        mImageBackdrop = (ImageView) findViewById(R.id.details_backdrop);
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadBackdrop();
        loadContent();
    }

    private void setUpToolbar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.details_toolbar);
        CollapsingToolbarLayout mCollapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.details_collapsing_toolbar);

        setSupportActionBar(mToolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        Bundle bundle = getIntent().getExtras();
        mPosition = bundle.getInt(PARAM_POSITION);

        if (mCollapsingToolbar != null) {
            mCollapsingToolbar.setTitle(getTitleByPosition());
        }
    }

    private void loadBackdrop() {

        Glide.with(this)
                .load(R.drawable.water_cup)
                .centerCrop()
                .into(mImageBackdrop);
    }

    private void loadContent() {

        String content = getTextByPosition();

        if (mTextDetails != null) {
            mTextDetails.setText(content);
        }

    }

    private String getTextByPosition() {

        ArrayList<String> contentList = new ArrayList<>();
        contentList.add(getString(R.string.text_tip_01));
        contentList.add(getString(R.string.text_tip_02));
        contentList.add(getString(R.string.text_tip_03));
        contentList.add(getString(R.string.text_tip_04));
        contentList.add(getString(R.string.text_tip_05));

        contentList.add(getString(R.string.text_tip_06));
        contentList.add(getString(R.string.text_tip_07));
        contentList.add(getString(R.string.text_tip_08));
        contentList.add(getString(R.string.text_tip_09));
        contentList.add(getString(R.string.text_tip_10));

        contentList.add(getString(R.string.text_tip_11));
        contentList.add(getString(R.string.text_tip_12));
        contentList.add(getString(R.string.text_tip_13));
        contentList.add(getString(R.string.text_tip_14));
        contentList.add(getString(R.string.text_tip_15));

        return contentList.get(mPosition);
    }

    private CharSequence getTitleByPosition() {

        String[] titleArray = getResources().getStringArray(R.array.title_tips);
        List<String> titleList = Arrays.asList(titleArray);

        return titleList.get(mPosition);
    }

}
