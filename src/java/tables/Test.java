/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tables;

import java.io.Serializable;

/**
 *
 * @author lopesdasilva
 */
public class Test implements Serializable {
 
    private String name;
    private String description;

    public String getDescription() {
        return description;
    }

    public String getName() {
        System.out.println(name);
        return name;
    }
    
    
    public Test(String name){
        this.name=name;
        //TODO: adicionar  
        this.description="";
    }
}
