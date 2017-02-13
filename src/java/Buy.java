/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Mihir
 */
@Named(value = "buy")
@RequestScoped
public class Buy {

    /**
     * Creates a new instance of Buy
     */
    public Buy() {
    }
    
    
    private String stockCode;
    private double buyPrice;
    private int quantity;

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    
   
    public String buyMethod()
    {
        
        String UserId = Login.id;
        
        //access the database and then login
      
        
        final String DB_URL = "jdbc:mysql://mis-sql.uhcl.edu/patelm8116";
        //three variables
        LoadDriver();
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        
        
        try{
            //connect to the Database
            
            conn = DriverManager.getConnection(DB_URL , "patelm8116" , "1460202");
            
            stat = conn.createStatement();
            
           //do a query
           rs = stat.executeQuery("Select * from pending_orders where stockCode = '"
                   + stockCode + "' and accountNumber <> '" + OnlineAccount.getAccountNumber() + "'"
                   + "and buyPrice <= '" + buyPrice + "'"
                   + "and rownum = '1'");
           
           
           if(rs.next()){
            int scount = rs.getInt(6);
            if(quantity <= scount){
                
                int r = stat.executeUpdate("update pending_orders set scount = scount - '" 
                        + quantity +"' where stockCode = '"
                   + stockCode + "' and accountNumber <> '" + OnlineAccount.getAccountNumber() + "'"
                   + "and buyPrice <= '" + buyPrice + "'"
                   + "and rownum = '1'");
                int u = stat.executeUpdate("insert into r_orders values('"
                        + OnlineAccount.getAccountNumber() + "','" + stockCode + "','" 
                        + buyPrice + "','"  +quantity + "',')");
                int v = stat.executeUpdate("delete from pending_orders where scount = '0'");
                
                return "Done";
            }
            else{
                int currents = quantity - scount;
                
                int r = stat.executeUpdate("insert into pending_orders values ('"
                        + OnlineAccount.getAccountNumber() + "','" + buyPrice+ "','"+ scount + "'");
                int u = stat.executeUpdate("insert into r_orders values('"
                        + OnlineAccount.getAccountNumber() + "','" + stockCode + "','" 
                        + buyPrice + "','"  +currents + "',')");
                return "Done";
            }
            
               
           }
           else {
               return "Error";
           }
                
        }
       catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            
            try{
                rs.close();
                stat.close();
                conn.close();
            }
            catch(Exception e){
                
                e.printStackTrace();
            }
           
            
        }        
          
    
        
        return "Done";
    }
  
    public static void LoadDriver()
    {
         try
        {
            Class.forName("com.mysql.jdbc.Driver");
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
            //return to internalError.xhtml
        }
    }
}
