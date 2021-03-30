package pl.switcher.io;

import lombok.extern.log4j.Log4j2;
import pl.switcher.exceptions.WrongFilePropertiesError;
import pl.switcher.model.DeviceProperties;
import pl.switcher.model.DeviceType;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Log4j2
public class DeviceFromFileReader {
    private  File pathToFile = new File(System.getProperty("user.dir") + "/ip.txt");
    private Scanner scanner;


    public List<DeviceProperties> readPropertiesFromFile(){
        List<DeviceProperties> devicePropertiesList = new ArrayList<>();
        try {
            scanner = new Scanner(pathToFile);
            while (scanner.hasNextLine()){
                String[] properties = scanner.nextLine().split(":");
                DeviceProperties deviceProperties = createDevice(properties);
                log.info("Create new devices from file properties : " + deviceProperties);
                devicePropertiesList.add(deviceProperties);
            }

        } catch (FileNotFoundException e) {
            log.error("File " + pathToFile + " doesnt exist !");
            e.getMessage();
        }
        return devicePropertiesList;
    }

    private DeviceProperties createDevice(String[] properties) {
        if(properties.length <3 || !isIPv4Address(properties[1]) || !isNumeric(properties[2]) || DeviceType.isSupportDevice(properties[0])==null) {
            log.error("Wrong parameters in file " + pathToFile);
            throw new WrongFilePropertiesError("Wrong parameters in file " + pathToFile);
        }

        String deviceName = properties[0];
        String deviceIpAddress = properties[1];
        String deviceId= properties[2];
        return new DeviceProperties(deviceName,deviceIpAddress,deviceId);
    }

    private boolean isIPv4Address(String address) {
        if (address.isEmpty()) {
            return false;
        }
        try {
            Object res = InetAddress.getByName(address);
            return res instanceof Inet4Address || res instanceof Inet6Address;
        } catch (final UnknownHostException exception) {
            log.error("Wrong Screen IP Address !");
            return false;
        }
    }

    private boolean isNumeric(String screenId){
        try {
            int Value = Integer.parseInt(screenId);
            return true;
        } catch (NumberFormatException e) {
            log.error("Wrong Screen ID !");
            return false;
        }
    }


}
