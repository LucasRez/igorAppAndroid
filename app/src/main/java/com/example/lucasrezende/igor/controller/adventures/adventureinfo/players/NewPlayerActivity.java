package com.example.lucasrezende.igor.controller.adventures.adventureinfo.players;

import android.content.Context;
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
import com.example.lucasrezende.igor.api.PlayerServiceImplentation;
import com.example.lucasrezende.igor.api.UserServiceImplentation;
import com.example.lucasrezende.igor.model.Adventure;
import com.example.lucasrezende.igor.model.Player;
import com.example.lucasrezende.igor.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vzaffalon on 14/09/17.
 */

public class NewPlayerActivity extends AppCompatActivity {

    private ImageButton close_new_adventure_button;
    private Player player;
    private EditText et_player_nickname;
    private EditText et_player_description,et_player_email;
    private Spinner sp_player_class;
    private int adventure_id;
    private User user;

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
        setContentView(R.layout.activity_add_player);
        adventure_id = getIntent().getExtras().getInt("adventure_id");
        setUpToolbar();
        setUpLayout();
    }

    private void setUpLayout(){
        close_new_adventure_button = (ImageButton) findViewById(R.id.close_new_adventure_button);
        close_new_adventure_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button add_player_button = (Button) findViewById(R.id.add_player_button);
        add_player_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                find_user_by_nickname();
            }
        });

        sp_player_class = (Spinner) findViewById(R.id.sp_player_class);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.player_class_types, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        sp_player_class.setAdapter(adapter);

        et_player_nickname = (EditText) findViewById(R.id.et_player_nickname);
        et_player_description = (EditText) findViewById(R.id.et_player_description);
        et_player_email = (EditText) findViewById(R.id.et_player_email);
    }

    private void setUpToolbar(){
        //creating toolbar
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar_geral);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void getPlayerData(int user_id){
        player = new Player(et_player_nickname.getText().toString(),et_player_description.getText().toString(),sp_player_class.getSelectedItem().toString(),user_id,adventure_id);
        createNewPlayer();
    }
    private void find_user_by_nickname(){
        UserServiceImplentation client = new UserServiceImplentation(getBaseContext());
        // Fetch a list of the Github repositoriesteste.
        Call<User> call = client.getService().get_user_by_nickname(et_player_email.getText().toString());

        // Execute the call asynchronously. Get a positive or negative callback.
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                // The network call was a success and we got a response
                // TODO: use the repository list and display it
                if(response.isSuccessful()) {
                    user = response.body();
                    try {
                        getPlayerData(user.getId());
                    }catch (Exception e){
                        Toast.makeText(getApplicationContext(),"Jogador com email não encontrado",Toast.LENGTH_SHORT).show();
                    };
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // the network call was a failure
                // TODO: handle error
                Log.d("Error", t.getMessage());
                Toast.makeText(getBaseContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private int getUserIdFromSharedPrefrences(){
        SharedPreferences sharedPref = getBaseContext().getSharedPreferences(Constants.SharedPreferences, Context.MODE_PRIVATE);
        return Integer.valueOf(sharedPref.getString("user_id", ""));
    }

    private void createNewPlayer(){
        PlayerServiceImplentation client = new PlayerServiceImplentation(getBaseContext());
        // Fetch a list of the Github repositoriesteste.
        Call<Player> call = client.getService().create(player);

        // Execute the call asynchronously. Get a positive or negative callback.
        call.enqueue(new Callback<Player>() {
            @Override
            public void onResponse(Call<Player> call, Response<Player> response) {
                // The network call was a success and we got a response
                // TODO: use the repository list and display it
                if(response.isSuccessful()) {
                    Toast.makeText(getBaseContext(),"Jogador adicionado com sucesso",Toast.LENGTH_SHORT).show();
                    player = response.body();
                    finish();
                }else{
                    Toast.makeText(getBaseContext(),"Falha ao adicionar jogador",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Player> call, Throwable t) {
                // the network call was a failure
                // TODO: handle error
                Log.d("Error", t.getMessage());
                Toast.makeText(getBaseContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
