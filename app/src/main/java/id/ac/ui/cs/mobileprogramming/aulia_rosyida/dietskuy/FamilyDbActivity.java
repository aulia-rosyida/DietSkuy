package id.ac.ui.cs.mobileprogramming.aulia_rosyida.dietskuy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class FamilyDbActivity extends AppCompatActivity {
    EditText edname, edage;
    List<Family> familyList;
    int selectPos = -1;
    TextView txtadd, txtdelete, txtreset;
    ListView lvfamily;
    String[] listItem;
    ArrayAdapter<String> adapter;
    FamilyDbHelper dhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_family_db);

        // edname = (EditText) findViewById(R.id.input_name);
        // edage = (EditText) findViewById(R.id.input_age);

        // txtadd = (TextView) findViewById(R.id.update_family);
        // txtadd.setOnClickListener(view -> {
        //     if(selectPos != -1){
        //         UpdateFamily();
        //     }else{
        //         AddFamily();
        //     }
        // });
        // txtdelete = (TextView) findViewById(R.id.delete_family);
        // txtdelete.setOnClickListener(view -> {
        //     DeleteFamily();
        // });
        // txtreset = (TextView) findViewById(R.id.reset_family);
        // txtreset.setOnClickListener(view -> {
        //     if(selectPos!=-1){
        //         selectPos=-1;
        //         edname.setText("");
        //         edage.setText("");
        //         txtadd.setText("Add");
        //         txtadd.setVisibility(View.GONE);
        //     }else{
        //         Toast.makeText(getApplicationContext(), R.string.not_added, Toast.LENGTH_SHORT).show();
        //     }
        // });
        // familyList= new ArrayList<>();
        // lvfamily = (ListView) findViewById(R.id.listView_family);
        // adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, listItem);
        // lvfamily.setAdapter(adapter);

        // lvfamily.setOnItemClickListener(new AdapterView.OnItemClickListener(){
        //     @Override
        //     public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        //         selectPos = position;
        //         edname.setText(familyList.get(selectPos).name);
        //         edage.setText(String.valueOf(familyList.get(selectPos).age));
        //         txtadd.setText(R.string.update);
        //         txtdelete.setVisibility(View.VISIBLE);

        //     }
        // });
        // getFamily();

    }

    private void DeleteFamily() {
        int de = getContentResolver().delete(FamilyContentProvider.FAMILY_URI_ID, FamilyDbContract.FamTable._ID+"=?", new String[]{});
        if(de>0){
            familyList.remove(selectPos);
            selectPos=-1;
            edname.setText("");
            edage.setText("");
        }

    }

    private void getFamily() {
        String sortOrder = FamilyDbContract.FamTable.COLUMN_NAME + " ASC";
        String selection = null;
        String[] selectionArgs = null;
        Cursor cursor = getContentResolver().query(FamilyContentProvider.FAMILY_URI, null, selection, selectionArgs, sortOrder);

        if(cursor!=null && cursor.getCount()>0){
            cursor.moveToFirst();

            Family sobj = null;
            do{
                sobj = new Family(cursor.getInt(0), cursor.getString(1), cursor.getInt(2));
                familyList.add(sobj);
            } while(cursor.moveToNext());
        }
        adapter.notifyDataSetChanged();
    }

    public void AddFamily(){
        ContentValues values = new ContentValues();
        values.put(FamilyDbContract.FamTable.COLUMN_NAME, edname.getText().toString());
        values.put(FamilyDbContract.FamTable.COLUMN_AGE, edage.getText().toString());
        Uri uri = getContentResolver().insert(FamilyContentProvider.FAMILY_URI, values);

        long rid = Long.valueOf(uri.getLastPathSegment());
        Log.e("id", uri.getLastPathSegment());
        if(rid>0){
            Family sobj = new Family((int) rid, edname.getText().toString(), Integer.valueOf(edage.getText().toString()));
            familyList.add(sobj);
            adapter.notifyDataSetChanged();
            edage.setText("");
            edname.setText("");
        }else{
            Toast.makeText(getApplicationContext(), R.string.not_added, Toast.LENGTH_SHORT).show();
        }
    }
    public void UpdateFamily(){
        dhelper = new FamilyDbHelper(getApplicationContext());
        SQLiteDatabase db = dhelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FamilyDbContract.FamTable.COLUMN_NAME, edname.getText().toString());
        values.put(FamilyDbContract.FamTable.COLUMN_AGE, edage.getText().toString());

        //row yang akan diupdate, berdasarkan judul
        String selection = FamilyDbContract.FamTable._ID + " = ?";

        String[] selectionArgs = {String.valueOf(familyList.get(selectPos).id)};

        int cnt = db.update(
                FamilyDbContract.FamTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);

//        int cnt2 = getContentResolver().update(FamilyContentProvider.FAMILY_URI_ID, values, FamilyDbContract.FamTable._ID+"=?", new);

        if(cnt>0){
            Family sobj = familyList.get(selectPos);
            sobj.name = edname.getText().toString();
            sobj.age = Integer.valueOf(edage.getText().toString());
            familyList.set(selectPos,sobj);

            adapter.notifyDataSetChanged();
        }

    }
}