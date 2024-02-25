import org.example.Exception.ProductException;
import org.example.Exception.SellerException;
import org.example.Model.Product;
import org.example.Model.Seller;
import org.example.Service.ProductService;
import org.example.Service.SellerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class ProductSellerTest {

        ProductService productService;
        SellerService sellerService;

 //       @Before
//        public void setUP()throws SellerException {
 //           sellerService = new SellerService();
 //           productService = new ProductService(sellerService);
        }

 /*       @Test public void productServiceEmptyAtStart(){
            List<Product> productList = productService.getProductList();
            assertEquals(0, productList.size());
        }

        @Test
        public void setSellerServiceEmptyAtStart () {
            List<Seller> sellerList = sellerService.getSellerList();
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

            sellerService.addSeller(seller);
            productService.addProduct(product);

            assertTrue(productService.getProductList().contains(product));
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

            sellerService.addSeller(seller);

            try {
                productService.addProduct(product);
                Assert.fail("Seller's name does not exist in seller list");
            }catch (Exception e){

            }

        }

        @Test
        public void testDeleteProduct()throws Exception {
            List<Product> productList = productService.getProductList();
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

            sellerService.addSeller(seller);

            productService.addProduct(product);
            long productId = product.productID;

            productService.deleteProductByID(product.productID);

            assertEquals(0, productList.size());
        }

    }
*/