package miniguiproject1;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Customergui extends JFrame implements ActionListener{
	
	 private JLabel title;
	 private JLabel cidLabel;
	 private JLabel nameLabel;
	 private JLabel mobileLabel;
	 private JLabel emailLabel;

	 private JTextField cidField;
	 private JTextField nameField;
	 private JTextField mobileField;
	 private JTextField emailField;

	 private JButton addButton;
	 private JButton updateButton;
	 private JButton deleteButton;
	 private JButton searchButton;
	 
	 private DBconnection db;
	 
	 public Customergui()
	 {
		 title = new JLabel("CUSTOMER MANAGEMENT");

	        cidLabel = new JLabel("Customer ID");
	        nameLabel = new JLabel("Name");
	        mobileLabel = new JLabel("Mobile");
	        emailLabel = new JLabel("Email");

	        cidField = new JTextField();
	        nameField = new JTextField();
	        mobileField = new JTextField();
	        emailField = new JTextField();

	        addButton = new JButton("Add Customer");
	        updateButton = new JButton("Update");
	        deleteButton = new JButton("Delete");
	        searchButton = new JButton("Search");

	        setTitle("Car Rental - Customer Management");
	        setSize(600, 500);
	        setLayout(null);
	        setLocationRelativeTo(null);
	        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	        getContentPane().setBackground(new Color(245, 248, 252));

	        title.setBounds(130, 30, 400, 40);

	        title.setFont(new Font("Arial", Font.BOLD, 22));

	        title.setForeground(new Color(20, 50, 90));

	        title.setHorizontalAlignment(JLabel.CENTER);

	        add(title);

	        cidLabel.setBounds(100, 100, 100, 30);
	        nameLabel.setBounds(100, 150, 100, 30);
	        mobileLabel.setBounds(100, 200, 100, 30);
	        emailLabel.setBounds(100, 250, 100, 30);

	        add(cidLabel);
	        add(nameLabel);
	        add(mobileLabel);
	        add(emailLabel);

	        designLabel(cidLabel);
	        designLabel(nameLabel);
	        designLabel(mobileLabel);
	        designLabel(emailLabel);

	        cidField.setBounds(220, 100, 220, 30);
	        nameField.setBounds(220, 150, 220, 30);
	        mobileField.setBounds(220, 200, 220, 30);
	        emailField.setBounds(220, 250, 220, 30);

	        add(cidField);
	        add(nameField);
	        add(mobileField);
	        add(emailField);

	        addButton.setBounds(70, 330, 130, 40);
	        updateButton.setBounds(210, 330, 100, 40);
	        deleteButton.setBounds(320, 330, 100, 40);
	        searchButton.setBounds(430, 330, 100, 40);

	        add(addButton);
	        add(updateButton);
	        add(deleteButton);
	        add(searchButton);

	        Color blue = new Color(30, 120, 220);
	        Color hoverBlue = new Color(15, 85, 170);

	        addButton.setBackground(blue);
	        updateButton.setBackground(blue);
	        deleteButton.setBackground(blue);
	        searchButton.setBackground(blue);

	        designButton(addButton);
	        designButton(updateButton);
	        designButton(deleteButton);
	        designButton(searchButton);

	        addHoverEffect(addButton, blue, hoverBlue);
	        addHoverEffect(updateButton, blue, hoverBlue);
	        addHoverEffect(deleteButton, blue, hoverBlue);
	        addHoverEffect(searchButton, blue, hoverBlue);

	        db = new DBconnection();

	        try {

	            db.connect();

	        } catch (SQLException e) {

	            e.printStackTrace();
	        }

	        addButton.addActionListener(this);
	        searchButton.addActionListener(this);
	        updateButton.addActionListener(this);
	        deleteButton.addActionListener(this);
	        setVisible(true);
	 }
	 
	 private void designButton(JButton button) {

	        button.setFont(new Font("Arial", Font.BOLD, 14));

	        button.setForeground(Color.WHITE);

	        button.setFocusPainted(false);

	        button.setBorderPainted(false);
	    }
	 
	 private void designLabel(JLabel label) {

		    label.setFont(new Font("Arial", Font.BOLD, 14));

		    label.setForeground(new Color(20, 50, 90));
		}

	private void addHoverEffect(JButton button, Color blue, Color hoverBlue) {
		// TODO Auto-generated method stub
		
		 button.addMouseListener(new MouseAdapter() {

	            @Override
	            public void mouseEntered(MouseEvent e) {

	                button.setBackground(hoverBlue);
	            }


	            @Override
	            public void mouseExited(MouseEvent e) {

	                button.setBackground(blue);
	            }
	        }); 
	}
	 
	 public static void main(String[]args)
	 {
		 new Customergui();
	 }
	 
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		 if(e.getSource() == addButton)
	        {
	            String cid = cidField.getText();
	            String name = nameField.getText();
	            String mobile = mobileField.getText();
	            String email = emailField.getText();

	            if(cid.isEmpty() || name.isEmpty() || mobile.isEmpty() || email.isEmpty())
	            {
	                JOptionPane.showMessageDialog(this,"Please fill all fields");
                    return;
	            }

	            customer c1 = new customer(cid, name, mobile, email);

	            try
	            {
	                db.insertCustomer(c1);

	                JOptionPane.showMessageDialog(this,"Customer added successfully");

	                cidField.setText("");
	                nameField.setText("");
	                mobileField.setText("");
	                emailField.setText("");
	            }
	            catch(SQLException e1)
	            {
	                e1.printStackTrace();

	                JOptionPane.showMessageDialog(this, "Customer ID already exists. Please enter a new CID.");
	            }
	        }


	        else if(e.getSource() == searchButton)
	        {
	            String cid = cidField.getText();

	            if(cid.isEmpty())
	            {
	                JOptionPane.showMessageDialog(this,"Please enter Customer ID");
	                return;
	            }

	            try
	            {
	                customer c1 = db.findCustomer(cid);

	                if(c1 == null)
	                {
	                    JOptionPane.showMessageDialog(this, "Customer not found");

	                    nameField.setText("");
	                    mobileField.setText("");
	                    emailField.setText("");
	                }
	                else
	                {
	                    nameField.setText(c1.getCname());
	                    mobileField.setText(c1.getMobile());
	                    emailField.setText(c1.getEmail());

	                    JOptionPane.showMessageDialog(this,"Customer found");
	                }
	            }
	            catch(SQLException e1)
	            {
	                e1.printStackTrace();

	                JOptionPane.showMessageDialog(this, "Database Error: " + e1.getMessage());
	            }
	        }
	        else if(e.getSource() == updateButton)
	        {
	            String cid = cidField.getText();
	            String name = nameField.getText();
	            String mobile = mobileField.getText();
	            String email = emailField.getText();

	            if(cid.isEmpty() || name.isEmpty() || mobile.isEmpty() || email.isEmpty())
	            {
	                JOptionPane.showMessageDialog(this, "Please fill all fields");
	                return;
	            }

	            customer c1 = new customer(cid, name, mobile, email);

	            try
	            {
	                customer existingCustomer = db.findCustomer(cid);

	                if(existingCustomer == null)
	                {
	                    JOptionPane.showMessageDialog(this, "Customer not found");
	                    return;
	                }

	                db.updateCustomer(c1);

	                JOptionPane.showMessageDialog(this, "Customer updated successfully");

	                cidField.setText("");
	                nameField.setText("");
	                mobileField.setText("");
	                emailField.setText("");
	            }
	            catch(SQLException e1)
	            {
	                e1.printStackTrace();

	                JOptionPane.showMessageDialog(this, "Database Error: " + e1.getMessage());
	            }
	        }
	        else if(e.getSource() == deleteButton)
	        {
	            String cid = cidField.getText();

	            if(cid.isEmpty())
	            {
	                JOptionPane.showMessageDialog(this, "Please enter Customer ID");
	                return;
	            }

	            try
	            {
	                customer c1 = db.findCustomer(cid);

	                if(c1 == null)
	                {
	                    JOptionPane.showMessageDialog(this,"Customer not found");
	                    return;
	                }

	                int choice = JOptionPane.showConfirmDialog(this,"Are you sure you want to delete customer " + cid + "?","Confirm Delete",JOptionPane.YES_NO_OPTION);

	                if(choice == JOptionPane.YES_OPTION)
	                {
	                    db.deleteCustomer(cid);

	                    JOptionPane.showMessageDialog(this,"Customer deleted successfully");

	                    cidField.setText("");
	                    nameField.setText("");
	                    mobileField.setText("");
	                    emailField.setText("");
	                }
	            }
	            catch(SQLException e1)
	            {
	                e1.printStackTrace();

	                JOptionPane.showMessageDialog(this,"Database Error: " + e1.getMessage() );
	            }
	        }
	}
}
