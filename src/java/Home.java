/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Mihir
 */
@Named(value = "home")
@RequestScoped
public class Home {

    /**
     * Creates a new instance of Home
     */
   
    public String Test()
    {
        return "test success";
        
    }
    
    
}
