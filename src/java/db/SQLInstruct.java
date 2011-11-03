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
    public static String disciplinas (String username){
        return "select Users.username, Course.name from Users,Course where Users.username='"+username+"'";
    }
}
