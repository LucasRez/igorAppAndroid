package com.example.lucasrezende.igor.controller.adventures.adventureinfo.sessions.diceroll;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lucasrezende.igor.R;

import java.util.Random;

public class RollResultsActivity extends AppCompatActivity {
    int result;

    public static final Random RANDOM = new Random();
    private Button rollDices;
    private ImageView imageView1, imageView2;
    private TextView tv_roll_result_value;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_empty, menu);
        return true;
    }

    private void setUpToolbar(){
        //creating toolbar
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar_geral);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roll_results);
        setUpToolbar();

        result = getIntent().getExtras().getInt("result");

        imageView1 = (ImageView) findViewById(R.id.imageView1);
        imageView2 = (ImageView) findViewById(R.id.imageView2);

        tv_roll_result_value = findViewById(R.id.tv_roll_result_value);
        tv_roll_result_value.setVisibility(View.GONE);
        tv_roll_result_value.setText(Integer.toString(result));
        animate();
    }

    private void animate(){
        final Animation anim1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
        final Animation anim2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
        final Animation.AnimationListener animationListener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
//                int value = randomDiceValue();
//                int res = getResources().getIdentifier("dice_" + value, "drawable", "com.ssaurel.dicer");
//
//                if (animation == anim1) {
//                    imageView1.setImageResource(res);
//                } else if (animation == anim2) {
//                    imageView2.setImageResource(res);
//                }
                tv_roll_result_value.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };

        anim1.setAnimationListener(animationListener);
        anim2.setAnimationListener(animationListener);

        imageView1.startAnimation(anim1);
        imageView2.startAnimation(anim2);
    }


    public static int randomDiceValue() {
        return RANDOM.nextInt(6) + 1;
    }
}
