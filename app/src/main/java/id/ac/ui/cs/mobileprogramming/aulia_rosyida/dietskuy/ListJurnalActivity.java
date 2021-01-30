package id.ac.ui.cs.mobileprogramming.aulia_rosyida.dietskuy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

// import android.content.Intent;
// import android.content.IntentFilter;
// import android.net.ConnectivityManager;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;
import android.os.Bundle;

// import id.ac.ui.cs.mobileprogramming.aulia_rosyida.dietskuy.Utility.NetworkChangeListener;

public class ListJurnalActivity extends AppCompatActivity {
    private TaskListFragment taskListFragment = new TaskListFragment();
    // NetworkChangeListener networkChangeListener = new NetworkChangeListener();
    private ConnectivityManager mCManager;
    private ConnectivityManager.NetworkCallback mCallback;
    private AlertDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_jurnal);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, taskListFragment)
                .addToBackStack("task_list")
                .commit();

        mCManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkRequest request = new NetworkRequest.Builder().build();

        mCallback = new ConnectivityManager.NetworkCallback(){
            @Override
            public void onLost(@NonNull Network network) {
                //ketika koneksi network sedang lost, showing alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(ListJurnalActivity.this, R.style.Theme_AppCompat_Dialog_Alert)
                        .setTitle(R.string.no_internet_connection)
                        .setMessage(R.string.check_internet_connection)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //closing app
                                //methodnya dapat berjalan dengan minimum API 21
                                finishAndRemoveTask();
                            }
                        });
                //memunculkan dialog
                mDialog = builder.show();
            }

            @Override
            public void onAvailable( Network network) {
                //menutup dialog pop-up jika networknya kembali sebelum user accept alert dialog
                if(mDialog!=null){
                    mDialog.dismiss();
                }
            }
        };

        //melakukan callback dan network request
        mCManager.registerNetworkCallback(request, mCallback);
    }

    @Override
    protected void onStop() {
        //unregister callback ketika closing list jurnal activity
        mCManager.unregisterNetworkCallback(mCallback);
        super.onStop();
    }
}