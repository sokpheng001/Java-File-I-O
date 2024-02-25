package online.hackpi.service;

import online.hackpi.dataStoreAsList.Store;
import online.hackpi.model.Product;
import online.hackpi.soundProducing.CreateSound;
import online.hackpi.utils.*;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

public class ServiceImp implements Service{
    private static final String [] animationChars = new String[]{"",".", "..", "...", "....",".....","\\"};
    private final static Random random = new Random();
    private final static Scanner scanner = new Scanner(System.in);
    private final static Product product = new Product();
    private static String transactionFileName;
    private static final String root = ProductPath.ROOT.getValue();
    private final static String absoluteConfigurationFilePath = root + "\\src\\main\\resources\\config.GlobalProperties.properties";
    private final static String outputLocation = root + "\\src\\main\\java\\online\\hackpi\\output\\data.csv";
    // Define the characters that can be used in the random string
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    public static final Thread daemonThread = new Thread(()->{
        Store.productList = new ArrayList<>();
//        List<Product> productList = new ArrayList<>();
        LoadingProperties.loadingProperties();
        File file = new File(root + GlobalProperties.properties.getProperty("file-output"));
        if(!file.exists()){
            try{
                boolean isCreated = file.createNewFile();
            }catch (Exception exception){
                System.out.println("Problem during create output file: " + exception.getMessage());
            }
        }
        LoadingProperties.loadingProperties();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(root + GlobalProperties.properties.getProperty("file-output")))){
            String data;
            int numLines = 0;
            while ((data = bufferedReader.readLine())!=null){
//                System.out.print("\r[*] Reading ".toUpperCase() + animationChars[numLines % 4]);
                numLines++;
                String [] strings = data.split(",");
                String date = strings[5];
                String trimmedDateText = date.substring(0, date.lastIndexOf(".")); // Remove milliseconds
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
//                System.out.println(trimmedDateText );
                trimmedDateText = trimmedDateText.trim(); // remove space
//                System.out.println(date);
                Store.productList.add(new Product(
                                Integer.valueOf(strings[0]),
                                strings[1],
                                Double.parseDouble(strings[2]),
                                Integer.parseInt(strings[3].replace(" ","")),
                                strings[4],
                                trimmedDateText
                        )
                );

//                System.out.flush();
            }

//            System.out.println();
//            System.out.println("[+] Reading completed.".toUpperCase(Locale.ROOT));
        }catch (Exception exception){
            System.out.println("Problem during read data from store: " + exception.getMessage());
        }
//        Store.productList.reversed();
    });
    private void startDaemonReadAllProducts(){
        daemonThread.setDaemon(true);
        daemonThread.start();
    }
    private static String objectToStringForCSVFile(Product e, String status){
        if(status.equalsIgnoreCase("")){
            return e.getId() + ", "
                    + e.getName() +", "
                    + e.getPrice() + ", "
                    + e.getQuantity() +", "
                    + e.getProductCode() + ", "
                    + e.getLocalDateTime();
        }else {
            return e.getId() + ", "
                    + e.getName() +", "
                    + e.getPrice() + ", "
                    + e.getQuantity() +", "
                    + e.getProductCode() + ", "
                    + e.getLocalDateTime() + ", "
                    + status;
        }
    };
    // Method to generate a random string of length 3
    public static String generateRandomString() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(3); // Set initial capacity to 3
        for (int i = 0; i < 3; i++) {
            // Generate a random index within the range of available characters
            int randomIndex = random.nextInt(CHARACTERS.length());
            // Append the character at the random index to the string
            sb.append(CHARACTERS.charAt(randomIndex));
        }
        return sb.toString();
    }
    public static List<Product> listAllProduct() {
        Store.productList = new ArrayList<>();
//        List<Product> productList = new ArrayList<>();
        LoadingProperties.loadingProperties();
        File file = new File(root + GlobalProperties.properties.getProperty("file-output"));
        if(!file.exists()){
            try{
                boolean isCreated = file.createNewFile();
            }catch (Exception exception){
                System.out.println("Problem during create output file: " + exception.getMessage());
            }
        }
        LoadingProperties.loadingProperties();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(root + GlobalProperties.properties.getProperty("file-output")))){
            String data;
            int numLines = 0;
            while ((data=bufferedReader.readLine())!=null){
                System.out.print("\r[*] Reading ".toUpperCase() + animationChars[numLines % 4]);
                numLines++;
                String [] strings = data.split(",");
                String date = strings[5];
                //String trimmedDateText = date.substring(0, date.lastIndexOf(".")); // Remove milliseconds
                //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
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
            System.out.println();
//            System.out.println("[+] Reading completed.".toUpperCase(Locale.ROOT));
        }catch (Exception exception){
            System.out.println("Problem during read data from store: " + exception.getMessage());
        }
        return Store.productList.reversed();
//        return productList.reversed();
    }
    static private Product createProduct(){
//        System.out.print("> Insert product ID: ");

//        product.setId(scanner.nextInt());
        System.out.println("====");
        product.setId(random.nextInt(10000));
        String name;
        do {
            System.out.print("> Insert product name: ");
            name = scanner.nextLine();
            if(name.isEmpty()){
                System.out.println("[!] Opp product name is required.");
                CreateSound.windowsRingSound();
            }
        } while (name.isBlank());
        System.out.print("> Insert product price: ");
        Double price = new Scanner(System.in).nextDouble();
        product.setPrice(price);
        System.out.print("> Insert product quantity: ");
        Integer q = new Scanner(System.in).nextInt();
        product.setQuantity(q);
        if(name.length()>5){
            product.setName(name.substring(0,Math.min(name.length(),5))+".");
        }else {
            product.setName(name);
        }
//        System.out.print("> Insert product code: ");
//        product.setProductCode(scanner.nextLine());
        product.setProductCode(UUID.randomUUID().toString().substring(0,5));
        LocalDateTime date = LocalDateTime.now();
        product.setLocalDateTime(date.toString().substring(0,date.toString().lastIndexOf(".")));
        return product;
    }
    static private String generateTransactionFileName(){
        
        LoadingProperties.loadingProperties();
//        System.out.println(GlobalProperties.properties.getProperty("file-transaction"));
        transactionFileName =  "transaction" + ".dat";
        return root + GlobalProperties.properties.getProperty("file-transaction") + transactionFileName;
    }
    @Override
    public void updateFileTransactionName() {
        //
        LoadingProperties.loadingProperties();
        //
        System.out.println("[+] File: " + transactionFileName);
        GlobalProperties.properties.setProperty("transaction-file-name",transactionFileName);
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(absoluteConfigurationFilePath))){
            GlobalProperties.properties.store(bufferedWriter, "Updated transaction file name");
        }catch (Exception exception){
            System.err.println("Problem during update transaction file name: " + exception.getMessage());
        }
    }
    @Override
    public void writeDataToDataStore(String data, String dataStoreLocation, boolean append) {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(dataStoreLocation, append))){
            bufferedWriter.write(data);
            bufferedWriter.flush();
//            System.out.println(append);
            if(!data.equalsIgnoreCase("")){
                bufferedWriter.newLine();
            }
        }catch (Exception exception){
            System.err.println("Problem during write data to datastore: " + exception.getMessage());
        }
    }
    @Override
    public void commitDataToDataStore(String fileName, boolean append) {
        LoadingProperties.loadingProperties();
        long startedTime = System.currentTimeMillis();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))){
            String data;
//            System.out.println(fileName);
//            if(bufferedReader.readLine()==null){
//                System.out.println("[+] No data in transaction file to commit.".toUpperCase());
//            }
            while ((data=bufferedReader.readLine())!=null){
                //
//                Logic
                // check status
                String [] strings = data.split(", ");
                if(strings[6].trim().equalsIgnoreCase("new")){
                    data = data.substring(0,data.lastIndexOf(", "));
                    //                System.out.println(data);
                    writeDataToDataStore(data, outputLocation, append);
                }else if (strings[6].equalsIgnoreCase("delete")){
                    List<Product> productListAfterDeleted = LoadingAllProducts.productList().stream()
                            .filter(e->!e.getId().equals(Integer.valueOf(strings[0]))).toList();
                    //clear data from output source
                    writeDataToDataStore("",outputLocation,false);
                    //write to output source
                    productListAfterDeleted.forEach(e->{
                        writeDataToDataStore(objectToStringForCSVFile(e,""), outputLocation,append);
                    });
                    //load data to list again
                    LoadingAllProducts.productList();
//                    System.out.println("Deleted product.");
                }else if (strings[6].equalsIgnoreCase("update")){
                    List<Product> productList = LoadingAllProducts.productList();
                    System.out.println("Updated");
                    writeDataToDataStore("",outputLocation,false);
                    productList.stream()
                            .filter(e->e.getId().equals(Integer.valueOf(strings[0]))).forEach(e->{
                               e.setPrice(Double.parseDouble(strings[2]));
                               e.setQuantity(Integer.parseInt(strings[3]));
                               e.setName(strings[1]);
                            });
                    productList.reversed().forEach(e->{
                        writeDataToDataStore(objectToStringForCSVFile(e,""),outputLocation,append);
                    });
                    LoadingAllProducts.productList();
                }
                // logic
                data = data.substring(0, data.lastIndexOf(", "));
            }
            listAllProduct();//pull product to list
            System.out.println("[+] Data committed to data source successfully.");
            try(BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(fileName))){
                bufferedOutputStream.write("".getBytes());
                bufferedOutputStream.flush();
            }catch (Exception exception){
                System.out.println("Problem during clear data from transaction file: " + exception.getMessage());
            }
        }catch (Exception exception){
            System.out.println("Problem during commit data to datastore: " + exception.getMessage());
        }
        long endedTime = System.currentTimeMillis();
        System.out.println("Time spent: " + (endedTime-startedTime)/1000.0 + " seconds");
    }
