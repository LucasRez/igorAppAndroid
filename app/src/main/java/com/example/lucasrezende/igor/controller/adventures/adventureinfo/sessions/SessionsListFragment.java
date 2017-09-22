package com.example.lucasrezende.igor.controller.adventures.adventureinfo.sessions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lucasrezende.igor.R;
import com.example.lucasrezende.igor.controller.adventures.AdventuresAdapter;
import com.example.lucasrezende.igor.controller.adventures.adventureinfo.AdventureInfoActivity;
import com.example.lucasrezende.igor.controller.adventures.adventureinfo.players.PlayersAdapter;
import com.example.lucasrezende.igor.model.Player;
import com.example.lucasrezende.igor.model.Session;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by vzaffalon on 21/09/17.
 */

// Instances of this class are fragments representing a single
// object in our collection.
public class SessionsListFragment extends Fragment {
    List<Session> sessions;
    RecyclerView recyclerView;
    View view;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sessions_list, container, false);
        setHasOptionsMenu(true);
        setUpList();
        setUpLayout();
        return view;
    }

    private void setUpLayout(){
    }


    private SessionsAdapter.OnClickListener onClickEvento(){
        return new SessionsAdapter.OnClickListener(){
            @Override
            public void onClickEvento(View view, int idx) {
                //aqui entra quando um dos elementos da lista é selecionado
                Intent intent = new Intent(getContext(), AdventureInfoActivity.class);
                startActivity(intent);
            }
        };
    }

    private void setUpList(){
        sessions = new ArrayList<>();
        Date aux = new Date();
        sessions.add(new Session(7,aux, "Aventura legal","uhuehue",5));
        sessions.add(new Session(7,aux, "Aventura legal","uhuehue",5));
        sessions.add(new Session(7,aux, "Aventura legal","uhuehue",5));
        sessions.add(new Session(7,aux, "Aventura legal","uhuehue",5));
        sessions.add(new Session(7,aux, "Aventura legal","uhuehue",5));
        sessions.add(new Session(7,aux, "Aventura legal","uhuehue",5));

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new SessionsAdapter(getContext(), sessions, onClickEvento()));
    }
}