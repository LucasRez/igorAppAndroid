package com.example.lucasrezende.igor.controller.adventures.adventureinfo.sessions.diceroll;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.lucasrezende.igor.R;

import java.util.Random;

public class DiceRollActivity extends AppCompatActivity {
    ImageButton newSessionButton;
    TextView rollResultView;
    Random rng = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice_roll);

        rollResultView = findViewById(R.id.tv_dice_roll_result);

        newSessionButton = findViewById(R.id.bt_dice_roller_roll);
        newSessionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), RollResultsActivity.class);
                startActivity(intent);
            }
        });
    }

}
