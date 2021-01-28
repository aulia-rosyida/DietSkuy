package id.ac.ui.cs.mobileprogramming.aulia_rosyida.dietskuy;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import id.ac.ui.cs.mobileprogramming.aulia_rosyida.dietskuy.databinding.TaskListItemBinding;
import id.ac.ui.cs.mobileprogramming.aulia_rosyida.dietskuy.databinding.FragmentTaskListBinding;

public class TaskListFragment extends Fragment {
    private FragmentTaskListBinding binding;
    private DetailsFragment detailsFragment = new DetailsFragment();

    public TaskListFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_task_list, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedViewModel viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        int count = 3;
        List<Item> list = new ArrayList<Item>();
        list.add(new Item("Selasa", "Proyek MPOS", "Fixing Bug"));
        list.add(new Item("Senin", "Training Lab RSE", "Belajar tentang ABS"));
        list.add(new Item("Rabu", "Beres-beres", "Beresin meja, kamar tidur, dan lemari"));

        if(list.size() == count) {
            RecylerNewAdapter adapter = new RecylerNewAdapter(list);
            binding.recyclerView.setAdapter(adapter);
            adapter.setListener((v, position) -> {
                viewModel.setSelected(adapter.getItemAt(position));
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.container, detailsFragment)
                        .addToBackStack(null)
                        .commit();
            });
        }
    }
}