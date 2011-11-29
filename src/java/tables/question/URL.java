/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tables.question;

import java.io.Serializable;



public class URL implements Serializable, Comparable<URL>{
    
private String url;
private String name;
private int evaluation;


    public URL(String url, String name, int evaluation) {
        this.url = url;
        this.name = name;
        this.evaluation = evaluation;
    }

    

    public int getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(int evaluation) {
        this.evaluation = evaluation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int compareTo(URL url){
        if (this.evaluation>url.evaluation)
            return 1;
        else return -1;
   
    }

    
    
    
}
