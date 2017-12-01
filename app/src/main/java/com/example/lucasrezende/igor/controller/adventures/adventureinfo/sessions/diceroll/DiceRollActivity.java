package com.example.lucasrezende.igor.controller.adventures.adventureinfo.sessions.diceroll;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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
        getMenuInflater().inflate(R.menu.menu_roll_list, menu);
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
        if (id == R.id.roll_list_item) {
            Intent intent = new Intent(getApplicationContext(),MenuRollListActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice_roll);
        setUpToolbar();

//        rollResultView = findViewById(R.id.tv_dice_roll_result);

        newSessionButton = findViewById(R.id.bt_dice_roller_roll);
        newSessionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int finalResult = 0;
                for(int j=0;j<dices.size();j++){

                    int result = 0;
                    for(int i=0;i<dices.get(j).getNumberOfDices();i++){
                        result = result + rng.nextInt(dices.get(j).getValue()) + 1;
                    }


                    result  = result + dices.get(j).getModifier();
                    finalResult = finalResult + result;
                }
                Intent intent = new Intent(getBaseContext(), RollResultsActivity.class);
                intent.putExtra("result",finalResult);
                startActivity(intent);
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
                int result = 0;
                for(int i=0;i<dices.get(idx).getNumberOfDices();i++){
                    result = result + rng.nextInt(dices.get(idx).getValue()) + 1;
                }


                result  = result + dices.get(idx).getModifier();
                dices.get(idx).setResult(result);
                dicesAdapter.notifyDataSetChanged();
            }
        };
    }

    private DicesAdapter.OnAddClickListener onAddClickListener(){
        return new DicesAdapter.OnAddClickListener(){
            @Override
            public void onClickAdd(View view, int idx) {
                dices.get(idx).setNumberOfDices(dices.get(idx).getNumberOfDices() + 1);
                dicesAdapter.notifyDataSetChanged();

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
                    dices.get(idx).setModifier(dices.get(idx).getModifier() + 1);
                    dicesAdapter.notifyDataSetChanged();
            }
        };
    }

    private DicesAdapter.OnNegativeClickListener onNegativeClickListener(){
        return new DicesAdapter.OnNegativeClickListener(){
            @Override
            public void onClickNegative(View view, int idx) {
                    dices.get(idx).setModifier(dices.get(idx).getModifier() - 1);
                    dicesAdapter.notifyDataSetChanged();
            }
        };
    }

}
