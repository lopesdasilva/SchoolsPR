package menu;

import java.util.LinkedList;
import javax.el.MethodExpression;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;
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

    public MenuBean(LinkedList<Discipline> disciplines) {

        model = new DefaultMenuModel();

        FacesContext ctx = FacesContext.getCurrentInstance();
        Application app = ctx.getApplication();

        for (Discipline d : disciplines) {

            Submenu submenu = new Submenu();
            submenu.setLabel(d.getName());

            MenuItem item = new MenuItem();
            item.setValue("Informações");

            MethodExpression methodExpression = 
                    FacesContext.getCurrentInstance().getApplication().getExpressionFactory().createMethodExpression(FacesContext.getCurrentInstance().getELContext(), "#{asd.asdas}", null, new Class<?>[0]);

            item.setActionExpression(methodExpression);
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
