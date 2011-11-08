package menu;

import java.util.LinkedList;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.el.ValueExpression;
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
            item.setUrl("#");

            submenu.getChildren().add(item);

            for (Module m : d.getModules()) {
                item = new MenuItem();
                item.setValue(m.getName());
                item.setAjax(false);
                
                //item.setActionListener(createActionListener("#{user.moduleSelection}"));

                //para usar mais tarde
                // item.setActionExpression(createAction("#{user.yahoo}", String.class ,String.class));
                //   String[] names={d.getName()};
                //Class[] sadsa= {String.class};
                //System.out.println("AQUIIIII"+resloveMethodExpression("#{user.moduleSelection}", null,new Class[] {String.class},null).toString());
                // item.setActionExpression((MethodExpression)resloveMethodExpression("#{user.moduleSelection}", null,new Class[] {String.class},null));
              
                //MARTELO!!!
                item.setActionListener(createActionListener("#{userM.moduleSelection}"));
                item.setActionExpression(createAction_old("#{userM.yahoo}", String.class));
                submenu.getChildren().add(item);
            }
            model.addSubmenu(submenu);

        }
    }

    public MenuModel getModel() {
        return model;
    }

    public static Object resloveMethodExpression(String expression,
            Class returnType,
            Class[] argTypes,
            Object[] argValues) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application app = facesContext.getApplication();
        ExpressionFactory elFactory = app.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        MethodExpression methodExpression =
                elFactory.createMethodExpression(elContext, expression, returnType,
                argTypes);
        return methodExpression.invoke(elContext, argValues);
    }

    public static MethodExpression createAction(String actionExpression, Class<?> returnType, Class<?> argType) {//,Class<?> argType) {
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getApplication().getExpressionFactory().createMethodExpression(context.getELContext(), actionExpression, returnType, new Class[]{argType});
    }

    public static MethodExpression createAction_old(String actionExpression, Class<?> returnType) {//,Class<?> argType) {
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getApplication().getExpressionFactory().createMethodExpression(context.getELContext(), actionExpression, null, new Class[0]);
    }

    public static MethodBinding createActionListener(String actionListenerExpression) {
        Application app = FacesContext.getCurrentInstance().getApplication();

        return app.createMethodBinding(actionListenerExpression, new Class[]{ActionEvent.class});

    }
}
