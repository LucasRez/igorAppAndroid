package com.example.lucasrezende.igor.controller.adventures.adventureinfo.players;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.lucasrezende.igor.R;
import com.example.lucasrezende.igor.controller.adventures.AdventuresAdapter;
import com.example.lucasrezende.igor.controller.adventures.NewAdventureActivity;
import com.example.lucasrezende.igor.controller.adventures.adventureinfo.AdventureInfoActivity;
import com.example.lucasrezende.igor.model.Adventure;
import com.example.lucasrezende.igor.model.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vzaffalon on 21/09/17.
 */

// Instances of this class are fragments representing a single
// object in our collection.
public class PlayersListFragment extends Fragment {
    List<Player> players;
    RecyclerView recyclerView;
    View view;

    @Override
    public void onResume() {
        super.onResume();
        setUpLayout();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_players_list, container, false);
        setHasOptionsMenu(true);
        setUpList();
        setUpLayout();
        return view;
    }

    private void setUpLayout(){
        ImageButton new_adventure_button = (ImageButton) view.findViewById(R.id.new_adventure_button);
        new_adventure_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),NewPlayerActivity.class);
                startActivity(intent);
            }
        });
    }


    private PlayersAdapter.OnClickListener onClickEvento(){
        return new PlayersAdapter.OnClickListener(){
            @Override
            public void onClickEvento(View view, int idx) {
                //aqui entra quando um dos elementos da lista é selecionado
                Intent intent = new Intent(getContext(), AdventureInfoActivity.class);
                startActivity(intent);
            }
        };
    }

    private void setUpList(){
        players = new ArrayList<>();
        players.add(new Player(1,"vzaffalon","guerreiro das selvas selvagens da guerralheria da guerra","",5,10));
        players.add(new Player(1,"vzaffalon","guerreiro das selvas selvagens da guerralheria da guerra","",5,10));
        players.add(new Player(1,"vzaffalon","guerreiro das selvas selvagens da guerralheria da guerra","",5,10));
        players.add(new Player(1,"vzaffalon","guerreiro das selvas selvagens da guerralheria da guerra","",5,10));

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new PlayersAdapter(getContext(), players, onClickEvento()));
    }
}