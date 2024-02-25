package online.hackpi.utils;

import java.io.FileInputStream;
import java.io.IOException;

public class LoadingProperties {
    private static final String absolute = ProductPath.ROOT.getValue() + "\\src\\main\\resources\\config.properties";
    public static void loadingProperties(){
        //        System.out.println("Path: " + System.getProperty("user.dir"));
        try(FileInputStream fileInputStream = new FileInputStream(absolute)){
            GlobalProperties.properties.load(fileInputStream);
        }catch (IOException ioException){
            System.err.println("Problem during create transaction file name: " + ioException.getMessage());
        }
    }
}
