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
    private boolean isRead;

    public String getDisciplineName() {
        return disciplineName;
    }
    
    public void putRead(){
        System.out.println(text+" está lido.");
        isRead=true;
    }

    public String getText() {
        return text;
    }

    public Notice(String disciplineName,String text,boolean isRead) {
        this.disciplineName=disciplineName;
        this.isRead=isRead;
        this.text = text;
    }
    
}
