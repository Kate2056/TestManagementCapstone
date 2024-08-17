package com.softwareengineering.testmanagementcapstone.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
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
import com.softwareengineering.testmanagementcapstone.entities.TestResult;
import com.softwareengineering.testmanagementcapstone.entities.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TestCaseResultsDetails extends AppCompatActivity {
    TextView userLoggedIn;
    int userID;
    Repository repository;
    User currentUser;
    String name;
    String steps;
    String expected;
    String created;
    String updated;
    int testID;
    String results;
    RadioButton passedButton;
    RadioButton blockedButton;
    RadioButton failedButton;
    TextView nameText;
    TextView stepsText;
    TextView expectedText;
    TextView createdText;
    TextView updatedText;
    int testResultID;
    Date currentDate = new Date();
    String dateFormat = "MM/dd/yy HH:mm:ss";
    SimpleDateFormat df = new SimpleDateFormat(dateFormat, Locale.US);
    String currentDateFormatted = df.format(currentDate);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_test_case_results_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        repository = new Repository(getApplication());
        nameText = findViewById(R.id.name);
        stepsText = findViewById(R.id.steps);
        expectedText = findViewById(R.id.expected);
        createdText = findViewById(R.id.created);
        updatedText = findViewById(R.id.updated);
        testID = getIntent().getIntExtra("id", -1);
        name = getIntent().getStringExtra("name");
        steps = getIntent().getStringExtra("steps");
        expected = getIntent().getStringExtra("expected");
        created = getIntent().getStringExtra("created");
        updated = getIntent().getStringExtra("updated");
        nameText.setText(name);
        stepsText.setText(steps);
        expectedText.setText(expected);
        createdText.setText(created);
        updatedText.setText(updated);
        passedButton = findViewById(R.id.passed);
        blockedButton = findViewById(R.id.blocked);
        failedButton = findViewById(R.id.failed);

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
            Intent intent = new Intent(TestCaseResultsDetails.this, MainActivity.class);
            startActivity(intent);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_test_case_results_details, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }

        if (item.getItemId() == R.id.logout) {
            Intent intent = new Intent(TestCaseResultsDetails.this, MainActivity.class);
            startActivity(intent);
        }

        if(item.getItemId() == R.id.saveResults){
            TestResult testResult;

            if(passedButton.isChecked() || failedButton.isChecked() || blockedButton.isChecked()){
                if(passedButton.isChecked()){
                    results = "Passed";
                }else if(blockedButton.isChecked()){
                    results = "Blocked";
                }else if(failedButton.isChecked()){
                    results = "Failed";
                }
                if(repository.getmAllTestResults().isEmpty()){
                    testResultID = 1;
                }else{
                    testResultID = repository.getmAllTestResults().get(repository.getmAllTestResults().size() - 1).getResultID() + 1;
                }

                testResult = new TestResult(testResultID, results, currentDateFormatted, testID );
                repository.insert(testResult);
                Toast.makeText(TestCaseResultsDetails.this, "Results Saved!", Toast.LENGTH_LONG).show();
                this.finish();

            }
            else{
                Toast.makeText(TestCaseResultsDetails.this, "Please choose a result.", Toast.LENGTH_LONG).show();
                return false;
            }

        }
        return true;
    }
}
