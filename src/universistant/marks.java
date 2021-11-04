/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universistant;

/**
 *
 * @author USER
 */
public class marks{
    
    String type;
    String mark;
    
    /**
     * Constructor to initialize values
     * @param type
     * @param mark
     *  
     */
    public marks(String a, String b) {
        this.type = a;
        this.mark = b;
        
    }
    
    /**
     * Get method for Date
     * @return Date 
     */
    public String getType() {
        return type;
    }

    /**
     * Get method for Amount
     * @return Amount
     */
    public String getMark() {
        return mark;
    }

    
    
    
}