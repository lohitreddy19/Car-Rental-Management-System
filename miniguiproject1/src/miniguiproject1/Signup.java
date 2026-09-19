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

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Signup extends JFrame implements ActionListener {

	private JLabel title;
    private JLabel name;
    private JLabel email;
    private JLabel mobile;
    private JLabel gender;
    private JLabel password;
    private JLabel conformpassword;

    private JTextField nameField;
    private JTextField emailField;
    private JTextField mobileField;

    private JRadioButton[] genderButton;

    private JTextField passwordField;
    private JTextField conformpasswordField;

    private JButton SignupButton;

    public Signup() {

        title = new JLabel("SIGNUP");

        name = new JLabel("Name");
        email = new JLabel("Email");
        mobile = new JLabel("Mobile");
        gender = new JLabel("Gender");

        genderButton = new JRadioButton[3];

        genderButton[0] = new JRadioButton("Male");
        genderButton[1] = new JRadioButton("Female");
        genderButton[2] = new JRadioButton("Other");

        ButtonGroup genderGroup = new ButtonGroup();

        genderGroup.add(genderButton[0]);
        genderGroup.add(genderButton[1]);
        genderGroup.add(genderButton[2]);

        password = new JLabel("Password");
        conformpassword = new JLabel("Confirm Password");

        nameField = new JTextField();
        emailField = new JTextField();
        mobileField = new JTextField();

        passwordField = new JTextField();
        conformpasswordField = new JTextField();

        SignupButton = new JButton("Signup");

        setLayout(null);

        getContentPane().setBackground(new Color(245, 248, 252));

        title.setBounds(250, 30, 120, 40);

        title.setFont(new Font("Arial", Font.BOLD, 30));

        title.setForeground(new Color(20, 50, 90));

        title.setHorizontalAlignment(JLabel.CENTER);


        name.setBounds(100, 80, 100, 30);

        nameField.setBounds(220, 80, 200, 30);


        email.setBounds(100, 130, 100, 30);

        emailField.setBounds(220, 130, 200, 30);


        mobile.setBounds(100, 180, 100, 30);

        mobileField.setBounds(220, 180, 200, 30);


        gender.setBounds(100, 230, 100, 30);

        genderButton[0].setBounds(220, 230, 70, 30);
        genderButton[1].setBounds(290, 230, 80, 30);
        genderButton[2].setBounds(370, 230, 80, 30);


        password.setBounds(100, 280, 120, 30);

        passwordField.setBounds(220, 280, 200, 30);


        conformpassword.setBounds(100, 330, 120, 30);

        conformpasswordField.setBounds(220, 330, 200, 30);


        SignupButton.setBounds(220, 380, 120, 40);


        add(title);

        add(name);
        add(nameField);

        add(email);
        add(emailField);

        add(mobile);
        add(mobileField);

        add(gender);

        add(genderButton[0]);
        add(genderButton[1]);
        add(genderButton[2]);

        add(password);
        add(passwordField);

        add(conformpassword);
        add(conformpasswordField);

        add(SignupButton);

        designLabel(name);
        designLabel(email);
        designLabel(mobile);
        designLabel(gender);
        designLabel(password);
        designLabel(conformpassword);

        designRadioButton(genderButton[0]);
        designRadioButton(genderButton[1]);
        designRadioButton(genderButton[2]);

        designButton(SignupButton);

        SignupButton.setBackground(new Color(30, 120, 220));

        addHoverEffect(SignupButton,
                new Color(30, 120, 220),
                new Color(15, 85, 170));


        SignupButton.addActionListener(this);

        setTitle("Car Rental Management System");
        setSize(600, 500);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);
        setVisible(true);
	}
    
    private void designLabel(JLabel label)
    {
        label.setFont(new Font("Arial", Font.BOLD, 14));

        label.setForeground(new Color(20, 50, 90));
    }
    
    private void designRadioButton(JRadioButton button)
    {
        button.setFont(new Font("Arial", Font.PLAIN, 13));

        button.setForeground(new Color(20, 50, 90));

        button.setBackground(new Color(245, 248, 252));
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
		
		 if (e.getSource() == SignupButton) {

	            String nameValue = nameField.getText().trim();
	            String emailValue = emailField.getText().trim();
	            String mobileValue = mobileField.getText().trim();

	            String genderValue = "";

	            if (genderButton[0].isSelected()) {
	                genderValue = "Male";
	            }
	            else if (genderButton[1].isSelected()) {
	                genderValue = "Female";
	            }
	            else if (genderButton[2].isSelected()) {
	                genderValue = "Other";
	            }

	            String passwordValue = passwordField.getText();
	            String confirmPasswordValue = conformpasswordField.getText();
			
	            if (nameValue.isEmpty() || emailValue.isEmpty() ||mobileValue.isEmpty() || passwordValue.isEmpty() || confirmPasswordValue.isEmpty()) 
	            {
	                    JOptionPane.showMessageDialog(this,"Please fill all fields");
	                    return;
	                }

	                if (genderValue.isEmpty()) 
	                {
	                    JOptionPane.showMessageDialog(this,"Please select gender");
	                    return;
	                }

	                if (!passwordValue.equals(confirmPasswordValue)) 
	                {
	                    JOptionPane.showMessageDialog(this,"Password does not match");
	                    return;
	                }

	                try 
	                {
	                    Class.forName("com.mysql.cj.jdbc.Driver");

	                    Connection c = DriverManager.getConnection(DBConfig.URL, DBConfig.USER, DBConfig.PASSWORD);

	                    PreparedStatement check = c.prepareStatement("SELECT * FROM users WHERE email = ?");

	                    check.setString(1, emailValue);

	                    ResultSet rs = check.executeQuery();

	                    if (rs.next()) 
	                    {
	                        JOptionPane.showMessageDialog(this,"Email already registered");

	                        rs.close();
	                        check.close();
	                        c.close();

	                        return;
	                    }

	                    rs.close();
	                    check.close();

	                    PreparedStatement uidStatement = c.prepareStatement(
	                    	    "SELECT uid FROM users " +
	                    	    "WHERE uid LIKE 'u%' " +
	                    	    "ORDER BY CAST(SUBSTRING(uid, 2) AS UNSIGNED) DESC " +
	                    	    "LIMIT 1");

	                    	ResultSet uidResult = uidStatement.executeQuery();

	                    	String uid;

	                    	if (uidResult.next()) 
	                    	{
	                    	    String lastUid = uidResult.getString("uid");
	                    	    int number = Integer.parseInt(lastUid.substring(1));
	                    	    number++;

	                    	    uid = "u" + number;

	                    	} 
	                    	else 
	                    	{
	                    	    uid = "u101";
	                    	}

	                    	uidResult.close();
	                    	uidStatement.close();

	                    PreparedStatement customerCountStatement =c.prepareStatement("SELECT COUNT(*) FROM customer");

	                    ResultSet customerCountResult = customerCountStatement.executeQuery();

	                    customerCountResult.next();

	                    int customerCount =customerCountResult.getInt(1);

	                    String cid = "c" + (customerCount + 101);

	                    customerCountResult.close();
	                    customerCountStatement.close();

	                    PreparedStatement userStatement =c.prepareStatement("INSERT INTO users (uid, email, password, role) " +"VALUES (?, ?, ?, ?)");

	                    userStatement.setString(1, uid);
	                    userStatement.setString(2, emailValue);
	                    userStatement.setString(3, passwordValue);
	                    userStatement.setString(4, "CUSTOMER");

	                    userStatement.executeUpdate();

	                    userStatement.close();

	                    PreparedStatement customerStatement =c.prepareStatement(
	                            "INSERT INTO customer " +
	                            "(cid, cname, mobile, email) " +
	                            "VALUES (?, ?, ?, ?)");

	                    customerStatement.setString(1, cid);
	                    customerStatement.setString(2, nameValue);
	                    customerStatement.setString(3, mobileValue);
	                    customerStatement.setString(4, emailValue);

	                    customerStatement.executeUpdate();

	                    customerStatement.close();

	                    JOptionPane.showMessageDialog(this,
	                        "Account created successfully\n" +
	                        "Customer ID: " + cid);

	                    c.close();

	                    new Login();
	                    this.dispose();

	                }
	                catch (Exception ee) 
	                {
	                    ee.printStackTrace();

	                    JOptionPane.showMessageDialog(this,"Database Error: " + ee.getMessage());
	                }
	            }
	        }
}
