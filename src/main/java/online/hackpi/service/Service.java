package online.hackpi.service;

import online.hackpi.model.Product;
import java.util.List;

public interface Service {
    void writeDataToDataStore(String data, String dataStoreLocation, boolean append);
    void commitDataToDataStore(String fileLocation, boolean append);
    void checkTransactionFileConsistsOfData(String message);
    void updateFileTransactionName();
    void writeDataToTransaction();
    void writeObjectToTransactionFile(String fileLocation, Product productList, boolean append, String status);
    //commit data
    void commitToStore(boolean append);
    //
    List<Product> getAllProducts();
    //Random record
    void randomRecord(int row);
    //DELETE data
    int deleteData(int id);
    //search
    Product searchProductById(int id);
    //destroy data
    void destroyData();
    void setRow();
    void updateProductById(int id);
}
