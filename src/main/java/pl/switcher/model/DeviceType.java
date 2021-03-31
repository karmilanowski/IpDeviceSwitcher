package pl.switcher.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DeviceType {
    LG("LG"),
    ATEN("ATEN");

    private String name;


}
