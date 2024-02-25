package online.hackpi.controller;

import online.hackpi.service.Service;
import online.hackpi.service.ServiceImp;

import javax.net.ssl.SSLContextSpi;
import java.util.Scanner;

public class ProductController {
    private final Service service = new ServiceImp();
    public synchronized void createProductToTransaction(){
        service.writeDataToTransaction();
    }
    public synchronized void checkIsTransactionFileConsistsOfData(String message){
        service.checkTransactionFileConsistsOfData(message);
    }
    public synchronized void getAllProducts(){
        service.getAllProducts();
    }
    // random record
    public synchronized void randomRecord(int ...row){
        if(row.length==0){
            System.out.print("> Insert number of row: " );
            service.randomRecord(new Scanner(System.in).nextInt());
        }else {
            service.randomRecord(row[0]);
        }
    }
    public synchronized void commitDataToStore(){
        service.commitToStore(true);
    }
    public synchronized void deleteProduct(){
        System.out.print("> Insert product ID to delete: ");
        service.deleteData(new Scanner(System.in).nextInt());
    }
    public synchronized void searchProductById(){
        System.out.print("> Insert product ID to search: ");
        int id = new Scanner(System.in).nextInt();
        service.searchProductById(id);
    }
    //destroy
    public void destroyDataInDataSource(){
        service.destroyData();
    }
    public void loadAllProduct(){
        long startedTime = System.currentTimeMillis();
        ServiceImp.listAllProduct();
        long endedTime = System.currentTimeMillis();
        System.out.println("[+] Reading time spent: " + (endedTime-startedTime)/1000.0 + " seconds");
    }
    //set row
    public void setRow(){
        service.setRow();
    }
    public void updateProduct(){
        System.out.print("> Insert product ID to update: ");
        int id = new Scanner(System.in).nextInt();
        service.updateProductById(id);
    }
}
