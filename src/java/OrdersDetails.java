/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mihir
 */
public class OrdersDetails {
    
    private String Stock_code;
    private double No_Of_shares;
    private double price;
    
    
    public OrdersDetails(String name, double quantity, double p)
    {
        Stock_code = name;
        No_Of_shares = quantity;
        price = p;
       
    }

    public String getStock_code() {
        return Stock_code;
    }

    public void setStock_code(String Stock_code) {
        this.Stock_code = Stock_code;
    }

    public double getNo_Of_shares() {
        return No_Of_shares;
    }

    public void setNo_Of_shares(double No_Of_shares) {
        this.No_Of_shares = No_Of_shares;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
   
    
}
