package com.example.lucasrezende.igor.controller;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.lucasrezende.igor.R;
import com.example.lucasrezende.igor.controller.adventures.AdventureListFragment;
import com.example.lucasrezende.igor.controller.books.BooksFragment;
import com.example.lucasrezende.igor.controller.configurations.ConfigurationsFragment;
import com.example.lucasrezende.igor.controller.notifications.NotificationsFragment;
import com.example.lucasrezende.igor.controller.useraccount.UserAccountFragment;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class NavigationActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        setUpNavigationDrawer();

    }

    @Override
    public void onBackPressed() {
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

    private Toolbar setUpToolbar(){
        //creating toolbar
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar_geral);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return mToolbar;
    }

    private void setUpNavigationDrawer(){
        //if you want to update the items at a later time it is recommended to keep it in a variable
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Aventuras");
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName("Livros");
        PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(3).withName("Contas");
        PrimaryDrawerItem item4 = new PrimaryDrawerItem().withIdentifier(4).withName("Notificações");
        PrimaryDrawerItem item5 = new PrimaryDrawerItem().withIdentifier(5).withName("Configurações");

        //create the drawer and remember the `Drawer` result object
        final Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(setUpToolbar())
                .addDrawerItems(
                        item1,
                        item2,
                        item3,
                        item4,
                        item5
                )
                .build();

        result.setOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                // do something with the clicked item :D
                switch (position){
                    case 0:
                        changeFragment(new AdventureListFragment());
                        result.closeDrawer();
                        break;
                    case 1:
                        changeFragment(new BooksFragment());
                        result.closeDrawer();
                        break;
                    case 2:
                        changeFragment(new UserAccountFragment());
                        result.closeDrawer();
                        break;
                    case 3:
                        changeFragment(new NotificationsFragment());
                        result.closeDrawer();
                        break;
                    case 4:
                        changeFragment(new ConfigurationsFragment());
                        result.closeDrawer();
                        break;

                }
                return true;
            }
        });

        result.setSelection(1,true);

    }


    private void changeFragment(Fragment newFragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
