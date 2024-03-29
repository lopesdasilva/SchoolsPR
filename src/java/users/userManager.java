package users;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import db.DBConnect;
import db.SQLInstruct;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlForm;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import sha1.sha1;
import tables.Discipline;
import tables.Module;
import tables.question.Question;
import tables.Test;
import tables.question.QuestionDesenolvimento;
import tables.question.QuestionEscolhaMultipla;
import tables.question.URL;


@ManagedBean(name = "userM")
@SessionScoped
public class userManager implements Serializable {

   
    int user_id;
    String loginname;
    String password;
    boolean loggedIn = false;
    User current;
    String disciplineSelected;
    String moduleSelected;
    Module moduleSelectedList;
    Discipline disciplineSelectedList;
    Test testSelected;
    URL newURL = new URL("", "", 0);
    String selectedQuestion = "";

    public void setNewURL(URL newURL) {
        this.newURL = newURL;
    }

    public URL getNewURL() {
        return newURL;
    }

    public void setTestSelected(Test testSelected) {
        this.testSelected = testSelected;
    }
    public String selectedTest;

    public String getSelectedTest() {
        return selectedTest;
    }

    public Test getTestSelected() {
        return testSelected;
    }

    public Discipline getDisciplineSelectedList() {
        return disciplineSelectedList;
    }

    public Module getModuleSelectedList() {
        return moduleSelectedList;
    }

    public String getDisciplineSelected() {
        return disciplineSelected;
    }

    public String getModuleSelected() {
        return moduleSelected;
    }

    /** Creates a new instance of loginController */
    public userManager() {
    }

    public User getCurrent() {
        return current;
    }

    public boolean getLoggedIn() {
        return loggedIn;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String test() {
        return "success";
    }

    public String CheckValidUser() {

        try {



            DBConnect db = new DBConnect(SQLInstruct.dbAdress, SQLInstruct.dbUsername, SQLInstruct.dbPassword);
            db.loadDriver();
            String sqlStatement = SQLInstruct.login(loginname, new sha1().parseSHA1Password(password));
            ResultSet rSet = db.queryDB(sqlStatement);


            if (rSet.next()) {
                user_id = rSet.getInt(1);

                this.loggedIn = true;
                current = new User(loginname);
                System.out.println("User: " + loginname + " has logged On. ADMIN: " + rSet.getBoolean("isAdmin"));
                if (rSet.getBoolean("isAdmin")) {
                    return "successA";
                } else {
                    return "success";
                }

            }


        } catch (NoSuchAlgorithmException ex) {
            System.out.println("NoSuchAlgorithmException");
        } catch (InstantiationException ex) {
            System.out.println("InstantiationException");
        } catch (IllegalAccessException ex) {
            System.out.println("IllegalAccessException");
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException");
        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Wrong User or Password"));
        return "fail";
    }

    public String moduleRedirect() {
        return "module";
    }

    public String homeRedirect() {
        System.out.println("home");
        return "home";
    }

    public String logOff() {
        System.out.println("User: " + current.username + " has loggedOff.");
        return "logoff";
    }

    public String infoRedirect() {
        return "info";
    }

    public String moduleSelection(ActionEvent event) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        Object obj = event.getSource();
        MenuItem aux_module = (MenuItem) obj;
        Submenu aux_discipline = (Submenu) aux_module.getParent();

        moduleSelected = aux_module.getValue() + "";
        disciplineSelected = aux_discipline.getLabel();



        DBConnect db = new DBConnect(SQLInstruct.dbAdress, SQLInstruct.dbUsername, SQLInstruct.dbPassword);
        db.loadDriver();

        String sqlStatement = SQLInstruct.tests(disciplineSelected, moduleSelected);
        ResultSet rSet = db.queryDB(sqlStatement);



        for (Discipline d : current.disciplines) {
            if (d.getName().equals(disciplineSelected)) {
                if (d.existe(moduleSelected).getTests().size() == 0) {
                    while (rSet.next()) {
                        d.addTest(moduleSelected, rSet.getString(1),rSet.getString(2));
                    }
                }

            }

        }
        moduleSelectedList = current.existe(disciplineSelected).existe(moduleSelected);






        return "success";
    }

    public String disciplineSelection(ActionEvent event) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        Object obj = event.getSource();
        MenuItem aux_info = (MenuItem) obj;
        Submenu aux_discipline = (Submenu) aux_info.getParent();
        disciplineSelected = aux_discipline.getLabel();




        disciplineSelectedList = current.existe(disciplineSelected);



        return "success";
    }

