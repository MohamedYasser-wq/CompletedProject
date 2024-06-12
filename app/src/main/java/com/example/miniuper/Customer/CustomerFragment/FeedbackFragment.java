package com.example.miniuper.Customer.CustomerFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.miniuper.Customer.CustomerViewModel;
import com.example.miniuper.Data.ComplainsModel;
import com.example.miniuper.Data.SharedPrefrence;
import com.example.miniuper.R;
import com.example.miniuper.databinding.FragmentFeedbackBinding;


public class FeedbackFragment extends Fragment {

    private FragmentFeedbackBinding binding;
    private NavController navController;

    private CustomerViewModel customerViewModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentFeedbackBinding.inflate(inflater);
        navController= Navigation.findNavController(container);
        customerViewModel=new ViewModelProvider(this).get(CustomerViewModel.class);
        // Inflate the layout for this fragment


        binding.backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_feedbackFragment_to_homeCustomerFragment);
            }
        });

        binding.BtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String feedback=binding.EdtFeedback.getText().toString();
                String rate=binding.EdtRating.getText().toString();
                Validate(feedback,rate);

            }
        });


        return binding.getRoot();
    }

    public void Validate(String complains,String rating){

        if(complains.equalsIgnoreCase("")||rating.equalsIgnoreCase(""))
        {
            Toast.makeText(getContext(), "Enter All Fields", Toast.LENGTH_SHORT).show();
        }
        else {
            int Rating=Integer.parseInt(rating);
            if(Rating<0||Rating>5){
                Toast.makeText(getContext(), "Enter Number Between 1...5", Toast.LENGTH_SHORT).show();
            }
            else {
                ComplainsModel complainsModel=new ComplainsModel(SharedPrefrence.getDriver_id(),complains,rating);
                FeedBack(complainsModel);

            }

        }



    }

    private void FeedBack(ComplainsModel complainsModel) {
        customerViewModel.Feedback(complainsModel);
        customerViewModel.FeedBackTripLiveData.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                Toast.makeText(getContext(), "Rated Successfully!!", Toast.LENGTH_SHORT).show();
            }
        });
        customerViewModel.ErrorFeedBackTripLiveData.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
            }
        });

    }
}