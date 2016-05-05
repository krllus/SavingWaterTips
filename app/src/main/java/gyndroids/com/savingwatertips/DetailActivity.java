package gyndroids.com.savingwatertips;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
    private FloatingActionButton mFab;
    private int mPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // gets the position passed through bundle
        Bundle bundle = getIntent().getExtras();
        mPosition = bundle.getInt(PARAM_POSITION);

        loadViews();

        setUpToolbar();
        setUpFab();

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

    @Override
    protected void onResume() {
        super.onResume();

        loadContent();
    }

    /**
     * Load the views from the layout file
     */
    private void loadViews() {
        mTextDetails = (TextView) findViewById(R.id.details_text);
        mImageBackdrop = (ImageView) findViewById(R.id.details_backdrop);
        mFab = (FloatingActionButton) findViewById(R.id.details_fab_share);
    }

    /**
     * setups the toolbar, actionbar and collapsing toolbar.
     */
    private void setUpToolbar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.details_toolbar);
        CollapsingToolbarLayout mCollapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.details_collapsing_toolbar);

        setSupportActionBar(mToolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (mCollapsingToolbar != null) {
            mCollapsingToolbar.setTitle(getTitleByPosition());
        }
    }

    /**
     * setup the Floating Action Button
     */
    private void setUpFab() {
        Log.d("DetailActivity", "SetUpFab");
        mFab.setOnClickListener(v -> {
            Log.d("DetailActivity", "Fab Clicked");
            shareContent();
        });
    }

    /**
     * Load the content inside the recipients
     */
    private void loadContent() {
        // load the image inside the imageView
        Glide.with(this)
                .load(getImageByPosition())
                .centerCrop()
                .into(mImageBackdrop);

        // load the content inside the textView
        mTextDetails.setText(getContentByPosition());
    }

    /**
     * Load the contents from the string file and
     * return the content according with the mPosition
     */
    private String getContentByPosition() {

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

    /**
     * Load the titles from the string file and
     * return the title according with the mPosition
     */
    private CharSequence getTitleByPosition() {

        String[] titleArray = getResources().getStringArray(R.array.title_tips);
        List<String> titleList = Arrays.asList(titleArray);

        return titleList.get(mPosition);
    }

    /**
     * Returns the id of the image that are chosen by position
     * TODO finish this method
     */
    public int getImageByPosition() {

        switch (mPosition) {
            default:
                return R.drawable.water_cup;
        }

    }

    /**
     * Create an intent to share content with any app that allow text/plain as input.
     */
    private void shareContent() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, getTitleByPosition());
        sendIntent.putExtra(Intent.EXTRA_TEXT, getContentByPosition());
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.action_share)));
    }


}
