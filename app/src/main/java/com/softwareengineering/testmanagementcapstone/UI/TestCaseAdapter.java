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
import com.softwareengineering.testmanagementcapstone.entities.TestCase;

import java.util.List;

public class TestCaseAdapter extends RecyclerView.Adapter<TestCaseAdapter.TestCaseViewHolder> {
    private List<TestCase> mTestCases;
    private final Context context;
    private final LayoutInflater mInflater;
    int adminID;

    public class TestCaseViewHolder extends RecyclerView.ViewHolder{
        private final TextView testCaseItemView;

        public TestCaseViewHolder(@NonNull View itemView){
            super(itemView);
            testCaseItemView = itemView.findViewById(R.id.testCaseView);
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    int position = getAdapterPosition();
                    final TestCase current = mTestCases.get(position);
                    Intent intent = new Intent(context, TestCaseDetails.class);
                    intent.putExtra("admin", adminID);
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
    public TestCaseAdapter.TestCaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View itemView = mInflater.inflate(R.layout.test_case_list_item, parent, false);
        return new TestCaseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TestCaseAdapter.TestCaseViewHolder holder, int position){
        if(mTestCases != null){
            TestCase current = mTestCases.get(position);
            String name = current.getName();
            holder.testCaseItemView.setText(name);
        }
        else{
            holder.testCaseItemView.setText("No Test Name");
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

    public void setTestCases(List<TestCase> testcases, int iD){
        mTestCases = testcases;
        adminID = iD;
        notifyDataSetChanged();
    }

    public TestCaseAdapter(Context context){
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }
}
