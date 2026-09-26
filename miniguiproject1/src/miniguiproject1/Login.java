package miniguiproject1;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Login extends JFrame implements ActionListener{
	
	private JLabel title;
	private JLabel emailLabel;
	private JLabel passwordLabel;
	private JLabel roleLabel;
	
	private JTextField emailField;
	private JPasswordField passwordField;
	
	private JRadioButton customerRadio;
	private JRadioButton adminRadio;
	private ButtonGroup roleGroup;
	
	private JButton loginButton;
	private JButton signupButton;
	
	
	public Login()
	{
		title = new JLabel("LOGIN");
		
		emailLabel = new JLabel("Email");
		passwordLabel = new JLabel("password");
		roleLabel = new JLabel("Role");
		
		emailField = new JTextField();
		passwordField = new JPasswordField();
		
		customerRadio = new JRadioButton("Customer");
		adminRadio = new JRadioButton("Admin");
		
		roleGroup = new ButtonGroup();
		
		loginButton = new JButton("LOGIN");
		signupButton = new JButton("SIGN UP");
		
		setLayout(null);
		
		getContentPane().setBackground(new Color(245, 248, 252));
		
		title.setBounds(250, 50, 120, 40);
		
		title.setFont(new Font("Arial", Font.BOLD, 30));
		
		title.setForeground(new Color(20, 50, 90));
		
		title.setHorizontalAlignment(JLabel.CENTER);
		
		
		emailLabel.setBounds(150, 130, 100, 40);
		
		emailLabel.setFont(new Font("Arial", Font.BOLD, 16));
		
		emailLabel.setForeground(new Color(20, 50, 90));
		
		
		emailField.setBounds(250, 130, 200, 40);
		
		
		passwordLabel.setBounds(150, 200, 100, 40);
		
		passwordLabel.setFont(new Font("Arial", Font.BOLD, 16));
		
		passwordLabel.setForeground(new Color(20, 50, 90));
		
		
		passwordField.setBounds(250, 200, 200, 40);
		
        roleLabel.setBounds(150, 270, 100, 40);
		
		roleLabel.setFont(new Font("Arial", Font.BOLD, 16));
		
		roleLabel.setForeground(new Color(20, 50, 90));
		
		
		customerRadio.setBounds(250, 270, 100, 40);
		
		customerRadio.setBackground(new Color(245, 248, 252));
		
		customerRadio.setForeground(new Color(20, 50, 90));
		
		customerRadio.setFont(new Font("Arial", Font.BOLD, 14));
		
		
		adminRadio.setBounds(360, 270, 90, 40);
		
		adminRadio.setBackground(new Color(245, 248, 252));
		
		adminRadio.setForeground(new Color(20, 50, 90));
		
		adminRadio.setFont(new Font("Arial", Font.BOLD, 14));
		
		
		roleGroup.add(customerRadio);
		roleGroup.add(adminRadio);
		
		customerRadio.setSelected(true);
		
		
		loginButton.setBounds(250, 340, 120, 40);

		signupButton.setBounds(250, 410, 120, 40);
		
		
		add(title);
		
		add(emailLabel);
		add(emailField);
		
		add(passwordLabel);
		add(passwordField);
		
		add(roleLabel);
		add(customerRadio);
		add(adminRadio);
		
		add(loginButton);
		add(signupButton);
		
		
		designButton(loginButton);
		designButton(signupButton);
		
		
		loginButton.setBackground(new Color(30, 120, 220));
		
		signupButton.setBackground(new Color(55, 75, 100));
		
		
		addHoverEffect(loginButton,
				new Color(30, 120, 220),
				new Color(15, 85, 170));
		
		addHoverEffect(signupButton,
				new Color(55, 75, 100),
				new Color(35, 55, 80));
		
		
		loginButton.addActionListener(this);
		signupButton.addActionListener(this);
		
		
		setTitle("Car Rental Management System");
		setSize(600, 600);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void designButton(JButton button)
	{
		button.setFont(new Font("Arial", Font.BOLD, 14));
		
		button.setForeground(Color.WHITE);
		
		button.setFocusPainted(false);
		
		button.setBorderPainted(false);
	}
	
	private void addHoverEffect(JButton button, Color normalColor, Color hoverColor)
	{
		button.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseEntered(MouseEvent e)
			{
				button.setBackground(hoverColor);
			}
			
			@Override
			public void mouseExited(MouseEvent e)
			{
				button.setBackground(normalColor);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == loginButton)
		{
			String Username = emailField.getText();
			String password = new String(passwordField.getPassword());
			
			if(Username.isEmpty() || password.isEmpty())
			{
			    JOptionPane.showMessageDialog(this, "Please enter Email and Password");
			    return;
			}
			
			try
			{
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection c = DriverManager.getConnection(DBConfig.URL, DBConfig.USER, DBConfig.PASSWORD);
			
			PreparedStatement p = c.prepareStatement("select * from users where email = ? and password = ?");
			
			p.setString(1, Username);
			p.setString(2, password);
			
			ResultSet r = p.executeQuery();
			
			if(r.next())
			{
			    String role = r.getString("role");

			    JOptionPane.showMessageDialog(this, "Login Successful");

			    if("ADMIN".equalsIgnoreCase(role))
			    {
			        new AdminDashboard();
			    }
			    else if("CUSTOMER".equalsIgnoreCase(role)) 
			    {
			        DBconnection db = new DBconnection();

			        try 
			        {
			            db.connect();

			            customer cust = db.findCustomerByEmail(Username);

			            if(cust != null) 
			            {
			                new CustomerDashboard(cust.getCid());
			            } 
			            else 
			            {
			                JOptionPane.showMessageDialog(this,"Customer profile not found");
			                return;
			            }

			        } 
			        catch(SQLException ex) 
			        {
			            ex.printStackTrace();

			            JOptionPane.showMessageDialog(this,"Database error");
			            return;
			        }
			    }
			    else
			    {
			        JOptionPane.showMessageDialog(this, "Role not assigned to this user");
			        return;
			    }

			    this.dispose();

			    System.out.println("LOGIN TRUE");
			    System.out.println("Role: " + role);
			}
			else
			{
				 JOptionPane.showMessageDialog(this, "Invalid Email or Password");
				 System.out.println("LOGIN FALSE");
			}
			
			r.close();
			p.close();
			c.close();
		}
			catch(Exception ee)
			{
			    ee.printStackTrace();
			    JOptionPane.showMessageDialog(this, "Database error:\n" + ee.toString());
			}
			
			System.out.println("Username: " + Username);
		}
		else if(e.getSource() == signupButton)
		{
			Signup s = new Signup();
			s.setVisible(true);
			this.dispose();
		}
	}
	
	public static void main(String[]args)
	{
		new Login();
	}

}
