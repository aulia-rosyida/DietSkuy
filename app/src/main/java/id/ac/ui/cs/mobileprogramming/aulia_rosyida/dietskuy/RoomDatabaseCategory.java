package id.ac.ui.cs.mobileprogramming.aulia_rosyida.dietskuy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RoomDatabaseCategory extends AppCompatActivity {
    EditText editText;
    Button btAdd, btReset, mChooseBtn;
    RecyclerView recyclerView;
    ImageView mImageView;

    List<MainData> dataList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    RoomDB database;
    MainAdapter adapter;

    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_database_category);

        //assign variable
        editText = findViewById(R.id.edit_text);
        btAdd = findViewById(R.id.bt_add);
        btReset = findViewById(R.id.bt_reset);
        recyclerView = findViewById(R.id.recycler_view2);
        mImageView = findViewById(R.id.image_view);
        mChooseBtn = findViewById(R.id.choose_image_btn);

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

        mChooseBtn.setOnClickListener(view -> {
            //cek runtime permission
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_DENIED){
                    //izin belum diberikan, meminta request
                    String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                    //memberi pop-up untuk runtime permission
                    requestPermissions(permissions, PERMISSION_CODE);
                }
                else{ //izin sudah diberikan
                    pickImageFromGallery();
                }
            }
            else{
                //sistem OS kurang dari marshmallow
                pickImageFromGallery();
            }
        });
    }

    private void pickImageFromGallery() {
        //menggunakan intent untuk pick image
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }

    //handle hasil dari runtime permision

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_CODE:{
                if(grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED){
                    //izin sudah diberikan
                    pickImageFromGallery();
                }
                else{//izin tidak diberikan
                    Toast.makeText(this, R.string.explanation_denied_permission, Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            //set image to image view
            mImageView.setImageURI(data.getData());
        }
    }
}