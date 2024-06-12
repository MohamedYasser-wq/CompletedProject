package com.example.miniuper.Admin.AdminFragments;

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

import com.example.miniuper.Admin.AdminViewModel;
import com.example.miniuper.Data.ComplainsModel;
import com.example.miniuper.Presentetion.RecViewFeedBack;
import com.example.miniuper.Presentetion.Rec_Feedback;
import com.example.miniuper.databinding.FragmentViewAllFeedbackBinding;

import java.util.ArrayList;


public class ViewAllFeedbackFragment extends Fragment {

    private FragmentViewAllFeedbackBinding binding;
    private NavController navController;
    AdminViewModel adminViewModel;
    RecViewFeedBack recFeedback;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentViewAllFeedbackBinding.inflate(inflater);
        navController= Navigation.findNavController(container);
        adminViewModel=new ViewModelProvider(this).get(AdminViewModel.class);
        // Inflate the layout for this fragment
        ViewFeedback();
        return binding.getRoot();
    }

    public void ViewFeedback(){


        adminViewModel.ViewAllFeedBack();
        adminViewModel.FeedBackDriverMutableLiveData.observe(getViewLifecycleOwner(), new Observer<ArrayList<ComplainsModel>>() {
            @Override
            public void onChanged(ArrayList<ComplainsModel> complainsModels) {

                if(!(complainsModels==null)||!(complainsModels.size()==0)){

                    binding.textView8.setVisibility(View.INVISIBLE);
                    SetAdapter(complainsModels);

                }
                else
                    binding.textView8.setVisibility(View.VISIBLE);




            }
        });
        adminViewModel.FeedBackDriverErrorMutableData.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void SetAdapter(ArrayList<ComplainsModel> complainsModels) {

        recFeedback=new RecViewFeedBack(complainsModels);
        binding.RecFeedback.setAdapter(recFeedback);

    }


}