package com.example.lucasrezende.igor.controller.books;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lucasrezende.igor.R;
import com.example.lucasrezende.igor.controller.adventures.adventureinfo.ViewPagerAdapter;
import com.example.lucasrezende.igor.controller.adventures.adventureinfo.players.PlayersListFragment;
import com.example.lucasrezende.igor.controller.adventures.adventureinfo.sessions.SessionsListFragment;

/**
 * Created by vzaffalon on 14/09/17.
 */

public class BookInfoActivity extends AppCompatActivity {

    private ImageButton close_new_adventure_button;
    private TextView adventureTitleView;
    private String title;
    private String description;
    private int adventure_id;
    ViewPagerAdapter adapter;
    RelativeLayout relativeLayout;

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
        setContentView(R.layout.activity_book_info);
        LinearLayout background = (LinearLayout) findViewById(R.id.background);
        title = getIntent().getExtras().getString("title");
        description = getIntent().getExtras().getString("description");
        adventure_id = getIntent().getExtras().getInt("adventure_id");
        setUpToolbar();
        setUpLayout();
    }

    private void setUpLayout(){
        adventureTitleView = (TextView) findViewById(R.id.tv_adventure_info_title);
        adventureTitleView.setText(title);

        final View viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager((ViewPager) viewPager);

        TextView first_tab = (TextView) findViewById(R.id.first_tab);
        TextView second_tab = (TextView) findViewById(R.id.second_tab);

        first_tab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeLayout.setBackgroundResource(R.drawable.aba_de_conteudo);
                ((ViewPager) viewPager).setCurrentItem(0);
            }
        });

        second_tab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeLayout.setBackgroundResource(R.drawable.caixa_de_conteudo);
                ((ViewPager) viewPager).setCurrentItem(1);
            }
        });

        relativeLayout = (RelativeLayout) findViewById(R.id.tabs);

    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        BooksResumeFragment sessions = new BooksResumeFragment();
        Bundle args = new Bundle();
        args.putString("description", description);
        args.putInt("adventure_id",adventure_id);
        sessions.setArguments(args);
        BookSectionslistFragment playersListFragment = new BookSectionslistFragment();
        args = new Bundle();
        args.putString("description", description);
        args.putInt("adventure_id",adventure_id);
        playersListFragment.setArguments(args);
        adapter.addFragment(playersListFragment, "Resumo");
        adapter.addFragment(sessions, "Sumário");
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
