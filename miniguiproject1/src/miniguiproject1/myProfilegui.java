package miniguiproject1;

import java.awt.event.ActionEvent;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class myProfilegui extends JFrame implements ActionListener{

	 private JLabel customerIdLabel, nameLabel, mobileLabel, emailLabel;

	 private JTextField customerIdField, nameField, mobileField, emailField;
	 private JButton updateButton, closeButton;
	 private DBconnection db;
	 private String customerId;

	 public myProfilegui(String customerId) {

		 this.customerId = customerId;
		 
		 db = new DBconnection();
		 
		 try 
		 {
			 db.connect();
			 } 
		 catch (SQLException e) 
		 {
			 e.printStackTrace();
			 JOptionPane.showMessageDialog(this,"Database connection failed");
		     return;
		  }

	        setTitle("My Profile");
	        setSize(600, 450);
	        setLayout(null);
	        setLocationRelativeTo(null);
	        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	        getContentPane().setBackground(
	                new Color(245, 248, 252));

	        JLabel title = new JLabel("MY PROFILE");
	        title.setBounds(190, 20, 220, 40);
	        title.setFont(new Font("Arial", Font.BOLD, 24));
	        title.setForeground(new Color(20, 50, 90));
	        title.setHorizontalAlignment(JLabel.CENTER);

	        add(title);

	        customerIdLabel = new JLabel("Customer ID");
	        customerIdLabel.setBounds(80, 80, 120, 30);
	        designLabel(customerIdLabel);
	        add(customerIdLabel);

	        customerIdField = new JTextField();
	        customerIdField.setBounds(220, 80, 250, 30);
	        customerIdField.setEditable(false);
	        add(customerIdField);

	        nameLabel = new JLabel("Name");
	        nameLabel.setBounds(80, 130, 120, 30);
	        designLabel(nameLabel);
	        add(nameLabel);

	        nameField = new JTextField();
	        nameField.setBounds(220, 130, 250, 30);
	        add(nameField);

	        mobileLabel = new JLabel("Mobile");
	        mobileLabel.setBounds(80, 180, 120, 30);
	        designLabel(mobileLabel);
	        add(mobileLabel);

	        mobileField = new JTextField();
	        mobileField.setBounds(220, 180, 250, 30);
	        add(mobileField);

	        emailLabel = new JLabel("Email");
	        emailLabel.setBounds(80, 230, 120, 30);
	        designLabel(emailLabel);
	        add(emailLabel);

	        emailField = new JTextField();
	        emailField.setBounds(220, 230, 250, 30);
	        add(emailField);

	        updateButton = new JButton("UPDATE");
	        updateButton.setBounds(150, 300, 120, 40);

	        closeButton = new JButton("CLOSE");
	        closeButton.setBounds(310, 300, 120, 40);

	        Color blue = new Color(30, 120, 220);
	        Color hoverBlue = new Color(15, 85, 170);

	        designButton(updateButton);
	        designButton(closeButton);

	        updateButton.setBackground(blue);
	        closeButton.setBackground(blue);

	        addHoverEffect(updateButton, blue, hoverBlue);
	        addHoverEffect(closeButton, blue, hoverBlue);

	        updateButton.addActionListener(this);
	        closeButton.addActionListener(this);

	        add(updateButton);
	        add(closeButton);

	        loadProfile();
	        setVisible(true);
	    }
	 
	 private void designLabel(JLabel label) 
	 {
		    label.setFont(new Font("Arial", Font.BOLD, 14));
		    label.setForeground(new Color(20, 50, 90));
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

	    private void loadProfile() {

	        try 
	        {
	            customer c = db.findCustomer(customerId);
	            if (c != null) 
	            {
	                customerIdField.setText(c.getCid());
	                nameField.setText(c.getCname());
	                mobileField.setText(c.getMobile());
	                emailField.setText(c.getEmail());
	            } 
	            else {
	                JOptionPane.showMessageDialog(this,"Customer profile not found");
	            }
	        } 
	        catch (SQLException e) 
	        {
	            e.printStackTrace();

	            JOptionPane.showMessageDialog(this,"Database error");
	        }
	    }

	    private void updateProfile() 
	    {
	        try 
	        {
	            String name = nameField.getText().trim();
	            String mobile = mobileField.getText().trim();
	            String email = emailField.getText().trim();

	            if (name.isEmpty() || mobile.isEmpty() || email.isEmpty()) 
	            {
	                JOptionPane.showMessageDialog(this,"Please fill all fields");
	                return;
	            }

	            customer c = new customer(customerId,name,mobile,email);

	            db.updateCustomer(c);

	            JOptionPane.showMessageDialog(this,"Profile updated successfully");
	        } 
	        catch (SQLException e) 
	        {
	            e.printStackTrace();

	            JOptionPane.showMessageDialog(this,"Unable to update profile");
	        }
	    }

	    @Override
	    public void actionPerformed(ActionEvent e) 
	    {
	        if (e.getSource() == updateButton) 
	        {
	            updateProfile();
	        } 
	        else if (e.getSource() == closeButton) 
	        {
	            dispose();
	        }
	    }
	    
	    public static void main(String[] args) {

	        new myProfilegui("c101");
	    }
}
