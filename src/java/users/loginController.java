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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.component.password.Password;
import sha1.sha1;

/**
 *
 * @author lopesdasilva
 */
@ManagedBean
@RequestScoped
public class loginController {

    String loginname;
    String password;

    /** Creates a new instance of loginController */
    public loginController() {
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

    public String CheckValidUser() {
        FacesContext facesContext = FacesContext.getCurrentInstance();

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        HttpSession session = request.getSession();

        System.out.println(session.getAttribute("loged"));
        try {
            System.out.println(loginname);
            System.out.println(password);

            DBConnect db = new DBConnect(SQLInstruct.dbAdress, SQLInstruct.dbUsername, SQLInstruct.dbPassword);
            db.loadDriver();

            System.out.println("Driver loaded");
            String sqlStatement = SQLInstruct.login(loginname, new sha1().parseSHA1Password(password));
            ResultSet rSet = db.queryDB(sqlStatement);

            if (rSet.next()) {

                session.setAttribute("loged", true);
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
        return "fail";
    }
}
