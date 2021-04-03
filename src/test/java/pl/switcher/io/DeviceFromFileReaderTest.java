package pl.switcher.io;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeviceFromFileReaderTest {

    DeviceFromFileReader deviceFromFileReader = new DeviceFromFileReader();


    @Test
    void shouldReturnFalseFromIpv4Address(){
        //given

        //when

        boolean isIPv4 = deviceFromFileReader.isIPv4Address("192.168.0.-1");

        //then

        Assertions.assertEquals(false,isIPv4);
    }

    @Test
    void shouldReturnTrueFromIpv4Address(){
        //given

        //when

        boolean isIPv4 = deviceFromFileReader.isIPv4Address("192.168.0.1");

        //then

        Assertions.assertEquals(true,isIPv4);
    }

}