package id.ac.ui.cs.mobileprogramming.aulia_rosyida.dietskuy;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//menambahkan entitas-entitas database
@Database(entities = {MainData.class}, version = 1, exportSchema = false)
public abstract class RoomDB extends RoomDatabase {

    //membuat instance database
    private static RoomDB database;

    //definisikan nama database
    private static String DATABASE_NAME = "database";

    public synchronized static RoomDB getInstance(Context context){
        //mengecek kondisi
        if (database == null){
            //ketika database null, maka lakukan inisialisasi database
            database = Room.databaseBuilder(context.getApplicationContext(),
                    RoomDB.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        //return database
        return database;
    }

    //create Dao
    public abstract MainDao mainDao();
}
