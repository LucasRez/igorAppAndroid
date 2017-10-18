package com.example.lucasrezende.igor.controller.adventures.adventureinfo.sessions;

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
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.lucasrezende.igor.R;
import com.example.lucasrezende.igor.api.AdventureServiceImplentation;
import com.example.lucasrezende.igor.controller.adventures.AdventuresAdapter;
import com.example.lucasrezende.igor.controller.adventures.adventureinfo.AdventureInfoActivity;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.lucasrezende.igor.R;
import com.example.lucasrezende.igor.api.SessionServiceImplentation;
import com.example.lucasrezende.igor.model.Adventure;
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
    TextView adventureDescription;
    String description;
    int adventure_id;

    @Override
    public void onResume() {
        super.onResume();
        getSessionsList();
        setUpLayout();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sessions_list, container, false);
        description = getArguments().getString("description");
        adventure_id = getArguments().getInt("adventure_id");
        setHasOptionsMenu(true);
        getSessionsList();
        setUpList();
        setUpLayout();
        return view;
    }

    private void setUpLayout(){
        adventureDescription = (TextView) view.findViewById(R.id.tv_adventure_info_description);
        adventureDescription.setText(description);

        newSessionButton = (ImageButton) view.findViewById(R.id.ib_new_session_button);
        newSessionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),NewSessionActivity.class);
                intent.putExtra("adventure_id",adventure_id);
                startActivity(intent);
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
        Call<List<Session>> call = client.getService().list_adventure_session(adventure_id);

        // Execute the call asynchronously. Get a positive or negative callback.
        call.enqueue(new Callback<List<Session>>() {
            @Override
            public void onResponse(Call<List<Session>> call, Response<List<Session>> response) {
                // The network call was a success and we got a response
                // TODO: use the repository list and display it
                if(response.isSuccessful()) {
                    //Toast.makeText(getContext(),"Lista obtida com sucesso",Toast.LENGTH_SHORT).show();
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
        recyclerView.setAdapter(new SessionsAdapter(getContext(), sessions, onClickEvento(),onClickDeleteButton()));
    }

    private void deleteSession(int idx){
        client = new SessionServiceImplentation(getContext());
        Call<Session> call = client.getService().delete(sessions.get(idx).getId());

        // Execute the call asynchronously. Get a positive or negative callback.
        call.enqueue(new Callback<Session>() {
            @Override
            public void onResponse(Call<Session> call, Response<Session> response) {
                // The network call was a success and we got a response
                // TODO: use the repository list and display it
                if(response.isSuccessful()) {
                    Toast.makeText(getContext(),"Aventura deletada com sucesso",Toast.LENGTH_SHORT).show();
                    getSessionsList();
                    setUpLayout();
                }else{
                    Toast.makeText(getContext(),"Falha ao deletar a aventura",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Session> call, Throwable t) {
                // the network call was a failure
                // TODO: handle error
                Log.d("Error", t.getMessage());
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private SessionsAdapter.OnClickListenerDeleteButton onClickDeleteButton(){
        return new SessionsAdapter.OnClickListenerDeleteButton(){
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
                                deleteSession(idx);
                            }
                        })
                        .show();
            }
        };
    }
}