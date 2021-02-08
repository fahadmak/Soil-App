package com.example.bujimuapp.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bujimuapp.R;
import com.example.bujimuapp.SoilAnalysisViewModel;
import com.example.bujimuapp.models.SoilAnalysis;

import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.recyclerview);
        final SoilAnalysisListAdapter adapter = new SoilAnalysisListAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        homeViewModel =
                ViewModelProviders.of(requireActivity()).get(HomeViewModel.class);
        homeViewModel.getAllSoilAnalysis().observe(this, new Observer<List<SoilAnalysis>>() {
            @Override
            public void onChanged(List<SoilAnalysis> soilAnalyses) {
                adapter.setSoilAnalysisList(soilAnalyses);
                Log.d("Soil Analysis", "onChanged: " + soilAnalyses);
            }
        });
        adapter.setOnItemClickListener(new SoilAnalysisListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SoilAnalysis soilAnalysis) {
                Intent intent = new Intent(getActivity(), FarmDetailActivity.class);
                intent.putExtra("site_info", soilAnalysis.getSiteInfo());
                intent.putExtra("recommendation", soilAnalysis.getRecommendation());
                intent.putExtra("date", soilAnalysis.getDate());
                intent.putExtra("crop_grown", soilAnalysis.getCropGrown());
                startActivity(intent);
            }
        }

        );
        return root;
    }
}