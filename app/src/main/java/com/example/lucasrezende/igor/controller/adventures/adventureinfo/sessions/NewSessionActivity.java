package com.example.lucasrezende.igor.controller.adventures.adventureinfo.sessions;

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

import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment;
import com.codetroopers.betterpickers.datepicker.DatePickerBuilder;
import com.example.lucasrezende.igor.R;
import com.example.lucasrezende.igor.Utils.Constants;
import com.example.lucasrezende.igor.api.AdventureServiceImplentation;
import com.example.lucasrezende.igor.api.SessionServiceImplentation;
import com.example.lucasrezende.igor.model.Adventure;
import com.example.lucasrezende.igor.model.Session;

import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vzaffalon on 14/09/17.
 */

public class NewSessionActivity extends AppCompatActivity {

    private ImageButton close_new_session_button;
    private Session session;
    EditText et_session_name;
    EditText et_session_description;
    Button session_date_button;
    Date session_date;
    int adventure_id;

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
        setContentView(R.layout.activity_add_session);
        adventure_id = getIntent().getExtras().getInt("adventure_id");
        setUpToolbar();
        setUpLayout();
    }


    private void setUpLayout(){
        close_new_session_button = (ImageButton) findViewById(R.id.close_new_session_button);
        close_new_session_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        et_session_name = (EditText) findViewById(R.id.et_session_name);
        et_session_description = (EditText) findViewById(R.id.et_session_description);
        session_date_button = (Button) findViewById(R.id.chose_date_button);


        Button create_adventure_button = (Button) findViewById(R.id.create_session_button);
        create_adventure_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSessionData();
            }
        });

        session_date_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalendarDatePickerDialogFragment cdp = new CalendarDatePickerDialogFragment()
                        .setOnDateSetListener(new CalendarDatePickerDialogFragment.OnDateSetListener() {
                            @Override
                            public void onDateSet(CalendarDatePickerDialogFragment dialog, int year, int monthOfYear, int dayOfMonth) {
                                session_date = new Date(dayOfMonth,monthOfYear,year);
                                session_date_button.setText(dayOfMonth+"/"+ monthOfYear);
                            }
                        })
                        .setFirstDayOfWeek(Calendar.SUNDAY)
                        .setDoneText("Confirmar")
                        .setCancelText("Cancelar");
                cdp.show(getSupportFragmentManager(), "frag_date_picker");

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

    private void getSessionData(){
        session = new Session(et_session_name.getText().toString(),et_session_description.getText().toString(),session_date,adventure_id);
        createNewSession();
    }

    private int getUserIdFromSharedPrefrences(){
        SharedPreferences sharedPref = getBaseContext().getSharedPreferences(Constants.SharedPreferences, Context.MODE_PRIVATE);
        return Integer.valueOf(sharedPref.getString("user_id", ""));
    }

    private void createNewSession(){
        SessionServiceImplentation client = new SessionServiceImplentation(getBaseContext());
        // Fetch a list of the Github repositoriesteste.
        Call<Session> call = client.getService().create(session);

        // Execute the call asynchronously. Get a positive or negative callback.
        call.enqueue(new Callback<Session>() {
            @Override
            public void onResponse(Call<Session> call, Response<Session> response) {
                // The network call was a success and we got a response
                // TODO: use the repository list and display it
                if(response.isSuccessful()) {
                    Toast.makeText(getBaseContext(),"Sessão criada com sucesso",Toast.LENGTH_SHORT).show();
                    session = response.body();
                    finish();
                }else{
                    Toast.makeText(getBaseContext(),"Falha na criação da sessão",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Session> call, Throwable t) {
                // the network call was a failure
                // TODO: handle error
                Log.d("Error", t.getMessage());
                Toast.makeText(getBaseContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
