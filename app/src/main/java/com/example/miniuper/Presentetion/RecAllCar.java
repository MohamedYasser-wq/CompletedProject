package com.example.miniuper.Presentetion;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miniuper.Data.CarModel;
import com.example.miniuper.Data.UserModel;
import com.example.miniuper.R;

import java.util.ArrayList;

public class RecAllCar extends RecyclerView.Adapter<RecAllCar.Holder>implements OnClickItem {
    private ArrayList<CarModel>list=new ArrayList<>();
    private OnClickItem onClickItem;

    public RecAllCar(ArrayList<CarModel>list,OnClickItem onClickItem){
        this.onClickItem=onClickItem;
        this.list=list;

    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_car,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.Color.setText(list.get(position).getColor());
        holder.Year.setText(list.get(position).getYear());
        holder.Model.setText(list.get(position).getModel());
        holder.PlateNumber.setText(list.get(position).getPlateNumber());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(int id) {

    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView Model;
        TextView Year;
        TextView PlateNumber;
        TextView Color;
        TextView Assigned;

        public Holder(@NonNull View itemView) {
            super(itemView);

            Model = itemView.findViewById(R.id.Tv_model);
            Year = itemView.findViewById(R.id.Tv_Year);
            PlateNumber = itemView.findViewById(R.id.Tv_PlatNumber);
            Color = itemView.findViewById(R.id.Tv_Color);
            Assigned = itemView.findViewById(R.id.Tv_Assigned_Car);

            Assigned.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        onClickItem.onClick(getAdapterPosition());

                }
            });
        }
    }
}
