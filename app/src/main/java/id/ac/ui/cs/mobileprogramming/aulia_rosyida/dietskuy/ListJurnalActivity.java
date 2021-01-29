package id.ac.ui.cs.mobileprogramming.aulia_rosyida.dietskuy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ListJurnalActivity extends AppCompatActivity {
    private TaskListFragment taskListFragment = new TaskListFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_jurnal);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, taskListFragment)
                .addToBackStack("task_list")
                .commit();
    }
}