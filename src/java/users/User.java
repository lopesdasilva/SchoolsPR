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
            String sqlStatement = SQLInstruct.disciplinas(username);
            ResultSet rSet = db.queryDB(sqlStatement);

            while (rSet.next()) {
                disciplines.addLast(new Discipline(rSet.getString(2)));
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
}