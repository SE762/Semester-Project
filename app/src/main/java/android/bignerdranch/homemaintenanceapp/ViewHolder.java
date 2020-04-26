package android.bignerdranch.homemaintenanceapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

    class ViewHolder {

        TextView task_name = null;
        ImageView task_wrench = null;
        ImageView divider = null;
        ImageView arrow = null;
        ViewHolder(View base) {
            this.task_name = (TextView) base.findViewById(R.id.task_name);
            this.task_wrench = (ImageView) base.findViewById(R.id.task_wrench);
            this.divider = (ImageView) base.findViewById(R.id.divider);
            this.arrow = (ImageView) base.findViewById(R.id.task_arrow);
        }
    }
