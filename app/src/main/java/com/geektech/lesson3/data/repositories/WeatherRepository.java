package com.geektech.lesson3.data.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.geektech.lesson3.common.Resource;
import com.geektech.lesson3.data.models.MainResponse;
import com.geektech.lesson3.data.remote.WeatherApi;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepository {
    private WeatherApi api;

    @Inject
    public WeatherRepository(WeatherApi api) {
        this.api = api;
    }

    public MutableLiveData<Resource<MainResponse>> getWeather(String city) {
        MutableLiveData<Resource<MainResponse>> liveData = new MutableLiveData<>();
        liveData.setValue(Resource.loading());
        api.getWeather(city, "c3c7a48e9f41c91118a98b94b4d957a7", "metric").enqueue(new Callback<MainResponse>() {
            @Override
            public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.setValue(Resource.success(response.body()));
                } else {
                    liveData.setValue(Resource.error(response.message(), null));
                    Log.d("Ray", response.message());
                }
            }

            @Override
            public void onFailure(Call<MainResponse> call, Throwable t) {
                liveData.setValue(Resource.error(t.getLocalizedMessage(), null));
                Log.d("Ray", t.getLocalizedMessage());
            }
        });
        return liveData;
    }

}
