package com.aware.plugin.contextbroadcaster;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import com.aware.cdm.record.ContextRecord;
import com.aware.cdm.ContextRecordCreator;

/**
 * Created by Krzysztof Balon on 2015-02-21.
 */
public class ContextObserver extends ContentObserver {
    private static final String TAG = ContextObserver.class.getSimpleName();

    private final Uri contentUri;
    private final ContentResolver contentResolver;
    private final ContextRecordCreator contextRecordCreator = new ContextRecordCreator();

    public ContextObserver(Handler handler, Uri contentUri, ContentResolver contentResolver) {
        super(handler);
        this.contentUri = contentUri;
        this.contentResolver = contentResolver;
    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        Cursor cursor = contentResolver.query(contentUri, null, null, null, null);

        if(cursor.moveToLast()) {
            ContextRecord contextRecord = contextRecordCreator.createContextRecord(contentUri, cursor);
            Log.d(TAG, contextRecord.toString());
        }
        cursor.close();
    }
}
