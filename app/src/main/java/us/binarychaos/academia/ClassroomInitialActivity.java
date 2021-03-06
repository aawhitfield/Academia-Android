package us.binarychaos.academia;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AbsoluteLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Objects;
import java.util.Random;

public class ClassroomInitialActivity extends AppCompatActivity {

    TextView timeLeft;
    ImageView healthBar;

    int minutes = 3;
    int seconds = 1;
    int timeInMiliseconds = (minutes * 60 + seconds + 2) * 1000;
    int maxHealthBarWidth = 324;
    int minHealthBarWidth = 0;
    double lifePercentage = 1.0;
    double green = 120.0;
    double healthHue = 120.0;

    // flags
    boolean lightBulbOnFlag = false;

    public void increamentHealthFunction()
    {
        int tempWidth = healthBar.getLayoutParams().width;
        tempWidth += 20;
        if(tempWidth > maxHealthBarWidth)
            tempWidth = maxHealthBarWidth;
        healthBar.getLayoutParams().width = tempWidth;

        updateHealthBarColor();
    }

    public void decrementHealthFunction()
    {
        int tempWidth = healthBar.getLayoutParams().width;
        tempWidth -= 20;
        if(tempWidth < minHealthBarWidth)
            tempWidth = minHealthBarWidth;
        healthBar.getLayoutParams().width = tempWidth;

        updateHealthBarColor();
    }

    public void displayQuestion()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.question)
                .setItems(R.array.choices_array, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        /* 2 button box
        builder.setMessage(R.string.question)
                .setPositiveButton(R.string.positive_choice, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // increment health
                        // incrementHealth();
                        increamentHealthFunction();
                    }
                })
                .setNegativeButton(R.string.negative_choice, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // decrement health
                        decrementHealthFunction();
                    }

                });
        */
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    public void turnLightBulbOff()
    {
        ImageView imageLight1 = findViewById(R.id.imageLight1);

        if(lightBulbOnFlag == true)
        {
            imageLight1.setImageResource(R.drawable.lightbulb_off);
            lightBulbOnFlag = false;
            displayQuestion();
        }
    }

    public void turnLightBulbOn()
    {
        ImageView imageLight1 = findViewById(R.id.imageLight1);

        if(!lightBulbOnFlag)
        {
            imageLight1.setImageResource(R.drawable.lightbulb_on);
            lightBulbOnFlag = true;
        }
    }

    public void seeIfStudentHasAQuestion()
    {
       int min = 1;
       int max = 10;
       Random r = new Random();
       int randNum = r.nextInt(max + 1);

       if(randNum >= 8)
       {
           turnLightBulbOn();
       }
    }

    public void studentSelected(View v)
    {
        turnLightBulbOff();
    }
    public void backButton(View v)
    {
        // back button to return home
        Intent backHomeIntent = new Intent(ClassroomInitialActivity.this, HomeScreenActivity.class);
        startActivity(backHomeIntent);
    }

    // create count down timer to represent amount of class left in the period
    final CountDownTimer countDownTimer = new CountDownTimer( timeInMiliseconds, 1000)
    {
        @Override
        public void onTick(long millisUntilFinished) {
            if(minutes >= 0)    // timer will run while time is still on the clock
            {
                if(seconds == 0)// sets when to reset seconds at the start of next minute
                {
                    minutes--;  // decrements minutes when switches to next minute
                    seconds = 59;   // resets seconds at the starts of the next minute
                }
                else
                {
                    seconds--;  // decrements timer by one
                }

                if(seconds >= 10)
                {
                    timeLeft.setText(Integer.toString(minutes) + ":" + Integer.toString(seconds));

                    seeIfStudentHasAQuestion();
                }
                else
                {
                    timeLeft.setText(Integer.toString(minutes) + ":0" + Integer.toString(seconds));
                    seeIfStudentHasAQuestion();
                    if(seconds == 0)
                    {
                        minutes--;
                        seconds = 60;
                    }
                }
            }
            else
            {

            }
        }

        @Override
        public void onFinish()
        {
            timeLeft.setText(R.string.time_over);
            countDownTimer.cancel();

            // 1. Instantiate an AlertDialog.Builder with its constructor
            AlertDialog.Builder builder = new AlertDialog.Builder(ClassroomInitialActivity.this);

            // 2. Chain together various setter methods to set the dialog characteristics
            builder.setMessage(R.string.text_end_of_class)
                    .setTitle(R.string.text_bell);

            // Add the buttons
            builder.setPositiveButton(R.string.button_ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User clicked OK button
                    // Return home to end game
                    Intent myIntent = new Intent(ClassroomInitialActivity.this, HomeScreenActivity.class);
                    startActivity(myIntent);
                }
            });

            // 3. Get the AlertDialog from create()
            AlertDialog dialog = builder.create();
            dialog.show();


        }
    };

    @SuppressLint("Range")
    public void updateHealthBarColor()
    {
        float lifePercentage = (float) healthBar.getLayoutParams().width / (float) maxHealthBarWidth;
        float healthHue = (float) (lifePercentage * green);
        // change health bar color
        float[] colorFloats = new float[]{healthHue,1,1};
        Log.i("width",String.valueOf(healthBar.getLayoutParams().width));
        Log.i("maxHealthBarWidth", String.valueOf((maxHealthBarWidth)));
        Log.i("lifePercentage",String.valueOf(lifePercentage));
        Log.i("green",String.valueOf(green));
        Log.i("healthHue",String.valueOf(healthHue));
        healthBar.setColorFilter(Color.HSVToColor(colorFloats));
    }

    public void incrementHealth(View v)
    {
        increamentHealthFunction();

    }

    public void decrementHealth(View v)
    {
        decrementHealthFunction();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_classroom_initial);

/*
        // create back button in action bar
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
*/

        // initialize screen objects from Design Layout View
        timeLeft = findViewById(R.id.timeLeft);
        healthBar = findViewById(R.id.healthBar);

        //start count down timer as soon as View is created
        countDownTimer.start();

        // adjust size of health bar
        healthBar.getLayoutParams().width = maxHealthBarWidth;

    }
    // overrides transitions so activity goes back in reverse with the back button and app bar back button
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
        {
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            return true;
        }
        return false;
    }
}
