/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tables.question;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author lopesdasilva
 */
public class QuestionEscolhaMultipla extends Question implements Serializable {

   
    
    String question;
    Map<String, String> hipoteses = new HashMap<String, String>();
    Answer userAnswer;

    @Override
    public Answer getUserAnswer() {
        return userAnswer;
    }
    
    @Override
    public void setUserAnswer(Answer userAnswer) {
        this.userAnswer = userAnswer;
    }

    public Map<String, String> getHipoteses() {
        return hipoteses;
    }

    public QuestionEscolhaMultipla(String question, String h1, String h2, String h3, String h4,String resposta) {
        hipoteses.put(h1, h1);
        hipoteses.put(h2, h2);
        hipoteses.put(h3, h3);
        hipoteses.put(h4, h4);
        this.question = question;
        this.userAnswer=new Answer(resposta);

    }

    public String getQuestion() {
        return question;
    }

    public QuestionEscolhaMultipla(String question) {
        this.question = question;
    }

    

    @Override
    public LinkedList<URL> getUrls() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addURL(URL url) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
