package pl.switcher.model;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class DeviceProperties {
    private String deviceName;
    private String deviceIpAddress;
    private String deviceId;

}
