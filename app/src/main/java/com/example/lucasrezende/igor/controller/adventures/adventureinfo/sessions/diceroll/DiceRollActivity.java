package com.example.lucasrezende.igor.controller.adventures.adventureinfo.sessions.diceroll;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.lucasrezende.igor.R;
import com.example.lucasrezende.igor.controller.adventures.adventureinfo.sessions.NewSessionActivity;
import com.example.lucasrezende.igor.controller.adventures.adventureinfo.sessions.SessionsAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class DiceRollActivity extends AppCompatActivity {
    ImageButton newSessionButton;
    TextView rollResultView;
    Random rng = new Random();
    RecyclerView recyclerView;
    ArrayList<Dice> dices;
    DicesAdapter dicesAdapter;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_empty, menu);
        return true;
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
        setContentView(R.layout.activity_dice_roll);

//        rollResultView = findViewById(R.id.tv_dice_roll_result);

        newSessionButton = findViewById(R.id.bt_dice_roller_roll);
        newSessionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), RollResultsActivity.class);
                startActivity(intent);
                int result = rng.nextInt(6) + 1;
//                rollResultView.setText("" + result);
//                rollResultView.setVisibility(View.VISIBLE);
            }
        });
        setUpDicesList();
        setUpList();
    }

    private void setUpDicesList(){
        dices = new ArrayList<>();
        Dice dice = new Dice("d4",0,4,0,true,0);
        dices.add(dice);
        dice = new Dice("d6",0,6,0,true,0);
        dices.add(dice);
        dice = new Dice("d8",0,8,0,true,0);
        dices.add(dice);
        dice = new Dice("d100",0,100,0,true,0);
        dices.add(dice);
        dice = new Dice("d12",0,12,0,true,0);
        dices.add(dice);
        dice = new Dice("d20",0,20,0,true,0);
        dices.add(dice);
    }

    private void setUpList(){
        dicesAdapter = new DicesAdapter(getApplicationContext(), dices, onDiceClickListener(),
                onAddClickListener(),onRemoveClickListener(),onPositiveClickListener(),onNegativeClickListener());


        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(dicesAdapter);
    }

    private DicesAdapter.OnDiceClickListener onDiceClickListener(){
        return new DicesAdapter.OnDiceClickListener(){
            @Override
            public void onClickDice(View view,final int idx) {
            }
        };
    }

    private DicesAdapter.OnAddClickListener onAddClickListener(){
        return new DicesAdapter.OnAddClickListener(){
            @Override
            public void onClickAdd(View view, int idx) {
                if(dices.get(idx).getNumberOfDices() > 0) {
                    dices.get(idx).setNumberOfDices(dices.get(idx).getNumberOfDices() + 1);
                    dicesAdapter.notifyDataSetChanged();
                }

            }
        };
    }


    private DicesAdapter.OnRemoveClickListener onRemoveClickListener(){
        return new DicesAdapter.OnRemoveClickListener(){
            @Override
            public void onClickRemove(View view, int idx) {
                if(dices.get(idx).getNumberOfDices() > 0) {
                    dices.get(idx).setNumberOfDices(dices.get(idx).getNumberOfDices() - 1);
                    dicesAdapter.notifyDataSetChanged();
                }
            }
        };
    }


    private DicesAdapter.OnPositiveClickListener onPositiveClickListener(){
        return new DicesAdapter.OnPositiveClickListener(){
            @Override
            public void onClickPositive(View view, int idx) {
                if(dices.get(idx).getModifier() > 0) {
                    dices.get(idx).setModifier(dices.get(idx).getModifier() + 1);
                    dicesAdapter.notifyDataSetChanged();
                }
            }
        };
    }

    private DicesAdapter.OnNegativeClickListener onNegativeClickListener(){
        return new DicesAdapter.OnNegativeClickListener(){
            @Override
            public void onClickNegative(View view, int idx) {
                if(dices.get(idx).getModifier() > 0) {
                    dices.get(idx).setModifier(dices.get(idx).getModifier() - 1);
                    dicesAdapter.notifyDataSetChanged();
                }
            }
        };
    }

}
