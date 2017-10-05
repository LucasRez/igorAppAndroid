package com.example.lucasrezende.igor.controller.adventures.adventureinfo.sessions;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lucasrezende.igor.R;
import com.example.lucasrezende.igor.model.Session;

import java.util.Calendar;
import java.util.List;


public class SessionsAdapter extends RecyclerView.Adapter<SessionsAdapter.ViewHolder> {

    private final Context context;
    private OnClickListener onClickListener;
    List<Session> sessions;

    public SessionsAdapter(Context context, List<Session> sessions,
                           OnClickListener eventoOnClickListener){

        this.context=context;
        this.sessions = sessions;
        this.onClickListener = eventoOnClickListener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_session,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    //put the data and the clickListeners in the List items
    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position) {
        //atualiza a view
        Session session = sessions.get(position);
        Calendar sessionDate = Calendar.getInstance();
        sessionDate.setTime(session.getDate());

        holder.name.setText(session.getTitle());
        holder.nextSessionDate.setText(sessionDate.get(Calendar.DAY_OF_MONTH) + "/" + sessionDate.get(Calendar.MONTH));
        holder.description.setText(session.getDescription());

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
        return this.sessions != null ? this.sessions.size() : 0;
    }

    //relates the layout from the xml with the java classes
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public TextView nextSessionDate;
        public TextView description;

        public ViewHolder(View view){
            super(view);
            name=(TextView) view.findViewById(R.id.tv_session_name);
            nextSessionDate = (TextView) view.findViewById(R.id.tv_next_session_date);
            description = (TextView) view.findViewById(R.id.tv_session_description);
        }

    }

    public interface OnClickListener{
        public void onClickEvento(View view, int idx);
    }
}
