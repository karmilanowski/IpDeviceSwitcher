package pl.switcher.io;


import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pl.switcher.model.DeviceType;

import static org.junit.jupiter.api.Assertions.*;


class DeviceFromFileReaderTest {

    DeviceFromFileReader deviceFromFileReader = new DeviceFromFileReader();

    @DisplayName("Should not be Ipv4 address")
    @ValueSource(strings = {"192.168.1.-1","192.168.1.a","10.0.1","0.0.0.0","192.168.1.256"})
    @ParameterizedTest(name= "{arguments} should not be Ipv4 address")
    void shouldReturnFalseFromIncorrectIpv4Address(String ipAddress){
        boolean IPv4 = deviceFromFileReader.isIPv4Address(ipAddress);

        assertThat(IPv4).isFalse();

    }

    @DisplayName("Should be Ipv4 address")
    @ValueSource(strings = {"192.168.1.1","10.0.0.1","1.0.0.1","192.168.1.255"})
    @ParameterizedTest(name= "{arguments} should be Ipv4 address")
    void shouldReturnTrueFromCorrectIpv4Address(String ipAddress){
        boolean IPv4 = deviceFromFileReader.isIPv4Address(ipAddress);

        assertThat(IPv4).isTrue();
    }

    @DisplayName("Should not be Screen ID number")
    @ValueSource(strings = {"a","$","-1","0","51"})
    @ParameterizedTest(name= "{arguments} should not be Screen ID number")
    void shouldReturnFalseFromIncorrectScreenIdNumber(String screenID){
        boolean isScreenIdNumber = deviceFromFileReader.isScreenIdNumber(screenID);

        assertThat(isScreenIdNumber).isFalse();

    }

    @DisplayName("Should be Screen ID number")
    @ValueSource(strings = {"1","50"})
    @ParameterizedTest(name= "{arguments} should be Screen ID number")
    void shouldReturnTrueFromCorrectScreenIdNumber(String screenID){
        boolean isScreenIdNumber = deviceFromFileReader.isScreenIdNumber(screenID);

        assertThat(isScreenIdNumber).isTrue();
    }

    @DisplayName("Should be not supported devices")
    @ValueSource(strings = {"Samsung","Lga","Atena"})
    @ParameterizedTest(name= "{arguments} should be not supported devices")
    void shouldReturnNullFromNotSupportedDevice(String deviceName){
        DeviceType supportDevice = deviceFromFileReader.isSupportDevice(deviceName);

        assertThat(supportDevice).isNull();

    }

    @DisplayName("Should be supported devices")
    @ValueSource(strings = {"Aten","ATEN","lG","LG","Lg"})
    @ParameterizedTest(name= "{arguments} should be supported devices")
    void shouldReturnNotNullFromSupportedDevice(String deviceName){
        DeviceType supportDevice = deviceFromFileReader.isSupportDevice(deviceName);

        assertThat(supportDevice).isNotNull();

    }


}