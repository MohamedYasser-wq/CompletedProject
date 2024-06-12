package com.example.miniuper.Customer;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.miniuper.Admin.AdminHelper;
import com.example.miniuper.Data.CarModel;
import com.example.miniuper.Data.ComplainsModel;
import com.example.miniuper.Data.Const;
import com.example.miniuper.Data.TripModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CustomerHelper {

    public interface TripCallBack{

        void Success();
        void Failure(String message);

    }

    public interface ViewAllTripCallBack{

        void Success(ArrayList<TripModel>list);
        void Failure(String message);

    }

    public void BookTrip(TripModel tripModel, TripCallBack callback){

        String id=Const.auth.getUid();
        tripModel.setId(id);

        Const.ref.child(Const.Key_Trip_UnAccepted).child(id).child(tripModel.getTime()).setValue(tripModel).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

                Const.ref.child(Const.Key_Trip_Accepted).child(id).child(tripModel.getTime()).setValue(tripModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                        callback.Success();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("TAG", "onFailure:Fail Fail Fail ");
                    }
                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                callback.Failure("Fail");
            }
        });




    }

    public void ViewHistoryTrip(ViewAllTripCallBack viewAllTripCallBack){

        ArrayList<TripModel>list=new ArrayList<>();

        Const.ref.child(Const.Key_Trip_Accepted).child(Const.auth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot snapshot1:snapshot.getChildren()){


                        list.add(snapshot1.getValue(TripModel.class));
                        viewAllTripCallBack.Success(list);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                viewAllTripCallBack.Failure("Fail");
            }
        });

    }

    public void  ViewAllAcceptedTrip(ViewAllTripCallBack callBack){


        ArrayList<TripModel>list=new ArrayList<>();

        Const.ref.child(Const.Key_Trip_AcceptedForCustomer).child(Const.auth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot snapshot1:snapshot.getChildren()){

                      list.add(snapshot1.getValue(TripModel.class));
                        callBack.Success(list);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                callBack.Failure("Fail");
            }
        });

    }

    public void FeedBackAcceptedTrip(ComplainsModel complainsModel,TripCallBack tripCallBack){

        Const.ref.child(Const.Key_Complains).child(complainsModel.getId()).child(complainsModel.getComplains()).setValue(complainsModel).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

                tripCallBack.Success();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                tripCallBack.Failure("Fail");
            }
        });




    }






}
