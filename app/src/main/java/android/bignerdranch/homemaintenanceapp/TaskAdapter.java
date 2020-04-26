package android.bignerdranch.homemaintenanceapp;

import android.content.Context;;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;
import java.util.List;

class TaskAdapter<T> extends ArrayAdapter<MaintenanceTask> {

        private final ArrayList<MaintenanceTask> tasks;

    public TaskAdapter(Context context, int resource, List<MaintenanceTask > objects){
                super(context, resource, objects);
                this.tasks = (ArrayList<MaintenanceTask>) objects;
            }

            @Override
            public int getCount () {
                return tasks.size();
            }

            @Override
            public MaintenanceTask getItem ( int position){
                return null;
            }

            @Override
            public long getItemId ( int position){
                return 0;
            }

    @Override
        public @NonNull View getView(int position, View convertView, @NonNull ViewGroup parent) {

            View v = convertView;

            // First check to see if the view is null. If so, we have to render it.
            if (v == null) {
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = inflater.inflate(R.layout.task_row, parent, false);
            }// end if (Inflater)

                ViewHolder holder = (ViewHolder) v.getTag();
                if (holder == null) {
                    holder = new ViewHolder(v);
                    v.setTag(holder);
                }// end if (ViewHolder)

                // Set the alternating background color for the task list.
                if (position % 2 == 0) {
                    v.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.even_bg));
                }// end if (BackGroundColor)
                else {
                    v.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.odd_bg));
                }// end else (BackGroundColor)

                // Get dynamic data.
                if (tasks.get(position).getTask() != null) {
                    holder.task_name.setText(String.valueOf(tasks.get(position).getTask()));
                }// end if (tasks)

                // Get static fields.
                holder.task_wrench.setImageResource(R.mipmap.ic_launcher);
                holder.divider.setImageResource(R.drawable.divider);
                holder.arrow.setImageResource(R.drawable.arrow3);
                return v;
            }
        }