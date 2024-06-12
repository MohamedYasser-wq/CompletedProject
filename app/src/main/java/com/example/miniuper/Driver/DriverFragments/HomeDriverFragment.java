package com.example.miniuper.Driver.DriverFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.miniuper.R;
import com.example.miniuper.databinding.FragmentHomeDriverBinding;

public class HomeDriverFragment extends Fragment {
    private FragmentHomeDriverBinding binding;
    private NavController navController;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentHomeDriverBinding.inflate(inflater);
        navController= Navigation.findNavController(container);
        // Inflate the layout for this fragment


//        binding.BtnLogOut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                navController.navigate(R.id.action_homeDriverFragment_to_splashFragment);
//
//            }
//        });
        binding.CardSearchTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_homeDriverFragment_to_searchTripFragment);
            }
        });

        binding.CardCardHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_homeDriverFragment_to_historyForDriverFragment);
            }
        });

        return binding.getRoot();
    }
}