// read object
    private void readObjects(String fileLocation, String message){
        File file = new File(fileLocation);
//        System.out.println("Transaction: " + fileLocation);
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(fileLocation))){
            String data;
            if((data=bufferedReader.readLine())!= null) {
                System.out.print(">>> " + message + " - preventing losing your data (Y\\N): ");
                CreateSound.alertSound();
                String opt = scanner.nextLine();
//                System.out.println(fileLocation);
                if (opt.equalsIgnoreCase("y")) {
                    commitDataToDataStore(fileLocation, true);
                } else {
                    try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(fileLocation))) {
                        bufferedOutputStream.flush();
                        bufferedOutputStream.write("".getBytes());
                    } catch (Exception exception) {
                        System.out.println("Problem during clear data from transaction file: " + exception.getMessage());
                    }
                }
            }
        }catch (Exception exception){
            System.err.println("Problem for checking is consisted of data in transaction file: " + exception.getMessage());
        }

    }
    @Override
    public void checkTransactionFileConsistsOfData(String message) {
        LoadingProperties.loadingProperties();
        String transactionFile = root + GlobalProperties.properties.getProperty("file-transaction") + GlobalProperties.properties.getProperty("transaction-file-name");
        File file = new File(transactionFile);
//        file.setWritable(true);
//        System.out.println(transactionFile);
//        System.out.println("[+] File existed: " + file.exists());
        if (file.exists()){
            readObjects(transactionFile, message);
        }
    }
    @Override
    public void writeObjectToTransactionFile(String transactionFileName, Product product, boolean append, String status){
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(transactionFileName, append))){
//            String data =
//                    product.getId() + ", " +
//                    product.getName() + ", " +
//                    product.getPrice() + ", " +
//                    product.getQuantity() + ", " +
//                    product.getProductCode() + ", " +
//                    product.getLocalDateTime() + ", " +
//                    status.toUpperCase(Locale.ROOT);
//            System.out.println(data);
            bufferedWriter.write(objectToStringForCSVFile(product,status));
            bufferedWriter.flush();
            bufferedWriter.newLine();
            updateFileTransactionName();
            System.out.println("[+] Successfully added new products.".toUpperCase());
        }catch (IOException ioException){
            System.err.println("Problem during write data to transaction file: " + ioException.getMessage());
        }
    }
    @Override
    public void commitToStore(boolean append) {
        LoadingProperties.loadingProperties();
        commitDataToDataStore(root +
                GlobalProperties.properties.getProperty("file-transaction") +
                "transaction.dat", true);
    }
    @Override
    public void writeDataToTransaction() {
//        System.out.println(GenerateKeyPairs.getKeyPairs());
        writeObjectToTransactionFile(generateTransactionFileName(), createProduct(), true, "New");
    }
    @Override
    public int deleteData(int id) {
//        Store.productList.stream().filter(e->e.getId().equals(id)).forEach(e->{
//            writeObjectToTransactionFile(generateTransactionFileName(), e,true);
//        }); // update
        List<Product> product = Store.productList.stream().filter(e->e.getId().equals(id)).toList();
//            System.out.println(e);
        writeObjectToTransactionFile(
                generateTransactionFileName(),
                product.getFirst(), true, "Delete");
        System.out.println("[+] Product ID " + id + " has been deleted.!!!".toUpperCase());
        return 1;
    }
    @Override
    public Product searchProductById(int id) {
        System.out.println("=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+==+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=++");;
        System.out.println("|   ID   |     Product name     |     Price ($)     |     Quantity     |     Product code      |          Imported Date        |");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++=++++");
        Store.productList.stream().filter(e-> e.getId().equals(id)).forEach(e->{
            System.out.printf("|  %d  |        %s         |       %s         |        %s   " +
                    "      |        %s         |      %s      |", e.getId(), e.getName(),e.getPrice(),e.getQuantity(), e.getProductCode(), e.getLocalDateTime().toString());
            System.out.println();
        });
//        System.out.println("=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+==+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
        System.out.println("................................................................................................................................");
        return null ;
    }
