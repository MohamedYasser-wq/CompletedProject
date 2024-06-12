package com.example.miniuper.Admin;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.miniuper.Data.CarModel;
import com.example.miniuper.Data.ComplainsModel;
import com.example.miniuper.Data.Const;
import com.example.miniuper.Data.DriverModel;
import com.example.miniuper.Data.TripModel;
import com.example.miniuper.Data.UserModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminHelper {

    public interface AdminCallback{

        void OnSuccess();
        void OnFailure(String message);


    }

    public interface CarCallback{

        void OnSuccess(ArrayList<CarModel>list);
        void OnFailure(String message);


    }
    public interface FeedBcakCallback{

        void OnSuccess(ArrayList<ComplainsModel>list);
        void OnFailure(String message);


    }

    public void AddNewCar(CarModel car,AdminCallback callback){



        Const.ref.child(Const.Key_CarsNotAssignedYet).child(car.getPlateNumber()).setValue(car).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                callback.OnSuccess();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                callback.OnFailure(e.getMessage());
            }
        });




    }

    public ArrayList<CarModel> GetAllCar(CarCallback callback){

        ArrayList<CarModel>carsList=new ArrayList<>();

        Const.ref.child(Const.Key_CarsNotAssignedYet).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {



                for (DataSnapshot snapshot1:snapshot.getChildren()){


                            carsList.add(snapshot1.getValue(CarModel.class));
                            callback.OnSuccess(carsList);



                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                callback.OnFailure(error.getMessage());
            }
        });

   return carsList;


    }


    public void AddNewDriverAuth(DriverModel driver,AdminCallback callback){

        Const.auth.createUserWithEmailAndPassword(driver.getEmail(),driver.getPassword()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {

                AddNewDriverRealTime(driver,callback);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("TAG", "onFailure: "+e.getMessage());

            }
        });



    }
   private void AddNewDriverRealTime(DriverModel driver, AdminCallback callback){


        Const.ref.child(Const.Key_Users).child(Const.Key_Drivers).child(driver.getPhone()).setValue(driver).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                callback.OnSuccess();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                callback.OnSuccess();e.getMessage();

            }
        });


   }

   public void ViewAllFeedback(FeedBcakCallback callback){

       ArrayList<ComplainsModel>list=new ArrayList<>();

       Const.ref.child(Const.Key_Complains).addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {



               for (DataSnapshot snapshot1:snapshot.getChildren()){
                   for (DataSnapshot snapshot2:snapshot1.getChildren()) {


                       list.add(snapshot2.getValue(ComplainsModel.class));
                       callback.OnSuccess(list);

                   }

               }

           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

               callback.OnFailure("Error");
           }
       });





   }






}
