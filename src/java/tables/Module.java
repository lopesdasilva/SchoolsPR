/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tables;

import java.util.LinkedList;
import java.io.Serializable;


public class Module  implements Serializable{
   private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
   private String name;
   private LinkedList<Test> tests;

    public LinkedList<Test> getTests() {
        return tests;
    }
   

    Module(String name,String info) {
        this.name=name;
        this.info=info;
        tests = new LinkedList<Test>();
    }
   
   public String getName(){
       return name;
   }

    void addTest(String test,String description) {
        tests.addLast(new Test(test,description));
    }
   
   
}
