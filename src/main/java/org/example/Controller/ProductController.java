package org.example.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import org.example.Exception.ProductException;
import org.example.Model.Product;
import org.example.Model.Seller;
import org.example.Service.ProductService;
import org.example.Service.SellerService;
import org.example.Exception.SellerException;

import java.util.ArrayList;
import java.util.List;

public class ProductController {

    SellerService sellerService;
    ProductService productService;

    public ProductController(SellerService sellerService, ProductService productService){
        this.sellerService = sellerService;
        this.productService = productService;
    }
    List<Product>getproductList = new ArrayList<>();

    public Javalin getAPI(){
        Javalin api = Javalin.create();

        api.get("health", context -> {context.result("Server is UP");});
        api.get("seller", context -> {
            List<Seller> sellerList = sellerService.getSellerList();
            context.json(sellerList);
        });
        api.get("product", context -> {
            List<Product> productList = productService.getAllProducts();
            context.json(productList);
        });
        api.post("seller", context -> {
            try {
                ObjectMapper om = new ObjectMapper();
                Seller s = om.readValue(context.body(), Seller.class);
                sellerService.addSeller(s);
                context.status(201);
            }catch(JsonProcessingException | SellerException e){
                e.printStackTrace();
                context.result(e.getMessage());
                context.status(400);
            }
        });
        api.post("product", context -> {
            try{
                ObjectMapper om = new ObjectMapper();
                Product p = om.readValue(context.body(), Product.class);
                Product newProduct = productService.addProduct(p);
                context.status(201);
                context.json(newProduct);
            }catch(JsonProcessingException e){
                context.result(e.getMessage());
                context.status(400);
            }catch(ProductException e){
                context.result(e.getMessage());
                context.status(400);
            }
        });
        api.get("product/{id}", context -> {
            long id = Long.parseLong(context.pathParam("id"));
            Product p = productService.getProductById(id);
            if(p ==null){
                context.status(404);
                context.result("The product ID entered was not found.");
            }else {
                context.json(p);
                context.status(200);
            }
        });
        api.delete("product/{id}", context -> {
            long id = Long.parseLong(context.pathParam("id"));
            Product p = productService.getProductById(id);
            if(p == null){
                context.status(404);
                context.result("Product ID entered was not found.");
            }else {
                productService.deleteProductByID(id);
                context.json(p);
                context.status(200);
                context.result("Product deleted");
            }
        });

        api.put("product/{id}", context -> {

            long id = Long.parseLong(context.pathParam("id"));
            ObjectMapper om = new ObjectMapper();
            Product updatedProduct = om.readValue(context.body(), Product.class);

            productService.updateProduct(id, updatedProduct);

            context.status(200);
            context.result("Product was updated");
        });


        return api;

    }

}