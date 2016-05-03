package gyndroids.com.savingwatertips;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

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

        boolean firstRun = sharedPreferences.getBoolean(Configs.PREFERENCE_FIRST_RUN, true);

        if (firstRun) {

            Log.w("Splash Screen", "First Run!");

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(Configs.PREFERENCE_FIRST_RUN, false);
            editor.apply();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();

        } else {
            Log.w("Splash Screen", "Not the first Run!");

            /* TODO pass an argument to the MainActivity, indicating to open the "MotivationScreen".
             */
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
