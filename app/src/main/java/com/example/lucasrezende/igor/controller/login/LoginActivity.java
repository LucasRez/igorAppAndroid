package com.example.lucasrezende.igor.controller.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.lucasrezende.igor.R;
import com.example.lucasrezende.igor.Utils.Constants;
import com.example.lucasrezende.igor.api.ResponseBody;
import com.example.lucasrezende.igor.api.UserServiceImplentation;
import com.example.lucasrezende.igor.controller.NavigationActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vzaffalon on 09/09/17.
 */

public class LoginActivity extends AppCompatActivity {

    private Button login_button;
    private Button register_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setUpLayout();
        //login("teste13@gmail.com","123456123456");
    }

    private void setUpLayout(){
        login_button = (Button) findViewById(R.id.login_button);
        register_button = (Button) findViewById(R.id.register_button);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(),NavigationActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(),RegisterActivity.class);
                startActivity(intent);
            }
        });
    }


    private void goToMainActivity(){
        Intent intent = new Intent(getBaseContext(),NavigationActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


    private void login(String email,String password){
        UserServiceImplentation client = new UserServiceImplentation(getApplicationContext());
        // Fetch a list of the Github repositories.
        Call<ResponseBody> call = client.getService().logIn(email,password);

        // Execute the call asynchronously. Get a positive or negative callback.
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                // The network call was a success and we got a response
                // TODO: use the repository list and display it
                if(response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"Usuario criado com sucesso",Toast.LENGTH_SHORT).show();
                    saveUserReponseHeader(response);
                }else{
                    Toast.makeText(getApplicationContext(),"Falha na criação do usuário",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                // the network call was a failure
                // TODO: handle error
                Log.d("Error", t.getMessage());
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveUserReponseHeader(Response<ResponseBody> response) {

        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(Constants.SharedPreferences, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("access-token", response.headers().get("access-token"));
        editor.putString("client", response.headers().get("client"));
        editor.putString("expiry", response.headers().get("expiry"));
        editor.putString("uid", response.headers().get("uid"));
        editor.commit();

        goToMainActivity();
    }
}
