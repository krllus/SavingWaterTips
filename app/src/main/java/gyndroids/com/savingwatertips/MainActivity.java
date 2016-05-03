package gyndroids.com.savingwatertips;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import gyndroids.com.savingwatertips.interfaces.InterfaceGridItemSelected;
import gyndroids.com.savingwatertips.interfaces.InterfaceToolbar;
import gyndroids.com.savingwatertips.ui.fragments.FragmentCronometer;
import gyndroids.com.savingwatertips.ui.fragments.FragmentMain;

public class MainActivity extends AppCompatActivity implements InterfaceToolbar, InterfaceGridItemSelected {

    private static String FRAGMENT_TAG_STRING = "com.gyndroids.savingwater.FRAGMENT_TAG";

    // refer to the app components
    // navigation drawer, toolbar and so on.
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationDrawer;
    private ActionBarDrawerToggle mDrawerToogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpToolbar();

        // set up the drawer layout
        mDrawerLayout = (DrawerLayout) findViewById(R.id.main_drawer_layout);
        mDrawerToogle = setUpDrawerToggle();
        mDrawerLayout.setDrawerListener(mDrawerToogle);

        setUpNavigationDrawer();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mDrawerToogle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToogle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToogle.onConfigurationChanged(newConfig);
    }

    @Override
    public void setMainActivityToolBar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
    }

    private void setUpToolbar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mToolbar);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setUpNavigationDrawer() {
        mNavigationDrawer = (NavigationView) findViewById(R.id.main_nav_view);
        if (mNavigationDrawer != null)
            setUpDrawerContent();
    }

    private ActionBarDrawerToggle setUpDrawerToggle() {
        return new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close);
    }

    private void setUpDrawerContent() {
        mNavigationDrawer.setNavigationItemSelectedListener(
                menuItem -> {
                    selectDrawerItem(menuItem);
                    return true;
                });

        // select Default Item
        mNavigationDrawer.getMenu().performIdentifierAction(R.id.drawer_grid_tips, 0);

        // http://stackoverflow.com/questions/33194594/navigationview-get-find-header-layout/33194816
        View mHeaderView = mNavigationDrawer.inflateHeaderView(R.layout.drawer_header);
        //mHeaderView = mNavigationDrawer.getHeaderView(0);
    }

    private void selectDrawerItem(MenuItem menuItem) {

        Class fragmentClass;
        switch (menuItem.getItemId()) {
            case R.id.drawer_grid_tips:
                fragmentClass = FragmentMain.class;
                break;

            case R.id.drawer_cronometer:
                fragmentClass = FragmentCronometer.class;
                break;
            // configs
            case R.id.drawer_settings:
                fragmentClass = FragmentCronometer.class;
                break;
            // default
            default:
                fragmentClass = FragmentMain.class;
                break;
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = null;

        try {
            setTitle(menuItem.getTitle());
            fragment = (Fragment) fragmentClass.newInstance();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment, FRAGMENT_TAG_STRING)
                .commit();

        // Highlight the selected item, update the title, and close the drawer
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        mDrawerLayout.closeDrawers();

    }


    @Override
    public void onGridItemSelected(int position, View sharedView) {
        Intent intent = new Intent(this, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(DetailActivity.PARAM_POSITION, position);
        intent.putExtras(bundle);

        Log.d("MainActivity", sharedView.getClass().toString());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            bundle = ActivityOptions
                    .makeSceneTransitionAnimation(
                            this /*,
                            sharedView,
                            sharedView.getTransitionName()*/)
                    .toBundle();
        }
        startActivity(intent, bundle);
    }
}
