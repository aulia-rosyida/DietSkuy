package id.ac.ui.cs.mobileprogramming.aulia_rosyida.dietskuy;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class FamilyContentProvider extends ContentProvider {
    private static final int FAMILY = 100;
    private static final int FAMILY_ID = 101;

    public static final String AUTHORITY_NAME = "id.ac.ui.cs.mobileprogramming.aulia_rosyida.dietskuy";

    public static final Uri FAMILY_URI = Uri.parse("content://" + AUTHORITY_NAME + "/" + FamilyDbContract.FamTable.TABLE_NAME);
    public static final Uri FAMILY_URI_ID = Uri.parse("content://" + AUTHORITY_NAME + "/" + FamilyDbContract.FamTable.TABLE_NAME + "/" + FAMILY_ID);

    private FamilyDbHelper famHelper;

    private static final UriMatcher famUriMatcher = buildUriMatcher();

    private static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

        matcher.addURI(AUTHORITY_NAME, FamilyDbContract.FamTable.TABLE_NAME, FAMILY);
        matcher.addURI(AUTHORITY_NAME, FamilyDbContract.FamTable.TABLE_NAME + "/*", FAMILY_ID);

        return matcher;
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();

        famHelper = new FamilyDbHelper(context);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Cursor cursor = null;

        final SQLiteDatabase db = famHelper.getReadableDatabase();

        switch (famUriMatcher.match(uri)){
            case FAMILY:
                cursor = db.query(FamilyDbContract.FamTable.TABLE_NAME, projection, selection,
                        selectionArgs, null, null, sortOrder);
                break;
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Uri turi = null;
        if(famHelper == null){
            famHelper = new FamilyDbHelper(FamilyDbActivity.getAppContext());
        }
        final SQLiteDatabase db = famHelper.getWritableDatabase();

        switch (famUriMatcher.match(uri)){
            case FAMILY:
                long _ID1 = db.insert(FamilyDbContract.FamTable.TABLE_NAME, null, values);

                if(_ID1 > 0){
                    turi = ContentUris.withAppendedId(FAMILY_URI, _ID1);
                    getContext().getContentResolver().notifyChange(turi, null);
                }
                break;

            default: throw new SQLException("Failed to insert row into "+ uri);
        }
        return turi;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int count = 0;
        final SQLiteDatabase db = famHelper.getWritableDatabase();

        switch (famUriMatcher.match(uri)){
            case FAMILY:
            case FAMILY_ID:
                count = db.update(FamilyDbContract.FamTable.TABLE_NAME, values, selection, selectionArgs);
                break;
        }
        return count;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;
        final SQLiteDatabase db = famHelper.getWritableDatabase();

        switch (famUriMatcher.match(uri)){
            case FAMILY:
            case FAMILY_ID:
                count = db.delete(FamilyDbContract.FamTable.TABLE_NAME, selection, selectionArgs);
                break;
        }
        return count;
    }
}
