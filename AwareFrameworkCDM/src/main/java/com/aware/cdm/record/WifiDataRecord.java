package com.aware.cdm.record;

/**
 * Created by Krzysztof Balon on 2015-02-21.
 */
public class WifiDataRecord implements ContextRecord {
    private final int id;
    private final long timestamp;
    private final String bssid;
    private final String ssid;
    private final String security;
    private final int frequency;
    private final int rssi;
    private final String label;

    public WifiDataRecord(int id, long timestamp, String bssid, String ssid, String security, int frequency, int rssi, String label) {
        this.id = id;
        this.timestamp = timestamp;
        this.bssid = bssid;
        this.ssid = ssid;
        this.security = security;
        this.frequency = frequency;
        this.rssi = rssi;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getBssid() {
        return bssid;
    }

    public String getSsid() {
        return ssid;
    }

    public String getSecurity() {
        return security;
    }

    public int getFrequency() {
        return frequency;
    }

    public int getRssi() {
        return rssi;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return "WifiDataRecord{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", bssid='" + bssid + '\'' +
                ", ssid='" + ssid + '\'' +
                ", security='" + security + '\'' +
                ", frequency=" + frequency +
                ", rssi=" + rssi +
                ", label='" + label + '\'' +
                '}';
    }
}
