/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package users;

import db.DBConnect;
import db.SQLInstruct;
import java.sql.ResultSet;
import java.util.LinkedList;
import menu.MenuBean;
import sha1.sha1;
import tables.Discipline;

/**
 *
 * @author lopesdasilva
 */
public class User {

    String username;
    String name;
    String idUser;
    //Ui
    MenuBean userMenu;
    //Disciplines
    LinkedList<Discipline> disciplines = new LinkedList<Discipline>();

    public LinkedList<Discipline> getDisciplines() {
        return disciplines;
    }

   
    public MenuBean getUserMenu() {
        return userMenu;
    }

    public User(String username) {
        this.username = username;


        //populate disciplines

        try {
            DBConnect db = new DBConnect(SQLInstruct.dbAdress, SQLInstruct.dbUsername, SQLInstruct.dbPassword);
            db.loadDriver();
            String sqlStatement = SQLInstruct.informacoes(username);
            ResultSet rSet = db.queryDB(sqlStatement);

            while (rSet.next()) {
                Discipline d = existe(rSet.getString(1));
                Discipline new_discipline = new Discipline(rSet.getString(1));

                if(d==null){
                    disciplines.addLast(new_discipline);
                    new_discipline.addModule(rSet.getString(2),rSet.getString(3));

                }else{
                d.addModule(rSet.getString(2), rSet.getString(3));
                }
                d=null;
            }
            

            
        } catch (Exception e) {
        }

        //test
        //disciplines.addLast(new Discipline("PSM"));
        //disciplines.addLast(new Discipline("IGRS"));
        //disciplines.addLast(new Discipline("PSM"));
        //disciplines.addLast(new Discipline("SRCMA"));
        //disciplines.addLast(new Discipline("GESTAO I"));

        this.userMenu = new MenuBean(disciplines);

    }

    private Discipline existe(String discip) {
    for(Discipline d:disciplines){
        if(d.getName().equals(discip)){
            return d;
        }
    }
    return null;
            }
}