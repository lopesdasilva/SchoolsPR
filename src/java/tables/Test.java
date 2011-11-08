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

    public String getName() {
        System.out.println(name);
        return name;
    }
    
    
    public Test(String name){
        this.name=name;
    }
}
