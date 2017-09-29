package com.example.lucasrezende.igor.controller.adventures;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lucasrezende.igor.R;
import com.example.lucasrezende.igor.Utils.Constants;
import com.example.lucasrezende.igor.api.AdventureServiceImplentation;
import com.example.lucasrezende.igor.model.Adventure;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.security.AccessController.getContext;

/**
 * Created by vzaffalon on 14/09/17.
 */

public class NewAdventureActivity extends AppCompatActivity {

    private ImageButton close_new_adventure_button;
    Adventure adventure;
    EditText et_adventure_name;
    EditText et_description;
    EditText et_book_name;
    Spinner sp_background_image;

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
        setContentView(R.layout.activity_new_adventure);
        setUpToolbar();
        setUpLayout();
    }

    private void setUpLayout(){
        et_adventure_name = (EditText) findViewById(R.id.et_adventure_name);
        et_description = (EditText) findViewById(R.id.et_description);
        et_book_name = (EditText) findViewById(R.id.et_book_name);
        sp_background_image = (Spinner) findViewById(R.id.sp_background_image);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.adventure_background_types, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        sp_background_image.setAdapter(adapter);

        close_new_adventure_button = (ImageButton) findViewById(R.id.close_new_adventure_button);
        close_new_adventure_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button create_adventure_button = (Button) findViewById(R.id.create_adventure_button);
        create_adventure_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAdventureData();
            }
        });

    }

    private void setUpToolbar(){
        //creating toolbar
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar_geral);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void getAdventureData(){
        adventure = new Adventure(et_adventure_name.getText().toString(),sp_background_image.getSelectedItem().toString(),et_description.getText().toString(),getUserIdFromSharedPrefrences(),et_book_name.getText().toString());
        createNewAdventure();
    }

    private int getUserIdFromSharedPrefrences(){
        SharedPreferences sharedPref = getBaseContext().getSharedPreferences(Constants.SharedPreferences, Context.MODE_PRIVATE);
        return Integer.valueOf(sharedPref.getString("user_id", ""));
    }

    private void createNewAdventure(){
        AdventureServiceImplentation client = new AdventureServiceImplentation(getBaseContext());
        // Fetch a list of the Github repositoriesteste.
        Call<Adventure> call = client.getService().create(adventure);

        // Execute the call asynchronously. Get a positive or negative callback.
        call.enqueue(new Callback<Adventure>() {
            @Override
            public void onResponse(Call<Adventure> call, Response<Adventure> response) {
                // The network call was a success and we got a response
                // TODO: use the repository list and display it
                if(response.isSuccessful()) {
                    Toast.makeText(getBaseContext(),"Aventura criada com sucesso",Toast.LENGTH_SHORT).show();
                    adventure = response.body();
                    finish();
                }else{
                    Toast.makeText(getBaseContext(),"Falha na criação da aventura",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Adventure> call, Throwable t) {
                // the network call was a failure
                // TODO: handle error
                Log.d("Error", t.getMessage());
                Toast.makeText(getBaseContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
