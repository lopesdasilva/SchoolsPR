package db;

public class SQLInstruct {

    //Database Configs:

   
   //public static final String dbAdress="jdbc:mysql://lopesdasilva.servebeer.com:3306/school";
    public static final String dbAdress="jdbc:mysql://localhost:3306/School";
    public static final String dbUsername="root";
    public static final String dbPassword="";



    public static String login(String userName, String hashPassword) {
        return "SELECT * FROM user where"
                + " username='" + userName + "' and password='" + hashPassword + "'";
    }
    public static String informacoes (String username){
       // return "Select name, discipline from User,Discipline,UserDiscipline WHERE User.name='"+username+"' "
       //         + "AND User.id=UserDiscipline.user_id AND UserDiscipline.discipline_id=Discipline.id";

        return "Select discipline, discipline.info ,module,module.info  from user,userdiscipline,discipline,disciplineModule,module WHERE user.name='"+username+"' AND user.id=userdiscipline.user_id AND userdiscipline.discipline_id=discipline.id AND discipline.id=disciplinemodule.discipline_id AND disciplinemodule.module_id=module.id ";
        
    }

    public static String tests(String discipline, String module) {
        return "SELECT test FROM discipline,disciplinemodule,module,moduletest,test WHERE discipline.discipline='"+ discipline +"' AND module.module='"+ module+"' AND discipline.id=disciplinemodule.discipline_id AND disciplinemodule.module_id=module.id AND module.id=moduletest.module_id AND moduletest.test_id=test.id";
    }
    
    public static String notices(String userName){
        return "SELECT discipline.discipline ,notice.notice, notice.isRead FROM user,userdiscipline,discipline,disciplinenotice,notice WHERE user.name='"+userName+"' AND user.id=userdiscipline.user_id AND userdiscipline.discipline_id=discipline.id AND discipline.id=disciplinenotice.discipline_id AND  disciplinenotice.notice_id=notice.id";
    }
    
    //só testado no workbench
    public static String developmentQuestions(String test){
        return "SELECT question,answer,correct_answer FROM development, test,testdevelopment WHERE test.test='"+test+"' AND test.id=testdevelopment.test_id AND testdevelopment.development_id = development.id";
    }
    
    public static String multipleQuestions(String test){
        return "SELECT question,answer,h1,h2,h3,h4,correct_answer FROM multiple, test,testmultiple WHERE test.test='"+test+"' AND test.id=testmultiple.test_id AND testmultiple.multiple_id = multiple.id";
    }
    
    //POR FAZER
    public static String updateAnswer(String answer, String user, String discipline, String module,String test,String question){
        return "UPDATE development SET development.answer='"+answer+"' WHERE EXISTS"
                 +"(SELECT question FROM user,userdiscipline,discipline,disciplinemodule,module,moduletest,test,testdevelopment WHERE"
                  +"user.name = '"+user+"' AND"
                  +"user.id=userdiscipline.user_id AND"
                  +"userdiscipline.discipline_id=discipline.id AND"
                  +"discipline.discipline='"+discipline+"' AND"
                  +"discipline.id=disciplinemodule.discipline_id AND"
                  +"disciplinemodule.module_id=module.id AND module.module='"+module+"' AND"
                  +"module.id=moduletest.module_id AND"
                  +"moduletest.test_id = test.id AND test.test='"+test+"' AND"
                  +"test.id=testdevelopment.test_id AND testdevelopment.development_id=development.id AND"
                  +"development.question='"+question+"') ";
        
    }

  
    
}
