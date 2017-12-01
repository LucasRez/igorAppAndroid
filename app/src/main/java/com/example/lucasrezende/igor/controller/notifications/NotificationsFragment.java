package com.example.lucasrezende.igor.controller.notifications;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.lucasrezende.igor.R;
import com.example.lucasrezende.igor.Utils.Constants;
import com.example.lucasrezende.igor.api.AdventureServiceImplentation;
import com.example.lucasrezende.igor.controller.adventures.AdventuresAdapter;
import com.example.lucasrezende.igor.controller.adventures.adventureinfo.AdventureInfoActivity;
import com.example.lucasrezende.igor.model.Adventure;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vzaffalon on 13/09/17.
 */

public class NotificationsFragment extends Fragment {
    List<Adventure> adventures = new ArrayList<>();
    RecyclerView recyclerView;
    AdventureServiceImplentation client;
    View view;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_notifications, container, false);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getAdventuresList();
    }



    private AdventuresAdapter.OnClickListener onClickEvento(){
        return new AdventuresAdapter.OnClickListener(){
            @Override
            public void onClickEvento(View view, int idx) {
                //aqui entra quando um dos elementos da lista é selecionado
                Intent intent = new Intent(getContext(), AdventureInfoActivity.class);
                intent.putExtra("title", adventures.get(idx).getName());
                intent.putExtra("description", adventures.get(idx).getDescription());
                intent.putExtra("adventure_id", adventures.get(idx).getId());
                startActivity(intent);
            }
        };
    }


    private AdventuresAdapter.OnClickListenerDeleteButton onClickDeleteButton(){
        return new AdventuresAdapter.OnClickListenerDeleteButton(){
            @Override
            public void onClickDeleteButton(View view,final int idx) {
                new MaterialDialog.Builder(getContext())
                        .title("Deseja deletar aventura?")
                        .content("Todos os dados relacionados com essa aventura também serão deletados.")
                        .positiveText("Sim")
                        .onNegative(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                            }
                        })
                        .negativeText("Não")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                deleteAdventure(idx);
                            }
                        })
                        .show();
            }
        };
    }

    private void deleteAdventure(int idx){
        client = new AdventureServiceImplentation(getContext());
        Call<Adventure> call = client.getService().delete(adventures.get(idx).getId());

        // Execute the call asynchronously. Get a positive or negative callback.
        call.enqueue(new Callback<Adventure>() {
            @Override
            public void onResponse(Call<Adventure> call, Response<Adventure> response) {
                // The network call was a success and we got a response
                // TODO: use the repository list and display it
                if(response.isSuccessful()) {
                    Toast.makeText(getContext(),"Aventura deletada com sucesso",Toast.LENGTH_SHORT).show();
                    getAdventuresList();
                }else{
                    Toast.makeText(getContext(),"Falha ao deletar a aventura",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Adventure> call, Throwable t) {
                // the network call was a failure
                // TODO: handle error
                Log.d("Error", t.getMessage());
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getUserIdFromSharedPrefrences(){
        SharedPreferences sharedPref = getContext().getSharedPreferences(Constants.SharedPreferences, Context.MODE_PRIVATE);
        return sharedPref.getString("user_id", "");
    }

    private void getAdventuresList(){
        client = new AdventureServiceImplentation(getContext());
        // Fetch a list of the Github repositoriesteste.
        Call<List<Adventure>> call = client.getService().list_user(getUserIdFromSharedPrefrences());

        // Execute the call asynchronously. Get a positive or negative callback.
        call.enqueue(new Callback<List<Adventure>>() {
            @Override
            public void onResponse(Call<List<Adventure>> call, Response<List<Adventure>> response) {
                // The network call was a success and we got a response
                // TODO: use the repository list and display it
                if(response.isSuccessful()) {
                    //Toast.makeText(getContext(),"Lista obtida com sucesso",Toast.LENGTH_SHORT).show();
                    adventures = response.body();
                    setUpList();
                }else{
                    Toast.makeText(getContext(),"Falha na obtenção da lista",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<Adventure>> call, Throwable t) {
                // the network call was a failure
                // TODO: handle error
                Log.d("Error", t.getMessage());
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void setUpList(){
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new AdventuresAdapter(getContext(), adventures, onClickEvento(),onClickDeleteButton()));
    }
}
