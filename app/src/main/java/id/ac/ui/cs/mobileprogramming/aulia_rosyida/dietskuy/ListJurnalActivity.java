package id.ac.ui.cs.mobileprogramming.aulia_rosyida.dietskuy;

import androidx.appcompat.app.AppCompatActivity;

// import android.content.Intent;
// import android.content.IntentFilter;
// import android.net.ConnectivityManager;
import android.os.Bundle;

// import id.ac.ui.cs.mobileprogramming.aulia_rosyida.dietskuy.Utility.NetworkChangeListener;

public class ListJurnalActivity extends AppCompatActivity {
    private TaskListFragment taskListFragment = new TaskListFragment();
    // NetworkChangeListener networkChangeListener = new NetworkChangeListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_jurnal);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, taskListFragment)
                .addToBackStack("task_list")
                .commit();
    }

    // @Override
    // protected void onStart() {
    //     IntentFilter filter = new IntentFilter();
    //     filter.addAction(CONNECTIVITY_DIAGNOSTICS_SERVICE);
    //     registerReceiver(networkChangeListener, filter);
    //     super.onStart();
    // }

    // @Override
    // protected void onStop() {
    //     unregisterReceiver(networkChangeListener);
    //     super.onStop();
    // }
}