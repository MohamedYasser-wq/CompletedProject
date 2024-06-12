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

public class Rec_Feedback extends RecyclerView.Adapter<Rec_Feedback.Holder> implements OnClickItem {

    private ArrayList<TripModel> list=new ArrayList<>();
    private OnClickItem onClickItem;
    public Rec_Feedback(ArrayList<TripModel>list2,OnClickItem onClickItem2){
        this.list=list2;
        this.onClickItem=onClickItem2;

    }



    @Override
    public void onClick(int id) {

    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_feedback_trip,parent,false);

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

            Date=itemView.findViewById(R.id.Tv_DateF);
            start=itemView.findViewById(R.id.Tv_StartF);
            End=itemView.findViewById(R.id.Tv_DestinationF);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickItem.onClick(getAdapterPosition());
                }
            });

        }
    }
}
