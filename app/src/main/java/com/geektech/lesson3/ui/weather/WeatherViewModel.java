package com.geektech.lesson3.ui.weather;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.geektech.lesson3.common.Resource;
import com.geektech.lesson3.data.models.MainResponse;
import com.geektech.lesson3.data.repositories.WeatherRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class WeatherViewModel extends ViewModel {

    public WeatherRepository repository;

    @Inject
    public WeatherViewModel(WeatherRepository repository) {
        this.repository = repository;
    }

    public LiveData<Resource<MainResponse>> weatherLiveData;

    public void getWeather(String city) {
        weatherLiveData = repository.getWeather(city);
    }
}
