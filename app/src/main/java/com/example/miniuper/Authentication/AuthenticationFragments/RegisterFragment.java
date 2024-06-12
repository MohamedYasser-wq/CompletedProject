package com.example.miniuper.Authentication.AuthenticationFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.miniuper.Authentication.AuthenticationViewModel;
import com.example.miniuper.Data.SharedPrefrence;
import com.example.miniuper.Data.UserModel;
import com.example.miniuper.R;
import com.example.miniuper.databinding.FragmentRegisterBinding;

public class RegisterFragment extends Fragment {

   private com.example.miniuper.databinding.FragmentRegisterBinding binding;
    private NavController navController;
    AuthenticationViewModel authenticationViewModel;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= com.example.miniuper.databinding.FragmentRegisterBinding.inflate(inflater);
        navController= Navigation.findNavController(container);
        authenticationViewModel=new ViewModelProvider(this).get(AuthenticationViewModel.class);


      binding.BtnRegister.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      String name=binding.EdtName.getText().toString();
                      String email=binding.EdtEmail.getText().toString();
                      String pass=binding.EdtPassword.getText().toString();
                      String phone=binding.EdtPhone.getText().toString();

                      Validate(name,email,pass,phone);

                  }
              });



        return binding.getRoot();
    }

    private void Validate(String name,String Email,String Password,String Phone){

        if(name.equalsIgnoreCase("")||Email.equalsIgnoreCase("")||
        Password.equalsIgnoreCase("")||Phone.equalsIgnoreCase(""))
        {
            Toast.makeText(getContext(), "Please Enter All Fields", Toast.LENGTH_SHORT).show();

        }
        else {
            if (!(Phone.length() == 11)) {

                Toast.makeText(getContext(), "Please Enter 11 Number on The Phone Field", Toast.LENGTH_SHORT).show();

            } else if (!(Email.contains("@"))) {

                Toast.makeText(getContext(), "Email Should Contain @", Toast.LENGTH_SHORT).show();

            } else if (Password.length() < 6) {

                Toast.makeText(getContext(), "Password should be 6 Number or more", Toast.LENGTH_SHORT).show();

            } else {
                UserModel user = new UserModel();
                user.setName(name);
                user.setEmail(Email);
                user.setPassword(Password);
                user.setPhone(Phone);
                user.setType(SharedPrefrence.getUserType());

                authenticationViewModel.RegisterUser(user);

                RegisterUser(user);
            }
        }
    }

    private void RegisterUser(UserModel user) {
authenticationViewModel.registrationSuccess.observe(this, new Observer<Boolean>() {
    @Override
    public void onChanged(Boolean aBoolean) {
        Toast.makeText(getContext(), "Register Successfully!!!", Toast.LENGTH_SHORT).show();
        navController.navigate(R.id.action_registerFragment_to_homeCustomerFragment);
    }
});

authenticationViewModel.RegistrationErrorMessage.observe(this, new Observer<String>() {
    @Override
    public void onChanged(String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
    }
});

    }


}