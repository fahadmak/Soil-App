package com.example.bujimuapp.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DashboardViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DashboardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Bujimu App is a mobile app that enables you to analyze soil " +
                "nutrients and provides you with soil fertility rates");
    }

    public LiveData<String> getText() {
        return mText;
    }
}