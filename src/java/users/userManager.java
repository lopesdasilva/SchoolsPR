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
import java.util.LinkedList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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

/**
 *
 * @author lopesdasilva
 */
@ManagedBean(name = "userM")
@SessionScoped
public class userManager implements Serializable {

    String rui = "teste";

    public void setRui(String rui) {
        this.rui = rui;
    }

    public String getRui() {
        return rui;
    }
    
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
                user_id=rSet.getInt(1);
                
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

        //Meter as variaveis que o rui ainda vai arranjar.
        String sqlStatement = SQLInstruct.tests(disciplineSelected, moduleSelected);
        ResultSet rSet = db.queryDB(sqlStatement);



        for (Discipline d : current.disciplines) {
            if (d.getName().equals(disciplineSelected)) {
                if (d.existe(moduleSelected).getTests().size() == 0) {//só adiciona caso a lista ainda esteja vazia :)
                    while (rSet.next()) {
                        d.addTest(moduleSelected, rSet.getString(1));//Substituir SumModule
                    }
                }

            }

        }
        moduleSelectedList = current.existe(disciplineSelected).existe(moduleSelected);





// questions
        //    String query_development = SQLInstruct.developmentQuestions("Math");//Alterar por botão.
        //  ResultSet query_questions_development = db.queryDB(query_development);




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

    public void yahoo(ActionEvent actionEvent) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
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

        //TODO importar as perguntas so de uma escolha para o java
        LinkedList<Question> qMulti = new LinkedList<Question>();

        while (rSet_multiple.next()) {
            qMulti.add(new QuestionEscolhaMultipla(rSet_multiple.getString(1) + "=", rSet_multiple.getString(3), rSet_multiple.getString(4), rSet_multiple.getString(5), rSet_multiple.getString(6)));
        }

        /* EXEMPLO DO RUI
        qMulti.add(new QuestionEscolhaMultipla("2+2=","4","5","3","6"));
        qMulti.add(new QuestionEscolhaMultipla("2*2=","2","4","6","7"));
        qMulti.add(new QuestionEscolhaMultipla("2/2=","1","5","9","6"));
         */

        testSelected = new Test(this.selectedTest);


        String development = SQLInstruct.developmentQuestions(selectedTest);
        ResultSet rSet_development = db.queryDB(development);

        LinkedList<Question> qDesen = new LinkedList<Question>();
        while (rSet_development.next()) {
            int development_id=rSet_development.getInt(4);
            String development_answer = SQLInstruct.developmentAnswer(loginname, disciplineSelected, moduleSelected, testSelected.getName(), rSet_development.getString(1));
            ResultSet rSet_development_answer = db.queryDB(development_answer);
            if (rSet_development_answer.next()) {
                qDesen.add(new QuestionDesenolvimento(rSet_development.getString(1), rSet_development_answer.getString(1)));
            } else {
                String add_development_answer=SQLInstruct.addDevelopmentAnswer(development_id,user_id);
                db.updateDB(add_development_answer);
                qDesen.add(new QuestionDesenolvimento(rSet_development.getString(1), "Sem resposta.clique aqui para responder."));
            }
        }

        //EXEMPLO DO RUI
        /*
         * qDesen.add(new QuestionDesenolvimento("Quem foi o Primeiro Rei de Portugal?"));
        qDesen.add(new QuestionDesenolvimento("Quem mandou plantar o pinhal de Leiria?"));
        qDesen.add(new QuestionDesenolvimento("Quem é o Presidente da República?"));
        qDesen.getFirst().setUserAnswer(new Answer("A minha Resposta"));
         */


        testSelected.setQuestions(qMulti);
        testSelected.setQuestionsDesenvolvimento(qDesen);

    }

    public void guardar(ActionEvent actionEvent) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {





        //  System.out.println("PERGUNTA: "+qD.getQuestion());
        // System.out.println("Resposta: "+qD.getUserAnswer().getS());


        DBConnect db = new DBConnect(SQLInstruct.dbAdress, SQLInstruct.dbUsername, SQLInstruct.dbPassword);
        db.loadDriver();

        /*
         *Falta este método ser invocado quando é clicado no botão.
         *Falta ter os dados que vem de seguida à mão para enviar para o sqlisntruct.
         *Falta, também, receber a resposta que está no campo de texto. 
         */
        for (Question qD : testSelected.getQuestionsDesenvolvimento()) {
            System.out.println("A guardar a pergunta: " + qD.getQuestion());
            String user = loginname;
            String discipline = disciplineSelected;
            String module = moduleSelected;
            String test = testSelected.getName();
            String question = qD.getQuestion();
            String answer = qD.getUserAnswer().getS();
            System.out.println("Username: " + user
                    + " \nDisciplina: " + discipline
                    + " \nModule: " + module
                    + " \nTestName: " + test
                    + " \nQuestion: " + question
                    + " \nUserAnswer: " + answer);

            String saveAnswer = SQLInstruct.updateAnswer(answer, user, discipline, module, test, question);
            System.out.println(saveAnswer);
            db.updateDB(saveAnswer);

        }
        db.closeDB();
    }
}
