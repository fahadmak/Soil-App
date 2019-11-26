package com.example.bujimuapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.bujimuapp.converters.DateConverter;
import com.example.bujimuapp.dao.SoilAnalysisDao;
import com.example.bujimuapp.models.SoilAnalysis;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {SoilAnalysis.class}, version = 1, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class SoilAnalysisDatabase extends RoomDatabase {

    public abstract SoilAnalysisDao soilAnalysisDao();

    private static volatile SoilAnalysisDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static SoilAnalysisDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (SoilAnalysisDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SoilAnalysisDatabase.class, "soil_analysis_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
