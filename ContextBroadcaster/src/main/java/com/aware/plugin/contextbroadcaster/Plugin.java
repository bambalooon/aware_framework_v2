package com.aware.plugin.contextbroadcaster;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.Handler;
import com.aware.cdm.ContextMapping;
import com.aware.utils.Aware_Plugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Krzysztof Balon on 2015-02-20.
 */
public class Plugin extends Aware_Plugin {
    private static final String POIRECOMMENDER_PLUGIN = "POIRECOMMENDER_PLUGIN";

    private final Handler contextChangeHandler = new Handler();
    private List<ContextObserver> contextObservers;

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

        contextObservers = new ArrayList<>();
        for (Uri uri : ContextMapping.getInstance().getContextUriList()) {
            ContextObserver contextObserver = new ContextObserver(contextChangeHandler, uri, contentResolver);
            contentResolver.registerContentObserver(uri, true, contextObserver);
            contextObservers.add(contextObserver);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        for (ContextObserver contextObserver : contextObservers) {
            getContentResolver().unregisterContentObserver(contextObserver);
        }
        contextObservers = null;
    }
}
