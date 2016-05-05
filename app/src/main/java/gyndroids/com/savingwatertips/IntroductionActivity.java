package gyndroids.com.savingwatertips;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import gyndroids.com.savingwatertips.adapter.ScreenSlidePagerAdapter;
import gyndroids.com.savingwatertips.adapter.ZoomOutPageTransformer;
import gyndroids.com.savingwatertips.utils.Configs;

public class IntroductionActivity extends AppCompatActivity {

    private ViewPager mPager;

    private Button mButtonPrev;
    private Button mButtonNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);

        setUpToolbar();

        setUpViews();
        setUpViewPager();
        setUpButtons();
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    /**
     * Set up the toolbar
     */
    private void setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.motivational_toolbar);
        setSupportActionBar(toolbar);
    }

    /**
     * Set up the views from the layout file resource
     */
    private void setUpViews() {
        mPager = (ViewPager) findViewById(R.id.motivation_viewpager);
        mButtonPrev = (Button) findViewById(R.id.introduction_btn_prev);
        mButtonNext = (Button) findViewById(R.id.introduction_btn_next);
    }

    /**
     * setup the view pager
     * create an adapter
     * add a PageChangeListener, allowing the Zoom Out effect between the transitions
     */
    private void setUpViewPager() {
        PagerAdapter mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setPageTransformer(true, new ZoomOutPageTransformer());
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            /**
             * according with the position,
             * enable the mButtonPrev,
             * toggle the mButtonNext text between 'Proximo' and 'Continuar'
             *
             * @param position
             */
            @Override
            public void onPageSelected(int position) {

                if (position == 0)
                    mButtonPrev.setEnabled(false);
                else
                    mButtonPrev.setEnabled(true);

                if (position == Configs.NUM_PAGES - 1)
                    mButtonNext.setText(getResources().getString(R.string.introduction_btn_continue));
                else
                    mButtonNext.setText(getResources().getString(R.string.introduction_btn_next));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * SetUp the onClickListener for the buttons
     * mButtonPrev decrement the mPager position when clicked
     * mButtonNext increment the mPager position when clicked, also when it is
     * in the last position, it can trigger the startMainActivity method
     */
    private void setUpButtons() {
        mButtonPrev.setOnClickListener(v -> {
            mPager.setCurrentItem((mPager.getCurrentItem() - 1) % Configs.NUM_PAGES);
        });

        mButtonNext.setOnClickListener(v -> {
            if (mPager.getCurrentItem() == Configs.NUM_PAGES - 1) {
                startMainActivity();
                return;
            }

            mPager.setCurrentItem((mPager.getCurrentItem() + 1) % Configs.NUM_PAGES);
        });

    }

    /**
     * Update the first_run boolean in the shared preferences
     * Launch the MainActivity
     */
    private void startMainActivity() {
        SharedPreferences preferences = getSharedPreferences(Configs.APP_IDENTIFICATION, MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(Configs.PREFERENCE_FIRST_RUN, false);
        editor.apply();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
