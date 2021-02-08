package com.example.bujimuapp.ui.login;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.bujimuapp.SoilAnalysisRepository;
import com.example.bujimuapp.models.User;

public class LoginViewModel extends AndroidViewModel {

    private SoilAnalysisRepository soilAnalysisRepository;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        soilAnalysisRepository = new SoilAnalysisRepository(application);
    }

    public LiveData<User> isUserValid(String email, String password) {
        return soilAnalysisRepository.getUserLiveData(email, password);
    }
}
