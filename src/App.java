import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Vector;

public class App {
    public static Hotel hotel=new Hotel();
    private static final String username="root";
    private static final String password="zahra1382";
    private static final String dataconn="jdbc:mysql://localhost:3307/hotel";
    static Connection sqlcon=null;
    static PreparedStatement pst=null;
    static ResultSet result=null;
    static int q;
    static int i;
    int id;
    int deletitem;
    public static JTable table=new JTable(0,0);
    public static void main(String[] args) throws ClassNotFoundException {
        if(connection()==true)
        {
            start();
        }else{
            System.out.println("database is not connect!");
        }
    }
    public static boolean connection()
    {
        try {
            Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3307/hotel","root","zahra1382");
            Statement statement=connection.createStatement();
            return true;
        }catch (SQLException e){
            return false;
        }
    }

    public static void updateDb()
    {

        try {
            sqlcon=DriverManager.getConnection(username,password,dataconn);
            pst=sqlcon.prepareStatement("select * from hotel");
            result=pst.executeQuery();
            ResultSetMetaData stdata=result.getMetaData();
            q=stdata.getColumnCount();
            DefaultTableModel recordtable=(DefaultTableModel)table.getModel();
            recordtable.setRowCount(0);
            while (result.next()){
                Vector columndata=new Vector();
                for(i=1 ; i<=q ; i++)
                {
                    columndata.add(result.getShort("idemployee"));
                    columndata.add(result.getShort("name"));
                    columndata.add(result.getShort("lastname"));
                    columndata.add(result.getShort("nationalcode"));
                    columndata.add(result.getShort("workcode"));
                    columndata.add(result.getShort("email"));
                    columndata.add(result.getShort("pass"));
                    columndata.add(result.getShort("salary"));
                }
                recordtable.addRow(columndata);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static void start()
    {
        JFrame f1=new JFrame("menu");
        ImageIcon icon = new ImageIcon("C:\\Users\\saraye tel\\Hotel_management\\pic\\logo.png");
        JLabel l0=new JLabel(icon);
        l0.setBounds(370,0,250,250);
        f1.add(l0);
        f1.setResizable(false);
        f1.getContentPane().setBackground(Color.white);
        f1.setSize(1000,1000);
        f1.setLayout(null);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setLocationRelativeTo(null);
        JButton b1=new JButton("sign");
        b1.setFont(new Font("Serif", Font.BOLD, 40));
        b1.setBounds(450,350,150,50);
        b1.setBackground(Color.black);
        b1.setBorder(new LineBorder(Color.black));
        f1.add(b1);
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f1.setVisible(false);
                signup();
            }
        });
        JButton b2=new JButton("log in");
        b2.setFont(new Font("Serif", Font.BOLD, 40));
        b2.setBounds(450,450,150,50);
        b2.setBackground(Color.black);
        b2.setBorder(new LineBorder(Color.black));
        f1.add(b2);
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f1.setVisible(false);
                login();
            }
        });
        f1.setVisible(true);
    }
    public static void signup()
    {
        JFrame f2=new JFrame("signup");
        f2.setResizable(false);
        f2.getContentPane().setBackground(Color.white);
        f2.setSize(1000,1000);
        f2.setLayout(null);
        f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f2.setLocationRelativeTo(null);
        f2.setVisible(true);
        JLabel l1 = new JLabel("Registration form");
        l1.setFont(new Font("Serif", Font.ITALIC, 50));
        l1.setForeground(Color.pink);
        l1.setBounds(300, 20, 2000, 100);
        f2.add(l1);
        JLabel name=new JLabel("name");
        name.setFont(new Font("Arial", Font.PLAIN, 20));
        name.setSize(250, 20);
        name.setForeground(Color.pink);
        name.setLocation(230, 220);
        f2.add(name);
        JTextField nam = new JTextField();
        nam.setFont(new Font("Arial", Font.PLAIN, 15));
        nam.setSize(250, 20);
        nam.setLocation(370, 220);
        f2.add(nam);
        JLabel Lastname=new JLabel(" last name");
        Lastname.setFont(new Font("Arial", Font.PLAIN, 20));
        Lastname.setSize(250, 20);
        Lastname.setForeground(Color.pink);
        Lastname.setLocation(225, 270);
        f2.add(Lastname);
        JTextField lnam = new JTextField();
        lnam.setFont(new Font("Arial", Font.PLAIN, 15));
        lnam.setSize(250, 20);
        lnam.setLocation(370, 270);
        f2.add(lnam);
        JLabel nationalcode=new JLabel("national code");
        nationalcode.setFont(new Font("Arial", Font.PLAIN, 20));
        nationalcode.setSize(250, 20);
        nationalcode.setForeground(Color.pink);
        nationalcode.setLocation(230, 320);
        f2.add(nationalcode);
        JTextField code = new JTextField();
        code.setFont(new Font("Arial", Font.PLAIN, 15));
        code.setSize(250, 20);
        code.setLocation(370, 320);
        f2.add(code);
        JLabel email = new JLabel("email");
        email.setFont(new Font("Arial", Font.PLAIN, 20));
        email.setSize(250, 20);
        email.setForeground(Color.pink);
        email.setLocation(230, 370);
        f2.add(email);
        JTextField em = new JTextField();
        em.setFont(new Font("Arial", Font.PLAIN, 15));
        em.setSize(250, 20);
        em.setLocation(370, 370);
        f2.add(em);
        JLabel pass = new JLabel("pass");
        pass.setFont(new Font("Arial", Font.PLAIN, 20));
        pass.setForeground(Color.pink);
        pass.setSize(100, 20);
        pass.setLocation(230, 420);
        f2.add(pass);
        JPasswordField p = new JPasswordField();
        p.setFont(new Font("Arial", Font.PLAIN, 20));
        p.setSize(250, 20);
        p.setLocation(370, 420);
        f2.add(p);
        JButton sub = new JButton("Submit");
        sub.setBorder(new LineBorder(Color.white));
        sub.setFont(new Font("Arial", Font.PLAIN, 15));
        sub.setSize(100, 20);
        sub.setLocation(440, 470);
        f2.add(sub);
        sub.addActionListener(e -> {
            if(hotel.signup(nam.getText(),lnam.getText(),code.getText(),em.getText(),p.getText())==true)
            {
                JOptionPane.showMessageDialog(f2,"good");
            }else{
                JOptionPane.showMessageDialog(f2,"try again");
            }
        });
        f2.setVisible(true);
    }

    public static void login()
    {
        Hotel h=new Hotel();
        JFrame f3=new JFrame("login");
        f3.setResizable(false);
        f3.getContentPane().setBackground(Color.white);
        f3.setSize(1000,1000);
        f3.setLayout(null);
        f3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f3.setLocationRelativeTo(null);
        JLabel email = new JLabel("email");
        email.setFont(new Font("Arial", Font.PLAIN, 20));
        email.setSize(250, 20);
        email.setForeground(Color.pink);
        email.setLocation(300, 370);
        f3.add(email);
        JTextField em = new JTextField();
        em.setFont(new Font("Arial", Font.PLAIN, 15));
        em.setSize(300, 20);
        em.setLocation(370, 370);
        f3.add(em);
        JLabel pass = new JLabel("pass");
        pass.setFont(new Font("Arial", Font.PLAIN, 20));
        pass.setForeground(Color.pink);
        pass.setSize(100, 20);
        pass.setLocation(300, 420);
        f3.add(pass);
        JPasswordField p = new JPasswordField();
        p.setFont(new Font("Arial", Font.PLAIN, 20));
        p.setSize(300, 20);
        p.setLocation(370, 420);
        f3.add(p);
        JRadioButton manager = new JRadioButton("manager");
        manager.setFont(new Font("Arial", Font.PLAIN, 15));
        manager.setSelected(true);
        manager.setSize(100, 20);
        manager.setLocation(365, 470);
        f3.add(manager);
        JRadioButton passenger = new JRadioButton("passenger");
        passenger.setFont(new Font("Arial", Font.PLAIN, 15));
        passenger.setSelected(false);
        passenger.setSize(100, 20);
        passenger.setLocation(465, 470);
        f3.add(passenger);
        JRadioButton employee = new JRadioButton("employee");
        employee.setFont(new Font("Arial", Font.PLAIN, 15));
        employee.setSelected(true);
        employee.setSize(100, 20);
        employee.setLocation(565, 470);
        f3.add(employee);
        ButtonGroup hotel = new ButtonGroup();
        hotel.add(manager);
        hotel.add(passenger);
        hotel.add(employee);
        JButton sub = new JButton("Submit");
        sub.setBorder(new LineBorder(Color.white));
        sub.setFont(new Font("Arial", Font.PLAIN, 15));
        sub.setSize(100, 20);
        sub.setLocation(460, 520);
        f3.add(sub);
        sub.addActionListener(e -> {
            if(h.log(em.getText(),p.getText())==true)
            {
                admintable();
                f3.setVisible(false);
            }else{
                JOptionPane.showMessageDialog(f3,"bad");
            }
        });
        f3.setVisible(true);
    }
    public static void admintable()
    {
        JFrame f4=new JFrame("adminPage");
        f4.setResizable(false);
        f4.getContentPane().setBackground(Color.white);
        f4.setSize(1000,1000);
        f4.setLayout(null);
        f4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f4.setLocationRelativeTo(null);
        f4.setVisible(true);
        JLabel l1 = new JLabel("ADMIN");
        l1.setFont(new Font("Serif", Font.ITALIC, 50));
        l1.setForeground(Color.pink);
        l1.setBounds(300, 20, 2000, 100);
        f4.add(l1);
        JButton b1=new JButton("log in");
        b1.setFont(new Font("Serif", Font.BOLD, 40));
        b1.setBounds(450,450,150,50);
        b1.setBackground(Color.black);
        b1.setBorder(new LineBorder(Color.black));
        f4.add(b1);
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                employee_management();
                f4.setVisible(false);
            }
        });
    }
    public static void employee_management()
    {
        Hotel h=new Hotel();
        JFrame f5=new JFrame("EMPLOYEE MANAGEMENT");
        f5.setResizable(false);
        f5.getContentPane().setBackground(Color.white);
        f5.setSize(1000,1000);
        f5.setLayout(null);
        f5.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f5.setLocationRelativeTo(null);
        JPanel panel=new JPanel();
        panel.setBorder(BorderFactory.createLineBorder(Color.pink,5));
        f5.add(panel);
        JLabel l1 = new JLabel("employee id");
        l1.setFont(new Font("Serif", Font.BOLD, 17));
        l1.setForeground(Color.pink);
        l1.setBounds(20, 20, 2000, 100);
        f5.add(l1);
        JLabel l2 = new JLabel("name");
        l2.setFont(new Font("Serif", Font.BOLD, 17));
        l2.setForeground(Color.pink);
        l2.setBounds(20, 55, 2000, 100);
        f5.add(l2);
        JLabel l3 = new JLabel("last name");
        l3.setFont(new Font("Serif", Font.BOLD, 17));
        l3.setForeground(Color.pink);
        l3.setBounds(10, 105, 2000, 100);
        f5.add(l3);
        JLabel l4 = new JLabel("national code");
        l4.setFont(new Font("Serif", Font.BOLD, 17));
        l4.setForeground(Color.pink);
        l4.setBounds(10, 155, 2000, 100);
        f5.add(l4);
        JLabel l5 = new JLabel("personal code");
        l5.setFont(new Font("Serif", Font.BOLD, 17));
        l5.setForeground(Color.pink);
        l5.setBounds(10, 205, 2000, 100);
        f5.add(l5);
        JLabel l6 = new JLabel("email");
        l6.setFont(new Font("Serif", Font.BOLD, 17));
        l6.setForeground(Color.pink);
        l6.setBounds(10, 255, 2000, 100);
        f5.add(l6);
        JLabel l7 = new JLabel("pass");
        l7.setFont(new Font("Serif", Font.BOLD, 17));
        l7.setForeground(Color.pink);
        l7.setBounds(10, 305, 2000, 100);
        f5.add(l7);
        JLabel l8 = new JLabel("payment");
        l8.setFont(new Font("Serif", Font.BOLD, 17));
        l8.setForeground(Color.pink);
        l8.setBounds(10, 355, 2000, 100);
        f5.add(l8);
        JTextField id = new JTextField();
        id.setFont(new Font("Arial", Font.BOLD, 15));
        id.setSize(300, 20);
        id.setLocation(150, 65);
        f5.add(id);
        JTextField nam = new JTextField();
        nam.setFont(new Font("Arial", Font.BOLD, 15));
        nam.setSize(300, 20);
        nam.setLocation(150, 100);
        f5.add(nam);
        JTextField lnam = new JTextField();
        lnam.setFont(new Font("Arial", Font.PLAIN, 15));
        lnam.setSize(300, 20);
        lnam.setLocation(150, 150);
        f5.add(lnam);
        JTextField code = new JTextField();
        code.setFont(new Font("Arial", Font.PLAIN, 15));
        code.setSize(300, 20);
        code.setLocation(150, 200);
        f5.add(code);
        JTextField ncode = new JTextField();
        ncode.setFont(new Font("Arial", Font.PLAIN, 15));
        ncode.setSize(300, 20);
        ncode.setLocation(150, 200);
        f5.add(code);
        JTextField pcode = new JTextField();
        pcode.setFont(new Font("Arial", Font.PLAIN, 15));
        pcode.setSize(300, 20);
        pcode.setLocation(150, 250);
        f5.add(pcode);
        JTextField em = new JTextField();
        em.setFont(new Font("Arial", Font.PLAIN, 15));
        em.setSize(300, 20);
        em.setLocation(150, 300);
        f5.add(em);
        JTextField pas = new JTextField();
        pas.setFont(new Font("Arial", Font.PLAIN, 15));
        pas.setSize(300, 20);
        pas.setLocation(150, 350);
        f5.add(pas);
        JTextField pay = new JTextField();
        pay.setFont(new Font("Arial", Font.PLAIN, 15));
        pay.setSize(300, 20);
        pay.setLocation(150, 400);
        f5.add(pay);
        f5.setVisible(true);
        JButton add = new JButton("add");
        add.setBorder(new LineBorder(Color.white));
        add.setFont(new Font("Arial", Font.PLAIN, 15));
        add.setSize(100, 20);
        add.setLocation(460, 520);
        f5.add(add);
        add.addActionListener(e -> {
            try {
                sqlcon=DriverManager.getConnection(username,password,dataconn);
                pst=sqlcon.prepareStatement("INSERT INTO hotel(idemployee,name,lastname,nationalcode,workcode,email,pass,salary)");
                pst.setString(1,id.getText());
                pst.setString(2,nam.getText());
                pst.setString(3,lnam.getText());
                pst.setString(4,ncode.getText());
                pst.setString(5,pcode.getText());
                pst.setString(6,em.getText());
                pst.setString(7,pas.getText());
                pst.setString(8,pay.getText());
                pst.executeUpdate();
                JOptionPane.showMessageDialog(f5,"new record add");
                updateDb();
        } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
    }
