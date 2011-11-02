package db;

public class SQLInstruct {

    //Database Configs:
    public static final String dbAdress="jdbc:mysql://localhost:3306/school";;
    public static final String dbUsername="root";
    public static final String dbPassword="";
   

    public static String login(String userName, String hashPassword) {
        return "SELECT * FROM Users where"
                + " username='" + userName + "' and password='" + hashPassword + "'";
    }
}
