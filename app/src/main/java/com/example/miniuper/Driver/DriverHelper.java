package com.example.miniuper.Driver;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.miniuper.Data.Const;
import com.example.miniuper.Data.TripModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DriverHelper {


    public interface SearchAllTripCallBack{

        void Success(ArrayList<TripModel> list);
        void Failure(String message);

    }
    public interface HandleAllTripCallBack{

        void Success();
        void Failure(String message);

    }

    public void ViewAllTrip(SearchAllTripCallBack searchAllTripCallBack){

        ArrayList<TripModel>list=new ArrayList<>();

        Const.ref.child(Const.Key_Trip_UnAccepted).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {



                for (DataSnapshot snapshot1:snapshot.getChildren()){
                    for (DataSnapshot snapshot2:snapshot1.getChildren()) {

                        list.add(snapshot2.getValue(TripModel.class));
                        searchAllTripCallBack.Success(list);


                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                searchAllTripCallBack.Failure("Fail");
            }
        });

    }

    public void AcceptTrip(TripModel tripModel,String id,HandleAllTripCallBack handleAllTripCallBack){
        Const.ref.child(Const.Key_AcceptedForDriver).child(Const.auth.getUid()).child(id).child(tripModel.getTime()).setValue(tripModel)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                         tripModel.setId(Const.auth.getUid());
                        Const.ref.child(Const.Key_Trip_AcceptedForCustomer).child(id).child(tripModel.getTime()).setValue(tripModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Const.ref.child(Const.Key_Trip_UnAccepted).child(id).child(tripModel.getTime()).setValue(null).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        handleAllTripCallBack.Success();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.e("TAG", "onFailure:::::::::: ");
                                    }
                                });
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.e("TAG", "onFailure 2 :::::::::: ");
                            }
                        });




                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        handleAllTripCallBack.Failure("Fail");
                    }
                });







    }


    public void ViewHistory(SearchAllTripCallBack searchAllTripCallBack){

        ArrayList<TripModel>list=new ArrayList<>();

        Const.ref.child(Const.Key_AcceptedForDriver).child(Const.auth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {



                for (DataSnapshot snapshot1:snapshot.getChildren()){
                    for (DataSnapshot snapshot2:snapshot1.getChildren()) {

                        list.add(snapshot2.getValue(TripModel.class));
                        searchAllTripCallBack.Success(list);


                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                searchAllTripCallBack.Failure("Fail");
            }
        });






    }




}
