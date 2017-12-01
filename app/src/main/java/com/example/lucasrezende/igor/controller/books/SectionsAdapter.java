package com.example.lucasrezende.igor.controller.books;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.lucasrezende.igor.R;
import com.example.lucasrezende.igor.model.Section;
import com.example.lucasrezende.igor.model.Session;

import java.util.Calendar;
import java.util.List;


public class SectionsAdapter extends RecyclerView.Adapter<SectionsAdapter.ViewHolder> {

    private final Context context;
    private OnClickListener onClickListener;
    List<Section> sections;
    private SectionsAdapter.OnClickListenerDeleteButton onClickListenerDeleteButton;

    public SectionsAdapter(Context context, List<Section> sections,
                           OnClickListener eventoOnClickListener){

        this.context=context;
        this.sections = sections;
        this.onClickListener = eventoOnClickListener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_section,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    //put the data and the clickListeners in the List items
    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position) {
        //atualiza a view
        Section section = sections.get(position);

        holder.name.setText(section.getName());

        //click config
        if(onClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.onClickEvento(holder.itemView, position);
                }
            });
        }

//        //click config
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
        return this.sections != null ? this.sections.size() : 0;
    }

    //relates the layout from the xml with the java classes
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView name;

        public ViewHolder(View view){
            super(view);
            name= (TextView) view.findViewById(R.id.name);
        }

    }

    public interface OnClickListener{
        public void onClickEvento(View view, int idx);
    }
    public interface OnClickListenerDeleteButton{
        public void onClickDeleteButton(View view, int idx);
    }
}
