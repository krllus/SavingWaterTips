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

import gyndroids.com.savingwatertips.utils.Configs;

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
            mCollapsingToolbar.setTitle(getString(Configs.getTitleResourceIdByPosition(mPosition)));
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
                .load(Configs.getImageResourceByPosition(mPosition))
                .centerCrop()
                .into(mImageBackdrop);

        // load the content inside the textView
        mTextDetails.setText(getString(Configs.getContentIdByPosition(mPosition)));
    }


    /**
     * Create an intent to share content with any app that allow text/plain as input.
     */
    private void shareContent() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, getString(
                Configs.getTitleResourceIdByPosition(mPosition)));
        sendIntent.putExtra(Intent.EXTRA_TEXT, getString(
                Configs.getContentIdByPosition(mPosition)));
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.action_share)));
    }


}
