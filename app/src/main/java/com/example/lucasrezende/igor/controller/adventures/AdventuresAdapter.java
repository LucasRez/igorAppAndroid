package com.example.lucasrezende.igor.controller.adventures;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lucasrezende.igor.R;
import com.example.lucasrezende.igor.model.Adventure;
import com.squareup.picasso.Picasso;
import java.util.List;


public class AdventuresAdapter extends RecyclerView.Adapter<AdventuresAdapter.ViewHolder> {

    private final Context context;
    private OnClickListener onClickListener;
    List<Adventure> adventures;

    public AdventuresAdapter(Context context, List<Adventure> adventures,
                             OnClickListener eventoOnClickListener){

        this.context=context;
        this.adventures = adventures;
        this.onClickListener = eventoOnClickListener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_adventure,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    //put the data and the clickListeners in the List items
    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position) {
        //atualiza a view
        Adventure adventure = adventures.get(position);
        holder.name.setText(adventure.getName());
        holder.nextSessionDate.setText("21/03");

        switch (adventure.getBackgroud_image()){
            case "coast":
                Picasso.with(context).load(R.drawable.miniatura_coast).into(holder.backgroudImage);
                break;
            case "corvali":
                Picasso.with(context).load(R.drawable.miniatura_corvali).into(holder.backgroudImage);
                break;
            case "heartlands":
                Picasso.with(context).load(R.drawable.miniatura_heartlands).into(holder.backgroudImage);
                break;
            case "krevast":
                Picasso.with(context).load(R.drawable.miniatura_krevast).into(holder.backgroudImage);
                break;
            case "automatica":
                Picasso.with(context).load(R.drawable.miniatura_imagem_automatica).into(holder.backgroudImage);
                break;
            default:
                Picasso.with(context).load(R.drawable.miniatura_imagem_automatica).into(holder.backgroudImage);
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
        return this.adventures != null ? this.adventures.size() : 0;
    }

    //relates the layout from the xml with the java classes
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public TextView nextSessionDate;
        public ImageView backgroudImage;;

        public ViewHolder(View view){
            super(view);
            name=(TextView) view.findViewById(R.id.name);
            nextSessionDate = (TextView) view.findViewById(R.id.tv_next_session_date);
            backgroudImage = (ImageView) view.findViewById(R.id.background_image);
        }

    }

    public interface OnClickListener{
        public void onClickEvento(View view, int idx);
    }
}
