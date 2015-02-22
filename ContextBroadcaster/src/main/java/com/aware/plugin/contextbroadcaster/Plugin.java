package com.aware.plugin.contextbroadcaster;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import com.aware.cdm.ContextMapping;
import com.aware.cdm.ContextRecordCreator;
import com.aware.utils.Aware_Plugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Krzysztof Balon on 2015-02-20.
 */
public class Plugin extends Aware_Plugin {
    private static final String POIRECOMMENDER_PLUGIN = "POIRECOMMENDER_PLUGIN";

    private final Handler contextChangeHandler = new Handler();
    private List<ContentObserver> contentObservers;

    @Override
    public void onCreate() {
        super.onCreate();

        TAG = POIRECOMMENDER_PLUGIN;
        CONTEXT_PRODUCER = new ContextProducer() {
            @Override
            public void onContext() {
                //do nothing
            }
        };

        ContentResolver contentResolver = getContentResolver();
        Context applicationContext = getApplicationContext();

        contentObservers = new ArrayList<>();
        for (Uri contentUri : ContextMapping.getInstance().getContextUriList()) {
            ContentObserver contextObserver = new ContextObserver(
                    contextChangeHandler,
                    contentUri,
                    NewRecordsCursorPositioner.createInstancePositionedAtEnd(contentUri, contentResolver),
                    new ContextRecordCreator(),
                    new ContextUpdateBroadcaster(applicationContext));
            contentResolver.registerContentObserver(contentUri, true, contextObserver);
            contentObservers.add(contextObserver);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        for (ContentObserver contentObserver : contentObservers) {
            getContentResolver().unregisterContentObserver(contentObserver);
        }
        contentObservers = null;
    }
}
