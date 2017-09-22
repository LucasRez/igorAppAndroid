package com.example.lucasrezende.igor.controller.adventures.adventureinfo.players;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lucasrezende.igor.R;
import com.example.lucasrezende.igor.model.Adventure;
import com.example.lucasrezende.igor.model.Player;
import com.squareup.picasso.Picasso;

import java.util.List;


public class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.ViewHolder> {

    private final Context context;
    private OnClickListener onClickListener;
    List<Player> players;

    public PlayersAdapter(Context context, List<Player> players,
                          OnClickListener eventoOnClickListener){

        this.context=context;
        this.players = players;
        this.onClickListener = eventoOnClickListener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_player,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    //put the data and the clickListeners in the List items
    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position) {
        //atualiza a view
        Player player = players.get(position);
//        holder.name.setText(adventure.getName());
//        holder.nextSessionDate.setText("21/03");

        //click config
        if(onClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.onClickEvento(holder.itemView, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return this.players != null ? this.players.size() : 0;
    }

    //relates the layout from the xml with the java classes
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public TextView nextSessionDate;
        public ImageView backgroudImage;;

        public ViewHolder(View view){
            super(view);
//            name=(TextView) view.findViewById(R.id.name);
//            nextSessionDate = (TextView) view.findViewById(R.id.next_session_date);
//            backgroudImage = (ImageView) view.findViewById(R.id.background_image);
        }

    }

    public interface OnClickListener{
        public void onClickEvento(View view, int idx);
    }
}
