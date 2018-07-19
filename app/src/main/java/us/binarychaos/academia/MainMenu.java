package us.binarychaos.academia;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import static java.security.AccessController.getContext;

public class MainMenu extends AppCompatActivity {
    // set time length that splash screen will show for in miliseconds
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // I think this is unneeded because of the next 3 blocks of code to remove title block
        // setContentView(R.layout.activity_main_menu);

        // Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // set content view AFTER ABOVE sequence (to avoid crash)
        this.setContentView(R.layout.activity_main_menu);

        // creates a new android Handler by adding a runnable to the thread and delays it by the int above
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // change the activity to the home menu screen by creating an intent and starting it
                Intent homeIntent = new Intent(MainMenu.this, HomeScreenActivity.class);

                startActivity(homeIntent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        },SPLASH_TIME_OUT); // delays starting the activity by the time out amount defined
    }
}
