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
    private LinkedList<Module> modules;

    public Discipline(String name) {
       this.name=name;
       modules=new LinkedList<Module>();
    }

    public String getName() {
        return name;
    }
    
    public LinkedList<Module> getModules(){
        return modules;
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

    public void addModule(String module) {
        Module m = existe(module);
        Module new_module = new Module(module);

                if(m==null){
                    System.out.println(getName()+"-Adicionado Modulo:"+module);
                    modules.addLast(new_module);
                }
                m=null;        }
    
}
