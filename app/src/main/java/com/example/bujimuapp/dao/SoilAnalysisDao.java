package com.example.bujimuapp.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.bujimuapp.models.SoilAnalysis;

import java.util.List;

@Dao
public interface SoilAnalysisDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(SoilAnalysis analysis);

    @Query("SELECT * FROM soil_analysis_table ORDER BY date_analyzed DESC")
    LiveData<List<SoilAnalysis>> getAllSoilAnalysis();

    @Query("SELECT * FROM soil_analysis_table WHERE userId=:userId")
    LiveData<List<SoilAnalysis>> findAnalysesForUser(long userId);
}
