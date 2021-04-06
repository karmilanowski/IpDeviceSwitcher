package pl.switcher.io;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;


class DeviceFromFileReaderTest {

    DeviceFromFileReader deviceFromFileReader = new DeviceFromFileReader();

    @DisplayName("Should not be Ipv4 address")
    @ValueSource(strings = {"192.168.1.-1","192.168.1.256"})
    @ParameterizedTest(name= "{arguments} should not be Ipv4 address")
    void shouldReturnFalseFromIncorrectIpv4Address(String ipAddress){
        boolean IPv4 = deviceFromFileReader.isIPv4Address(ipAddress);

        Assertions.assertThat(IPv4).isFalse();

    }

    @DisplayName("Should be Ipv4 address")
    @ValueSource(strings = {"192.168.1.1","192.168.1.255"})
    @ParameterizedTest(name= "{arguments} should be Ipv4 address")
    void shouldReturnTrueFromCorrectIpv4Address(String ipAddress){
        boolean IPv4 = deviceFromFileReader.isIPv4Address(ipAddress);

        assertTrue(IPv4);
    }

    @DisplayName("Should not be Screen ID number")
    @ValueSource(strings = {"a","$","-1","0","51"})
    @ParameterizedTest(name= "{arguments} should not be Screen ID number")
    void shouldReturnFalseFromIncorrectScreenIdNumber(String screenID){
        boolean isScreenIdNumber = deviceFromFileReader.isScreenIdNumber(screenID);

        assertFalse(isScreenIdNumber);

    }

    @DisplayName("Should be Screen ID number")
    @ValueSource(strings = {"1","50"})
    @ParameterizedTest(name= "{arguments} should be Screen ID number")
    void shouldReturnTrueFromCorrectScreenIdNumber(String screenID){
        boolean isScreenIdNumber = deviceFromFileReader.isScreenIdNumber(screenID);

        assertTrue(isScreenIdNumber);
    }

    @DisplayName("Should be not supported devices")
    @ValueSource(strings = {"Samsung","Lga","Atena"})
    @ParameterizedTest(name= "{arguments} should be not supported devices")
    void shouldReturnNullFromNotSupportedDevice(String deviceName){

        assertNull(deviceFromFileReader.isSupportDevice(deviceName));

    }

    @DisplayName("Should be supported devices")
    @ValueSource(strings = {"Aten","ATEN","lG","LG","Lg"})
    @ParameterizedTest(name= "{arguments} should be supported devices")
    void shouldReturnNotNullFromSupportedDevice(String deviceName){
        assertNotNull(deviceFromFileReader.isSupportDevice(deviceName));

    }


}