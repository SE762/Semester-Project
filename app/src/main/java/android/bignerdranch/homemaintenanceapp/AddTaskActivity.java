package android.bignerdranch.homemaintenanceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Arrays;

public class AddTaskActivity extends AppCompatActivity {

    private static final int DB_TO_DISPLAY = 0;
    private static final int DISPLAY_TO_DB = 1;
    private EditText mTaskName;
    private EditText mAdditionalInfo;
    private int mTaskId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        // Get task id from main activity for updating task.
        // If it don't exist, create a new task.
        Intent mIntent = getIntent();
        mTaskId = mIntent.getIntExtra("task_id", -1);

        mTaskName = (EditText) findViewById(R.id.editText_task_name);
        mAdditionalInfo = (EditText) findViewById(R.id.additional_info);

        Button mDelete = (Button) findViewById(R.id.delete);
        Button mAddUpdate = (Button) findViewById(R.id.submit);

        // Delete button is only enabled and shown on task update, not when a new task is created.
        mDelete.setEnabled(false);
        mDelete.setVisibility(View.GONE);

        // If a valid task ID exists, an item is being updated and data gets pulled into the fields.
        if (mTaskId != -1) {
            DatabaseHandler db = new DatabaseHandler(this);
            MaintenanceTask task = db.getMaintenanceTask(mTaskId);

            // Populate form fields w/ task data.
            mTaskName.setText(task.getTask());
            int index = Arrays.asList(task).indexOf(task.getTask());
            if (index != -1) {
                mTaskName.setSelection(index);
            }


            mAdditionalInfo.setText(task.getAdditionalInfo());

            // Enable and show delete button since we have a valid task to delete.
            mDelete.setEnabled(true);
            mDelete.setVisibility(View.VISIBLE);

            // Override the button's default text for adding a new task.
            mAddUpdate.setText(R.string.button_update);

            // Changes title in action bar to reflect that an item is being updated.
            setTitle(R.string.update_task);
        }
    }

    // Deletes specified task.
    public void deleteTask(View view) {
        DialogFragment newFragment = new DeleteDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", mTaskId);
        newFragment.setArguments(bundle);
        newFragment.show(getSupportFragmentManager(), "deleteTask");
    }

    // Adds new maintenance task.
    public void addTask(View view) {
        if (mTaskName != null) {
            DatabaseHandler db = new DatabaseHandler(this);
            long result;
            boolean error = false;

            // Validates task name.
            String taskName = mTaskName.getText().toString().trim();
            if (taskName.equals("")) {
                mTaskName.setError(getResources().getString(R.string.valid_name));
                error = true;
            }

            // If a valid id exists, update the item. Otherwise, add a new item.
            String nextTask = mTaskName.getText().toString();
            if (mTaskId == -1) {
                result = db.addMaintenanceTask(new MaintenanceTask(taskName), DISPLAY_TO_DB); mAdditionalInfo.getText().toString();
            }
            else
            {
                result = db.updateMaintenanceTask(new MaintenanceTask(mTaskId, mTaskName.getText().toString(),(DISPLAY_TO_DB), mAdditionalInfo.getText().toString()));
            }

            // Give feedback on success/failure of action.
            if (result > -1) {
                Toast.makeText(this, R.string.success, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, R.string.failure, Toast.LENGTH_SHORT).show();
            }
        }
    }
}

