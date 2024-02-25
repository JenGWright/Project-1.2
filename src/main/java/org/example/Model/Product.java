package org.example.Model;

import java.util.Objects;


public class Product {
    public long productID;
    public String productName;
    public String sellerName;
    public double productPrice;

    //no args constructor - postman wasnt returning anything unless we had this
    public Product(){

    }

    public Product(long productID, String productName, String sellerName, double productPrice) {
        this.productID = productID;
        this.productName = productName;
        this.sellerName = sellerName;
        this.productPrice = productPrice;
    }

    public long getProductID() {
        return productID;
    }

    public void setProductID(long productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productID == product.productID && Double.compare(productPrice, product.productPrice) == 0 && Objects.equals(productName, product.productName) && Objects.equals(sellerName, product.sellerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productID, productName, sellerName, productPrice);
    }

    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productID +
                ", productName='" + productName + '\'' +
                ", sellerName='" + sellerName + '\'' +
                ", productPrice=" + productPrice +
                '}';
    }
}
