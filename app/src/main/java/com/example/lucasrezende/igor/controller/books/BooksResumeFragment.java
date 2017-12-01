package com.example.lucasrezende.igor.controller.books;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lucasrezende.igor.R;
import com.example.lucasrezende.igor.model.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vzaffalon on 13/09/17.
 */

public class BooksResumeFragment extends Fragment {
    List<Book> books = new ArrayList<>();
    RecyclerView recyclerView;
    View view;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.book_resume_fragment, container, false);
        setHasOptionsMenu(true);
        return view;
    }
}
