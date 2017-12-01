package com.example.lucasrezende.igor.controller.books;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
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
import com.example.lucasrezende.igor.Utils.Constants;
import com.example.lucasrezende.igor.api.AdventureServiceImplentation;
import com.example.lucasrezende.igor.controller.adventures.AdventuresAdapter;
import com.example.lucasrezende.igor.controller.adventures.NewAdventureActivity;
import com.example.lucasrezende.igor.controller.adventures.adventureinfo.AdventureInfoActivity;
import com.example.lucasrezende.igor.model.Adventure;
import com.example.lucasrezende.igor.model.Book;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vzaffalon on 13/09/17.
 */

public class BooksFragment extends Fragment {
    List<Book> books = new ArrayList<>();
    RecyclerView recyclerView;
    View view;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_books, container, false);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getBookList();
        setUpList();
    }

    private void setUpLayout(){
    }

    private void getBookList(){
        Book book = new Book("Dungeons and dragons","Monster guide","http://client-cdn.crystalcommerce.com/photo/gamersspot/file/380183/large/10320560_10152396043671071_4524827311232386104_n.jpg?1433260806");
        books.add(book);
        book = new Book("Dungeons and dragons","The rise of tiamat","http://s3-eu-west-1.amazonaws.com/images.geeknative.com/wp-content/uploads/2014/12/The-Rise-of-Tiamat.jpg");
        books.add(book);
        book = new Book("Dungeons and dragons","Players Handbook","https://images-na.ssl-images-amazon.com/images/I/81Zgv2hxG-L.jpg");
        books.add(book);
        book = new Book("Dungeons and dragons","Master Guide","http://static.youblisher.com/publications/251/1504288/large-1504288-1.jpg");
        books.add(book);
        book = new Book("Dungeons and dragons","Starter Set","http://statics.livrariacultura.net.br/products/capas_lg/572/42273572.jpg");
        books.add(book);
        book = new Book("Dungeons and dragons","Hoard of the dragon queen","http://www.thealexandrian.net/images/20140920.jpg");
        books.add(book);
    }


    private BooksAdapter.OnClickListener onClickEvento(){
        return new BooksAdapter.OnClickListener(){
            @Override
            public void onClickEvento(View view, int idx) {
                //aqui entra quando um dos elementos da lista é selecionado
                Intent intent = new Intent(getContext(), BookInfoActivity.class);
                intent.putExtra("title", books.get(idx).getName());
                intent.putExtra("description", books.get(idx).getSubtitle());
//                intent.putExtra("adventure_id", adventures.get(idx).getId());
                startActivity(intent);
            }
        };
    }

    private BooksAdapter.OnClickListenerDeleteButton onClickDeleteButton(){
        return new BooksAdapter.OnClickListenerDeleteButton(){
            @Override
            public void onClickDeleteButton(View view,final int idx) {
            }
        };
    }

    private void setUpList(){
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), numberOfColumns));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new BooksAdapter(getContext(), books, onClickEvento(),onClickDeleteButton()));
    }
}
