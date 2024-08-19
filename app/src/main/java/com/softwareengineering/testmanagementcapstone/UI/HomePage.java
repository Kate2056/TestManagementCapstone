package com.softwareengineering.testmanagementcapstone.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

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
import com.softwareengineering.testmanagementcapstone.entities.TestCase;
import com.softwareengineering.testmanagementcapstone.entities.TestResult;
import com.softwareengineering.testmanagementcapstone.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomePage extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    TextView userLoggedIn;
    int userID;
    Repository repository;
    User currentUser;
    Spinner spinner;
    EditText editCriteria;
    String criteria;
    String searchBy = "all";
    String[] spinnerItems = new String[]{"Search All", "Name", "Steps", "Expected", "Created", "Updated"};
    ImageButton search;
    List<TestCase> tests;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        editCriteria = findViewById(R.id.criteria);
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(HomePage.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, spinnerItems);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        search = findViewById(R.id.searchButton);
        repository = new Repository(getApplication());
        tests = repository.getmAllTestCases();

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                criteria = editCriteria.getText().toString();
                tests = searchTests(criteria, searchBy);
                onResume();
            }
        });


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
            Intent intent = new Intent(HomePage.this, MainActivity.class);
            startActivity(intent);
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        List<TestCase> allTests = repository.getmAllTestCases();
        final TestCaseAdapter testCaseAdapter = new TestCaseAdapter(this);
        recyclerView.setAdapter(testCaseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        testCaseAdapter.setTestCases(allTests, userID);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id){
        switch(position){
            case 0:
                searchBy = "all";
                break;
            case 1:
                searchBy = "name";
                break;
            case 2:
                searchBy = "steps";
                break;
            case 3:
                searchBy = "expected";
                break;
            case 4:
                searchBy = "create";
                break;
            case 5:
                searchBy = "updated";
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
            searchBy = "all";
    }

    @Override
    protected void onResume(){
        super.onResume();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        List<TestCase> allTests = tests;
        final TestHomeAdapter testHomeAdapter = new TestHomeAdapter(this);
        recyclerView.setAdapter(testHomeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<TestResult> allResults = repository.getmAllTestResults();
        testHomeAdapter.setTestCases(allTests, userID, allResults);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.logout){
            Intent intent = new Intent(HomePage.this, MainActivity.class);
            startActivity(intent);
            return true;
        }

        if(item.getItemId() == R.id.report){
            Intent intent = new Intent(HomePage.this, CreateReport.class);
            intent.putExtra("user", userID);
            startActivity(intent);
            return true;
        }

        return true;
    }

    public List<TestCase> searchTests(String criteria, String selection){
        List<TestCase> allTests = repository.getmAllTestCases();
        List<TestCase> toReturn = new ArrayList<>();
        criteria = criteria.toLowerCase();

        if (Objects.equals(selection, "all")) {
            for(TestCase t : allTests){
                if(t.getName().toLowerCase().contains(criteria) || t.getSteps().toLowerCase().contains(criteria) || t.getExpectedResult().toLowerCase().contains(criteria) || t.getDateUpdated().toLowerCase().contains(criteria) || t.getDateCreated().toLowerCase().contains(criteria)){
                    toReturn.add(t);
                }
            }
        }
        else if(Objects.equals(selection, "name")){
            for(TestCase t : allTests){
                if(t.getName().toLowerCase().contains(criteria)){
                    toReturn.add(t);
                }
            }
        }
        else if(Objects.equals(selection, "steps")){
            for(TestCase t : allTests){
                if(t.getSteps().toLowerCase().contains(criteria)){
                    toReturn.add(t);
                }
            }
        }
        else if(Objects.equals(selection, "expected")){
            for(TestCase t : allTests){
                if(t.getExpectedResult().toLowerCase().contains(criteria)){
                    toReturn.add(t);
                }
            }
        }
        else if(Objects.equals(selection, "create")){
            for(TestCase t : allTests){
                if(t.getDateCreated().toLowerCase().contains(criteria)){
                    toReturn.add(t);
                }
            }
        }
        else if(Objects.equals(selection, "updated")){
            for(TestCase t : allTests){
                if(t.getDateUpdated().toLowerCase().contains(criteria)){
                    toReturn.add(t);
                }
            }
        }
        return toReturn;
    }
}
