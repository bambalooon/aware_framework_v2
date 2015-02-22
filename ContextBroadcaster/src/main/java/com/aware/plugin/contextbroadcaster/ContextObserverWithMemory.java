package com.aware.plugin.contextbroadcaster;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import com.aware.cdm.ContextRecordCreator;
import com.aware.cdm.record.ContextRecord;

/**
 * Created by Krzysztof Balon on 2015-02-22.
 */
public class ContextObserverWithMemory extends ContentObserver {
    private static final String TAG = ContextObserverWithMemory.class.getSimpleName();
    private static final int CURSOR_START_POSITION = -1;

    public static ContextObserverWithMemory createInstanceLocatedAtStart(Handler handler, Uri contentUri, ContentResolver contentResolver) {
        return new ContextObserverWithMemory(handler, contentUri, contentResolver);
    }

    public static ContextObserverWithMemory createInstanceLocatedAtEnd(Handler handler, Uri contentUri, ContentResolver contentResolver) {
        Cursor cursor = contentResolver.query(contentUri, null, null, null, null);
        int initialPosition = cursor.getCount();
        cursor.close();
        return new ContextObserverWithMemory(handler, contentUri, contentResolver, initialPosition);
    }

    private final Uri contentUri;
    private final ContentResolver contentResolver;
    private final ContextRecordCreator contextRecordCreator = new ContextRecordCreator();
    private int lastReadRecordPosition;

    private ContextObserverWithMemory(Handler handler, Uri contentUri, ContentResolver contentResolver, int initialPosition) {
        super(handler);
        this.contentUri = contentUri;
        this.contentResolver = contentResolver;
        this.lastReadRecordPosition = initialPosition;
    }

    private ContextObserverWithMemory(Handler handler, Uri contentUri, ContentResolver contentResolver) {
        this(handler, contentUri, contentResolver, CURSOR_START_POSITION);
    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        Cursor cursor = contentResolver.query(contentUri, null, null, null, null);
        cursor.moveToPosition(lastReadRecordPosition);

        while(cursor.moveToNext()) {
            ContextRecord contextRecord = contextRecordCreator.createContextRecord(contentUri, cursor);
            Log.d(TAG, contextRecord.toString());
            lastReadRecordPosition = cursor.getPosition();
        }
        cursor.close();
    }
}
