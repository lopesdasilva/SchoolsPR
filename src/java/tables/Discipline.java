/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tables;

import java.util.LinkedList;
import java.io.Serializable;

/**
 *
 * @author lopesdasilva
 */
public class Discipline implements Serializable{
    private String name;
    private String info;

    public String getInfo() {
        return info;
    }

    
    private LinkedList<Module> modules;

    public Discipline(String name,String info) {
       this.name=name;
       this.info=info;
       System.out.println("AQUIIIII - "+this.name+":"+this.info);
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

    public void addModule(String module,String info_module) {
        Module m = existe(module);
        Module new_module = new Module(module,info_module);

                if(m==null){
                    modules.addLast(new_module);
                }
                m=null;        }
    
    public void addTest(String module,String test){
        for(Module m: modules){
            if(m.getName().equals(module)){
                m.addTest(test);
            }
        }
    }
    
}
