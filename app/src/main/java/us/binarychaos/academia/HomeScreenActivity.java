package us.binarychaos.academia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;

public class HomeScreenActivity extends AppCompatActivity {

    ImageButton headToClassButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home_screen);

        // locate Head to Classroom Button
        headToClassButton = (ImageButton) findViewById(R.id.HeadClassButton);

        // capture button clicks
        headToClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Head to Classroom Activity
                Intent myIntent = new Intent(HomeScreenActivity.this, ClassroomInitialActivity.class);
                startActivity(myIntent);
            }
        });
    }
}
