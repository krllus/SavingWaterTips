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
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import gyndroids.com.savingwatertips.interfaces.InterfaceGridItemSelected;
import gyndroids.com.savingwatertips.ui.fragments.FragmentMain;

public class MainActivity extends AppCompatActivity implements InterfaceGridItemSelected {

    private static String FRAGMENT_TAG_STRING = "com.gyndroids.savingwater.FRAGMENT_TAG";

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationDrawer;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpToolbar();

        // set up the drawer layout
        mDrawerLayout = (DrawerLayout) findViewById(R.id.main_drawer_layout);
        mDrawerToggle = setUpDrawerToggle();
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        setUpNavigationDrawer();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);

    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    /**
     * Implementation of the InterfaceGridItemSelected
     * create a intent, add some arguments, the position of the item selected, launch the intent
     * if the version of the OS running is greater then API 21(Lollipop), add a custom animation
     * between the views.
     *
     * @param position requested position
     */
    @Override
    public void onGridItemSelected(int position) {
        Intent intent = new Intent(this, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(DetailActivity.PARAM_POSITION, position);
        intent.putExtras(bundle);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            ImageView sharedView = (ImageView) findViewById(R.id.gridview_item_picture);
            String transitionName = getResources().getString(R.string.gridimage_transition);

            bundle = ActivityOptions
                    .makeSceneTransitionAnimation(
                            this /*,
                            sharedView,
                            transitionName*/)
                    .toBundle();
        }
        startActivity(intent, bundle);
    }

    /**
     * setup the toolbar and actionbar
     */
    private void setUpToolbar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mToolbar);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * setup the navigation drawer
     * the header of the drawer is defined in the drawer_header.xml file.
     * the content of the drawer is defined in the menu_drawer.xml file.
     */
    private void setUpNavigationDrawer() {
        mNavigationDrawer = (NavigationView) findViewById(R.id.main_nav_view);
        if (mNavigationDrawer != null)
            setUpDrawerContent();
    }

    /**
     * setup the DrawerToggle
     * make use of a helper for the hamburger menu animation and state control
     *
     * @return action bar toggle item
     */
    private ActionBarDrawerToggle setUpDrawerToggle() {
        return new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close);
    }

    /**
     * Setup the content for the navigation drawer
     * Perform a select in the first item of the navigation drawer.
     */
    private void setUpDrawerContent() {
        mNavigationDrawer.setNavigationItemSelectedListener(
                menuItem -> {
                    selectDrawerItem(menuItem);
                    return true;
                });

        // select Default Item
        mNavigationDrawer.getMenu().performIdentifierAction(R.id.drawer_main, 0);

        // http://stackoverflow.com/questions/33194594/navigationview-get-find-header-layout/33194816
        View mHeaderView = mNavigationDrawer.inflateHeaderView(R.layout.drawer_header);
        //mHeaderView = mNavigationDrawer.getHeaderView(0);
    }

    /**
     * perform an action according with the selected menu item
     *
     * @param menuItem menu item from the xml file menu_drawer
     */
    private void selectDrawerItem(MenuItem menuItem) {
        Class fragmentClass;
        switch (menuItem.getItemId()) {
            // Introduction Activity
            case R.id.drawer_motivation:
                Intent intent = new Intent(this, IntroductionActivity.class);
                startActivity(intent);
                return;

            // FragmentMain
            case R.id.drawer_main:
                fragmentClass = FragmentMain.class;
                break;

            // default - FragmentMain
            default:
                fragmentClass = FragmentMain.class;
                break;
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = null;

        // instantiate a new fragment using the factory pattern
        // if not possible, launches an error.
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
}
