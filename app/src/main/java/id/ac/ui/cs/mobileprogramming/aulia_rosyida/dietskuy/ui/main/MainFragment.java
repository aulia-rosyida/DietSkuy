package id.ac.ui.cs.mobileprogramming.aulia_rosyida.dietskuy.ui.main;

//import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import id.ac.ui.cs.mobileprogramming.aulia_rosyida.dietskuy.ListJurnalActivity;
import id.ac.ui.cs.mobileprogramming.aulia_rosyida.dietskuy.NewJurnalActivity;
import id.ac.ui.cs.mobileprogramming.aulia_rosyida.dietskuy.R;
import id.ac.ui.cs.mobileprogramming.aulia_rosyida.dietskuy.RoomDatabaseCategory;
import id.ac.ui.cs.mobileprogramming.aulia_rosyida.dietskuy.WorkoutActivity;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    Button b1, b2, b3;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_fragment, container, false);

        b1 = v.findViewById(R.id.workout_button);
        b2 = v.findViewById(R.id.list_button);
        b3 = v.findViewById(R.id.new_journal_button);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), WorkoutActivity.class));
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ListJurnalActivity.class));
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), NewJurnalActivity.class));
            }
        });

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        // TODO: Use the ViewModel
    }

}