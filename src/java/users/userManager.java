package users;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import db.DBConnect;
import db.SQLInstruct;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import menu.MenuBean;
import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import sha1.sha1;
import tables.Discipline;

/**
 *
 * @author lopesdasilva
 */
@ManagedBean(name = "user")
@SessionScoped
public class userManager {

    String loginname;
    String password;
    boolean loggedIn = false;
    String d = "teste d";
    User current;

    /** Creates a new instance of loginController */
    public userManager() {
    }

    public User getCurrent() {
        return current;
    }

    public boolean getLoggedIn() {
        return loggedIn;
    }

    public String getD() {
        return d;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String test() {
        System.out.println("yahoo");
        return "success";
    }

    public String CheckValidUser() {

        try {



            DBConnect db = new DBConnect(SQLInstruct.dbAdress, SQLInstruct.dbUsername, SQLInstruct.dbPassword);
            db.loadDriver();
            String sqlStatement = SQLInstruct.login(loginname, new sha1().parseSHA1Password(password));
            ResultSet rSet = db.queryDB(sqlStatement);

            if (rSet.next()) {

                this.loggedIn = true;
                current = new User(loginname);
                System.out.println("User: " + loginname + " has logged On.");
                return "success";

            }


        } catch (NoSuchAlgorithmException ex) {
            System.out.println("NoSuchAlgorithmException");
        } catch (InstantiationException ex) {
            System.out.println("InstantiationException");
        } catch (IllegalAccessException ex) {
            System.out.println("IllegalAccessException");
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException");
        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Wrong User or Password"));
        return "fail";
    }

<<<<<<< HEAD
    public String yahoo() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
            DBConnect db = new DBConnect(SQLInstruct.dbAdress, SQLInstruct.dbUsername, SQLInstruct.dbPassword);
            db.loadDriver();
            
            //Meter as variaveis que o rui ainda vai arranjar.
            String sqlStatement = SQLInstruct.tests("Math","SumModule");
            ResultSet rSet = db.queryDB(sqlStatement);

            while(rSet.next()){
        for(Discipline d: current.disciplines){
            if(d.getName().equals("Math")){//Sybstituir Math
                //fazer while do rSet(Fazer rSet)
                d.addTest("SumModule", rSet.getString(1));//Substituir SumModule
            }
        }
            }
        
=======
    public String yahoo() {
>>>>>>> já é possivel identificar os botoes
        
        System.out.println("test");
        System.out.println("google");
        return "success";
    }

    public void onItemClick(ActionEvent event) {
        Object obj = event.getSource();
        MenuItem aux_module = (MenuItem)obj;
        Submenu aux_discipline = (Submenu) aux_module.getParent();
        
        String moduleSelected=aux_module.getValue()+"";
        String disciplineSelected= aux_discipline.getLabel();
        
        
        System.out.println("Module Selected: "+moduleSelected);
        System.out.println("Discipline Selected: "+disciplineSelected);
       
    }
}
