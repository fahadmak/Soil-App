package com.example.bujimuapp.ui.home;

import android.content.Context;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bujimuapp.R;
import com.example.bujimuapp.models.SoilAnalysis;

import java.util.List;

public class SoilAnalysisListAdapter extends RecyclerView.Adapter<SoilAnalysisListAdapter.SoilAnalysisViewHolder> {

    public class SoilAnalysisViewHolder extends RecyclerView.ViewHolder {

        private final TextView nameItemView;
        private final TextView cropItemView;
        private final TextView dateItemView;

        public SoilAnalysisViewHolder(@NonNull View itemView) {
            super(itemView);
            this.nameItemView = itemView.findViewById(R.id.farm_name);
            this.cropItemView = itemView.findViewById(R.id.crop);
            this.dateItemView = itemView.findViewById(R.id.date);
        }
    }

    private final LayoutInflater mInflater;
    private List<SoilAnalysis> mSoilAnalysisList; // Cached copy of words

    public SoilAnalysisListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public SoilAnalysisViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recycler_item, parent, false);
        return new SoilAnalysisViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SoilAnalysisViewHolder holder, int position) {
        if (mSoilAnalysisList != null) {
            SoilAnalysis current = mSoilAnalysisList.get(position);
            holder.nameItemView.setText(current.getSiteInfo().getName());
            holder.cropItemView.setText(current.getCropGrown());
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            String strDate = dateFormat.format(current.getDate());
            holder.dateItemView.setText(strDate);
        } else {
            // Covers the case of data not being ready yet.
            holder.nameItemView.setText("No Word");
        }
    }

    void setSoilAnalysisList(List<SoilAnalysis> soilAnalysisList){
        mSoilAnalysisList = soilAnalysisList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mSoilAnalysisList != null)
            return mSoilAnalysisList.size();
        else return 0;
    }

}
