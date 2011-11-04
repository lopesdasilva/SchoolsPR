package menu;

import java.util.LinkedList;
import javax.el.MethodExpression;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;
import javax.faces.event.ActionEvent;
import javax.faces.event.MethodExpressionActionListener;
import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;
import tables.Discipline;
import tables.Module;

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

        for (Discipline d : disciplines) {

            Submenu submenu = new Submenu();
            submenu.setLabel(d.getName());

            MenuItem item = new MenuItem();
            item.setValue("Informações");
            //item.setUrl("profile.xhtml")
            Application app = FacesContext.getCurrentInstance().getApplication();
            javax.faces.el.MethodBinding mb = app.createMethodBinding("#{user.onItemClick}", new Class[]{ActionEvent.class});

            item.setActionListener(mb);
            //  item.setActionListener(createActionListener(("#{user.yahoo}")));
            submenu.getChildren().add(item);

            for (Module m : d.getModules()) {
                item = new MenuItem();
                item.setValue(m.getName());
                item.setAjax(false);
                item.setValue(m.getName());
                item.setActionListener(createActionListener("#{user.onItemClick}"));

                //para usar mais tarde
                //item.setActionExpression(createAction("#{user.yahoo}", ActionEvent.class));
                submenu.getChildren().add(item);
            }
            model.addSubmenu(submenu);

        }
    }

    public MenuModel getModel() {
        return model;
    }

    public static MethodExpression createAction(String actionExpression, Class<?> returnType) {
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getApplication().getExpressionFactory().createMethodExpression(context.getELContext(), actionExpression, returnType, new Class[0]);
    }

    public static MethodBinding createActionListener(String actionListenerExpression) {
        Application app = FacesContext.getCurrentInstance().getApplication();
        return app.createMethodBinding("#{user.onItemClick}", new Class[]{ActionEvent.class});
 
    }
}
