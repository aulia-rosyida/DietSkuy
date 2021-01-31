package id.ac.ui.cs.mobileprogramming.aulia_rosyida.dietskuy;

import android.provider.BaseColumns;

public class FamilyDbContract {

    private FamilyDbContract(){}

    public static class FamTable implements BaseColumns {
        public static final String TABLE_NAME = "family";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_AGE = "age";
    }
}
