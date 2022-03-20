package com.geektech.lesson3.ui.weather;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.geektech.lesson3.Prefs;
import com.geektech.lesson3.R;
import com.geektech.lesson3.WeatherDetailFragmentArgs;
import com.geektech.lesson3.base.BaseFragment;
import com.geektech.lesson3.common.Resource;
import com.geektech.lesson3.data.models.MainResponse;
import com.geektech.lesson3.databinding.FragmentWeatherBinding;

import java.util.Calendar;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class WeatherFragment extends BaseFragment<FragmentWeatherBinding> {

    private WeatherViewModel viewModel;
    private MainResponse data;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected FragmentWeatherBinding bing() {
        return FragmentWeatherBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void setupObservers() {
        viewModel.weatherLiveData.observe(getViewLifecycleOwner(), new Observer<Resource<MainResponse>>() {
            @Override
            public void onChanged(Resource<MainResponse> resource) {
                switch (resource.status) {
                    case SUCCESS: {
                        Toast.makeText(requireActivity(), "sucess", Toast.LENGTH_SHORT).show();
                        setupUI(resource.data);
                        break;
                    }
                    case LOADING: {
                        Toast.makeText(requireActivity(), "loading", Toast.LENGTH_SHORT).show();

                        break;
                    }
                    case ERROR: {
                        Toast.makeText(requireActivity(), resource.msg, Toast.LENGTH_SHORT).show();
                        Log.e("TAG", "onChanged: " + resource.msg);
                        break;
                    }
                }
            }
        });
    }

    private void setupUI(MainResponse data) {

        binding.textTemp.setText(data.getSys().getCountry());
        binding.textHumidityCifry.setText(data.getMain().getHumidity().toString());
    }

    @Override
    protected void setupListeners() {
        binding.textCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(requireActivity(), "asdasd", Toast.LENGTH_SHORT).show();
                navController.navigate(R.id.weatherDetailFragment);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Calendar uh = Calendar.getInstance();
        int timeOfDay = uh.get(Calendar.HOUR_OF_DAY);

        if (timeOfDay >= 0 && timeOfDay < 12) {
            binding.imageCity.setImageResource(R.drawable.city_night);
        } else {
            binding.imageCity.setImageResource(R.drawable.city_day);
        }
    }

    @Override
    protected void callRequests() {
        Prefs prefs = new Prefs(requireContext());
        viewModel.getWeather(prefs.getCity());
    }

    @Override
    protected void setupViews() {
        viewModel = new ViewModelProvider(requireActivity()).get(WeatherViewModel.class);
    }
}