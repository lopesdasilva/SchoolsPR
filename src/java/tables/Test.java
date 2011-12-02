/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tables;

import tables.question.Question;
import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author lopesdasilva
 */
public class Test implements Serializable {
    
 
    private LinkedList<Question> questions= new LinkedList<Question>();
    private LinkedList<Question> questionsDesenvolvimento= new LinkedList<Question>();

    public void setQuestionsDesenvolvimento(LinkedList<Question> questionsDesenvolvimento) {
        this.questionsDesenvolvimento = questionsDesenvolvimento;
    }

    public LinkedList<Question> getQuestionsDesenvolvimento() {
        return questionsDesenvolvimento;
    }

    public void setQuestions(LinkedList<Question> questions) {
        this.questions = questions;
    }

    public LinkedList<Question> getQuestions() {
        return questions;
    }
    private String name;
    private String description;

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }
    
    
    public Test(String name){
        this.name=name;
        //TODO: adicionar  
        this.description="";
    }
}
