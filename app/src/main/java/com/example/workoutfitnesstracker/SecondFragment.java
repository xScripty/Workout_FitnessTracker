package com.example.workoutfitnesstracker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.workoutfitnesstracker.databinding.FragmentSecondBinding;
import com.google.gson.Gson;

import java.util.ArrayList;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    SharedPreferences preferences;
    private MealAdapter adapter;
    private ArrayList<Meal> meals;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        preferences=getActivity().getApplicationContext().getSharedPreferences("Userinfo",0);
        meals=new ArrayList<Meal>();
        adapter=new MealAdapter(getActivity().getApplicationContext(),meals);
        binding.mealsListView.setAdapter(adapter);

        String jsonText=preferences.getString("Meals","[]");
        Meal[] mealsObjs=new Gson().fromJson(jsonText,Meal[].class);
        for(Meal meal:mealsObjs){
            meals.add(meal);
        }

        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}