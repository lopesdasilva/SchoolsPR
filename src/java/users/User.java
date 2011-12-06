/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package users;

import db.DBConnect;
import db.SQLInstruct;
import java.io.Serializable;
import java.sql.ResultSet;
import java.util.LinkedList;
import menu.MenuBean;
import tables.Discipline;
import tables.Notice;


/**
 *
 * @author lopesdasilva
 */
public class User implements Serializable{
    
    String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    String username;
    String name;
    String idUser;
    //Ui
    MenuBean userMenu;
    //Disciplines
    LinkedList<Discipline> disciplines = new LinkedList<Discipline>();
    LinkedList<Notice> notices = new LinkedList<Notice>();

    public LinkedList<Notice> getNotices() {
        return notices;
    }
    
    public LinkedList<Discipline> getDisciplines() {
        return disciplines;
    }

    public MenuBean getUserMenu() {
        return userMenu;
    }

    public User(String username) {
        this.username = username;


        try {
            
            DBConnect db = new DBConnect(SQLInstruct.dbAdress, SQLInstruct.dbUsername, SQLInstruct.dbPassword);
            db.loadDriver();
            
            //avisos
            String querysqlStatement = SQLInstruct.notices(username);
            ResultSet query_notices = db.queryDB(querysqlStatement);
            
            while(query_notices.next()){
                if(!query_notices.getBoolean("isRead"))
                notices.add(new Notice(query_notices.getString(1),query_notices.getString(2),query_notices.getBoolean("isRead")));
            }
            
            //fim avisos
            String sqlStatement = SQLInstruct.informacoes(username);
            ResultSet rSet = db.queryDB(sqlStatement);

            while (rSet.next()) {
                Discipline d = existe(rSet.getString(1));
                Discipline new_discipline = new Discipline(rSet.getString(1),rSet.getString(2));

                if (d == null) {
                    disciplines.addLast(new_discipline);
                    new_discipline.addModule(rSet.getString(3),rSet.getString(4));

                } else {
                    d.addModule(rSet.getString(3),rSet.getString(4));
                }
                d = null;
            }


        } catch (Exception e) {
        }

    

        this.userMenu = new MenuBean(disciplines);

    }
    
    
    
    public String putRead(){
        System.out.println("Entrei no putRead do user." + text);
        for(Notice n:notices){
            if(n.getText().equals(text)){
                System.out.println(n.getText() + " - aviso removido");
                n.putRead();
                notices.remove(n);;
            }
        }
        return "sucess";
    }
    


    public Discipline existe(String discip) {
        for (Discipline d : disciplines) {
            if (d.getName().equals(discip)) {
                return d;
            }
        }
        return null;
    }
    
    
    
}