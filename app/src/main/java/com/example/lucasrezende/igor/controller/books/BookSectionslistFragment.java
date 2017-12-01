package com.example.lucasrezende.igor.controller.books;

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
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.lucasrezende.igor.R;
import com.example.lucasrezende.igor.api.PlayerServiceImplentation;
import com.example.lucasrezende.igor.controller.adventures.adventureinfo.players.NewPlayerActivity;
import com.example.lucasrezende.igor.controller.adventures.adventureinfo.players.PlayersAdapter;
import com.example.lucasrezende.igor.model.Player;
import com.example.lucasrezende.igor.model.Section;

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
public class BookSectionslistFragment extends Fragment {
    List<Section> sections;
    RecyclerView recyclerView;
    View view;

    @Override
    public void onResume() {
        super.onResume();
        setUpLayout();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_secions_list, container, false);
        sections = new ArrayList<>();
        setHasOptionsMenu(true);
        setUpList();
        setUpLayout();
        return view;
    }

    private void setUpLayout(){
//        ImageButton new_adventure_button = (ImageButton) view.findViewById(R.id.new_adventure_button);
//        new_adventure_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getContext(),NewPlayerActivity.class);
////                intent.putExtra("adventure_id",adventure_id);
//                startActivity(intent);
//            }
//        });
    }


    private SectionsAdapter.OnClickListener onClickEvento(){
        return new SectionsAdapter.OnClickListener(){
            @Override
            public void onClickEvento(View view, int idx) {
                //aqui entra quando um dos elementos da lista é selecionado
                Intent intent = new Intent(getContext(), CapterInfoActvity.class);
                startActivity(intent);
            }
        };
    }


    private SectionsAdapter.OnClickListener onClickDeleteButton(){
        return new SectionsAdapter.OnClickListener(){
            @Override
            public void onClickEvento(View view, int idx) {
                //aqui entra quando um dos elementos da lista é selecionado
//                Intent intent = new Intent(getContext(), AdventureInfoActivity.class);
//                startActivity(intent);
            }
        };
    }

    private void setUpList(){
        Date aux = new Date();

        Section section = new Section("Préfacio");
        sections.add(section);
        section = new Section("Introdução");
        sections.add(section);
        section = new Section("Cápitulo1: Steb-by-step characters");
        sections.add(section);
        section = new Section("Cápitulo 2: Raças");
        sections.add(section);
        section = new Section("Cápitulo 3: Classes");
        sections.add(section);
        section = new Section("Cápitulo 4: Monstros");
        sections.add(section);
        section = new Section("Cápitulo 5: Mapas");
        sections.add(section);


        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new SectionsAdapter(getContext(), sections, onClickEvento()));
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