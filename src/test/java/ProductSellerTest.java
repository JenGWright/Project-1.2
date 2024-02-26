import Utility.ConnectionSingleton;
import org.example.DAO.ProductDAO;
import org.example.DAO.SellerDAO;
import org.example.Exception.ProductException;
import org.example.Exception.SellerException;
import org.example.Model.Product;
import org.example.Model.Seller;
import org.example.Service.ProductService;
import org.example.Service.SellerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class ProductSellerTest {

        ProductService productService;
        SellerService sellerService;

        ProductDAO productDAO;
        SellerDAO sellerDAO;
        Connection conn = ConnectionSingleton.getConnection();

        @Before
        public void setUP()throws SellerException {
            sellerService = new SellerService(sellerDAO);
            productService = new ProductService(sellerDAO, productDAO);
            sellerDAO = new SellerDAO(conn);
            productDAO = new ProductDAO(conn, productService);
        }

        @Before
        public void dbReset(){
            ConnectionSingleton.resetTestDatabase();
        }

        @Test
        public void productServiceEmptyAtStart(){
            List<Product> productList = productDAO.getAllProducts();
            assertEquals(0, productList.size());
        }

        @Test
        public void setSellerServiceEmptyAtStart () {
            List<Seller> sellerList = sellerDAO.getSellerList();
            assertEquals(0, sellerList.size());
        }

        @Test
        public void testAddProduct() throws ProductException, SellerException{
            String testProductName = "grill";
            double testProductPrice = 399.99;
            String testSellerName = "home depot";
            String testSellerName2 ="home depot";

            Product product = new Product ();
            product.setProductName(testProductName);
            product.setProductPrice(testProductPrice);
            product.setSellerName(testSellerName);

            Seller seller = new Seller();
            seller.setName(testSellerName2);

            sellerDAO.addSeller(seller);
            productDAO.addProduct(product);

            assertTrue(productDAO.getAllProducts().contains(product));
        }

        @Test
        public void testSellerExistsException()throws SellerException {
            String testProductName = "bike";
            double testProductPrice = 199.99;
            String testSellerName = "walmart";
            String testSellerName2 ="home depot";

            Product product = new Product ();
            product.setProductName(testProductName);
            product.setProductPrice(testProductPrice);
            product.setSellerName(testSellerName);

            Seller seller = new Seller();
            seller.setName(testSellerName2);

            sellerDAO.addSeller(seller);

            try {
                productDAO.addProduct(product);
                Assert.fail("Seller's name does not exist in seller list");
            }catch (Exception e){

            }

        }

        @Test
        public void testDeleteProduct()throws Exception {
            List<Product> productList = productDAO.getAllProducts();
            String testProductName = "tv";
            double testProductPrice = 199.99;
            String testSellerName = "walmart";
            String testSellerName2 ="walmart";

            Product product = new Product ();
            product.setProductName(testProductName);
            product.setProductPrice(testProductPrice);
            product.setSellerName(testSellerName);

            Seller seller = new Seller();
            seller.setName(testSellerName2);

            sellerDAO.addSeller(seller);

            productDAO.addProduct(product);
            long productId = product.productID;

            productDAO.deleteProductByID(product);

            assertEquals(0, productList.size());
        }

        @Test
        public void testUpdateProduct() throws Exception {

            List<Product> productList = productDAO.getAllProducts();
            String testProductName = "tV";
            double testProductPrice = 199.99;
            String testSellerName = "walmart";
            String testSellerName2 = "walmart";

            Product product = new Product ();
            product.setProductName(testProductName);
            product.setProductPrice(testProductPrice);
            product.setSellerName(testSellerName);

            Seller seller = new Seller();
            seller.setName(testSellerName2);

            sellerDAO.addSeller(seller);

            productDAO.addProduct(product);
            long productId = product.productID;

            String updatedProductName = "Updated TV";
            double updatedProductPrice = 249.99;
            String updatedSellerName = "Best Buy";

            Product updatedProduct = new Product ();
            updatedProduct.setProductName(updatedProductName);
            updatedProduct.setProductPrice(updatedProductPrice);
            updatedProduct.setSellerName(updatedSellerName);

        }

    }
