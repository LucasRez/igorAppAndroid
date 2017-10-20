package com.example.lucasrezende.igor.controller.adventures.adventureinfo.players;

import android.content.Intent;
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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.lucasrezende.igor.R;
import com.example.lucasrezende.igor.api.PlayerServiceImplentation;
import com.example.lucasrezende.igor.api.SessionServiceImplentation;
import com.example.lucasrezende.igor.controller.adventures.AdventuresAdapter;
import com.example.lucasrezende.igor.controller.adventures.NewAdventureActivity;
import com.example.lucasrezende.igor.controller.adventures.adventureinfo.AdventureInfoActivity;
import com.example.lucasrezende.igor.controller.adventures.adventureinfo.sessions.SessionsAdapter;
import com.example.lucasrezende.igor.model.Adventure;
import com.example.lucasrezende.igor.model.Player;
import com.example.lucasrezende.igor.model.Session;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vzaffalon on 21/09/17.
 */

// Instances of this class are fragments representing a single
// object in our collection.
public class PlayersListFragment extends Fragment {
    List<Player> players;
    RecyclerView recyclerView;
    View view;
    PlayerServiceImplentation client;
    int adventure_id;

    @Override
    public void onResume() {
        super.onResume();
        getPlayerList();
        setUpLayout();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_players_list, container, false);
        setHasOptionsMenu(true);
        setUpList();
        setUpLayout();
        adventure_id = getArguments().getInt("adventure_id");
        return view;
    }

    private void setUpLayout(){
        ImageButton new_adventure_button = (ImageButton) view.findViewById(R.id.new_adventure_button);
        new_adventure_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),NewPlayerActivity.class);
                intent.putExtra("adventure_id",adventure_id);
                startActivity(intent);
            }
        });
    }


    private PlayersAdapter.OnClickListener onClickEvento(){
        return new PlayersAdapter.OnClickListener(){
            @Override
            public void onClickEvento(View view, int idx) {
                //aqui entra quando um dos elementos da lista é selecionado
//                Intent intent = new Intent(getContext(), AdventureInfoActivity.class);
//                startActivity(intent);
            }
        };
    }

    private void getPlayerList(){
        client = new PlayerServiceImplentation(getContext());
        // Fetch a list of the Github repositoriesteste.
        Call<List<Player>> call = client.getService().list_adventure_player(adventure_id);

        // Execute the call asynchronously. Get a positive or negative callback.
        call.enqueue(new Callback<List<Player>>() {
            @Override
            public void onResponse(Call<List<Player>> call, Response<List<Player>> response) {
                // The network call was a success and we got a response
                // TODO: use the repository list and display it
                if(response.isSuccessful()) {
                    //Toast.makeText(getContext(),"Lista obtida com sucesso",Toast.LENGTH_SHORT).show();
                    players = response.body();
                    setUpList();
                }else{
                    Toast.makeText(getContext(),"Falha na obtenção da lista",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<Player>> call, Throwable t) {
                // the network call was a failure
                // TODO: handle error
                Log.d("Error", t.getMessage());
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setUpList(){
        Date aux = new Date();

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new PlayersAdapter(getContext(), players, onClickEvento(),onClickDeleteButton()));
    }

    private void deletePlayer(int idx){
        client = new PlayerServiceImplentation(getContext());
        Call<Player> call = client.getService().delete(players.get(idx).getId());

        // Execute the call asynchronously. Get a positive or negative callback.
        call.enqueue(new Callback<Player>() {
            @Override
            public void onResponse(Call<Player> call, Response<Player> response) {
                // The network call was a success and we got a response
                // TODO: use the repository list and display it
                if(response.isSuccessful()) {
                    Toast.makeText(getContext(),"Jogador deletado com sucesso",Toast.LENGTH_SHORT).show();
                    getPlayerList();
                    setUpLayout();
                }else{
                    Toast.makeText(getContext(),"Falha ao deletar a jogador",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Player> call, Throwable t) {
                // the network call was a failure
                // TODO: handle error
                Log.d("Error", t.getMessage());
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private PlayersAdapter.OnClickListenerDeleteButton onClickDeleteButton(){
        return new PlayersAdapter.OnClickListenerDeleteButton(){
            @Override
            public void onClickDeleteButton(View view,final int idx) {
                new MaterialDialog.Builder(getContext())
                        .title("Deseja deletar essa sessão?")
                        .content("Todos os dados relacionados com essa sessão também serão deletados.")
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
                                deletePlayer(idx);
                            }
                        })
                        .show();
            }
        };
    }

//    private void setUpList(){
//        players = new ArrayList<>();
//        players.add(new Player(1,"vzaffalon","guerreiro das selvas selvagens da guerralheria da guerra","",5,10));
//        players.add(new Player(1,"vzaffalon","guerreiro das selvas selvagens da guerralheria da guerra","",5,10));
//        players.add(new Player(1,"vzaffalon","guerreiro das selvas selvagens da guerralheria da guerra","",5,10));
//        players.add(new Player(1,"vzaffalon","guerreiro das selvas selvagens da guerralheria da guerra","",5,10));
//
//        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
//        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
//        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setAdapter(new PlayersAdapter(getContext(), players, onClickEvento()));
//    }
}