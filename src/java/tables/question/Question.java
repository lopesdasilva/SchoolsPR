/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tables.question;

import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author lopesdasilva
 */


public abstract class Question implements Serializable {
    
    public abstract LinkedList<URL> getUrls();

    public abstract Answer getUserAnswer();

    public abstract String getQuestion();
        
    public abstract void setUserAnswer(Answer userAnswer);
    
    public abstract void addURL(URL url);
}
