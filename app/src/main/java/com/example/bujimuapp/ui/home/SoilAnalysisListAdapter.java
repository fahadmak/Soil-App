package com.example.bujimuapp.ui.home;

import android.content.Context;
import android.content.Intent;
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position == RecyclerView.NO_POSITION) {
                        listener.onItemClick(mSoilAnalysisList.get(position));
                    }

                    SoilAnalysis soilAnalysis = mSoilAnalysisList.get(position);

                    Intent intent = new Intent(context, FarmDetailActivity.class);
                    intent.putExtra("site_info", soilAnalysis.getSiteInfo());
                    intent.putExtra("recommendation", soilAnalysis.getRecommendation());
                    intent.putExtra("date", dateFormat.format(soilAnalysis.getDate()));
                    intent.putExtra("crop_grown", soilAnalysis.getCropGrown());
                    context.startActivity(intent);
                }
            });
        }
    }

    private final LayoutInflater mInflater;
    private List<SoilAnalysis> mSoilAnalysisList;
    private OnItemClickListener listener;
    private Context context;
    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");


    public SoilAnalysisListAdapter(Context context) {
        this.context = context;
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
            String strDate = dateFormat.format(current.getDate());
            holder.dateItemView.setText(strDate);
        } else {
            // Covers the case of data not being ready yet.
            holder.nameItemView.setText("No Word");
        }
    }

    void setSoilAnalysisList(List<SoilAnalysis> soilAnalysisList) {
        mSoilAnalysisList = soilAnalysisList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mSoilAnalysisList != null)
            return mSoilAnalysisList.size();
        else return 0;
    }

    interface OnItemClickListener {
        void onItemClick(SoilAnalysis soilAnalysis);
    }

    void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.listener = itemClickListener;
    }

}
