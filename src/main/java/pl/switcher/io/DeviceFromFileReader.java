package pl.switcher.io;

import lombok.extern.log4j.Log4j2;
import pl.switcher.exceptions.WrongFilePropertiesError;
import pl.switcher.dto.DeviceDto;
import pl.switcher.model.DeviceType;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Log4j2
public class DeviceFromFileReader {
    private File pathToFile;
    private Scanner scanner;


    public List<DeviceDto> readPropertiesFromFile(String argumentSeparator, String fileType){
        List<DeviceDto> devicePropertiesList = new ArrayList<>();
        pathToFile= new File(System.getProperty("user.dir") + "/ip." + fileType);
        try {
            scanner = new Scanner(pathToFile);
            while (scanner.hasNextLine()){
                String[] properties = scanner.nextLine().split(argumentSeparator);
                DeviceDto deviceProperties = validateAndCreateDevice(properties);
                log.info("Create new devices from file properties : " + deviceProperties);
                devicePropertiesList.add(deviceProperties);
            }

        } catch (FileNotFoundException e) {
            e.getMessage();
        }
        return devicePropertiesList;
    }

    protected DeviceDto validateAndCreateDevice(String[] properties) {
        String deviceName = properties[0];
        String deviceIpAddress = properties[1];
        String deviceId= properties[2];

        if(properties.length <3 || !isIPv4Address(deviceIpAddress) || !isScreenIdNumber(deviceId) || isSupportDevice( deviceName)==null) {
            log.error("Wrong parameters in file " + pathToFile);
            throw new WrongFilePropertiesError("Wrong parameters in file " + pathToFile);
        }

        return new DeviceDto(deviceName,deviceIpAddress,deviceId);
    }

    protected boolean isIPv4Address(String ipAddress) {
        if (ipAddress.isEmpty()) {
            return false;
        }
        try {
            Object res = InetAddress.getByName(ipAddress);
            return res instanceof Inet4Address;
        } catch (final UnknownHostException exception) {
            log.error("Wrong Device IP Address !");
            return false;
        }
    }


    //Screen ID from scope 1 to 50
    protected boolean isScreenIdNumber(String screenId){
        try {
            int value = Integer.parseInt(screenId);
            if(value<=0 || value > 50) {
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            log.error("Screen ID is not a number !");
            return false;
        }
    }

    protected DeviceType isSupportDevice(String deviceName) {
        DeviceType[] values = DeviceType.values();
        return Arrays.stream(values).filter(d->deviceName.equalsIgnoreCase(d.getName())).findAny().orElse(null);
    }


}
