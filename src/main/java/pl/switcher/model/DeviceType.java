package pl.switcher.model;

import pl.switcher.exceptions.NoSuchDeviceExecption;

import java.util.Arrays;

public enum DeviceType {
    LG("LG"),
    ATEN("ATEN");

    private String name;

    DeviceType(String name) {
        this.name = name;
    }

    public static DeviceType isSupportDevice(String deviceName) {
        DeviceType[] values = DeviceType.values();
        return Arrays.stream(values).filter(d->deviceName.equalsIgnoreCase(d.name)).findAny().orElse(null);
    }
}
