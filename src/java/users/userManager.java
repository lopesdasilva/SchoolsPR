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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import sha1.sha1;

/**
 *
 * @author lopesdasilva
 */
@ManagedBean(name="user")
@SessionScoped
public class userManager {

    String loginname;
    String password;
    boolean loggedIn=false;
    String d="teste d";
    User current;
    /** Creates a new instance of loginController */
    public userManager() {
    }

    public boolean getLoggedIn(){
        return loggedIn;
    }
    public String getD(){
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

    public String CheckValidUser() {
        FacesContext facesContext = FacesContext.getCurrentInstance();

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        HttpSession session = request.getSession();

        System.out.println(session.getAttribute("loged"));
        try {
            System.out.println("User: "+loginname+" has logged On.");
            

            DBConnect db = new DBConnect(SQLInstruct.dbAdress, SQLInstruct.dbUsername, SQLInstruct.dbPassword);
            db.loadDriver();
            String sqlStatement = SQLInstruct.login(loginname, new sha1().parseSHA1Password(password));
            ResultSet rSet = db.queryDB(sqlStatement);

            if (rSet.next()) {
                
               this.loggedIn=true;
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
 
 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error", "Wrong User or Password"));
        return "fail";
    }
    
}
