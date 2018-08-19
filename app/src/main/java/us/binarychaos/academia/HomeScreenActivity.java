package us.binarychaos.academia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

public class HomeScreenActivity extends AppCompatActivity {

    private ImageButton headToClassButton;
    private ImageButton storeButton;
    private ImageButton settingsButton;
    private ImageButton achievementsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home_screen);

        // locate Head to Classroom Button
        headToClassButton = findViewById(R.id.HeadClassButton);

        // capture button clicks
        headToClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Head to Classroom Activity
                Intent myIntent = new Intent(HomeScreenActivity.this, ClassroomInitialActivity.class);
                startActivity(myIntent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        // locate Store Button
        storeButton = findViewById(R.id.storeButton);

        // capture button clicks
        storeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Head to Classroom Activity
                Intent myIntentStore = new Intent(HomeScreenActivity.this, Store.class);
                startActivity(myIntentStore);
            }
        });

        // locate Settings Button
        settingsButton = findViewById(R.id.settingsButton);

        // capture button clicks
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Head to Classroom Activity
                Intent mySettingsIntent = new Intent(HomeScreenActivity.this, Settings.class);
                startActivity(mySettingsIntent);
            }
        });

        // locate Achievements Button
        achievementsButton = findViewById(R.id.achievementsButton);

        // capture button clicks
        achievementsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Head to Classroom Activity
                Intent myAchievementsIntent = new Intent(HomeScreenActivity.this, Achievements.class);
                startActivity(myAchievementsIntent);
            }
        });
    }
}
