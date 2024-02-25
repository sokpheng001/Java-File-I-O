package online.hackpi.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Product implements Serializable {
    @Serial
    private static final long serialVersionUID = 10L;
    private Integer id;
    private String name;
    private Double price;
    private Integer quantity;
    private String productCode;
    private String localDateTime;
    public Product(){}

    public Product(Integer id, String name, Double price, Integer quantity, String productCode, String localDateTime) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.productCode = productCode;
        this.localDateTime = localDateTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(String localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", productCode='" + productCode + '\'' +
                ", localDateTime=" + localDateTime +
                '}';
    }
}
