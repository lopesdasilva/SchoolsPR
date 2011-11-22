/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tables.question;

/**
 *
 * @author lopesdasilva
 */
public class QuestionDesenolvimento extends Question {

    String question;
    Answer userAnswer;

    public QuestionDesenolvimento(String question) {
        this.question = question;
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
