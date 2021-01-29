// package id.ac.ui.cs.mobileprogramming.aulia_rosyida.dietskuy.Utility;

// import android.content.Context;
// import android.net.ConnectivityManager;
// import android.net.Network;
// import android.net.NetworkCapabilities;

// public class Common {
//     private static boolean bool;

//     public static boolean isConnectedToInternet(Context context){

//         ConnectivityManager cm = (ConnectivityManager)
//                 context.getSystemService(context.CONNECTIVITY_SERVICE);

//         if (cm != null) {
//             final Network n = cm.getActiveNetwork();

//             if (n != null) {
//                 final NetworkCapabilities nc = cm.getNetworkCapabilities(n);
//                 bool = (nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI));
//             }
//         }
//         else{
//             bool= false;
//         }
//         return bool;
//     }
// }
