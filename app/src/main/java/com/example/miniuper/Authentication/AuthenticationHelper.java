package com.example.miniuper.Authentication;

import android.util.Log;
import androidx.annotation.NonNull;
import com.example.miniuper.Data.Const;
import com.example.miniuper.Data.UserModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;

public class AuthenticationHelper {

    public interface RegistrationCallback {
        void onSuccess();
        void onFailure(String error);
    }

    public void Register_User(UserModel user, final RegistrationCallback callback) {

        Const.auth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        registerUserInRealTime(user, callback);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        callback.onFailure(e.getMessage());
                    }
                });
    }

    private void registerUserInRealTime(UserModel user, final RegistrationCallback callback) {

        String id = Const.auth.getUid();
        user.setId(id);

        Const.ref.child(Const.Key_Users).child(Const.Key_Customers).child(id).setValue(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                        callback.onSuccess();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        callback.onFailure(e.getMessage());
                    }
                });
    }



    public void Login(String Email,String Pass,final RegistrationCallback callback){

        Const.auth.signInWithEmailAndPassword(Email,Pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                callback.onSuccess();
            }
        })   .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                callback.onFailure("Email or Password inCorrect");
            }
        });




    }

}
