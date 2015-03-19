package org.guides.content_provider.app;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;


public class StudentsContentProvider extends ContentProvider {

    private static final String AUTHORITY = "org.guides.content_provider.app";

    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    private static final int STUDENTS_ID = 0;

    private static final UriMatcher URI_MATCHER;

    static {
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

        URI_MATCHER.addURI(AUTHORITY,
                "students",
                STUDENTS_ID);

    }

    private StudentsDbHelper dbHelper;

    @Override
    public boolean onCreate() {
        dbHelper = new StudentsDbHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        switch (URI_MATCHER.match(uri)) {
            case STUDENTS_ID:
                builder.setTables(StudentContract.StudentEntry.TABLE_NAME);
                break;
            default:
                throw new RuntimeException("No Uri matched " + uri);

        }
        Cursor cursor = builder.query(db, projection, selection, selectionArgs, null, null, sortOrder);


        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }


    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long rowId = -1;
        switch (URI_MATCHER.match(uri)) {
            case STUDENTS_ID:
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                rowId = db.insert(StudentContract.StudentEntry.TABLE_NAME, null, values);
                break;
            default:
                throw new IllegalArgumentException(
                        "Unsupported URI for insertion: " + uri);
        }
        Uri itemUri = ContentUris.withAppendedId(uri, rowId);
        getContext().getContentResolver().notifyChange(itemUri, null);
        return itemUri;

    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
