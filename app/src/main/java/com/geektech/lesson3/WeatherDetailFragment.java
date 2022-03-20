package com.geektech.lesson3;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geektech.lesson3.base.BaseFragment;
import com.geektech.lesson3.databinding.FragmentWeatherDetailBinding;
import com.geektech.lesson3.ui.weather.WeatherFragmentDirections;


public class WeatherDetailFragment extends BaseFragment<FragmentWeatherDetailBinding> {


    @Override
    protected FragmentWeatherDetailBinding bing() {
        return FragmentWeatherDetailBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void setupObservers() {

    }

    @Override
    protected void setupListeners() {
        binding.btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Prefs prefs = new Prefs(requireContext());
                prefs.saveCity(binding.etTypeCity.getText().toString());
                navController.navigateUp();
            }
        });
    }

    @Override
    protected void callRequests() {

    }

    @Override
    protected void setupViews() {

    }
}