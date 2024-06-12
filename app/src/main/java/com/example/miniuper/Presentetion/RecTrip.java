package com.example.miniuper.Presentetion;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miniuper.Data.TripModel;
import com.example.miniuper.R;

import java.util.ArrayList;

public class RecTrip extends RecyclerView.Adapter<RecTrip.Holder>{
  private   ArrayList<TripModel>list=new ArrayList<>();



    public RecTrip(ArrayList<TripModel>list2){
        this.list=list2;

    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.trip_card,parent,false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        holder.End.setText(list.get(position).getDestinationPoint());
        holder.start.setText(list.get(position).getStartingPoint());
        holder.Date.setText(list.get(position).getTime());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView start;
        TextView End;
        TextView Date;

        public Holder(@NonNull View itemView) {
            super(itemView);

            Date=itemView.findViewById(R.id.Tv_Date);
            start=itemView.findViewById(R.id.Tv_Start);
            End=itemView.findViewById(R.id.Tv_Destination);

        }
    }
}
