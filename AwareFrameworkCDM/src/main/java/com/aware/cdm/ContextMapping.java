package com.aware.cdm;

import android.net.Uri;
import com.aware.cdm.factory.ContextRecordFactory;
import com.aware.cdm.factory.WifiDataRecordFactory;
import com.aware.cdm.factory.WifiSensorRecordFactory;
import com.aware.providers.WiFi_Provider;
import com.google.common.collect.ImmutableMap;

import java.util.Map;
import java.util.Set;

/**
 * Created by Krzysztof Balon on 2015-02-21.
 */
public class ContextMapping {
    private static ContextMapping INSTANCE;
    public static ContextMapping getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ContextMapping(ImmutableMap.<Uri, ContextRecordFactory>builder()
                    .put(WiFi_Provider.WiFi_Sensor.CONTENT_URI, new WifiSensorRecordFactory())
                    .put(WiFi_Provider.WiFi_Data.CONTENT_URI, new WifiDataRecordFactory())
                    .build());
        }
        return INSTANCE;
    }

    private final Map<Uri, ContextRecordFactory> map;

    public ContextMapping(Map<Uri, ContextRecordFactory> map) {
        this.map = map;
    }

    public ContextRecordFactory getContextRecordFactory(Uri uri) {
        return map.get(uri);
    }

    public Set<Uri> getContextUriList() {
        return map.keySet();
    }
}
