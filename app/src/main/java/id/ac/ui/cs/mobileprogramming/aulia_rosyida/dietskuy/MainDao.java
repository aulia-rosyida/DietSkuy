package id.ac.ui.cs.mobileprogramming.aulia_rosyida.dietskuy;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface MainDao {
    //insert query
    @Insert(onConflict = REPLACE)
    void insert(MainData mainData);

    //hapus query
    @Delete
    void delete(MainData mainData);

    //hapus semua query
    @Delete
    void reset(List<MainData> mainData);

    //update query
    @Query("UPDATE table_name SET text = :sText WHERE ID = :sID")
    void update(int sID, String sText);

    //dapatkan semua query data
    @Query("SELECT * FROM table_name")
    List<MainData> getAll();

}