    public void setTest(ActionEvent actionEvent) {
        Object obj = actionEvent.getSource();
        CommandButton cb = (CommandButton) obj;
        this.selectedTest = cb.getLabel();
    }

    public void resolverTeste(ActionEvent actionEvent) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        Object obj = actionEvent.getSource();
        CommandButton cb = (CommandButton) obj;
        this.selectedTest = cb.getLabel();
        System.out.println("DEBUG: " + this.selectedTest);

        //ligar à db
        DBConnect db = new DBConnect(SQLInstruct.dbAdress, SQLInstruct.dbUsername, SQLInstruct.dbPassword);
        db.loadDriver();

        //fazer query
        String multiple = SQLInstruct.multipleQuestions(selectedTest);
        ResultSet rSet_multiple = db.queryDB(multiple);

        testSelected = new Test(this.selectedTest,"");
        
        //TODO 
        LinkedList<Question> qMulti = new LinkedList<Question>();
        
        while (rSet_multiple.next()) {
            int multiple_id=rSet_multiple.getInt(8);
            String question = rSet_multiple.getString(1);
            String multiple_answer = SQLInstruct.multipleAnswer(loginname, disciplineSelected, moduleSelected, testSelected.getName(),question);
            ResultSet rSet_multiple_answer = db.queryDB(multiple_answer);
            if(rSet_multiple_answer.next()){
                qMulti.add(new QuestionEscolhaMultipla(question + "=", rSet_multiple.getString(3), rSet_multiple.getString(4), rSet_multiple.getString(5), rSet_multiple.getString(6),rSet_multiple_answer.getString(1)));
            }else{
               String add_multiple_answer = SQLInstruct.addMultipleAnswer(multiple_id, user_id);
                db.updateDB(add_multiple_answer);
                qMulti.add(new QuestionEscolhaMultipla(question + "=", rSet_multiple.getString(3), rSet_multiple.getString(4), rSet_multiple.getString(5), rSet_multiple.getString(6),"Sem resposta"));
                
                
            }
            
            
        
        }

        


        String development = SQLInstruct.developmentQuestions(selectedTest);
        ResultSet rSet_development = db.queryDB(development);

        LinkedList<Question> qDesen = new LinkedList<Question>();
        while (rSet_development.next()) {
            int development_id = rSet_development.getInt(4);
            String development_answer = SQLInstruct.developmentAnswer(loginname, disciplineSelected, moduleSelected, testSelected.getName(), rSet_development.getString(1));
            ResultSet rSet_development_answer = db.queryDB(development_answer);
            if (rSet_development_answer.next()) {
                QuestionDesenolvimento qD = new QuestionDesenolvimento(rSet_development.getString(1), rSet_development_answer.getString(1));
                qDesen.add(qD);

                //ir buscar URL
                String queryURL = SQLInstruct.urls(rSet_development.getInt(4));
                ResultSet rSet_urls = db.queryDB(queryURL);

                while (rSet_urls.next()) {
                    qD.getUrls().addLast(new URL(rSet_urls.getString(2), rSet_urls.getString(1), rSet_urls.getInt(3)));

                }

            } else {
                String add_development_answer = SQLInstruct.addDevelopmentAnswer(development_id, user_id, "Sem resposta.Clique aqui para responder");
                db.updateDB(add_development_answer);
                qDesen.add(new QuestionDesenolvimento(rSet_development.getString(1), "Sem resposta.clique aqui para responder."));
            }


        }



