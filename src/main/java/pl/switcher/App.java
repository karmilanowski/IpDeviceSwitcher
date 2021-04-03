package pl.switcher;

import pl.switcher.io.DeviceFromFileReader;
import pl.switcher.dto.DeviceDto;

import java.util.List;


public class App {

    public static void main(String[] args) {
        DeviceFromFileReader ipFileReader = new DeviceFromFileReader();
        List<DeviceDto> devicePropertiesList = ipFileReader.readPropertiesFromFile(":", "txt");
        devicePropertiesList.forEach(System.out::println);

    }
}
