package online.hackpi.view;

import online.hackpi.controller.ProductController;
import online.hackpi.soundProducing.CreateSound;


import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class View {
    private static final ProductController productController = new ProductController();
    private static Integer choose(){
        String choose = null;
        while (true){
         try{
             System.out.print("> Insert option: ");
             choose = new Scanner(System.in).nextLine();
             return Integer.parseInt(choose);
         }catch (Exception exception ){
             CreateSound.windowsRingSound();
             System.err.println("[!] Not Valid.");
         }
        }
    }
    private static void enter(){
        System.out.print(">>> Press to Continue...");
        new Scanner(System.in).nextLine();
    }
    private static Thread thread;
    public static void main(String[] args) throws IOException, InterruptedException {
        productController.checkIsTransactionFileConsistsOfData("Resolve your data to data store");
        Thread threadForGetAllProducts  = new Thread(productController::getAllProducts);
        productController.loadAllProduct();
        while (true){
            Thumbnail.choice();
            switch (choose()){
                case 1 -> {
//                    ProcessCommand.run();
                    productController.createProductToTransaction();
                    enter();
                }
                case 2->{
                    productController.getAllProducts();
                    System.out.print(">> Pagination (B/b to back): ");
                    String page = new Scanner(System.in).nextLine();
                }
                case 3 ->{
                    productController.updateProduct();
                }
                case 4->{
                    productController.deleteProduct();
                    enter();
                }
                case 7 ->{
                    productController.randomRecord();
                    enter();
                }
                case 8-> {
                    productController.commitDataToStore();
                }
                case 9 -> {
                    productController.searchProductById();
                    enter();
                }
                case 10-> {
                    productController.checkIsTransactionFileConsistsOfData("Commit your data to data store before close the software");
                    System.out.println("[+] Closing software.");
                    System.exit(0);
                }
                case 11 -> {
                    productController.destroyDataInDataSource();
                    enter();
                }
                case 0 ->{
                    productController.setRow();
                }
                default -> {
                    System.out.println("[!] Unexpected choice, please try again.");
                }
            }
        }
    }
}
