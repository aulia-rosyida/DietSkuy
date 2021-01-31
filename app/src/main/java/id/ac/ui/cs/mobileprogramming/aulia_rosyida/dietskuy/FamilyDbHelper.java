package id.ac.ui.cs.mobileprogramming.aulia_rosyida.dietskuy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FamilyDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "family.db";

    private static final String CREATE_FAMILY =
            "CREATE TABLE " + FamilyDbContract.FamTable.TABLE_NAME + " (" +
                    FamilyDbContract.FamTable._ID + " INTEGER PRIMARY KEY," +
                    FamilyDbContract.FamTable.COLUMN_NAME + " TEXT," +
                    FamilyDbContract.FamTable.COLUMN_AGE + " TEXT)";

    private static final String DELETE_FAMILY =
            "DROP TABLE IF EXISTS " + FamilyDbContract.FamTable.TABLE_NAME;

    public FamilyDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_FAMILY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_FAMILY);
        onCreate(db);
    }

    public void onDownGrade(SQLiteDatabase db, int oldVersion, int newVersion){
        onUpgrade(db, oldVersion, newVersion);
    }
}
