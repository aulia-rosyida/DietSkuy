package id.ac.ui.cs.mobileprogramming.aulia_rosyida.dietskuy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;

import id.ac.ui.cs.mobileprogramming.aulia_rosyida.dietskuy.ui.main.MainFragment;

public class MainActivity extends AppCompatActivity {
    // private OpenGLView openGLView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        // openGLView = findViewById(R.id.openGLView);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }

        //Change action bar title, if you dont change it will be according to your system default language
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));

    }

    // @Override
    // protected void onResume() {
    //     super.onResume();
    //     openGLView.onResume();
    // }

    // @Override
    // protected void onPause() {
    //     super.onPause();
    //     openGLView.onPause();
    // }
}