package com.example.lucasrezende.igor.controller.adventures.adventureinfo.sessions.diceroll;

import android.content.Intent;
import android.os.Bundle;
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

import com.example.lucasrezende.igor.R;
import com.example.lucasrezende.igor.controller.adventures.AdventuresAdapter;
import com.example.lucasrezende.igor.model.DiceResult;

import java.util.ArrayList;
import java.util.Random;

public class MenuRollListActivity extends AppCompatActivity {
    ImageButton newSessionButton;
    TextView rollResultView;
    Random rng = new Random();
    RecyclerView recyclerView;
    ArrayList<DiceResult> dices;

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
        if (id == R.menu.menu_roll_list) {
            Intent intent = new Intent(getApplicationContext(),MenuRollListActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_roll_list);
        setUpToolbar();
        setUpList();
    }

    private void setUpList(){
        dices = new ArrayList<>();
        DiceResult diceResult = new DiceResult("vzaffalon tirou 5 nos dados");
        dices.add(diceResult);
        diceResult = new DiceResult("vzaffalon tirou 14 no dados");
        dices.add(diceResult);
        diceResult = new DiceResult("lucasresende tirou 24 no dados");
        dices.add(diceResult);
        diceResult = new DiceResult("vzaffalon tirou 15 no dados");
        dices.add(diceResult);
        diceResult = new DiceResult("lucasresende tirou 10 no dados");
        dices.add(diceResult);
        diceResult = new DiceResult("bruno tirou 4 no dados");
        dices.add(diceResult);
        diceResult = new DiceResult("vzaffalon tirou 19 no dados");
        dices.add(diceResult);
        diceResult = new DiceResult("bruno tirou 12 no dados");
        dices.add(diceResult);
        diceResult = new DiceResult("vzaffalon tirou 55 no dados");
        dices.add(diceResult);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new DiceRollAdapter(getApplicationContext(), dices));
    }
}
