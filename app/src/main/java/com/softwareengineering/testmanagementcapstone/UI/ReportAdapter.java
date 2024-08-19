package com.softwareengineering.testmanagementcapstone.UI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softwareengineering.testmanagementcapstone.R;
import com.softwareengineering.testmanagementcapstone.entities.TestCase;
import com.softwareengineering.testmanagementcapstone.entities.TestResult;

import java.util.List;

public class ReportAdapter  extends RecyclerView.Adapter<ReportAdapter.ReportViewHolder>{
    private List<TestResult> mTestResults;
    private List<TestCase> mTestCases;
    private final Context context;
    private final LayoutInflater mInflater;
    int userID;

    public class ReportViewHolder extends RecyclerView.ViewHolder{
        private final TextView name;
        private final TextView date;
        private final TextView result;


        public ReportViewHolder(@NonNull View itemView){
            super(itemView);
            result = itemView.findViewById(R.id.result);
            date = itemView.findViewById(R.id.date);
            name = itemView.findViewById(R.id.name);
        }
    }

    @NonNull
    @Override
    public ReportAdapter.ReportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View itemView = mInflater.inflate(R.layout.report_item, parent, false);
        return new ReportAdapter.ReportViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportAdapter.ReportViewHolder holder, int position){
        if(mTestResults != null){
            TestResult current = mTestResults.get(position);
            String result = current.getResult();
            String date = current.getDateTested();
            String name = "No name";
            int testID = current.getTestID();
            for(TestCase i : mTestCases){
                if(i.getTestID() == current.getTestID()){
                    name = i.getName();
                }
            }
            holder.result.setText(result);
            holder.name.setText(name);
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

    public void setTestResults(List<TestResult> testresults, List<TestCase> testcases, int iD){
        mTestResults = testresults;
        mTestCases = testcases;
        userID = iD;
        notifyDataSetChanged();
    }

    public ReportAdapter(Context context){
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

}
