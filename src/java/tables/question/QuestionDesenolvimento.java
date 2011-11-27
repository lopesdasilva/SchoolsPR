/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tables.question;

import java.util.LinkedList;

/**
 *
 * @author lopesdasilva
 */
public class QuestionDesenolvimento extends Question {

    
    String testarrrr="1234";
    
    LinkedList<URL> urls = new LinkedList<URL>();

    public LinkedList<URL> getUrls() {
        return urls;
    }

    public void setTestarrrr(String testarrrr) {
        this.testarrrr = testarrrr;
    }

    public String getTestarrrr() {
        return testarrrr;
    }
    String question;
    Answer userAnswer;

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
}
