package com.example.lucasrezende.igor.controller.adventures.adventureinfo.sessions.diceroll;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.lucasrezende.igor.R;
import com.example.lucasrezende.igor.controller.adventures.adventureinfo.sessions.SessionsAdapter;
import com.example.lucasrezende.igor.model.Session;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.List;

/**
 * Created by vzaffalon on 09/11/2017.
 */

public class DicesAdapter extends RecyclerView.Adapter<DicesAdapter.ViewHolder> {

    private final Context context;
    private DicesAdapter.OnDiceClickListener onDiceClickListener;
    private DicesAdapter.OnAddClickListener onAddClickListener;
    private DicesAdapter.OnRemoveClickListener onRemoveClickListener;
    private DicesAdapter.OnPositiveClickListener onPositiveClickListener;
    private DicesAdapter.OnNegativeClickListener onNegativeClickListener;
    List<Dice> dices;

    public DicesAdapter(Context context, List<Dice> dices,
                        DicesAdapter.OnDiceClickListener onDiceClickListener,
                        DicesAdapter.OnAddClickListener onAddClickListener,
                        DicesAdapter.OnRemoveClickListener onRemoveClickListener,
                        DicesAdapter.OnPositiveClickListener onPositiveClickListener,
                        DicesAdapter.OnNegativeClickListener onNegativeClickListener
                        ){

        this.context=context;
        this.dices = dices;
        this.onDiceClickListener = onDiceClickListener;
        this.onAddClickListener = onAddClickListener;
        this.onRemoveClickListener = onRemoveClickListener;
        this.onPositiveClickListener = onPositiveClickListener;
        this.onNegativeClickListener = onNegativeClickListener;
    }


    @Override
    public DicesAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_dices,viewGroup,false);
        DicesAdapter.ViewHolder holder = new DicesAdapter.ViewHolder(view);
        return holder;
    }

    //put the data and the clickListeners in the List items
    @Override
    public void onBindViewHolder(final DicesAdapter.ViewHolder holder, final int position) {
        //atualiza a view
        Dice dice = dices.get(position);

        holder.modifier_text_view.setText(Integer.toString(dice.getModifier()));
        holder.number_of_dices_text.setText(Integer.toString(dice.getNumberOfDices()));
        holder.tipo_de_dato_text_view.setText(dice.getType());
        holder.result_text_view.setText(dice.getResult());



        switch (dice.getValue()){
            case 4:
                Picasso.with(context).load(R.drawable.d4).into(holder.dice_button);
                break;
            case 6:
                Picasso.with(context).load(R.drawable.d6).into(holder.dice_button);
                break;
            case 8:
                Picasso.with(context).load(R.drawable.d8).into(holder.dice_button);
                break;
            case 10:
                Picasso.with(context).load(R.drawable.d10).into(holder.dice_button);
                break;
            case 100:
                Picasso.with(context).load(R.drawable.d4).into(holder.dice_button);
                break;
            case 12:
                Picasso.with(context).load(R.drawable.d12).into(holder.dice_button);
                break;
            case 20:
                Picasso.with(context).load(R.drawable.d20).into(holder.dice_button);
                break;
        }


        //click config
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onDiceClickListener.onClickDice(holder.itemView, position);
                }
            });

        //click config
        holder.dice_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDiceClickListener.onClickDice(holder.itemView, position);
            }
        });

        //click config
        holder.add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddClickListener.onClickAdd(holder.itemView, position);
            }
        });

        //click config
        holder.remove_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRemoveClickListener.onClickRemove(holder.itemView, position);
            }
        });

        //click config
        holder.positive_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPositiveClickListener.onClickPositive(holder.itemView, position);
            }
        });

        //click config
        holder.negative_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNegativeClickListener.onClickNegative(holder.itemView, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.dices != null ? this.dices.size() : 0;
    }

    //relates the layout from the xml with the java classes
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageButton dice_button;
        public ImageButton add_button;
        public TextView number_of_dices_text;
        public ImageButton remove_button;
        public ImageButton positive_button;
        public ImageButton negative_button;
        public TextView modifier_text_view;
        public TextView tipo_de_dato_text_view;
        public TextView result_text_view;

        public ViewHolder(View view){
            super(view);
            dice_button = view.findViewById(R.id.dice_button);
            add_button = view.findViewById(R.id.add_button);
            number_of_dices_text = view.findViewById(R.id.number_of_dices_text);
            remove_button = view.findViewById(R.id.remove_button);
            positive_button = view.findViewById(R.id.positive_button);
            negative_button = view.findViewById(R.id.negative_button);
            modifier_text_view = view.findViewById(R.id.modifier_text_view);
            tipo_de_dato_text_view = view.findViewById(R.id.tipo_de_dado_text_view);
            result_text_view = view.findViewById(R.id.result_text_view);
        }

    }

    public interface OnDiceClickListener{
        public void onClickDice(View view, int idx);
    }

    public interface OnAddClickListener{
        public void onClickAdd(View view, int idx);
    }

    public interface OnRemoveClickListener{
        public void onClickRemove(View view, int idx);
    }

    public interface OnPositiveClickListener{
        public void onClickPositive(View view, int idx);
    }

    public interface OnNegativeClickListener{
        public void onClickNegative(View view, int idx);
    }

}