//    List all data from file
    @Override
    public List<Product> getAllProducts() {
//        listAllProduct().forEach(System.out::println);
        TableUtils.tableForProductList(Store.productList.reversed());
        return null;
    }
    @Override
    public void randomRecord(int row) {
        LoadingProperties.loadingProperties();
        long startedTime = System.currentTimeMillis();
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(
                root + GlobalProperties.properties.getProperty("file-output"), true))){
            for(int i = 1;i<=row;i++){
                // Display the loading animation
                System.out.print("\r[*] Writing Data ".toUpperCase() + animationChars[i % 4]);
                String date = LocalDateTime.now().toString();
                String trimmedDateText = date.substring(0, date.lastIndexOf(".")); // Remove milliseconds
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
//                System.out.println(trimmedDateText );
                trimmedDateText = trimmedDateText.trim(); // remove space
                String data = (random.nextInt(1000) + 1) + ", "
                        + "Apple" +", " + 4.5 +", " + 4 +", " + "00Sss" + ", " + trimmedDateText;
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.newLine();
                // Ensure the animation is visible by flushing the output stream
                System.out.flush();
            }
            System.out.println("\n[+] Writing completed!!!".toUpperCase(Locale.ROOT));
//            System.out.println("[+] Successfully added " + row + " new products.".toUpperCase());
        }catch (IOException ioException){
            System.err.println("Problem during write data to transaction file: " + ioException.getMessage());
        }
        long endedTime = System.currentTimeMillis();
        System.out.println("[+] Writing time spent: " +  (endedTime-startedTime)/1000.0 + " seconds");
        //
