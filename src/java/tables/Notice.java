/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tables;

/**
 *
 * @author lopesdasilva
 */
public class Notice {
    private String text;
    private final String disciplineName;

    public String getDisciplineName() {
        return disciplineName;
    }

    public String getText() {
        return text;
    }

    public Notice(String disciplineName,String text) {
        this.disciplineName=disciplineName;
        this.text = text;
    }
    
}
