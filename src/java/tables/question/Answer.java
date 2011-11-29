/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tables.question;

import java.io.Serializable;

/**
 *
 * @author lopesdasilva
 */
 public class Answer implements Serializable{

        private String s;

    public void setS(String s) {
        this.s = s;
    }

        public String getS() {
            return s;
        }

        public Answer(String s) {


            this.s = s;

        }
    }
