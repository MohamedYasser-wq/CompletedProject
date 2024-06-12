package com.example.miniuper.Authentication;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.miniuper.Data.UserModel;

public class AuthenticationViewModel extends ViewModel {

    public MutableLiveData<Boolean> registrationSuccess = new MutableLiveData<>();
    public MutableLiveData<String> RegistrationErrorMessage = new MutableLiveData<>();

    public MutableLiveData<Boolean> LoginSuccess = new MutableLiveData<>();
    public MutableLiveData<String> LoginErrorMessage = new MutableLiveData<>();
    private AuthenticationHelper authenticationHelper;

    public AuthenticationViewModel() {
        authenticationHelper = new AuthenticationHelper();
    }

    public void RegisterUser(UserModel user) {

        authenticationHelper.Register_User(user, new AuthenticationHelper.RegistrationCallback() {
            @Override
            public void onSuccess() {
                registrationSuccess.setValue(true);
            }

            @Override
            public void onFailure(String error) {
                RegistrationErrorMessage.setValue(error);
            }
        });
    }

    public void LoginUser(String Email,String pass){

        authenticationHelper.Login(Email, pass, new AuthenticationHelper.RegistrationCallback() {
            @Override
            public void onSuccess() {
                LoginSuccess.setValue(true);
            }

            @Override
            public void onFailure(String error) {
               LoginErrorMessage.setValue(error);
            }
        });




    }



}
