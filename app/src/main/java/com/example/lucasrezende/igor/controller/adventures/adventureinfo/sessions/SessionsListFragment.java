package com.example.lucasrezende.igor.controller.adventures.adventureinfo.sessions;

import android.os.Bundle;
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

import com.example.lucasrezende.igor.R;
import com.example.lucasrezende.igor.api.SessionServiceImplentation;
import com.example.lucasrezende.igor.model.Session;

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
public class SessionsListFragment extends Fragment {
    List<Session> sessions;
    RecyclerView recyclerView;
    SessionServiceImplentation client;
    ImageButton newSessionButton;
    View view;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sessions_list, container, false);
        setHasOptionsMenu(true);
        getSessionsList();
        setUpList();
        setUpLayout();
        return view;
    }

    private void setUpLayout(){
        newSessionButton = (ImageButton) view.findViewById(R.id.ib_new_session_button);
        newSessionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Vamos criar uma sessao",Toast.LENGTH_SHORT).show();
            }
        });
    }


    private SessionsAdapter.OnClickListener onClickEvento(){
        return new SessionsAdapter.OnClickListener(){
            @Override
            public void onClickEvento(View view, int idx) {
                //aqui entra quando um dos elementos da lista é selecionado
//                Intent intent = new Intent(getContext(), AdventureInfoActivity.class);
//                startActivity(intent);
            }
        };
    }

    private void getSessionsList(){
        client = new SessionServiceImplentation(getContext());
        // Fetch a list of the Github repositoriesteste.
        Call<List<Session>> call = client.getService().list();

        // Execute the call asynchronously. Get a positive or negative callback.
        call.enqueue(new Callback<List<Session>>() {
            @Override
            public void onResponse(Call<List<Session>> call, Response<List<Session>> response) {
                // The network call was a success and we got a response
                // TODO: use the repository list and display it
                if(response.isSuccessful()) {
                    Toast.makeText(getContext(),"Lista obtida com sucesso",Toast.LENGTH_SHORT).show();
                    sessions = response.body();
                    setUpList();
                }else{
                    Toast.makeText(getContext(),"Falha na obtenção da lista",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<Session>> call, Throwable t) {
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
        recyclerView.setAdapter(new SessionsAdapter(getContext(), sessions, onClickEvento()));
    }
}