package com.aware.cdm.record;

/**
 * Created by Krzysztof Balon on 2015-02-21.
 */
public class WifiSensorRecord implements ContextRecord {
    private final int id;
    private final long timestamp;
    private final String deviceId;
    private final String macAddress;
    private final String ssid;
    private final String bssid;

    public WifiSensorRecord(int id, long timestamp, String deviceId, String macAddress, String ssid, String bssid) {
        this.id = id;
        this.timestamp = timestamp;
        this.deviceId = deviceId;
        this.macAddress = macAddress;
        this.ssid = ssid;
        this.bssid = bssid;
    }

    public int getId() {
        return id;
    }

    public double getTimestamp() {
        return timestamp;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public String getSsid() {
        return ssid;
    }

    public String getBssid() {
        return bssid;
    }

    @Override
    public String toString() {
        return "WifiSensorRecord{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", deviceId='" + deviceId + '\'' +
                ", macAddress='" + macAddress + '\'' +
                ", ssid='" + ssid + '\'' +
                ", bssid='" + bssid + '\'' +
                '}';
    }
}
