import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Hotel {
    //--------------------------------list-----------------------------------------------
    public static List<Manager> managers = new ArrayList<>();
    public static List<Employee> employees = new ArrayList<>();
    public static List<Reserve> reserves = new ArrayList<>();
    public static List<Passenger> passengers = new ArrayList<>();
    public static List<Room> rooms = new ArrayList<>();
    public static List<hotelBank> bank = new ArrayList<>();
    public static List<Waiting> waitings=new ArrayList<>();
    public static List<decline> decline=new ArrayList<>();
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
        int c=0;
        for (Passenger i : passengers) {
            if (code.equals(i.getNational_code()) || email.equals(i.getEmail())) {
                c=0;
            } else {
                c++;
            }
        }
        for(Employee i: employees){
            if (code.equals(i.getNational_code()) || email.equals(i.getEmail())) {
                c=0;
            } else {
                c++;
            }
        }
        for(Manager i:managers){
            if (code.equals(i.getNational_code()) || email.equals(i.getEmail())) {
                c=0;
            } else {
                c++;
            }
        }
        if(c==3){
            return true;
        }else{
            return false;
        }

    }

    public static void userproedit(String name, String lname, String email, String pass, String code, double bank) throws SQLException {
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
        connection.close();
    }

    public Passenger passengerLogin(String email, String pass) {
        for (Passenger i : passengers) {
            if (i.getEmail().equals(email) && i.getPass().equals(pass)) {
                return i;
            }
        }
        return null;
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
            Manager manager = new Manager(name, lastname, nationalCode, email, pass);
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

    public void mangerproedit(String name, String lname, String email, String pass, String code) throws SQLException {
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
        connection.close();
    }

    public Manager managerLogin(String email, String pass) {
        for (Manager i : managers) {
            if (i.getEmail().equals(email) && i.getPass().equals(pass)) {
                return i;
            }
        }
        return null;
    }

    public void deletemployee(int employeeid, String name, String lname) throws SQLException {
        for (Employee i : employees) {
            if (i.getPerson_id() == employeeid && i.getName().equals(name) && i.getLast_name().equals(lname)) {
                employees.remove(i);
                String sql = "DELETE FROM employee WHERE idemployee = ?";
                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/hotel", "root", "zahra1382");
                     PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setInt(1, employeeid);
                    statement.executeUpdate();
                    connection.close();
                }
                break;
            }
        }

    }

    public void payment(int employeeid,double pay) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/hotel", "root", "zahra1382");
        Statement statement = connection.createStatement();
        String sql = "DELETE FROM employee";
        statement.executeUpdate(sql);
        for (Employee i : employees) {
            if (i.getPerson_id() == employeeid) {
                i.setSalary(pay);
                }
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
        connection.close();
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
            String email = employeeRS.getString("emaill");
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

    public void employeeproedit(String name, String lname, String email, String pass, String code, double bank) throws SQLException {
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
        connection.close();
    }

    public Employee employeeLogin(String email, String pass) {
        for (Employee i : employees) {
            if (i.getEmail().equals(email) && i.getPass().equals(pass)) {
                return i;

            }
        }
        return null;
    }
    //-----------------------------------------------------------------------------------------

   //---------------------------------all about rooms and reserves-----------------------------

    public static void roomsWrite(Room r) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/hotel",
                "root", "zahra1382");
        Statement statement = connection.createStatement();
        String sqlQuery = "INSERT INTO rooms VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setInt(1, r.getRoom_num());
        preparedStatement.setDouble(2, r.getPrice());
        preparedStatement.setInt(3, r.getBed_num());
        preparedStatement.setInt(4, r.getIsave());
        preparedStatement.executeUpdate();
    }

    public void roomsRead() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/hotel",
                "root", "zahra1382");

        Statement statement = connection.createStatement();
        ResultSet roomsRS = statement.executeQuery("SELECT * FROM rooms");
        while (roomsRS.next()) {
            double price = roomsRS.getDouble("price");
            int bednum = roomsRS.getInt("bednumber");
            int isave = roomsRS.getInt("isave");
            Room room = new Room(price,bednum,isave);
            Hotel.rooms.add(room);
        }
    }

    public void deleteroom(int roomnum) throws SQLException {
        for (Room i : rooms) {
            if (i.getRoom_num()==roomnum) {
                rooms.remove(i);
                String sql = "DELETE FROM rooms WHERE roomnum = ?";
                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/hotel", "root", "zahra1382");
                     PreparedStatement statement = connection.prepareStatement(sql)) {
                     statement.setInt(1, roomnum);
                     statement.executeUpdate();
                     connection.close();
                }
            }
        }

    }

    public void price(int roomnum,double price) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/hotel", "root", "zahra1382");
        Statement statement = connection.createStatement();
        String sql = "DELETE FROM rooms";
        statement.executeUpdate(sql);
        for (Room i : rooms) {
            if (i.getRoom_num()== roomnum) {
                i.setPrice(price);
            }
        }

        for (Room i : rooms) {
            String sqlQuery = "INSERT INTO rooms VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, i.getRoom_num());
            preparedStatement.setDouble(2, i.getPrice());
            preparedStatement.setInt(3, i.getBed_num());
            preparedStatement.setInt(4, i.getIsave());
            preparedStatement.executeUpdate();
        }
        connection.close();
    }

    public Room cheackroom(int bed) throws SQLException {
        for (Room r : rooms) {
            if (r.getBed_num() == bed && r.getIsave() == 0) {
                notave(r.getRoom_num());
                return r;
            }
        }
        return null;
    }

    public void notave(int roomnum) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/hotel", "root", "zahra1382");
        Statement statement = connection.createStatement();
        String sql = "DELETE FROM rooms";
        statement.executeUpdate(sql);
        for (Room i : rooms) {
            if (i.getRoom_num()== roomnum) {
               i.setIsave(-1);
            }
        }

        for (Room i : rooms) {
            String sqlQuery = "INSERT INTO rooms VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, i.getRoom_num());
            preparedStatement.setDouble(2, i.getPrice());
            preparedStatement.setInt(3, i.getBed_num());
            preparedStatement.setInt(4, i.getIsave());
            preparedStatement.executeUpdate();
        }
        connection.close();
    }

    public void makeave(int roomnum) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/hotel", "root", "zahra1382");
        Statement statement = connection.createStatement();
        String sql = "DELETE FROM rooms";
        statement.executeUpdate(sql);
        for (Room i : rooms) {
            if (i.getRoom_num()== roomnum) {
                i.setIsave(0);
            }
        }

        for (Room i : rooms) {
            String sqlQuery = "INSERT INTO rooms VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, i.getRoom_num());
            preparedStatement.setDouble(2, i.getPrice());
            preparedStatement.setInt(3, i.getBed_num());
            preparedStatement.setInt(4, i.getIsave());
            preparedStatement.executeUpdate();
        }
        connection.close();
    }
    public void employee_check_reserve(Employee employee)
    {
        JFrame f8 = new JFrame("User "+employee.getName());
        f8.setResizable(false);
        f8.getContentPane().setBackground(Color.white);
        f8.setSize(1000,800);
        JTable table=new JTable();
        JPanel panel = new JPanel();
        panel.setLayout(null);
        DefaultTableModel model  = new DefaultTableModel();
        model.addColumn(" ");
        model.addColumn("passenger code");
        model.addColumn("stay time");
        model.addColumn("bed number");
        model.addColumn("room number");
        model.addColumn("pay");
        for (Waiting w : waitings) {

            model.addRow(new Object[]{w.getWaiting_id(),w.getNational(),w.getStaytime(), w.getBednumber(),w.getRoomnumber(),w.getPay()});
        }
        table = new JTable(model);
        JButton acceptButton = new JButton("Accept");
        JButton declineButton = new JButton("Decline");
        JTextField textField = new JTextField(20);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(acceptButton);
        buttonPanel.add(declineButton);
        buttonPanel.add(textField);
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(new JScrollPane(table), BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        f8.add(mainPanel);
        acceptButton.addActionListener(e -> {
            int id= Integer.parseInt(textField.getText());
            for(Waiting w:waitings)
            {
                if(w.getWaiting_id()==id)
                {
                    try {
                        deletewaiting(id);
                        JOptionPane.showMessageDialog(f8,"the request accepted");
                        Reserve reserve=new Reserve(w.getNational(),w.getPay(),employee.getNational_code(),w.getStaytime(),w.getRoomnumber());
                        reserves.add(reserve);
                        App.employeepage(employee);
                        f8.setVisible(false);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        declineButton.addActionListener(e -> {
            int id= Integer.parseInt(textField.getText());
            for(Waiting w:waitings){
                if(w.getWaiting_id()==id){
                    for(Room r:rooms){
                        try {
                            makeave(r.getRoom_num());
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    decline d=new decline(w.getNational(),employee.getNational_code());
                    decline.add(d);
                    try {
                        declinewrite(d);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    JOptionPane.showMessageDialog(f8,"the request declined");
                }
            }
        });
        f8.setVisible(true);
    }

    public void declinewrite(decline d) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/hotel",
                "root", "zahra1382");
        Statement statement = connection.createStatement();
        String sqlQuery = "INSERT INTO decline VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setInt(1,d.getDeclineid() );
        preparedStatement.setString(2,d.getPassengercode());
        preparedStatement.setString(3, d.getEmployeecode());
        preparedStatement.executeUpdate();
    }

    public void declineread() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/hotel",
                "root", "zahra1382");

        Statement statement = connection.createStatement();
        ResultSet declineRS = statement.executeQuery("SELECT * FROM decline");
        while (declineRS.next()) {
            String passengercode = declineRS.getString("passengercode");
            String employeecode = declineRS.getString("employeecode");
            decline d= new decline(passengercode,employeecode);
            Hotel.decline.add(d);
        }
    }

//----------------------------------------------waiting-------------------------------------------
    public void waitingWrite(Waiting w) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/hotel",
                "root", "zahra1382");
        Statement statement = connection.createStatement();
        String sqlQuery = "INSERT INTO waitings VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setInt(1, w.getWaiting_id());
        preparedStatement.setString(2, w.getNational());
        preparedStatement.setInt(3, w.getStaytime());
        preparedStatement.setInt(4, w.getBednumber());
        preparedStatement.setInt(5, w.getRoomnumber());
        preparedStatement.setDouble(6, w.getPay());
        preparedStatement.executeUpdate();
    }

    public void waitingRead() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/hotel",
                "root", "zahra1382");

        Statement statement = connection.createStatement();
        ResultSet waitingRS = statement.executeQuery("SELECT * FROM waitings");
        while (waitingRS.next()) {
            String code = waitingRS.getString("nationalcode");
            int staytime = waitingRS.getInt("staytime");
            int bednumber = waitingRS.getInt("bednumber");
            int roomnumber = waitingRS.getInt("roomnumber");
            double pay = waitingRS.getDouble("pay");
            Waiting waiting = new Waiting(code,staytime,bednumber,roomnumber,pay);
            Hotel.waitings.add(waiting);
        }
    }
    public static void deletewaiting(int waitid) throws SQLException {
        for (Waiting i : waitings) {
            if (i.getWaiting_id() == waitid) {
                waitings.remove(i);
                String sql = "DELETE FROM waitings WHERE idwaitings = ?";
                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/hotel", "root", "zahra1382");
                     PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setInt(1, waitid);
                    statement.executeUpdate();
                    connection.close();
                }
                break;
            }
        }

    }

    //------------------------------------------------------------------------------------------------------

    //--------------------------------------------------bank-------------------------------------------------
    public static void bank()
    {
        for (Passenger i:passengers)
        {
            hotelBank bank=new hotelBank(i.getNational_code(),i.getBankBalance(),0,0,0);

        }


    }
}


