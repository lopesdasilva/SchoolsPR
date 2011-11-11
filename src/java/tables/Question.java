/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tables;

import java.util.LinkedList;

/**
 *
 * @author lopesdasilva
 */
public class Question {

    String question;
    LinkedList<String> hipoteses=new LinkedList<String>();

    public LinkedList<String> getHipoteses() {
        return hipoteses;
    }
    public String getQuestion() {
        return question;
    }

    public Question(String question) {
        this.question = question;
    }
}
