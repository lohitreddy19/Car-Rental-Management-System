package miniguiproject1;

import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class BookVehiclegui extends JFrame implements ActionListener{
	
	private JLabel title;
    private JLabel customerIdLabel;
    private JLabel vehicleIdLabel;
    private JLabel daysLabel;

    private JTextField customerIdField;
    private JTextField vehicleIdField;
    private JTextField daysField;

    private JButton bookButton;
    private JButton clearButton;
    private JButton closeButton;
    
    private RentalManager manager;

    public BookVehiclegui() {

        title = new JLabel("BOOK VEHICLE");

        customerIdLabel = new JLabel("Customer ID");
        vehicleIdLabel = new JLabel("Vehicle ID");
        daysLabel = new JLabel("Rental Days");

        customerIdField = new JTextField();
        vehicleIdField = new JTextField();
        daysField = new JTextField();

        bookButton = new JButton("BOOK VEHICLE");
        clearButton = new JButton("CLEAR");
        closeButton = new JButton("CLOSE");
        
        manager = new RentalManager();

        setLayout(null);

        setTitle("Car Rental - Book Vehicle");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        getContentPane().setBackground(new Color(245, 248, 252));

        title.setBounds(200, 30, 200, 40);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(new Color(20, 50, 90));
        title.setHorizontalAlignment(JLabel.CENTER);

        designLabel(customerIdLabel);
        designLabel(vehicleIdLabel);
        designLabel(daysLabel);

        customerIdLabel.setBounds(100, 160, 120, 40);
        customerIdField.setBounds(230, 160, 200, 40);

        vehicleIdLabel.setBounds(100, 220, 120, 40);
        vehicleIdField.setBounds(230, 220, 200, 40);

        daysLabel.setBounds(100, 280, 120, 40);
        daysField.setBounds(230, 280, 200, 40);

        bookButton.setBounds(100, 360, 150, 40);
        clearButton.setBounds(270, 360, 100, 40);
        closeButton.setBounds(390, 360, 100, 40);

        Color blue = new Color(30, 120, 220);
        Color hoverBlue = new Color(15, 85, 170);

        designButton(bookButton);
        designButton(clearButton);
        designButton(closeButton);

        bookButton.setBackground(blue);
        clearButton.setBackground(blue);
        closeButton.setBackground(blue);

        addHoverEffect(bookButton, blue, hoverBlue);
        addHoverEffect(clearButton, blue, hoverBlue);
        addHoverEffect(closeButton, blue, hoverBlue);

        add(title);

        add(customerIdLabel);
        add(customerIdField);

        add(vehicleIdLabel);
        add(vehicleIdField);

        add(daysLabel);
        add(daysField);

        add(bookButton);
        add(clearButton);
        add(closeButton);

        bookButton.addActionListener(this);
        clearButton.addActionListener(this);
        closeButton.addActionListener(this);

        setVisible(true);
    }
    
    private void designLabel(JLabel label) {

        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(new Color(20, 50, 90));
    }
    
    private void designButton(JButton button) {

        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
    }
    
    private void addHoverEffect(JButton button, Color normalColor, Color hoverColor) {

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
    	
    	if(e.getSource() == bookButton)
    	{
    		String cid = customerIdField.getText();
    		String vid = vehicleIdField.getText();
    		String daysText = daysField.getText();

    		if(cid.isEmpty() || vid.isEmpty() || daysText.isEmpty())
    		{
    			JOptionPane.showMessageDialog(this, "Please fill all fields");
    			return;
    		}

    		try
    		{
    			int days = Integer.parseInt(daysText);

    			DBconnection db = new DBconnection();

    			db.connect();

    			String rid = db.generateRentalId();
    			
    			Rental rental = manager.createRental(rid, cid, vid, days);

    			if(rental != null)
    			{
    				JOptionPane.showMessageDialog(
    						this,
    						"Vehicle Booked Successfully!\n"
    						+ "Rental ID: " + rental.getRid()
    						+ "\nVehicle: " + rental.getVehicle().getModel()
    						+ "\nDays: " + rental.getDays()
    						+ "\nStart Date: " + rental.getstartDate());

    				clearFields();
    			}
    		}
    		catch(NumberFormatException ex)
    		{
    			JOptionPane.showMessageDialog(this, "Rental days must be a number");
    		}
    		catch(invalidRentalException ex)
    		{
    			JOptionPane.showMessageDialog(this, ex.getMessage());
    		}
    		catch(SQLException ex)
    		{
    			ex.printStackTrace();

    			JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
    		}
    	}
    	
    	else if(e.getSource() == clearButton)
    	{
    		clearFields();
    	}

    	else if(e.getSource() == closeButton)
    	{
    		this.dispose();
    	}
    }

	    private void clearFields() 
	    {
	        customerIdField.setText("");
	        vehicleIdField.setText("");
	        daysField.setText("");
	    }

	    public static void main(String[] args) {

	        new BookVehiclegui();
	    }
}
