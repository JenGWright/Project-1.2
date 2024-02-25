package org.example.DAO;

import org.example.Model.Seller;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SellerDAO {
    Connection conn;
    public SellerDAO(Connection conn) {
        this.conn = conn;
    }
    public List<Seller>getSellerList(){
        List<Seller> sellerResults = new ArrayList<>();
        try{
            PreparedStatement ps = conn.prepareStatement("select * from Seller");
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                String sellerName = resultSet.getString("name");
                Seller newseller = new Seller(sellerName);
                sellerResults.add(newseller);
                System.out.println();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return sellerResults;
    }

    public void addSeller(Seller newSeller){
        try{
            PreparedStatement ps = conn.prepareStatement("insert into " + "Seller (name) values (?)");
            ps.setString(1, newSeller.getName());
            System.out.println(newSeller.getName());
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public String getSellerByName(String name){
        try{
            PreparedStatement ps = conn.prepareStatement("select * from seller where name = ?");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                name = rs.getString("name");
                return name;
            }else {
                return null;
            }
            }catch (SQLException e){
                e.printStackTrace();
            }
            return null;

    }

}


