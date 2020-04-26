package android.bignerdranch.homemaintenanceapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Arrays;


public class Task extends AppCompatActivity {

    private int mTaskId;
    private EditText mTaskName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        Intent mIntent = getIntent();
        mTaskId = mIntent.getIntExtra("task_id", -1);

    }


}
