package com.aware.plugin.contextbroadcaster;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

/**
 * Created by Krzysztof Balon on 2015-02-22.
 */
public class NewRecordsCursorPositioner implements CursorPositioner {
    private static final int CURSOR_START_POSITION = -1;

    public static NewRecordsCursorPositioner createInstancePositionedAtStart(Uri contentUri, ContentResolver contentResolver) {
        return new NewRecordsCursorPositioner(contentUri, contentResolver);
    }

    public static NewRecordsCursorPositioner createInstancePositionedAtEnd(Uri contentUri, ContentResolver contentResolver) {
        Cursor cursor = contentResolver.query(contentUri, null, null, null, null);
        int lastRecordPosition = cursor.getCount() - 1;
        cursor.close();
        return new NewRecordsCursorPositioner(contentUri, contentResolver, lastRecordPosition);
    }

    private final Uri contentUri;
    private final ContentResolver contentResolver;
    private int cursorPosition;
    private Cursor cursor;

    public NewRecordsCursorPositioner(Uri contentUri, ContentResolver contentResolver, int cursorPosition) {
        this.contentUri = contentUri;
        this.contentResolver = contentResolver;
        this.cursorPosition = cursorPosition;
    }

    public NewRecordsCursorPositioner(Uri contentUri, ContentResolver contentResolver) {
        this(contentUri, contentResolver, CURSOR_START_POSITION);
    }

    @Override
    public void init() {
        cursor = contentResolver.query(contentUri, null, null, null, null);
        cursor.moveToPosition(cursorPosition);
    }

    @Override
    public Cursor moveToNext() {
        if (cursor.moveToNext()) {
            cursorPosition = cursor.getPosition();
        } else {
            cursor.close();
            cursor = null;
        }
        return cursor;
    }
}
