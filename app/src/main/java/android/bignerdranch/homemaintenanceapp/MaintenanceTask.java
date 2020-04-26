package android.bignerdranch.homemaintenanceapp;

public class MaintenanceTask {

    private int mId;
    private String mTask;
    private String mAdditionalInfo;

    public MaintenanceTask() {}

    public MaintenanceTask(String task, String nextDate, String frequency, String additionalInfo) {
        this.mTask = task;
        this.mAdditionalInfo = additionalInfo;
    }
    public MaintenanceTask(int id, String task, String nextDate, String frequency, String additionalInfo) {
        this.mId = id;
        this.mTask = task;
        this.mAdditionalInfo = additionalInfo;
    }

    public MaintenanceTask(String taskName) {
    }

    public MaintenanceTask(int taskId, String nextDate, int displayToDb, String additionalInfo) {
    }

    public int getId() {
        return mId;
    }
    public void setId(int id) {
        this.mId = id;
    }
    public String getTask() {
        return mTask;
    }
    public void setTask(String task) {
        this.mTask = task;
    }
    public String getAdditionalInfo() { return mAdditionalInfo; }
    public void setAdditionalInfo(String additionalInfo) { this.mAdditionalInfo = additionalInfo; }
}