/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tables.question;

import java.io.Serializable;
import java.util.LinkedList;


public class QuestionDesenolvimento extends Question implements Serializable {


    String question;
    Answer userAnswer;
    
    LinkedList<URL> urls = new LinkedList<URL>();

    public LinkedList<URL> getUrls() {
        return urls;
    }
    

    public QuestionDesenolvimento(String question,String userAnswer) {
        this.question = question;
        Answer answer = new Answer(userAnswer);
        this.userAnswer=answer;
    }

    @Override
    public Answer getUserAnswer() {
        return this.userAnswer;
    }

    @Override
    public String getQuestion() {
        return this.question;
    }

    @Override
    public void setUserAnswer(Answer userAnswer) {
        this.userAnswer = userAnswer;
    }

    @Override
    public void addURL(URL url) {
        this.addURL(url);
    }
}
