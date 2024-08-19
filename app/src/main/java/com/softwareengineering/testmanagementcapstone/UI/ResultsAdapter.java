package com.softwareengineering.testmanagementcapstone.UI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softwareengineering.testmanagementcapstone.R;
import com.softwareengineering.testmanagementcapstone.entities.TestResult;

import java.util.List;

public class ResultsAdapter extends RecyclerView.Adapter<ResultsAdapter.ResultViewHolder> {
    private List<TestResult> mTestResults;
    private final Context context;
    private final LayoutInflater mInflater;
    int userID;

    public class ResultViewHolder extends RecyclerView.ViewHolder{
        private final TextView result;
        private final TextView date;

        public ResultViewHolder(@NonNull View itemView){
            super(itemView);
            result = itemView.findViewById(R.id.result);
            date = itemView.findViewById(R.id.date);
        }
    }

    @NonNull
    @Override
    public ResultsAdapter.ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View itemView = mInflater.inflate(R.layout.result_item, parent, false);
        return new ResultsAdapter.ResultViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultsAdapter.ResultViewHolder holder, int position){
        if(mTestResults != null){
            TestResult current = mTestResults.get(position);
            String result = current.getResult();
            String date = current.getDateTested();
            holder.result.setText(result);
            holder.date.setText(date);
        }
        else{
            holder.result.setText("No Test Result");
            holder.date.setText("No Date");
        }
    }

    @Override
    public int getItemCount(){
        if(mTestResults != null){
            return mTestResults.size();
        }
        else{
            return 0;
        }
    }

    public void setTestResults(List<TestResult> testresults, int iD){
        mTestResults = testresults;
        userID = iD;
        notifyDataSetChanged();
    }

    public ResultsAdapter(Context context){
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

}
