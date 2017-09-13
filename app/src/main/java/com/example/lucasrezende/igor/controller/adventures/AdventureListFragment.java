package com.example.lucasrezende.igor.controller.adventures;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lucasrezende.igor.R;
import com.example.lucasrezende.igor.model.Adventure;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vzaffalon on 13/09/17.
 */

public class AdventureListFragment extends Fragment {
    List<Adventure> adventures;
    RecyclerView recyclerView;
    View view;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_adventure_list, container, false);
        setHasOptionsMenu(true);
        setUpList();
        return view;
    }


    private AdventuresAdapter.OnClickListener onClickEvento(){
        return new AdventuresAdapter.OnClickListener(){
            @Override
            public void onClickEvento(View view, int idx) {
                //aqui entra quando um dos elementos da lista é selecionado
            }
        };
    }

    private void setUpList(){
        adventures = new ArrayList<>();
        adventures.add(new Adventure(1,"Shun lee, o FastFood","coast","1","Shun lee, o FastFood",5,"Livro do Shun lee"));
        adventures.add(new Adventure(1,"Campos de Nhame","corvali","1","soltando uns birl",5,"Livro do birl"));
        adventures.add(new Adventure(1,"Meáu, o cachorro-gato","heartlands","1","soltando uns birl",5,"Livro do birl"));
        adventures.add(new Adventure(1,"O diário de Unbora","krevast","1","soltando uns birl",5,"Livro do birl"));
        adventures.add(new Adventure(1,"Projeto I.G.O.R","automatica","1","soltando uns birl",5,"Livro do birl"));

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new AdventuresAdapter(getContext(), adventures, onClickEvento()));
    }
}
