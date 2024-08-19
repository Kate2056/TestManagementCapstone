package com.softwareengineering.testmanagementcapstone.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.softwareengineering.testmanagementcapstone.MainActivity;
import com.softwareengineering.testmanagementcapstone.R;
import com.softwareengineering.testmanagementcapstone.database.Repository;
import com.softwareengineering.testmanagementcapstone.entities.Report;
import com.softwareengineering.testmanagementcapstone.entities.TestCase;
import com.softwareengineering.testmanagementcapstone.entities.TestResult;
import com.softwareengineering.testmanagementcapstone.entities.User;

import java.util.ArrayList;
import java.util.List;

public class CreateReport extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    TextView userLoggedIn;
    int userID;
    Repository repository;
    User currentUser;
    List<String> spinnerItems = new ArrayList<>();
    Spinner spinner;
    ImageButton search;
    String report = "none";
    List<TestResult> reportShow;
    List<TestCase> testCases;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_report);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        repository = new Repository(getApplication());
        for(Report r : repository.getmAllReports()){
            spinnerItems.add(r.getReportName());
        }
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CreateReport.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, spinnerItems);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        search = findViewById(R.id.searchButton);

        userID = getIntent().getIntExtra("user", -1);
        userLoggedIn = findViewById(R.id.userLoggedIn);

        for (User i : repository.getmAllUsers()) {
            if (i.getUserID() == userID) {
                currentUser = i;
            }
        }
        if (userID != -1) {
            userLoggedIn.setText(currentUser.getEmail());
        } else {
            Intent intent = new Intent(CreateReport.this, MainActivity.class);
            startActivity(intent);
        }

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reportShow = createReport(report);
                onResume();
            }
        });
        testCases = repository.getmAllTestCases();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        final ReportAdapter reportAdapter = new ReportAdapter(this);
        recyclerView.setAdapter(reportAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        reportAdapter.setTestResults(reportShow, testCases, userID);
    }
    @Override
    protected void onResume(){
        super.onResume();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        final ReportAdapter reportAdapter = new ReportAdapter(this);
        recyclerView.setAdapter(reportAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        reportAdapter.setTestResults(reportShow, testCases, userID);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
        report = spinner.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(CreateReport.this, "Please choose a report", Toast.LENGTH_LONG).show();
    }


    public List<TestResult> createReport(String type){
        List<TestResult> allResults = repository.getmAllTestResults();
        List<TestResult> toReturn = new ArrayList<>();

        for(TestResult t : allResults){
            if(t.getResult().equalsIgnoreCase(type)){
                toReturn.add(t);
            }
        }
        return toReturn;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.logout){
            Intent intent = new Intent(CreateReport.this, MainActivity.class);
            startActivity(intent);
            return true;
        }

        return true;
    }
}
