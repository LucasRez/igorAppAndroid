package com.example.lucasrezende.igor.controller.books;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lucasrezende.igor.R;

/**
 * Created by vzaffalon on 13/09/17.
 */

public class BooksFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_books, container, false);
        setHasOptionsMenu(true);
        return view;
    }
}
