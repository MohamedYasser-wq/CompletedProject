package com.example.miniuper.Presentetion;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miniuper.Data.ComplainsModel;
import com.example.miniuper.Data.TripModel;
import com.example.miniuper.R;

import java.util.ArrayList;

public class RecViewFeedBack extends RecyclerView.Adapter<RecViewFeedBack.Holder> {

    private ArrayList<ComplainsModel> list=new ArrayList<>();



    public RecViewFeedBack(ArrayList<ComplainsModel>list2){
        this.list=list2;

    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.feddback_card,parent,false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.DriverId.setText(list.get(position).getId());
        holder.Rating.setText(list.get(position).getRating());
        holder.Complains.setText(list.get(position).getComplains());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class Holder extends RecyclerView.ViewHolder {

        TextView Rating;
        TextView Complains;
        TextView DriverId;

        public Holder(@NonNull View itemView) {
            super(itemView);

            Rating=itemView.findViewById(R.id.tv_rating);
            Complains=itemView.findViewById(R.id.tv_FeeBack);
            DriverId=itemView.findViewById(R.id.tv_id);

        }
    }
}
