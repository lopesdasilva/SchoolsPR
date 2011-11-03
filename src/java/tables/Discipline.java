/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tables;

import java.sql.ResultSet;
import java.util.LinkedList;

/**
 *
 * @author lopesdasilva
 */
public class Discipline {
    private String name;
    LinkedList<Module> modules;

    public Discipline(String name) {
       this.name=name;
       modules=new LinkedList<Module>();
    }

    public String getName() {
        return name;
    }
 
    public String toString(){
        return name;
    }
    public Module existe(String module){
        for(Module m:modules){
        if(m.getName().equals(module)){
            return m;
        }
    }
    return null;
    }

    public void addModule(String module, String test) {
        Module m = existe(module);
        Module new_module = new Module(module);

                if(m==null){
                    System.out.println(module);
                    modules.addLast(new_module);
                    new_module.addTest(test);

                }else{
                m.addTest(test);
                }
                m=null;        }
    
}
