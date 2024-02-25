package org.example;


import Utility.ConnectionSingleton;
import io.javalin.Javalin;
import org.example.Controller.ProductController;
import org.example.DAO.ProductDAO;
import org.example.DAO.SellerDAO;
import org.example.Service.ProductService;
import org.example.Service.SellerService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.sql.Connection;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static Logger log = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        Connection conn = ConnectionSingleton.getConnection();
        SellerDAO sellerDAO = new SellerDAO(conn);
        ProductService productService = null;
        ProductDAO productDAO = new ProductDAO(conn,productService);
        SellerService sellerService = new SellerService(sellerDAO);
        productService = new ProductService(sellerDAO, productDAO);
        ProductController productController = new ProductController(sellerService, productService);
        Javalin api = productController.getAPI();

        api.start(9013);

    }
}



