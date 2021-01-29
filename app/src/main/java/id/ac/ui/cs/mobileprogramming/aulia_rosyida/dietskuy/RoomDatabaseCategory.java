package id.ac.ui.cs.mobileprogramming.aulia_rosyida.dietskuy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class RoomDatabaseCategory extends AppCompatActivity {
    EditText editText;
    Button btAdd, btReset;
    RecyclerView recyclerView;

    List<MainData> dataList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    RoomDB database;
    MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_database_category);

        //assign variable
        editText = findViewById(R.id.edit_text);
        btAdd = findViewById(R.id.bt_add);
        btReset = findViewById(R.id.bt_reset);
        recyclerView = findViewById(R.id.recycler_view2);

        //inisialisasi database
        database = RoomDB.getInstance(this);

        //simpan nilai database di data list
        dataList = database.mainDao().getAll();

        //inisialisasi linear layout manager
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        //inisialisasi adapter
        adapter = new MainAdapter(RoomDatabaseCategory.this, dataList);
        recyclerView.setAdapter(adapter);

        btAdd.setOnClickListener(view -> {
            //ambil string dari edit teks
            String sText = editText.getText().toString().trim();

            //cek kondisi
            if(!sText.equals("")){
                //ketika teksnya tidak kosong, inisialisasi MainData
                MainData data = new MainData();
                data.setText(sText);
                //melakukan insert teks pada database
                database.mainDao().insert(data);

                //hapus field edit text
                editText.setText("");

                //beri notifikasi ketika ada data yang di insert
                dataList.clear();
                dataList.addAll(database.mainDao().getAll());
                adapter.notifyDataSetChanged();
            }
        });

        btReset.setOnClickListener(view -> {
            //hapus semua data dari database
            database.mainDao().reset(dataList);

            //memberi notifikasi ketika semua data sudah selesai dihapus
            dataList.clear();
            dataList.addAll(database.mainDao().getAll());
            adapter.notifyDataSetChanged();
        });
    }
}