package com.example.lucasrezende.igor.controller.adventures.adventureinfo;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.lucasrezende.igor.R;
import com.example.lucasrezende.igor.controller.adventures.adventureinfo.players.PlayersListFragment;
import com.example.lucasrezende.igor.controller.adventures.adventureinfo.sessions.SessionsListFragment;

/**
 * Created by vzaffalon on 14/09/17.
 */

public class AdventureInfoActivity extends AppCompatActivity {

    private ImageButton close_new_adventure_button;
    private TextView adventureTitleView;
    private String title;
    private String description;
    private int adventure_id;

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
        setContentView(R.layout.activity_adventure_info);
        title = getIntent().getExtras().getString("title");
        description = getIntent().getExtras().getString("description");
        adventure_id = getIntent().getExtras().getInt("adventure_id");
        setUpToolbar();
        setUpLayout();
    }

    private void setUpLayout(){
        adventureTitleView = (TextView) findViewById(R.id.tv_adventure_info_title);
        adventureTitleView.setText(title);

        View viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager((ViewPager) viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager((ViewPager) viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        SessionsListFragment sessions = new SessionsListFragment();
        Bundle args = new Bundle();
        args.putString("description", description);
        args.putInt("adventure_id",adventure_id);
        sessions.setArguments(args);
        adapter.addFragment(sessions, "História");
        adapter.addFragment(new PlayersListFragment(), "Jogadores");
        viewPager.setAdapter(adapter);
    }

    private void setUpToolbar(){
        //creating toolbar
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar_geral);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
