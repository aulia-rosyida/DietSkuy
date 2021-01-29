// package id.ac.ui.cs.mobileprogramming.aulia_rosyida.dietskuy.Utility;

// import android.app.AlertDialog;
// import android.content.BroadcastReceiver;
// import android.content.Context;
// import android.content.Intent;
// import android.view.Gravity;
// import android.view.LayoutInflater;
// import android.view.View;

// import androidx.appcompat.widget.AppCompatButton;

// import id.ac.ui.cs.mobileprogramming.aulia_rosyida.dietskuy.R;

// public class NetworkChangeListener extends BroadcastReceiver {

//     @Override
//     public void onReceive(Context context, Intent intent) {
//         if(!Common.isConnectedToInternet(context)){ //internet tidak connect
//             AlertDialog.Builder builder = new AlertDialog.Builder(context);
//             View layout_dialog = LayoutInflater.from(context).inflate(R.layout.check_internet_dialog, null);
//             builder.setView(layout_dialog);

//             AppCompatButton btnRetry = layout_dialog.findViewById(R.id.btnRetry);
//             //show dialog
//             AlertDialog dialog = builder.create();
//             dialog.show();
//             dialog.setCancelable(false);

//             dialog.getWindow().setGravity(Gravity.CENTER);
//             btnRetry.setOnClickListener(view -> {
//                 dialog.dismiss();
//                 onReceive(context, intent);
//             });
//         }
//     }
// }
