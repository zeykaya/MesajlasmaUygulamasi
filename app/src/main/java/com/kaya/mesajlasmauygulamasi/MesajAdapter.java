package com.kaya.mesajlasmauygulamasi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MesajAdapter extends RecyclerView.Adapter<MesajAdapter.ViewHolder>{


    Context context;
    List<MesajModel>list;
    Activity activity;
    String userName;
    Boolean state;
    int view_send=1,view_received=2;

    public MesajAdapter(Context context, List<MesajModel> list, Activity activity, String userName) {
        this.context = context;
        this.list = list;
        this.activity = activity;
        this.userName=userName;
        state=false;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        if (viewType==view_send) {
            view = LayoutInflater.from(context).inflate(R.layout.send_layout, parent, false);
        }else {
            view = LayoutInflater.from(context).inflate(R.layout.received_layout, parent, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         holder.textView.setText(list.get(position).getText().toString());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textView;

        public ViewHolder( View itemView) {
            super(itemView);
            if (state==true) {
                textView = itemView.findViewById(R.id.send_textview);
            }else {
                textView = itemView.findViewById(R.id.received_text);

            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getFrom().equals(userName)){
            state=true;
            return view_send;
        }else {
            state=false;
            return view_received;
        }
    }
}
