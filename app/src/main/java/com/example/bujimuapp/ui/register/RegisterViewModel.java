package com.example.bujimuapp.ui.register;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.bujimuapp.SoilAnalysisRepository;
import com.example.bujimuapp.models.User;

public class RegisterViewModel extends AndroidViewModel {
    // TODO: Implement the ViewModel
    private SoilAnalysisRepository soilAnalysisRepository;

    public RegisterViewModel(@NonNull Application application) {
        super(application);
        soilAnalysisRepository = new SoilAnalysisRepository(application);
    }

    public LiveData<User> isUserValid(String email, String password) {
        return soilAnalysisRepository.getUserLiveData(email, password);
    }

    public void insert(User user) {
        soilAnalysisRepository.insertUser(user);
    }
}