        testSelected.setQuestions(qMulti);
        testSelected.setQuestionsDesenvolvimento(qDesen);

    }

    public void guardarEM(ActionEvent event) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        
        DBConnect db = new DBConnect(SQLInstruct.dbAdress, SQLInstruct.dbUsername, SQLInstruct.dbPassword);
        db.loadDriver();

        for (Question q : testSelected.getQuestions()) {
     
            
            String question = q.getQuestion().replace("=","");
            
            String saveMultipleAnswer = SQLInstruct.updateMultipleAnswer(q.getUserAnswer().getS(), loginname, disciplineSelected, moduleSelected, testSelected.getName(), question);
            db.updateDB(saveMultipleAnswer);
            
            
        }
        
                db.closeDB();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Respostas guardadas."));
    }

    public void guardar(ActionEvent actionEvent) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {




        DBConnect db = new DBConnect(SQLInstruct.dbAdress, SQLInstruct.dbUsername, SQLInstruct.dbPassword);
        db.loadDriver();

  
        for (Question qD : testSelected.getQuestionsDesenvolvimento()) {
            String user = loginname;
            String discipline = disciplineSelected;
            String module = moduleSelected;
            String test = testSelected.getName();
            String question = qD.getQuestion();
            String answer = qD.getUserAnswer().getS();
            

            String saveAnswer = SQLInstruct.updateAnswer(answer, user, discipline, module, test, question);
            db.updateDB(saveAnswer);

        }
        db.closeDB();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Respostas guardadas."));

    }

    public void selectQuestion(ActionEvent actionEvent) {
        CommandButton cb = (CommandButton) actionEvent.getComponent();
        selectedQuestion = cb.getLabel();
    }

    public void insertURL(ActionEvent actionEvent) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        UIComponent a = actionEvent.getComponent();


        DBConnect db = new DBConnect(SQLInstruct.dbAdress, SQLInstruct.dbUsername, SQLInstruct.dbPassword);
        db.loadDriver();
        String insert_url = SQLInstruct.addUrl(newURL.getName(), newURL.getUrl());
        db.updateDB(insert_url);
        String link_url = SQLInstruct.linkUrlQuestion(selectedQuestion, newURL.getName());
        db.updateDB(link_url);

        for (Question q : testSelected.getQuestionsDesenvolvimento()) {

            if (q.getQuestion().equals(selectedQuestion)) {
                q.getUrls().addLast(newURL);
            }
        }


        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "URL " + newURL.getName() + " adicionado."));


        db.closeDB();




    }

    public void like(ActionEvent actionEvent) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

        CommandButton cb = (CommandButton) actionEvent.getComponent();
        HtmlForm hf = (HtmlForm) cb.getParent();
    



        DBConnect db = new DBConnect(SQLInstruct.dbAdress, SQLInstruct.dbUsername, SQLInstruct.dbPassword);
        db.loadDriver();
        String is_voted = SQLInstruct.isVoted(hf.getTitle(), loginname);
        ResultSet rSet_voted = db.queryDB(is_voted);
        rSet_voted.next();
        int v = rSet_voted.getInt(1);
    
        if (v != 1) {
            String add_evaluation = SQLInstruct.updateUrl(cb.getLabel());
            db.updateDB(add_evaluation);
            String voted = SQLInstruct.voted(hf.getTitle(), loginname);
            db.updateDB(voted);

            for (Question q : testSelected.getQuestionsDesenvolvimento()) {
                if (q.getQuestion().equals(hf.getTitle())) {
                    for (URL url : q.getUrls()) {
                        if (url.getName().equals(cb.getLabel())) {
                            url.setEvaluation(url.getEvaluation() + 1);
                        }
                    }
                }

            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Voto Enviado."));



        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Já Votou uma vez."));
        }
        
        
    }
}
