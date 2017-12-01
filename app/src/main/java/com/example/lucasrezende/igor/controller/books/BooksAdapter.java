package com.example.lucasrezende.igor.controller.books;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lucasrezende.igor.R;
import com.example.lucasrezende.igor.model.Adventure;
import com.example.lucasrezende.igor.model.Book;
import com.squareup.picasso.Picasso;

import java.util.List;


public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.ViewHolder> {

    private final Context context;
    private OnClickListener onClickListener;
    private OnClickListenerDeleteButton onClickListenerDeleteButton;
    List<Book> books;

    public BooksAdapter(Context context, List<Book> books,
                        OnClickListener eventoOnClickListener, OnClickListenerDeleteButton onClickListenerDeleteButton){

        this.context=context;
        this.books = books;
        this.onClickListener = eventoOnClickListener;
        this.onClickListenerDeleteButton = onClickListenerDeleteButton;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_books,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    //put the data and the clickListeners in the List items
    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position) {
        //atualiza a view
        Book book = books.get(position);
        Picasso.with(context).load(book.getImageUrl()).into(holder.image);

        holder.subtitle.setText(book.getSubtitle());
        holder.name.setText(book.getName());

        //click config
        if(onClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.onClickEvento(holder.itemView, position);
                }
            });
        }

        //click config
//        if(onClickListener != null){
//            holder.ib_delete_button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    onClickListenerDeleteButton.onClickDeleteButton(holder.itemView, position);
//                }
//            });
//        }
    }

    @Override
    public int getItemCount() {
        return this.books != null ? this.books.size() : 0;
    }

    //relates the layout from the xml with the java classes
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public TextView subtitle;
        public ImageView image;

        public ViewHolder(View view){
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            subtitle = (TextView) view.findViewById(R.id.subtitle);
            image = (ImageView) view.findViewById(R.id.image);

        }

    }

    public interface OnClickListener{
        public void onClickEvento(View view, int idx);
    }
    public interface OnClickListenerDeleteButton{
        public void onClickDeleteButton(View view, int idx);
    }
}
