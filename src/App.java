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
    public static void main(String[] args){
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
            return true;
        }catch (SQLException e){
            return false;
        }
    }
    public static void start()
    {
        JFrame f1=new JFrame("menu");
        ImageIcon icon = new ImageIcon("C:\\Users\\saraye tel\\Desktop\\hotel_managementservice\\Hotel_management\\pic\\full4.jpg");
        JLabel l0=new JLabel(icon);
        l0.setBounds(90,0,820,794);
        f1.add(l0);
        JButton button=new JButton(new ImageIcon("C:\\Users\\saraye tel\\Desktop\\hotel_managementservice\\Hotel_management\\pic\\train.png"));
        button.setBounds(95,530,100,120);
        l0.add(button);
        JButton button2=new JButton(new ImageIcon("C:\\Users\\saraye tel\\Desktop\\hotel_managementservice\\Hotel_management\\pic\\food.png"));
        button2.setBounds(370,540,98,110);
        l0.add(button2);
        f1.setResizable(false);
        f1.getContentPane().setBackground(new Color(255, 255 ,207));
        f1.setSize(1012,1000);
        f1.setLayout(null);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setLocationRelativeTo(null);
        JButton b1=new JButton("sign up");
        b1.setFocusPainted(false);
        b1.setFont(new Font("Serif", Font.BOLD, 40));
        b1.setBounds(0,180,200,50);
        b1.setBackground(new Color(255, 255 ,207));
        b1.setBorder(new LineBorder(new Color(255, 255 ,207)));
        l0.add(b1);
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f1.setVisible(false);
                signup();
            }
        });
        JButton b2=new JButton("log in");
        b2.setFont(new Font("Serif", Font.BOLD, 40));
        b2.setBounds(0,270,200,50);
        b2.setBackground(new Color(255, 255 ,207));
        b2.setBorder(new LineBorder(new Color(255, 255 ,207)));
        l0.add(b2);
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
        JLabel bankBalance = new JLabel("Bank");
        bankBalance.setFont(new Font("Arial", Font.PLAIN, 20));
        bankBalance.setForeground(Color.pink);
        bankBalance.setSize(100, 20);
        bankBalance.setLocation(230, 470);
        f2.add(bankBalance);
        JTextField b = new JTextField();
        b.setFont(new Font("Arial", Font.PLAIN, 20));
        b.setSize(250, 20);
        b.setLocation(370, 470);
        f2.add(b);
        JButton sub = new JButton("Submit");
        sub.setBorder(new LineBorder(Color.white));
        sub.setFont(new Font("Arial", Font.PLAIN, 15));
        sub.setSize(100, 20);
        sub.setLocation(440, 520);
        f2.add(sub);
        sub.addActionListener(e -> {
            if(hotel.signup(nam.getText(),lnam.getText(),code.getText(),em.getText(),p.getText())==true)
            {
                try {
                    if(hotel.similar(code.getText(),email.getText())==false){
                        JOptionPane.showMessageDialog(f2,"you have already registered");
                    }else{
                        double bb=Double.parseDouble(b.getText());
                        Passenger passenger=new Passenger(code.getText(),nam.getText(),lnam.getText(),pass.getText(), em.getText(),bb);
                        Hotel.passengers.add(passenger);
                        Hotel.passengerWrite(passenger);
                        userpagee(passenger);
                        f2.setVisible(false);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
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
            if(!em.getText().equals("") && !p.getText().equals(""))
            {
                String ep=em.getText();
                String pp=p.getText();
                if(manager.isSelected())
                {
                    admintable();
                    f3.setVisible(false);

                }else if(employee.isSelected())
                {
                    try {
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/hotel","root","zahra1382");
                        try {
                            Statement statement=connection.createStatement();
                            String emailEmployee="SELECT * FROM employee WHERE emaill='tara1234@gmail.com'";
                            ResultSet resultSet=statement.executeQuery(emailEmployee);
                            while (resultSet.next())
                            {
                                String emaill= String.valueOf(resultSet.getInt("emaill"));
                                if(ep.equals(emaill)){
                                    JOptionPane.showMessageDialog(f3,"welcome");
                                }
                            }
                            resultSet.close();
                            statement.close();
                            connection.close();
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }


                }else if(passenger.isSelected())
                {

                }
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
    public static void employee_management() {
        Hotel h = new Hotel();
        JFrame f5 = new JFrame("EMPLOYEE MANAGEMENT");
        f5.setResizable(false);
        f5.getContentPane().setBackground(Color.white);
        f5.setSize(1000, 1000);
        f5.setLayout(null);
        f5.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f5.setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createLineBorder(Color.pink, 5));
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
    }
    public static void userpagee(Passenger passenger)
    {
        JFrame f6 = new JFrame("User ");
        f6.setResizable(false);
        f6.getContentPane().setBackground(Color.white);
        f6.setSize(1000, 1000);
        f6.setLayout(null);
        f6.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f6.setLocationRelativeTo(null);
        f6.setVisible(true);
        JLabel l1=new JLabel("welcome dear sina ");
        l1.setFont(new Font("Serif", Font.ITALIC, 50));
        l1.setForeground(Color.pink);
        f6.add(l1);
        l1.setBounds(300, 20, 2000, 100);
        JLabel l2=new JLabel("what can i do for you?");
        l2.setFont(new Font("Serif", Font.ITALIC, 30));
        l2.setForeground(Color.pink);
        l2.setBounds(350, 100, 2000, 100);
        f6.add(l2);
        JButton b1=new JButton("edit profile");
        b1.setFocusPainted(false);
        b1.setBorder(new LineBorder(Color.white));
        b1.setFont(new Font("Arial", Font.PLAIN, 15));
        b1.setSize(300, 50);
        b1.setLocation(350, 200);
        f6.add(b1);
        b1.addActionListener(e -> {

        });
        JButton b2=new JButton("reserve");
        b2.setFocusPainted(false);
        b2.setBorder(new LineBorder(Color.white));
        b2.setFont(new Font("Arial", Font.PLAIN, 15));
        b2.setSize(300, 50);
        b2.setLocation(350, 250);
        f6.add(b2);
        b1.addActionListener(e -> {

        });
        JButton b3=new JButton("tourist attraction");
        b3.setFocusPainted(false);
        b3.setBorder(new LineBorder(Color.white));
        b3.setFont(new Font("Arial", Font.PLAIN, 15));
        b3.setSize(300, 50);
        b3.setLocation(350, 300);
        f6.add(b3);
        b1.addActionListener(e -> {

        });
    }
    }
