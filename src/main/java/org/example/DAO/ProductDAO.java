package org.example.DAO;

import org.example.Model.Product;

import org.example.Service.ProductService;
import org.example.Service.SellerService;
import org.h2.command.Prepared;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    Connection conn;

    ProductService productService;
    SellerService sellerService;

    public ProductDAO(Connection conn, ProductService productService) {
        this.conn = conn;
        this.productService = productService;
    }

    public void addProduct(Product p){
        try{
            PreparedStatement ps = conn.prepareStatement ("insert into PRODUCT" +
                    " (productID, productName, sellerName, productPrice) " +
                    "values (?,?,?,?)");
            ps.setLong(1, p.getProductID());
            ps.setString(2, p.getProductName());
            ps.setString(3, p.getSellerName());
            ps.setDouble(4, p.getProductPrice());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Product> getAllProducts(){
        List<Product>productList = new ArrayList<>();
        try{
            PreparedStatement ps = conn.prepareStatement("select * from PRODUCT");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                long productID = rs.getLong("productID");
                String productName = rs.getString("productName");
                String sellerName = rs.getString("sellerName");
                Double productPrice = rs.getDouble("productPrice");
                Product p = new Product(productID, productName, sellerName, productPrice);
                productList.add(p);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return productList;
    }

    public void deleteProductByID (Product currentProduct){
        try{
            PreparedStatement ps = conn.prepareStatement("delete from PRODUCT where productID = ?");
            ps.setLong(1, currentProduct.productID);
            ps.executeUpdate();
            ps.close();

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateProduct(Product updatedProduct){
        try{
            PreparedStatement ps = conn.prepareStatement(" Update PRODUCT Set productName = ?, sellerName = ?, productPrice = ?" +
                    "WHERE productID = ?");
            ps.setString(1, updatedProduct.getProductName());
            ps.setString(2, updatedProduct.getSellerName());
            ps.setDouble(3, updatedProduct.getProductPrice());
            ps.setLong(4, updatedProduct.getProductID());
            System.out.println(updatedProduct.getProductName() + " " + updatedProduct.getSellerName() + " " + updatedProduct.getProductPrice()
            + " " + updatedProduct.getProductID());
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


}

