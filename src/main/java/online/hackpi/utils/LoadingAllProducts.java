package online.hackpi.utils;

import online.hackpi.dataStoreAsList.Store;
import online.hackpi.model.Product;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class LoadingAllProducts {
    private static final String [] animationChars = new String[]{"",".", "..", "...", "....",".....","\\"};

    public static List<Product> productList(){
        Store.productList = new ArrayList<>();
//        List<Product> productList = new ArrayList<>();
        LoadingProperties.loadingProperties();
        File file = new File(ProductPath.ROOT.getValue() + GlobalProperties.properties.getProperty("file-output"));
        if(!file.exists()){
            try{
                boolean isCreated = file.createNewFile();
            }catch (Exception exception){
                System.out.println("Problem during create output file: " + exception.getMessage());
            }
        }
        LoadingProperties.loadingProperties();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(ProductPath.ROOT.getValue() + GlobalProperties.properties.getProperty("file-output")))){
            String data;
            int numLines = 0;
            while ((data=bufferedReader.readLine())!=null){
//                System.out.print("\r[*] Reading ".toUpperCase() + animationChars[numLines % 4]);
//                numLines++;
                String [] strings = data.split(",");
                String date = strings[5];
                //String trimmedDateText = date.substring(0, date.lastIndexOf(".")); // Remove milliseconds
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
//                System.out.println(trimmedDateText );
                //trimmedDateText = trimmedDateText.trim(); // remove space
//                System.out.println(date);
                Store.productList.add(new Product(
                                Integer.valueOf(strings[0]),
                                strings[1],
                                Double.parseDouble(strings[2]),
                                Integer.parseInt(strings[3].replace(" ","")),
                                strings[4],
                                date
                        )
                );
//                System.out.flush();
            }
//            System.out.println();
//            System.out.println("[+] Reading completed.".toUpperCase(Locale.ROOT));
        }catch (Exception exception){
            System.out.println("Problem during read data from store: " + exception.getMessage());
        }
        return Store.productList.reversed();
//        return productList.reversed();
    }
}
