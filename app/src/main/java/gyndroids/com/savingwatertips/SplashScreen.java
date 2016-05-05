package gyndroids.com.savingwatertips;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import gyndroids.com.savingwatertips.utils.Configs;

public class SplashScreen extends AppCompatActivity {

    private SharedPreferences sharedPreferences = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getSharedPreferences(Configs.APP_IDENTIFICATION, MODE_PRIVATE);
    }

    @Override
    protected void onResume() {
        super.onResume();

        /**
         * Create a preference to check if it's the first run of the application
         */
        boolean firstRun = sharedPreferences.getBoolean(Configs.PREFERENCE_FIRST_RUN, true);

        Intent mIntent;

        //just to test if this part of the code is working.
        if (Configs.isTesting)
            firstRun = true;

        /**
         * check if its the first time the program is running.
         * if is, launch IntroductionActivity
         * if isn't launch MainActivity
         */
        if (firstRun)
            mIntent = new Intent(this, IntroductionActivity.class);
        else
            mIntent = new Intent(this, MainActivity.class);

        startActivity(mIntent);
        finish();
    }
}
