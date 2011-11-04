package menu;

import java.util.LinkedList;
import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;
import tables.Discipline;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author lopesdasilva
 */
public class MenuBean {

    private MenuModel model;

    public MenuBean() {
        model = new DefaultMenuModel();

        //First submenu  
        Submenu submenu = new Submenu();
        submenu.setLabel("Disiciplina 1");

        MenuItem item = new MenuItem();
        item.setValue("Informações");
        item.setUrl("#");
        submenu.getChildren().add(item);

        model.addSubmenu(submenu);

        //Second submenu  
        submenu = new Submenu();
        submenu.setLabel("Disciplina 2");

        item = new MenuItem();
        item.setValue("Informações");
        item.setUrl("#");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue("Testes");
        item.setUrl("#");
        submenu.getChildren().add(item);

        model.addSubmenu(submenu);
    }

    public MenuBean(LinkedList<Discipline> disciplines) {
        
        model = new DefaultMenuModel();

          
        for (Discipline d : disciplines) {
            
            Submenu submenu = new Submenu();
            submenu.setLabel(d.getName());

            MenuItem item = new MenuItem();
            item.setValue("Informações");
            item.setUrl("#");
            submenu.getChildren().add(item);

            item = new MenuItem();
            item.setValue("Testes");
            item.setUrl("#");
            submenu.getChildren().add(item);
            
            model.addSubmenu(submenu);
            
        }
    }

    public MenuModel getModel() {
        return model;
    }
}
