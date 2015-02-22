package com.aware.plugin.contextbroadcaster;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

/**
 * Created by Krzysztof Balon on 2015-02-22.
 */
public class LastRecordCursorPositioner implements CursorPositioner {
    private final Uri contentUri;
    private final ContentResolver contentResolver;
    private int cursorPosition = CURSOR_START_POSITION;
    private Cursor cursor;

    public LastRecordCursorPositioner(Uri contentUri, ContentResolver contentResolver) {
        this.contentUri = contentUri;
        this.contentResolver = contentResolver;
    }

    @Override
    public void init() {
        cursor = contentResolver.query(contentUri, null, null, null, null);
    }

    @Override
    public Cursor moveToNext() {
        if (cursor.moveToLast() && cursor.getPosition() > cursorPosition) {
            cursorPosition = cursor.getPosition();
        } else {
            cursor.close();
            cursor = null;
        }
        return cursor;
    }
}
