package org.example.Service;

import org.example.DAO.SellerDAO;
import org.example.Exception.SellerException;
import org.example.Main;
import org.example.Model.Seller;

import java.util.ArrayList;
import java.util.List;

public class SellerService {
    SellerDAO sellerDAO;
    public SellerService(SellerDAO sellerDAO){
        this.sellerDAO = sellerDAO;

    }
   List<Seller> sellerList;

   public SellerService(){
         this.sellerList = new ArrayList<>();
    }

     public List<Seller>getSellerList(){
        sellerList = sellerDAO.getSellerList();
        return sellerList;
     }
     public void saveSeller(Seller newSeller){
        sellerDAO.addSeller(newSeller);
     }


    public void addSeller(Seller newSeller) throws SellerException {

        Main.log.info("Add: Adding a seller");
        List<Seller>existingSeller = getSellerList();
        for (Seller existing : existingSeller) {
            System.out.println("Existing: " + existing.getName());
            if (existing.getName().equals(newSeller.getName())){
                System.out.println("Duplicate found");
                throw new SellerException("Seller name already exists");
            }
        }
        sellerDAO.addSeller(newSeller);
    }

    public boolean sellerExists(String name) {
       for (Seller seller : sellerList){
           if (seller.getName().equals(name)){
               return true;
           }
       }
       return false;
    }


}
