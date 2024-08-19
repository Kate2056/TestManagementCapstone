package com.softwareengineering.testmanagementcapstone.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softwareengineering.testmanagementcapstone.R;
import com.softwareengineering.testmanagementcapstone.database.Repository;
import com.softwareengineering.testmanagementcapstone.entities.TestCase;
import com.softwareengineering.testmanagementcapstone.entities.TestResult;

import java.util.List;

public class TestHomeAdapter extends RecyclerView.Adapter<TestHomeAdapter.TestHomeViewHolder> {
    private List<TestCase> mTestCases;
    private List<TestResult> mTestResults;
    private final Context context;
    private final LayoutInflater mInflater;
    int userID;

    public class TestHomeViewHolder extends RecyclerView.ViewHolder{
        private final TextView nameText;
        private final TextView createText;
        private final TextView resultsText;
        public Repository repository;

        public TestHomeViewHolder(@NonNull View itemView){
            super(itemView);
            nameText = itemView.findViewById(R.id.name);
            createText = itemView.findViewById(R.id.dateCreated);
            resultsText = itemView.findViewById(R.id.resultsNum);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    int position = getAdapterPosition();
                    final TestCase current = mTestCases.get(position);
                    Intent intent = new Intent(context, TestCaseResults.class);
                    intent.putExtra("user", userID);
                    intent.putExtra(("id"), current.getTestID());
                    intent.putExtra(("name"), current.getName());
                    intent.putExtra(("steps"), current.getSteps());
                    intent.putExtra(("expected"), current.getExpectedResult());
                    intent.putExtra(("created"), current.getDateCreated());
                    intent.putExtra(("updated"), current.getDateUpdated());
                    context.startActivity(intent);

                }
            });

        }
    }
    @NonNull
    @Override
    public TestHomeAdapter.TestHomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View itemView = mInflater.inflate(R.layout.test_case_results_item, parent, false);
        return new TestHomeAdapter.TestHomeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TestHomeAdapter.TestHomeViewHolder holder, int position){
        if(mTestCases != null){
            TestCase current = mTestCases.get(position);
            String name = current.getName();
            String dateUpdated = current.getDateUpdated();
            String dateCreated = current.getDateCreated();
            int results = 0;
            int id = current.getTestID();
            for(TestResult r : mTestResults){
                if(r.getTestID() == current.getTestID()){
                    results = results + 1;
                }
            }

            holder.nameText.setText(name);
            holder.createText.setText(dateCreated);
            holder.resultsText.setText(Integer.toString(results));
        }
        else{
            holder.nameText.setText("No Test Name");
        }
    }

    @Override
    public int getItemCount(){
        if(mTestCases != null){
            return mTestCases.size();
        }
        else{
            return 0;
        }
    }

    public void setTestCases(List<TestCase> testcases, int iD, List<TestResult>testresults){
        mTestCases = testcases;
        userID = iD;
        mTestResults = testresults;
        notifyDataSetChanged();
    }

    public TestHomeAdapter(Context context){
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }
}
