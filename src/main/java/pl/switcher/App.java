package pl.switcher;

import pl.switcher.io.DeviceFromFileReader;
import pl.switcher.model.DeviceProperties;

import java.util.List;


public class App {

    public static void main(String[] args) {
        DeviceFromFileReader ipFileReader = new DeviceFromFileReader();
        List<DeviceProperties> devicePropertiesList = ipFileReader.readPropertiesFromFile();
        devicePropertiesList.forEach(System.out::println);

    }
}
