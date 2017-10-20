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

import de.hdodenhof.circleimageview.CircleImageView;


public class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.ViewHolder> {

    private final Context context;
    private OnClickListener onClickListener;
    List<Player> players;
    private  OnClickListenerDeleteButton onClickListenerDeleteButton;

    public PlayersAdapter(Context context, List<Player> players,
                          OnClickListener eventoOnClickListener,OnClickListenerDeleteButton onClickListenerDeleteButton){

        this.context=context;
        this.players = players;
        this.onClickListener = eventoOnClickListener;
        this.onClickListenerDeleteButton = onClickListenerDeleteButton;
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
        holder.player_name.setText(player.getNickname());
        holder.player_nickname.setText(player.getNickname());
        holder.player_description.setText(player.getDescription());

        switch (player.getPicture()){
            case "Cavaleiro":
                Picasso.with(context).load(R.drawable.warrior).into(holder.profile_image);
                break;

            case "Mago":
                Picasso.with(context).load(R.drawable.witch).into(holder.profile_image);
                break;

            case "Curandeiro":
                Picasso.with(context).load(R.drawable.healer).into(holder.profile_image);
                break;

            case "Arqueiro":
                Picasso.with(context).load(R.drawable.bow).into(holder.profile_image);
                break;

            case "Gatuno":
                Picasso.with(context).load(R.drawable.burglar).into(holder.profile_image);
                break;

            case "Druida":
                Picasso.with(context).load(R.drawable.bear).into(holder.profile_image);
                break;

            case "Bardo":
                Picasso.with(context).load(R.drawable.bard).into(holder.profile_image);
                break;

            default:
                Picasso.with(context).load(R.drawable.warrior).into(holder.profile_image);
                break;

        }

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
        public TextView player_name;
        public TextView player_nickname;
        public TextView player_description;
        public CircleImageView profile_image;;

        public ViewHolder(View view){
            super(view);
            player_name = (TextView) view.findViewById(R.id.et_player_name);
            player_nickname = (TextView) view.findViewById(R.id.et_player_nickname);
            player_description = (TextView) view.findViewById(R.id.et_player_description);
            profile_image = (CircleImageView) view.findViewById(R.id.profile_image);
        }

    }

    public interface OnClickListener{
        public void onClickEvento(View view, int idx);
    }

    public interface OnClickListenerDeleteButton{
        public void onClickDeleteButton(View view, int idx);
    }
}
