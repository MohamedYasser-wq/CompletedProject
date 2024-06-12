package com.example.miniuper.Authentication.AuthenticationFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.miniuper.Authentication.AuthenticationViewModel;
import com.example.miniuper.Data.Const;
import com.example.miniuper.Data.SharedPrefrence;
import com.example.miniuper.R;

public class LoginFragment extends Fragment {

    private com.example.miniuper.databinding.FragmentLoginBinding binding;
    private NavController navController;
    AuthenticationViewModel authenticationViewModel;

    final String EmailAdmin="admin@gmail.com";
    final String PassAdmin="adminn";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= com.example.miniuper.databinding.FragmentLoginBinding.inflate(inflater);
        navController= Navigation.findNavController(container);
        authenticationViewModel=new ViewModelProvider(this).get(AuthenticationViewModel.class);

        ValidateType();

        binding.TvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_loginFragment_to_registerFragment);
            }
        });

        binding.BTnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=binding.EdtUserEmail.getText().toString();
                String pass=binding.EdtUserPassword.getText().toString();

                Validate(email,pass);
            }
        });

        return binding.getRoot();
    }

    public void ValidateType() {
        if(SharedPrefrence.getUserType().equalsIgnoreCase(Const.Key_Customers))
        {
            binding.TvSignup.setVisibility(View.VISIBLE);
            binding.Tv.setVisibility(View.VISIBLE);
        }

    }

    public void Validate(String Email,String pass){

        if(Email.equalsIgnoreCase("")||pass.equalsIgnoreCase("")){

            Toast.makeText(getContext(), "Enter All Fields please", Toast.LENGTH_SHORT).show();

        }
        else {

            if(!(Email.contains("@"))){
                Toast.makeText(getContext(), "Email Should Contain @", Toast.LENGTH_SHORT).show();
            } else if (pass.length()<6) {
                Toast.makeText(getContext(), "Password Should equal 6 number or more", Toast.LENGTH_SHORT).show();

            }




            authenticationViewModel.LoginUser(Email,pass);

            Validate2(Email,pass);



        }



    }

    private void Validate2(String Email,String Pass){

        authenticationViewModel.LoginSuccess.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){


                        if(Email.equalsIgnoreCase(EmailAdmin)&&Pass.equalsIgnoreCase(PassAdmin)){

                            navController.navigate(R.id.action_loginFragment_to_homeEmployeeFragment);
                        }



                    else {


                        if (SharedPrefrence.getUserType().equalsIgnoreCase(Const.Key_Customers))
                        {
                            navController.navigate(R.id.action_loginFragment_to_homeCustomerFragment);

                        }
                        else if (SharedPrefrence.getUserType().equalsIgnoreCase(Const.Key_Drivers))
                        {
                            navController.navigate(R.id.action_loginFragment_to_homeDriverFragment);
                        }

                    }
                }


          }





        });
        authenticationViewModel.LoginErrorMessage.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
            }
        });



    }

}