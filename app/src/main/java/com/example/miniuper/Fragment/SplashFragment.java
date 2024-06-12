package com.example.miniuper.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.miniuper.R;
import com.example.miniuper.databinding.FragmentSplashBinding;

public class SplashFragment extends Fragment {
  public FragmentSplashBinding binding;
  public NavController navController;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentSplashBinding.inflate(inflater);
        navController= Navigation.findNavController(container);

        binding.Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           navController.navigate(R.id.action_splashFragment_to_choosePersonFragment);

            }
        });



        return binding.getRoot();
    }
}