package com.aware.plugin.contextbroadcaster;

import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import com.aware.cdm.ContextRecordCreator;
import com.aware.cdm.record.ContextRecord;

/**
 * Created by Krzysztof Balon on 2015-02-21.
 */
public class ContextObserver extends ContentObserver {
    private static final String TAG = ContextObserver.class.getSimpleName();

    private final Uri contentUri;
    private final CursorPositioner cursorPositioner;
    private final ContextRecordCreator contextRecordCreator;

    public ContextObserver(Handler handler, Uri contentUri, CursorPositioner cursorPositioner, ContextRecordCreator contextRecordCreator) {
        super(handler);
        this.contentUri = contentUri;
        this.cursorPositioner = cursorPositioner;
        this.contextRecordCreator = contextRecordCreator;
    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        cursorPositioner.initialize();

        Cursor cursor;
        while ((cursor = cursorPositioner.moveToNext()) != null) {
            ContextRecord contextRecord = contextRecordCreator.createContextRecord(contentUri, cursor);
            Log.d(TAG, contextRecord.toString());
        }
    }
}
