package online.hackpi.utils;

import online.hackpi.dataStoreAsList.Store;
import online.hackpi.model.Product;

import java.util.List;
import java.util.Scanner;

public class TableUtils {
    public static void tableForProductList(List<Product> productList, int ...row){
        //        System.out.println(productList.size());
        System.out.println("=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+==+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
        System.out.println("|   ID   |        Product name       |     Price ($)     |     Quantity     |     Product code      |          Imported Date        |");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        if(productList.isEmpty() ){
            System.out.println("                                                          No Product yet                                                             ");
        }else {
            for(int i = 0; i< SetRow.row; i++){
                System.out.printf("   %d             %s                     %s                %s  " +
                                "                %s               %s      ", productList.get(i).getId(),
                        productList.get(i).getName(),productList.get(i).getPrice(),
                        productList.get(i).getQuantity(), productList.get(i).getProductCode(),
                        productList.get(i).getLocalDateTime().toString());
                System.out.println();
//            System.out.println("----------------------------------------------------------------------------------------");
                System.out.println("_____________________________________________________________________________________________________________________________________");
            }
//            productList.forEach(e->{
//                System.out.printf("   %d             %s                     %s                %s  " +
//                        "                %s               %s      ", e.getId(), e.getName(),e.getPrice(),e.getQuantity(), e.getProductCode(), e.getLocalDateTime().toString());
//                System.out.println();
////            System.out.println("----------------------------------------------------------------------------------------");
//                System.out.println("_____________________________________________________________________________________________________________________________________");
//            });
            System.out.printf("Page 1 of %d\n", Store.productList.size()/SetRow.row);
            System.out.printf("Active row: %d                                                                                                Row: %d\t\tColumn: 4\n",SetRow.row, Store.productList.size());
        }
        System.out.println("=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+==+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
    }
}
