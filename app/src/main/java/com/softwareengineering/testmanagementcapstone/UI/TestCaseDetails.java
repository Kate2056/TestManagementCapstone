package com.softwareengineering.testmanagementcapstone.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.softwareengineering.testmanagementcapstone.MainActivity;
import com.softwareengineering.testmanagementcapstone.R;
import com.softwareengineering.testmanagementcapstone.database.Repository;
import com.softwareengineering.testmanagementcapstone.entities.Admin;
import com.softwareengineering.testmanagementcapstone.entities.TestCase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TestCaseDetails extends AppCompatActivity {
    TextView adminLoggedIn;
    int adminID;
    Repository repository;
    Admin currentAdmin;
    String name;
    String steps;
    String expected;
    String created;
    String updated;
    int testID;
    EditText editName;
    EditText editSteps;
    EditText editExpected;
    Date currentDate = new Date();
    String dateFormat = "MM/dd/yy HH:mm:ss";
    SimpleDateFormat df = new SimpleDateFormat(dateFormat, Locale.US);
    String currentDateFormatted = df.format(currentDate);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_test_case_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        repository = new Repository(getApplication());
        editName = findViewById(R.id.name);
        editSteps = findViewById(R.id.steps);
        editExpected = findViewById(R.id.expected);
        testID = getIntent().getIntExtra("id", -1);
        name = getIntent().getStringExtra("name");
        steps = getIntent().getStringExtra("steps");
        expected = getIntent().getStringExtra("expected");
        created = getIntent().getStringExtra("created");
        updated = getIntent().getStringExtra("updated");
        editName.setText(name);
        editSteps.setText(steps);
        editExpected.setText(expected);


        adminID = getIntent().getIntExtra("admin", -1);
        adminLoggedIn = findViewById(R.id.adminLoggedIn);
        for (Admin i : repository.getmAllAdmins()) {
            if (i.getUserID() == adminID) {
                currentAdmin = i;
            }
        }
        if (adminID != -1) {
            adminLoggedIn.setText(currentAdmin.getEmail());
        } else {
            Intent intent = new Intent(TestCaseDetails.this, MainActivity.class);
            startActivity(intent);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_test_case_details, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            this.finish();
            return true;
        }

        if(item.getItemId() == R.id.logout){
            Intent intent = new Intent(TestCaseDetails.this, MainActivity.class);
            startActivity(intent);
        }

        if(item.getItemId() == R.id.saveTest){
            TestCase testCase;

            if(testID == -1){
                if(repository.getmAllTestCases().isEmpty()){
                    testID = 1;
                }
                else{
                    testID = repository.getmAllTestCases().get(repository.getmAllTestCases().size() - 1).getTestID() + 1;
                }

                testCase = new TestCase(testID, editName.getText().toString(), editSteps.getText().toString(), editExpected.getText().toString(), created, currentDateFormatted);
                if(testCase.getName().isEmpty() || testCase.getSteps().isEmpty() || testCase.getExpectedResult().isEmpty()){
                    Toast.makeText(TestCaseDetails.this, "Please fill out all fields.", Toast.LENGTH_LONG).show();
                    return false;
                }
                repository.insert(testCase);
                Toast.makeText(TestCaseDetails.this, "Test Case Saved!", Toast.LENGTH_LONG).show();
                this.finish();
            }
            else{
                testCase = new TestCase(testID, editName.getText().toString(), editSteps.getText().toString(), editExpected.getText().toString(), created, currentDateFormatted);
                if(testCase.getName().isEmpty() || testCase.getSteps().isEmpty() || testCase.getExpectedResult().isEmpty()){
                    Toast.makeText(TestCaseDetails.this, "Please fill out all fields.", Toast.LENGTH_LONG).show();
                    return false;
                }
                repository.update(testCase);
                Toast.makeText(TestCaseDetails.this, "Test Case Saved!", Toast.LENGTH_LONG).show();
                this.finish();
            }
        }

        return true;
    }
}
