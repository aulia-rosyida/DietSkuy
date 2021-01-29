package id.ac.ui.cs.mobileprogramming.aulia_rosyida.dietskuy;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

//definisikan nama tabel
@Entity(tableName = "table_name")
public class MainData implements Serializable {

    //create id column
    @PrimaryKey(autoGenerate = true)
    private int ID;

    //create text column
    @ColumnInfo(name = "text")
    private String text;

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getID() {
        return ID;
    }

    public String getText() {
        return text;
    }
}
