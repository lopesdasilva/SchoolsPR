/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tables;

import java.util.LinkedList;

/**
 *
 * @author lopesdasilva
 */
public class Module {
   private String name;
   private LinkedList<Test> tests;
   

    Module(String name) {
        this.name=name;
        tests = new LinkedList<Test>();
    }
   
   public String getName(){
       return name;
   }

    void addTest(String test) {
        
    }
   
   
}
