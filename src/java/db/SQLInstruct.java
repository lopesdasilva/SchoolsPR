package db;

public class SQLInstruct {

    //Database Configs:
    //public static final String dbAdress="jdbc:mysql://192.168.1.2:3306/school";
   public static final String dbAdress="jdbc:mysql://lopesdasilva.servebeer.com:3306/school";
    //public static final String dbAdress="jdbc:mysql://localhost:3306/school";
    public static final String dbUsername="root";
    public static final String dbPassword="rr2011";
   

    public static String login(String userName, String hashPassword) {
        return "SELECT * FROM User where"
                + " username='" + userName + "' and password='" + hashPassword + "'";
    }
    public static String informacoes (String username){
       // return "Select name, discipline from User,Discipline,UserDiscipline WHERE User.name='"+username+"' "
       //         + "AND User.id=UserDiscipline.user_id AND UserDiscipline.discipline_id=Discipline.id";

        return "Select discipline, module  from User,UserDiscipline,Discipline,DisciplineModule,Module WHERE User.name='"+username+"' AND User.id=UserDiscipline.user_id AND UserDiscipline.discipline_id=Discipline.id AND Discipline.id=DisciplineModule.discipline_id AND DisciplineModule.module_id=Module.id ";
        
    }
    
    public static String tests(String module){
        return "SELECT test FROM Module,ModuleTest,Test WHERE Module.id='"+module+"' AND Module.id=ModuleTest.module_id AND ModuleTest.test_id=Test.id";
    }
    
    
    
    
}
