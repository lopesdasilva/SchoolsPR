/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package users;

import java.util.LinkedList;
import menu.MenuBean;
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



        //test
        disciplines.addLast(new Discipline("PSM"));
        disciplines.addLast(new Discipline("IGRS"));
        disciplines.addLast(new Discipline("PSM"));
        disciplines.addLast(new Discipline("SRCMA"));
        disciplines.addLast(new Discipline("GESTAO I"));

        this.userMenu = new MenuBean(disciplines);

    }
}