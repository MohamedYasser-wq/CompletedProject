package com.example.miniuper.Customer.CustomerFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.miniuper.R;
import com.example.miniuper.databinding.FragmentHomeCustomerBinding;


public class HomeCustomerFragment extends Fragment {
    private FragmentHomeCustomerBinding binding;
    private NavController navController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding= FragmentHomeCustomerBinding.inflate(inflater);
        navController= Navigation.findNavController(container);
        // Inflate the layout for this fragment



        binding.CardBookTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                navController.navigate(R.id.action_homeCustomerFragment_to_addTripFragment);

            }
        });


        binding.btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                navController.navigate(R.id.action_homeCustomerFragment_to_splashFragment);

            }
        });
        binding.CardHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                navController.navigate(R.id.action_homeCustomerFragment_to_viewAllTripsFragment);


            }
        });

        binding.CardViewAcceptedTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                navController.navigate(R.id.action_homeCustomerFragment_to_viewAcceptedTripFragment);

            }
        });



        return binding.getRoot();
    }
}