//        startDaemonReadAllProducts();
        LoadingAllProducts.productList();
    }
//    destroy data
    @Override
    public void destroyData() {
        try(BufferedReader bufferedReader = new BufferedReader(
                new FileReader(root + GlobalProperties.properties.getProperty("file-output")))) {
            if(bufferedReader.readLine()!=null){
                CreateSound.windowsRingSound();
                String opt = null;
                do {
                    System.out.print("[🥶] Are you sure to delete all data in data store [!!] (Y/N): ".toUpperCase(Locale.ROOT));
                    opt = new Scanner(System.in).nextLine();
                }while (opt.isEmpty());
                if(opt.equalsIgnoreCase("y")){
                    LoadingProperties.loadingProperties();
                    try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(
                            root + GlobalProperties.properties.getProperty("file-output")))){
                        LoadingProperties.loadingProperties();
                        bufferedWriter.write("");
                        bufferedWriter.flush();
                        // destroy
                        Store.productList = new ArrayList<>();
                        //
                    }catch (IOException exception){
                        System.out.println("[!] Problem during destroy data: " + exception.getMessage());
                    }
                    System.out.println("!😶 Data were destroyed. You have done.");
                }else {
                    System.out.println(">>> 😊 Your data are still alive.");
                }
            }else {
                System.out.println("[🥴] File doesn't data. XD");
            }
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }
    @Override
    public void setRow() {
        System.out.print("> Insert amount of set row: ");
        int newRow = new Scanner(System.in).nextInt();
        SetRow.row = Math.min(newRow, Store.productList.size());
    }

    @Override
    public void updateProductById(int id) {
        try{
            List<Product> product = Store.productList.stream().filter(e->e.getId().equals(id)).toList();
            product.getFirst().setName("sokpheng");
            product.getFirst().setPrice(0.0);
            product.getFirst().setQuantity(0);
//            System.out.println(e);
            writeObjectToTransactionFile(
                    generateTransactionFileName(),
                    product.getFirst(), true, "update");
            System.out.println("[+] Product ID " + id + " has been updated.!!!".toUpperCase());
        }catch (Exception exception){
            CreateSound.windowsRingSound();
            System.out.println("[!] Product not found!".toUpperCase(Locale.ROOT));
        }
    }
}
