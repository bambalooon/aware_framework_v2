package com.aware.plugin.contextbroadcaster;

import android.database.Cursor;

/**
 * Created by Krzysztof Balon on 2015-02-22.
 */
public interface CursorPositioner {
    /**
     * Method initializes positioner with new records
     */
    void init();

    /**
     * @return Cursor located at one of records or null if there is none anymore (cursor closed when null returned)
     */
    Cursor moveToNext();
}
