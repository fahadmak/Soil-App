package com.example.bujimuapp.ui.home;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.bujimuapp.SoilAnalysisRepository;
import com.example.bujimuapp.models.SoilAnalysis;

import java.util.List;

public class HomeViewModel extends AndroidViewModel {

    private SoilAnalysisRepository mRepository;

    private LiveData<List<SoilAnalysis>> mAllSoilAnalysis;

    public HomeViewModel(Application application) {
        super(application);
        mRepository = new SoilAnalysisRepository(application);
        mAllSoilAnalysis = mRepository.getAllSoilAnalysis();
    }

    LiveData<List<SoilAnalysis>> getAllSoilAnalysis() {
        return mAllSoilAnalysis;
    }
}