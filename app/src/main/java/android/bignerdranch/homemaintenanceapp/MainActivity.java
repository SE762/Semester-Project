package android.bignerdranch.homemaintenanceapp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import androidx.annotation.RequiresApi;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity {

    private List<MaintenanceTask> tasks = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_activity_main_actionbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
*/

        DatabaseHandler db = new DatabaseHandler(this);
        tasks = db.getAllMaintenanceTasks();
        // If there are no maintenance items, populate the list with items for testing.
        if (tasks.size() == 0) {
            populateList();
            tasks = db.getAllMaintenanceTasks();
        }

        // Bind the list items to the activity's sole Listview.
        setListAdapter(new TaskAdapter(this, R.layout.task_row, tasks));
    }
    @Override
    /**
     * This method loads an item from the list in the AddTaskActivitiy for updating.
     */
    public void onListItemClick(ListView l, View view, int position, long id) {
        super.onListItemClick(l, view, position, id);
        // Load the item in a new activity for viewing/updating.
        Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
        // Pass along the id of the task to be updated.
        intent.putExtra("task_id", tasks.get(position).getId());
        // Open the item.
        startActivity(intent);
    }
    /**
     * onClick handler for the "Add new task" button.
     * Opens the same activity as the one used to updated existing tasks.
     */
    public void addTask(View view) {
        Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
        startActivity(intent);
    }
    /**
     * Reloads the activity with fresh data so that if the back button is clicked
     * after an item is updated, the new data shows in the list.
     */
    public void onResume() {
        super.onResume();
        DatabaseHandler db = new DatabaseHandler(this);
        tasks = db.getAllMaintenanceTasks();
        TaskAdapter<MaintenanceTask> taskAdapter = new TaskAdapter<>(this, R.layout.task_row, tasks);
        setListAdapter(taskAdapter);
    }

    /**
     * Populate the list with dummy content for testing.
     */
    private void populateList() {
        /* DatabaseHandler db = new DatabaseHandler(this);
                db.addMaintenanceTask(new MaintenanceTask("Clean chimney", "Capitol Area Chimney Sweep\nphone:717-555-1212"));
                db.addMaintenanceTask(new MaintenanceTask("Change Reverse Osmosis Filters", null));
                db.addMaintenanceTask(new MaintenanceTask("Change whole house water filter", "Walmart's filters are cheapest"));
                db.addMaintenanceTask(new MaintenanceTask("Clean gutters", null));
                db.addMaintenanceTask(new MaintenanceTask("Clean AC filters", null));
                db.addMaintenanceTask(new MaintenanceTask("Pump septic tank", "Baymont On-call - 717-555-1212"));
                db.addMaintenanceTask(new MaintenanceTask("Clean oven", null));
                db.addMaintenanceTask(new MaintenanceTask("Flush hot water heater", null));
                db.addMaintenanceTask(new MaintenanceTask("Service lawn mower", "Mower guy - 717-555-1212"));
                db.addMaintenanceTask(new MaintenanceTask("Service chainsaw",  "Mower guy - 717-555-1212"));
                db.addMaintenanceTask(new MaintenanceTask("Service weed whacker", "Mower guy - 717-555-1212"));
                db.addMaintenanceTask(new MaintenanceTask("Prune bushes", null));
                db.addMaintenanceTask(new MaintenanceTask("Winterize AC", null));
        */
    }

}// end public class MainActivity extends ListActivity.