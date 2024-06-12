package com.example.miniuper.Admin.AdminFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.miniuper.R;
import com.example.miniuper.databinding.FragmentHomeEmployeeBinding;


public class HomeEmployeeFragment extends Fragment {
    private FragmentHomeEmployeeBinding binding;
    private NavController navController;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= com.example.miniuper.databinding.FragmentHomeEmployeeBinding.inflate(inflater);
        navController= Navigation.findNavController(container);

//        binding.BtnLogOut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                navController.navigate(R.id.action_homeEmployeeFragment_to_splashFragment);
//            }
//        });


        binding.CardViewComplains.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_homeEmployeeFragment_to_viewAllFeedbackFragment);
            }
        });

        binding.CardAddNewDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_homeEmployeeFragment_to_addDriverFragment);

            }
        });

        binding.CardCardAddNewCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              navController.navigate(R.id.action_homeEmployeeFragment_to_addCarFragment);
            }
        });






        return binding.getRoot();
    }
}