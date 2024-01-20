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
    public static void main(String[] args) throws SQLException {
        if(connection()==true)
        {
            hotel.managerRead();
            hotel.passengerRead();
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
                        Passenger passenger=new Passenger(code.getText(),nam.getText(),lnam.getText(),p.getText(), em.getText(),bb);
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
                if(manager.isSelected())
                {
                    if (h.managerLogin(em.getText(),p.getText())==true)
                    {
                    }else{
                        JOptionPane.showMessageDialog(f3,"check your email and password again!");
                    }
                }
                else if(employee.isSelected())
                {
                }
                else if(passenger.isSelected())
                {
                   if(h.passengerLogin(em.getText(),p.getText())==true){

                   }else{
                       JOptionPane.showMessageDialog(f3,"check your email and password again!");
                   }
                }
            }else{
                JOptionPane.showMessageDialog(f3,"you have to fill the box completely");
            }
        });
        f3.setVisible(true);
    }
    public static void managerpage(Manager manager)
    {
        JFrame f4=new JFrame("manager +"+manager.getName());
        f4.setResizable(false);
        f4.getContentPane().setBackground(Color.white);
        f4.setSize(1000,1000);
        f4.setLayout(null);
        f4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f4.setLocationRelativeTo(null);
        JLabel l1=new JLabel("welcome dear "+manager.getName());
        l1.setFont(new Font("Serif", Font.ITALIC, 50));
        l1.setForeground(Color.pink);
        f4.add(l1);
        l1.setBounds(300, 20, 2000, 100);
        JLabel l2=new JLabel("what can i do for you?");
        l2.setFont(new Font("Serif", Font.ITALIC, 30));
        l2.setForeground(Color.pink);
        l2.setBounds(350, 100, 2000, 100);
        f4.add(l2);
        JButton b1=new JButton("edit profile");
        b1.setFocusPainted(false);
        b1.setBorder(new LineBorder(Color.white));
        b1.setFont(new Font("Arial", Font.PLAIN, 15));
        b1.setSize(300, 50);
        b1.setLocation(400, 200);
        f4.add(b1);
        b1.addActionListener(e -> {
            f4.setVisible(false);
            JFrame f5 = new JFrame("edit profile");
            f5.setResizable(false);
            f5.getContentPane().setBackground(Color.white);
            f5.setSize(1000, 1000);
            f5.setLayout(null);
            f5.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f5.setLocationRelativeTo(null);
            JLabel lable1=new JLabel("edit profile");
            lable1.setFont(new Font("Arial", Font.PLAIN, 50));
            lable1.setSize(250, 50);
            lable1.setForeground(Color.pink);
            lable1.setLocation(350, 50);
            f5.add(lable1);
            JLabel lable2=new JLabel("fill the form completely with change");
            lable2.setFont(new Font("Arial", Font.PLAIN, 20));
            lable2.setSize(400, 100);
            lable2.setForeground(Color.pink);
            lable2.setLocation(315, 70);
            f5.add(lable2);
            JLabel name=new JLabel("name: "+manager.getName());
            name.setFont(new Font("Arial", Font.PLAIN, 20));
            name.setSize(250, 20);
            name.setForeground(Color.pink);
            name.setLocation(230, 220);
            f5.add(name);
            JTextField nam = new JTextField();
            nam.setFont(new Font("Arial", Font.PLAIN, 10));
            nam.setSize(250, 20);
            nam.setLocation(480, 220);
            f5.add(nam);
            JLabel Lastname=new JLabel(" last name: "+manager.getLast_name());
            Lastname.setFont(new Font("Arial", Font.PLAIN, 20));
            Lastname.setSize(250, 20);
            Lastname.setForeground(Color.pink);
            Lastname.setLocation(225, 270);
            f5.add(Lastname);
            JTextField lnam = new JTextField();
            lnam.setFont(new Font("Arial", Font.PLAIN, 10));
            lnam.setSize(250, 20);
            lnam.setLocation(480, 270);
            f5.add(lnam);
            JLabel email=new JLabel("email: "+manager.getEmail());
            email.setFont(new Font("Arial", Font.PLAIN, 20));
            email.setSize(250, 30);
            email.setForeground(Color.pink);
            email.setLocation(230, 315);
            f5.add(email);
            JTextField em = new JTextField();
            em.setFont(new Font("Arial", Font.PLAIN, 10));
            em.setSize(250, 20);
            em.setLocation(480, 320);
            f5.add(em);
            JLabel pass = new JLabel("pass: "+manager.getPass());
            pass.setFont(new Font("Arial", Font.PLAIN, 20));
            pass.setSize(250, 20);
            pass.setForeground(Color.pink);
            pass.setLocation(230, 370);
            f5.add(pass);
            JTextField p = new JTextField();
            p.setFont(new Font("Arial", Font.PLAIN, 10));
            p.setSize(250, 20);
            p.setLocation(480, 370);
            f5.add(p);
            JButton sub = new JButton("Submit");
            sub.setBorder(new LineBorder(Color.white));
            sub.setFont(new Font("Arial", Font.PLAIN, 15));
            sub.setSize(100, 20);
            sub.setLocation(440, 420);
            f5.add(sub);
            sub.addActionListener(E ->{
                try {
                    if(hotel.signup(nam.getText(),lnam.getText(),manager.getNational_code(),em.getText(),p.getText())==true){
                        hotel.mangerproedit(nam.getText(),lnam.getText(), em.getText(),p.getText(),manager.getNational_code());
                        JOptionPane.showMessageDialog(f5,"your information successfully changed!");
                    }else{
                        JOptionPane.showMessageDialog(f5,"try again!");
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            });
            f5.setVisible(true);
        });
        JButton b2=new JButton("reserves");
        b2.setFocusPainted(false);
        b2.setBorder(new LineBorder(Color.white));
        b2.setFont(new Font("Arial", Font.PLAIN, 15));
        b2.setSize(400, 50);
        b2.setLocation(350, 250);
        f4.add(b2);
        b2.addActionListener(e -> {
            f4.setVisible(false);
        });
        JButton b3=new JButton("employee management");
        b3.setFocusPainted(false);
        b3.setBorder(new LineBorder(Color.white));
        b3.setFont(new Font("Arial", Font.PLAIN, 15));
        b3.setSize(400, 50);
        b3.setLocation(350, 300);
        f4.add(b3);
        b3.addActionListener(e -> {
            employee_management();
            f4.setVisible(false);
        });
        f4.setVisible(true);
    }
    public static void employee_management() {
        Hotel h = new Hotel();
        JFrame f6 = new JFrame("EMPLOYEE MANAGEMENT");
        f6.setResizable(false);
        f6.getContentPane().setBackground(Color.white);
        f6.setSize(1000, 1000);
        f6.setLayout(null);
        f6.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f6.setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("C:\\Users\\saraye tel\\Desktop\\hotel_managementservice\\Hotel_management\\pic\\back.jpg");
        JLabel l0=new JLabel(icon);
        l0.setBounds(0,0,1000,1000);
        f6.add(l0);
        JLabel l1 = new JLabel("employee id");
        l1.setFont(new Font("Serif", Font.BOLD, 20));
        l1.setForeground(Color.white);
        l1.setBounds(10, 110, 2000, 100);
        l0.add(l1);
        JLabel l2 = new JLabel("name");
        l2.setFont(new Font("Serif", Font.BOLD, 20));
        l2.setForeground(Color.white);
        l2.setBounds(10, 160, 2000, 100);
        l0.add(l2);
        JLabel l3 = new JLabel("last name");
        l3.setFont(new Font("Serif", Font.BOLD, 20));
        l3.setForeground(Color.white);
        l3.setBounds(10, 210, 2000, 100);
        l0.add(l3);
        JLabel l4 = new JLabel("national code");
        l4.setFont(new Font("Serif", Font.BOLD, 20));
        l4.setForeground(Color.white);
        l4.setBounds(10, 260, 2000, 100);
        l0.add(l4);
        JLabel l5 = new JLabel("personal code");
        l5.setFont(new Font("Serif", Font.BOLD, 20));
        l5.setForeground(Color.white);
        l5.setBounds(10, 310, 2000, 100);
        l0.add(l5);
        JLabel l6 = new JLabel("email");
        l6.setFont(new Font("Serif", Font.BOLD, 20));
        l6.setForeground(Color.white);
        l6.setBounds(10, 360, 2000, 100);
        l0.add(l6);
        JLabel l7 = new JLabel("pass");
        l7.setFont(new Font("Serif", Font.BOLD, 20));
        l7.setForeground(Color.white);
        l7.setBounds(10, 410, 2000, 100);
        l0.add(l7);
        JLabel l8 = new JLabel("payment");
        l8.setFont(new Font("Serif", Font.BOLD, 20));
        l8.setForeground(Color.white);
        l8.setBounds(10, 460, 2000, 100);
        l0.add(l8);
        JTextField id = new JTextField();
        id.setFont(new Font("Arial", Font.BOLD, 15));
        id.setSize(300, 20);
        id.setLocation(150, 150);
        l0.add(id);
        JTextField nam = new JTextField();
        nam.setFont(new Font("Arial", Font.BOLD, 15));
        nam.setSize(300, 20);
        nam.setLocation(150, 200);
        l0.add(nam);
        JTextField lnam = new JTextField();
        lnam.setFont(new Font("Arial", Font.PLAIN, 15));
        lnam.setSize(300, 20);
        lnam.setLocation(150, 250);
        l0.add(lnam);
        JTextField ncode = new JTextField();
        ncode.setFont(new Font("Arial", Font.PLAIN, 15));
        ncode.setSize(300, 20);
        ncode.setLocation(150, 300);
        l0.add(ncode);
        JTextField wcode = new JTextField();
        wcode.setFont(new Font("Arial", Font.PLAIN, 15));
        wcode.setSize(300, 20);
        wcode.setLocation(150, 350);
        l0.add(wcode);
        JTextField em = new JTextField();
        em.setFont(new Font("Arial", Font.PLAIN, 15));
        em.setSize(300, 20);
        em.setLocation(150, 400);
        l0.add(em);
        JTextField pas = new JTextField();
        pas.setFont(new Font("Arial", Font.PLAIN, 15));
        pas.setSize(300, 20);
        pas.setLocation(150, 450);
        l0.add(pas);
        JTextField pay = new JTextField();
        pay.setFont(new Font("Arial", Font.PLAIN, 15));
        pay.setSize(300, 20);
        pay.setLocation(150, 500);
        l0.add(pay);
        JButton add = new JButton("add");
        add.setBorder(new LineBorder(Color.white));
        add.setFont(new Font("Arial", Font.PLAIN, 15));
        add.setSize(100, 20);
        add.setLocation(780, 200);
        l0.add(add);
        add.addActionListener(e -> {
            f6.setVisible(false);
        });
        f6.setVisible(true);
    }

    public static void userpagee(Passenger passenger)
    {
        JFrame f6 = new JFrame("User "+passenger.getName());
        f6.setResizable(false);
        f6.getContentPane().setBackground(Color.white);
        f6.setSize(1000, 1000);
        f6.setLayout(null);
        f6.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f6.setLocationRelativeTo(null);
        f6.setVisible(true);
        JLabel l1=new JLabel("welcome dear "+passenger.getName());
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
            f6.setVisible(false);
            int id=passenger.getPerson_id();
            JFrame f7 = new JFrame("edit profile");
            f7.setResizable(false);
            f7.getContentPane().setBackground(Color.white);
            f7.setSize(1000, 1000);
            f7.setLayout(null);
            f7.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f7.setLocationRelativeTo(null);
            JLabel lable1=new JLabel("edit profile");
            lable1.setFont(new Font("Arial", Font.PLAIN, 50));
            lable1.setSize(250, 50);
            lable1.setForeground(Color.pink);
            lable1.setLocation(350, 50);
            f7.add(lable1);
            JLabel lable2=new JLabel("fill the form completely with change");
            lable2.setFont(new Font("Arial", Font.PLAIN, 20));
            lable2.setSize(400, 100);
            lable2.setForeground(Color.pink);
            lable2.setLocation(315, 70);
            f7.add(lable2);
            JLabel name=new JLabel("name: "+passenger.getName());
            name.setFont(new Font("Arial", Font.PLAIN, 20));
            name.setSize(250, 20);
            name.setForeground(Color.pink);
            name.setLocation(230, 220);
            f7.add(name);
            JTextField nam = new JTextField();
            nam.setFont(new Font("Arial", Font.PLAIN, 10));
            nam.setSize(250, 20);
            nam.setLocation(480, 220);
            f7.add(nam);
            JLabel Lastname=new JLabel(" last name: "+passenger.getLast_name());
            Lastname.setFont(new Font("Arial", Font.PLAIN, 20));
            Lastname.setSize(250, 20);
            Lastname.setForeground(Color.pink);
            Lastname.setLocation(225, 270);
            f7.add(Lastname);
            JTextField lnam = new JTextField();
            lnam.setFont(new Font("Arial", Font.PLAIN, 10));
            lnam.setSize(250, 20);
            lnam.setLocation(480, 270);
            f7.add(lnam);
            JLabel email=new JLabel("email: "+passenger.getEmail());
            email.setFont(new Font("Arial", Font.PLAIN, 20));
            email.setSize(250, 30);
            email.setForeground(Color.pink);
            email.setLocation(230, 315);
            f7.add(email);
            JTextField em = new JTextField();
            em.setFont(new Font("Arial", Font.PLAIN, 10));
            em.setSize(250, 20);
            em.setLocation(480, 320);
            f7.add(em);
            JLabel pass = new JLabel("pass: "+passenger.getPass());
            pass.setFont(new Font("Arial", Font.PLAIN, 20));
            pass.setSize(250, 20);
            pass.setForeground(Color.pink);
            pass.setLocation(230, 370);
            f7.add(pass);
            JTextField p = new JTextField();
            p.setFont(new Font("Arial", Font.PLAIN, 10));
            p.setSize(250, 20);
            p.setLocation(480, 370);
            f7.add(p);
            JLabel bankBalance = new JLabel("Bank: "+passenger.getBankBalance());
            bankBalance.setFont(new Font("Arial", Font.PLAIN, 20));
            bankBalance.setForeground(Color.pink);
            bankBalance.setSize(250, 20);
            bankBalance.setLocation(230, 420);
            f7.add(bankBalance);
            JTextField b = new JTextField();
            b.setFont(new Font("Arial", Font.PLAIN, 10));
            b.setSize(250, 20);
            b.setLocation(480, 420);
            f7.add(b);
            JButton sub = new JButton("Submit");
            sub.setBorder(new LineBorder(Color.white));
            sub.setFont(new Font("Arial", Font.PLAIN, 15));
            sub.setSize(100, 20);
            sub.setLocation(440, 470);
            f7.add(sub);
            sub.addActionListener(E ->{
                double bb=Double.parseDouble(b.getText());
                try {
                    if(hotel.signup(nam.getText(),lnam.getText(),passenger.getNational_code(),em.getText(),p.getText())==true){
                        hotel.userproedit(nam.getText(),lnam.getText(), em.getText(),p.getText(),passenger.getNational_code(),bb);
                        JOptionPane.showMessageDialog(f7,"your information successfully changed!");
                    }else{
                        JOptionPane.showMessageDialog(f7,"try again!");
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            });
            f7.setVisible(true);
        });
        JButton b2=new JButton("reserve");
        b2.setFocusPainted(false);
        b2.setBorder(new LineBorder(Color.white));
        b2.setFont(new Font("Arial", Font.PLAIN, 15));
        b2.setSize(300, 50);
        b2.setLocation(350, 250);
        f6.add(b2);
        b1.addActionListener(e -> {
        passengerReserve();
        f6.setVisible(false);
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
        f6.setVisible(true);
    }
    public static void passengerReserve()
    {
        JFrame f9 = new JFrame("reserve");
        f9.setResizable(false);
        f9.getContentPane().setBackground(Color.white);
        f9.setSize(1000, 1000);
        f9.setLayout(null);
        f9.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f9.setLocationRelativeTo(null);
        JLabel l1 = new JLabel("Registration form");
        l1.setFont(new Font("Serif", Font.ITALIC, 50));
        l1.setForeground(Color.pink);
        l1.setBounds(300, 20, 2000, 100);
        f9.add(l1);
        f9.setVisible(true);
    }
    }
