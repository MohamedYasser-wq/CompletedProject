package com.example.miniuper.Customer.CustomerFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.example.miniuper.Data.SharedPrefrence;
import com.example.miniuper.R;
import com.example.miniuper.databinding.FragmentCalenderBinding;


public class CalenderFragment extends Fragment {

    private FragmentCalenderBinding binding;
    private NavController navController;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        binding= FragmentCalenderBinding.inflate(inflater);
        navController= Navigation.findNavController(container);

        binding.calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {


                String myDate = year + "-" + (month + 1) + "-" + dayOfMonth;
                binding.date.setText(myDate);
                SharedPrefrence.set_Date(myDate);
                navController.navigate(R.id.action_calenderFragment_to_addTripFragment);
            }
        });





        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}