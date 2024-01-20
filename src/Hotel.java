import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Hotel {
    //--------------------------------list--------------------------------------
    public static List<Manager> managers = new ArrayList<>();
    public static List<Employee> employees = new ArrayList<>();
    public static Queue<Reserve> reserves = new LinkedList<>();
    public static List<Passenger> passengers = new ArrayList<>();
    public static List<Room> rooms = new ArrayList<>();
    //---------------------------------------------------------------------------

    //----------------------------data write--------------------------------
    public static void dataWrite() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/hotel",
                    "root", "zahra1382");

            Statement statement = connection.createStatement();

            // statement.execute("USE jdbc");

            String sql = "DELETE FROM employee";
            statement.executeUpdate(sql);

            //sql = "DELETE FROM passenger";
            // statement.executeUpdate(sql);

            sql = "DELETE FROM manager";
            statement.executeUpdate(sql);

            sql = "DELETE FROM reserves";
            statement.executeUpdate(sql);

            for (Manager i : managers) {
                String sqlQuery = "INSERT INTO manager VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                preparedStatement.setInt(1, i.getPerson_id());
                preparedStatement.setString(2, i.getName());
                preparedStatement.setString(3, i.getLast_name());
                preparedStatement.setString(4, i.getNational_code());
                preparedStatement.setString(5, i.getEmail());
                preparedStatement.setString(6, i.getPass());
                preparedStatement.executeUpdate();
            }

            for (Employee i : employees) {

                String sqlQuery = "INSERT INTO employee VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                preparedStatement.setInt(1, i.getPerson_id());
                preparedStatement.setString(2, i.getName());
                preparedStatement.setString(3, i.getLast_name());
                preparedStatement.setString(4, i.getNational_code());
                preparedStatement.setInt(5, i.getWorkcode());
                preparedStatement.setString(6, i.getEmail());
                preparedStatement.setString(7, i.getPass());
                preparedStatement.setDouble(8, i.getSalary());
                preparedStatement.executeUpdate();
            }

            for (Passenger i : passengers) {

                String sqlQuery = "INSERT INTO passenger VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                preparedStatement.setInt(1, i.getPerson_id());
                preparedStatement.setString(2, i.getNational_code());
                preparedStatement.setString(3, i.getName());
                preparedStatement.setString(4, i.getLast_name());
                preparedStatement.setString(5, i.getPass());
                preparedStatement.setString(6, i.getEmail());
                preparedStatement.setDouble(7, i.getBankBalance());
                preparedStatement.executeUpdate();
            }

            for (Reserve i : reserves) {
                String sqlQuery = "INSERT INTO reserves VALUES (?, ?, ?, ?, ?, ?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                preparedStatement.executeUpdate();
            }
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //--------------------------------data read----------------------------------
    public static void dataRead() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/hotel",
                    "root", "zahra1382");

            Statement statement = connection.createStatement();
            ResultSet mangerRs = statement.executeQuery("SELECT * FROM manager");


            while (mangerRs.next()) {
                String name = mangerRs.getString("name");
                String lastname = mangerRs.getString("lastname");
                String nationalCode = mangerRs.getString("nationalcode");
                String email = mangerRs.getString("email");
                String pass = mangerRs.getString("pass");
                Manager manager = new Manager(name, lastname, nationalCode, email, pass);
                Hotel.managers.add(manager);
            }

            ResultSet employeeRS = statement.executeQuery("SELECT * FROM employee");

            while (employeeRS.next()) {
                String name = employeeRS.getString("name");
                String lastname = employeeRS.getString("lastname");
                String nationalCode = employeeRS.getString("nationalcode");
                String workcode = employeeRS.getString("workcode");
                String email = employeeRS.getString("email");
                String pass = employeeRS.getString("pass");
                double salary = employeeRS.getDouble("Salary");

                Employee employee = new Employee(name, lastname, nationalCode, workcode, email, pass, salary);
                Hotel.employees.add(employee);
            }

            ResultSet passengerRS = statement.executeQuery("SELECT * FROM passenger");
            while (passengerRS.next()) {
                String nationalCode = passengerRS.getString("nationalcode");
                String name = passengerRS.getString("name");
                String lastname = passengerRS.getString("lastname");
                String pass = passengerRS.getString("pass");
                String email = passengerRS.getString("email");
                double banckblance = passengerRS.getDouble("Salary");
                Passenger passenger = new Passenger(nationalCode, name, lastname, pass, email, banckblance);
                Hotel.passengers.add(passenger);
            }

            ResultSet reserveRS = statement.executeQuery("SELECT * FROM reserves");

            while (reserveRS.next()) {
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //------------------for add and remove a person or an object------------------
    public static void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public static void addReserve(Reserve reserve) {
        reserves.add(reserve);
    }

    public static void addPassenger(Passenger passenger) {
        passengers.add(passenger);
    }

    public static void addRoom(Room room) {
        rooms.add(room);
    }

    public static void removeEmployee(Employee employee) {
        employees.remove(employee);
    }

    public static void removeReserve(Reserve reserve) {
        reserves.remove(reserve);
    }

    public static void removePassenger(Passenger passenger) {
        passengers.remove(passenger);
    }

    public static void removeRoom(Room room) {
        rooms.remove(room);
    }
    //------------------------------------------------------------------------------------

    //--------------------------------sign up method--------------------------------------
    public boolean signup(String name, String lastname, String code, String email, String pass) {
        String[] Code = code.split("");
        int c = 0;
        if (!name.equals("")) {
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
        if (cheackPassword(pass)) {
            c++;
        }
        if (c == 5) {
            return true;
        } else {
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



//-----------------------------------all about passenger---------------------------------
    public static void passengerWrite(Passenger p) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/hotel",
                "root", "zahra1382");
        Statement statement = connection.createStatement();
        String sqlQuery = "INSERT INTO passenger VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setInt(1, p.getPerson_id());
        preparedStatement.setString(2, p.getNational_code());
        preparedStatement.setString(3, p.getName());
        preparedStatement.setString(4, p.getLast_name());
        preparedStatement.setString(5, p.getPass());
        preparedStatement.setString(6, p.getEmail());
        preparedStatement.setDouble(7, p.getBankBalance());
        preparedStatement.executeUpdate();
    }

    public void passengerRead() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/hotel",
                "root", "zahra1382");

        Statement statement = connection.createStatement();
        ResultSet passengerRS = statement.executeQuery("SELECT * FROM passenger");
        while (passengerRS.next()) {
            String nationalCode = passengerRS.getString("nationalcode");
            String name = passengerRS.getString("name");
            String lastname = passengerRS.getString("lastname");
            String pass = passengerRS.getString("pass");
            String email = passengerRS.getString("email");
            double banckblance = passengerRS.getDouble("banckbalance");
            Passenger passenger = new Passenger(nationalCode, name, lastname, pass, email, banckblance);
            Hotel.passengers.add(passenger);
        }

    }
    public boolean similar(String code, String email) throws SQLException {
        passengerRead();
        for (Passenger i : passengers) {
            if (code.equals(i.getNational_code()) || email.equals(i.getEmail())) {
                return false;
            } else {
                return true;
            }
        }
        return true;
    }

    public static void userproedit( String name, String lname, String email, String pass,String code,double bank) throws SQLException {
        for (Passenger i : passengers) {
            if (i.getNational_code().equals(code)) {
                i.setName(name);
                i.setLast_name(lname);
                i.setEmail(email);
                i.setPass(pass);
                i.setBankBalance(bank);
            }
        }
        overwritepassenger();
    }

    public static void overwritepassenger() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/hotel", "root", "zahra1382");
        Statement statement = connection.createStatement();
        String sql = "DELETE FROM passenger";
        statement.executeUpdate(sql);
        for(Passenger i:passengers)
        {
            String sqlQuery = "INSERT INTO passenger VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, i.getPerson_id());
            preparedStatement.setString(2, i.getNational_code());
            preparedStatement.setString(3, i.getName());
            preparedStatement.setString(4, i.getLast_name());
            preparedStatement.setString(5, i.getPass());
            preparedStatement.setString(6, i.getEmail());
            preparedStatement.setDouble(7, i.getBankBalance());
            preparedStatement.executeUpdate();
        }
        connection.close();
    }
    public boolean passengerLogin(String email,String pass){
        for (Passenger i : passengers) {
            if (i.getEmail().equals(email) && i.getPass().equals(pass)) {
                App.userpagee(i);
                return true;
            }else{
                return false;
            }
        }
        return true;
    }
 //--------------------------------------------------------------------------------------

 //-----------------------------------all about manager----------------------------------
 public static void managerWrite(Manager m) throws SQLException {
     Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/hotel",
             "root", "zahra1382");
     Statement statement = connection.createStatement();
     String sqlQuery = "INSERT INTO manager VALUES (?, ?, ?, ?, ?, ?)";
     PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
     preparedStatement.setInt(1, m.getPerson_id());
     preparedStatement.setString(2, m.getName());
     preparedStatement.setString(3, m.getLast_name());
     preparedStatement.setString(4, m.getNational_code());
     preparedStatement.setString(5, m.getEmail());
     preparedStatement.setString(6, m.getPass());
     preparedStatement.executeUpdate();
 }
    public void managerRead() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/hotel",
                "root", "zahra1382");

        Statement statement = connection.createStatement();
        ResultSet managerRS = statement.executeQuery("SELECT * FROM manager");
        while (managerRS.next()) {
            String name = managerRS.getString("name");
            String lastname = managerRS.getString("lastname");
            String nationalCode = managerRS.getString("nationalcode");
            String email = managerRS.getString("email");
            String pass = managerRS.getString("pass");
            Manager manager = new Manager(name,lastname,nationalCode,email,pass);
            Hotel.managers.add(manager);
        }
    }

    public boolean similarmanger(String code, String email) throws SQLException {
        managerRead();
        for (Manager i : managers) {
            if (code.equals(i.getNational_code()) || email.equals(i.getEmail())) {
                return false;
            } else {
                return true;
            }
        }
        return true;
    }

    public void mangerproedit( String name, String lname, String email, String pass,String code) throws SQLException {
        for (Manager i : managers) {
            if (i.getNational_code().equals(code)) {
                i.setName(name);
                i.setLast_name(lname);
                i.setEmail(email);
                i.setPass(pass);
            }
        }
        overwritemanager();
    }

    public static void overwritemanager() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/hotel", "root", "zahra1382");
        Statement statement = connection.createStatement();
        String sql = "DELETE FROM manager";
        statement.executeUpdate(sql);
        for(Manager i:managers)
        {
            String sqlQuery = "INSERT INTO manager VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, i.getPerson_id());
            preparedStatement.setString(2, i.getName());
            preparedStatement.setString(3, i.getLast_name());
            preparedStatement.setString(4, i.getNational_code());
            preparedStatement.setString(5, i.getEmail());
            preparedStatement.setString(6, i.getPass());
            preparedStatement.executeUpdate();
        }
        connection.close();
    }
    public boolean managerLogin(String email,String pass) {
        for (Manager i : managers) {
            System.out.println(i.getName());
            if (i.getEmail().equals(email) && i.getPass().equals(pass)) {
                App.managerpage(i);
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

//---------------------------------------------------------------------------------

//----------------------------------all about employee----------------------------
    public static void employeeWrite(Employee e) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/hotel",
                "root", "zahra1382");
        Statement statement = connection.createStatement();
        String sqlQuery = "INSERT INTO employee VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setInt(1, e.getPerson_id());
        preparedStatement.setString(2, e.getName());
        preparedStatement.setString(3, e.getLast_name());
        preparedStatement.setString(4, e.getNational_code());
        preparedStatement.setInt(5, e.getWorkcode());
        preparedStatement.setString(6, e.getEmail());
        preparedStatement.setString(7, e.getPass());
        preparedStatement.setDouble(8, e.getSalary());
        preparedStatement.executeUpdate();
    }

    public void employeeRead() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/hotel",
                "root", "zahra1382");

        Statement statement = connection.createStatement();
        ResultSet employeeRS = statement.executeQuery("SELECT * FROM employee");
        while (employeeRS.next()) {
            String name = employeeRS.getString("name");
            String lastname = employeeRS.getString("lastname");
            String nationalCode = employeeRS.getString("nationalcode");
            String workcode = employeeRS.getString("workcode");
            String email = employeeRS.getString("email");
            String pass = employeeRS.getString("pass");
            double salary = employeeRS.getDouble("Salary");

            Employee employee = new Employee(name, lastname, nationalCode, workcode, email, pass, salary);
            Hotel.employees.add(employee);
        }

    }
    public boolean similaremployee(String code, String email) throws SQLException {
        employeeRead();
        for (Employee i : employees) {
            if (code.equals(i.getNational_code()) || email.equals(i.getEmail())) {
                return false;
            } else {
                return true;
            }
        }
        return true;
    }

    public static void employeeproedit( String name, String lname, String email, String pass,String code,double bank) throws SQLException {
        for (Employee i : employees) {
            if (i.getNational_code().equals(code)) {
                i.setName(name);
                i.setLast_name(lname);
                i.setEmail(email);
                i.setPass(pass);
                i.setSalary(bank);
            }
        }
        overwriteemmployee();
    }

    public static void overwriteemmployee() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/hotel", "root", "zahra1382");
        Statement statement = connection.createStatement();
        String sql = "DELETE FROM employee";
        statement.executeUpdate(sql);
        for(Employee i:employees)
        {
            String sqlQuery = "INSERT INTO employee VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, i.getPerson_id());
            preparedStatement.setString(2, i.getName());
            preparedStatement.setString(3, i.getLast_name());
            preparedStatement.setString(4, i.getNational_code());
            preparedStatement.setInt(5, i.getWorkcode());
            preparedStatement.setString(6, i.getEmail());
            preparedStatement.setString(7, i.getPass());
            preparedStatement.setDouble(8, i.getSalary());
            preparedStatement.executeUpdate();
        }
        connection.close();
    }
    public boolean employeeLogin(String email,String pass){
        for (Employee i : employees) {
            if (i.getEmail().equals(email) && i.getPass().equals(pass)) {
                App.employeepage(i);
                return true;
            }else{
                return false;
            }
        }
        return true;
    }

}