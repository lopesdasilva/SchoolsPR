/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package users;

import db.DBConnect;
import db.SQLInstruct;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import sha1.sha1;
import menu.MenuBean;
/**
 *
 * @author lopesdasilva
 */
public class User {

    String username;
    String name;
    MenuBean userMenu=new MenuBean();
    String idUser;
    String[] disciplinas={"","",""}; 
    DBConnect db = new DBConnect(SQLInstruct.dbAdress, SQLInstruct.dbUsername, SQLInstruct.dbPassword);

        
    public MenuBean getUserMenu(){
        return userMenu;
    }
    public String[] getDisciplinas(){
        return disciplinas;
    }
    
    public User(String username) {
        this.username = username;



    }

    public void getInfo() {

        //Obtain userInfon(Id, name)
        try {
            db.loadDriver();
            String sqlStatement = SQLInstruct.disciplinas(username);
            ResultSet rSet = db.queryDB(sqlStatement);
          
            int i=0;
            if (rSet.next()) {
            disciplinas[i]=rSet.getString("name");  
            System.out.println("Disciplina "+i+" "+disciplinas[i]);
            i++;
            }
        }catch(Exception e){}

    }
}