import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Hotel {
    public static List<Employee> employees = new ArrayList<>();
    public static Queue<Reserve> reserves = new LinkedList<>();
    public static List<Passenger> passengers = new ArrayList<>();
    public static List<Room> rooms = new ArrayList<>();

    public static void addEmployee(Employee employee) {
        employees.add(employee);
    }

    //--------------------------------sign up method--------------------------------------
    public boolean signup(String name, String lastname, String code, String email, String pass) {
        String[] Code = code.split("");
        int c = 0;
        if (!name.equals("")){
            c++;
        }
        if (!lastname.equals("")) {
            c++;
        }
        if (Code.length == 10) {
            c++;
        }
        if (cheack(email) == true) {
            c++;
        }
        if (cheackPassword(pass)){
            c++;
        }
        if (c==5){
            return true;
        }else{
            return false;
        }
    }

    public boolean cheack(String email) {
        String regex = "^[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9.]+)*@" + "(?:[a-zA-Z0-9.]+\\.)+[a-zA-Z]{2,}$";
        boolean result = email.matches(regex);
        if (result) {
            return true;
        } else
            return false;
    }

    public boolean cheackPassword(String in) {
        int c = 0;
        if (level1(in) == true) {
            c++;
        }
        if (level2(in) == true) {
            c++;
        }
        if (level3(in) == true) {
            c++;
        }
        if (level4(in) == true) {
            c++;
        }
        if (level5(in) == true) {
            c++;
        }
        if (c >= 3) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean level1(String in) {
        Pattern pattern1 = Pattern.compile("[a-z]+");
        Matcher match = pattern1.matcher(in);
        boolean matchFound = match.find();
        if (matchFound == true) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean level2(String in) {
        Pattern pattern2 = Pattern.compile("[A-Z]+");
        Matcher match = pattern2.matcher(in);
        boolean matchFound = match.find();
        if (matchFound == true) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean level3(String in) {
        Pattern pattern3 = Pattern.compile("[0-9]+");
        Matcher match = pattern3.matcher(in);
        boolean matchFound = match.find();
        if (matchFound == true) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean level4(String in) {
        Pattern pattern4 = Pattern.compile("[!@#%&/.=]+");
        Matcher match = pattern4.matcher(in);
        boolean matchFound = match.find();
        if (matchFound == true) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean level5(String in) {
        String[] arr = in.split("");
        if (arr.length >= 8) {
            return true;
        } else {
            return false;
        }
    }
   //---------------------------------------end---------------------------------------------
    public boolean log(String email,String pass)
    {
        if(email.equals("a") && pass.equals("a"))
        {
            return true;
        }else{
            return false;
        }
    }
}
