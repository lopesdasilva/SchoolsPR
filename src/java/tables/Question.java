/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tables;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author lopesdasilva
 */
public class Question implements Serializable{

    public static class Answer {

        private String s;

        public String getS() {
            return s;
        }

        public Answer(String s) {


            this.s = s;

        }
    }
    String question;
    Map<String,String> answer = new HashMap<String, String>();
    

    public Map<String,String> getAnswer() {
        return answer;
    }

    public Question(String question, String h1, String h2, String h3, String h4) {
        //TODO ARRANJAR
        answer.put(h1,h1);
        answer.put(h2,h2);
        answer.put(h3,h3);
        answer.put(h4,h4);
        this.question = question;

    }

    public String getQuestion() {
        return question;
    }

    public Question(String question) {
        this.question = question;
    }
}
