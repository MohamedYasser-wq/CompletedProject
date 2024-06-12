package com.example.miniuper.Authentication.AuthenticationFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.miniuper.Data.Const;
import com.example.miniuper.Data.SharedPrefrence;
import com.example.miniuper.R;
import com.example.miniuper.databinding.FragmentChoosePersonBinding;

public class ChoosePersonFragment extends Fragment {
   private com.example.miniuper.databinding.FragmentChoosePersonBinding binding;
   private NavController navController;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= com.example.miniuper.databinding.FragmentChoosePersonBinding.inflate(inflater);
        navController= Navigation.findNavController(container);


        binding.BtnLetsGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

           Validate();

            }
        });

        return binding.getRoot();
    }

    public void Validate(){
        if(binding.radioButtonCustomer.isChecked())
        {
            SharedPrefrence.setUserType(Const.Key_Customers);
            navController.navigate(R.id.action_choosePersonFragment_to_loginFragment);
        }
        else if (binding.radioButtonEmployee.isChecked())
        {
            SharedPrefrence.setUserType(Const.Key_Employees);
            navController.navigate(R.id.action_choosePersonFragment_to_loginFragment);
        }
        else if (binding.radioButtonDriver.isChecked())
        {
            SharedPrefrence.setUserType(Const.Key_Drivers);
            navController.navigate(R.id.action_choosePersonFragment_to_loginFragment);
        }
        else
        {
            Toast.makeText(getActivity(), "Please Choose role", Toast.LENGTH_SHORT).show();
        }
    }
}