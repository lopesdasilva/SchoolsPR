
  
import java.util.ArrayList;  
import java.util.List;  
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
 
  @ManagedBean(name = "tabBean")
@SessionScoped
public class tabBean {

    public  class test {
        String name;

        public String getName() {
            return name;
        }
        public test(String name) {
            this.name=name;
        }
    }
      
    private List<test> players;  
  
    public tabBean() {  
        players = new ArrayList<test>();  
          
        players.add(new test("Messi"));  
        players.add(new test("Iniesta"));  
        players.add(new test("Villa"));  
        players.add(new test("Xavi"));  
        players.add(new test("Puyol"));  
        players.add(new test("Rui")); 
        players.add(new test("TIAGO")); 
        players.add(new test("ASDASDSAD")); 
        
        
    }  
  
    public List<test> getPlayers() {  
        return players;  
    }  
    
}  
  
                      