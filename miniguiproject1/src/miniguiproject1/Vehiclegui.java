package miniguiproject1;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Vehiclegui extends JFrame implements ActionListener{
	
	 private JLabel title;
	    private JLabel vidLabel;
	    private JLabel brandLabel;
	    private JLabel modelLabel;
	    private JLabel yearLabel;
	    private JLabel ftypeLabel;
	    private JLabel transmissionLabel;
	    private JLabel priceLabel;
	    private JLabel vtypeLabel;
	    private JLabel availableLabel;

	    private JTextField vidField;
	    private JTextField brandField;
	    private JTextField modelField;
	    private JTextField yearField;
	    private JTextField ftypeField;
	    private JTextField transmissionField;
	    private JTextField priceField;

	    private JComboBox<String> vtypeBox;
	    private JComboBox<String> availableBox;

	    private JButton addButton;
	    private JButton updateButton;
	    private JButton deleteButton;
	    private JButton searchButton;
	    
	    private DBconnection db;
	    
	   public Vehiclegui() 
	   {
		   title = new JLabel("VEHICLE MANAGEMENT");

	        vidLabel = new JLabel("Vehicle ID");
	        brandLabel = new JLabel("Brand");
	        modelLabel = new JLabel("Model");
	        yearLabel = new JLabel("Year");
	        ftypeLabel = new JLabel("Fuel Type");
	        transmissionLabel = new JLabel("Transmission");
	        priceLabel = new JLabel("Price");
	        vtypeLabel = new JLabel("Vehicle Type");
	        availableLabel = new JLabel("Availability");

	        vidField = new JTextField();
	        brandField = new JTextField();
	        modelField = new JTextField();
	        yearField = new JTextField();
	        ftypeField = new JTextField();
	        transmissionField = new JTextField();
	        priceField = new JTextField();
	        
	        String[] vehicleTypes = {"Seden", "SUV", "Hatchback"};
	        vtypeBox = new JComboBox<>(vehicleTypes);

	        String[] availability = {"Available", "Not Available"};
	        availableBox = new JComboBox<>(availability);

	        addButton = new JButton("Add Vehicle");
	        updateButton = new JButton("Update");
	        deleteButton = new JButton("Delete");
	        searchButton = new JButton("Search");
	        

	        setTitle("Car Rental - Vehicle Management");
	        setSize(600, 600);
	        setLocationRelativeTo(null);
	        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	        setLayout(null);
	        
	        getContentPane().setBackground(new Color(245, 248, 252));

	        title.setBounds(150, 20, 300, 40);
	        title.setFont(new Font("Arial", Font.BOLD, 24));
	        title.setForeground(new Color(20, 50, 90));
	        title.setHorizontalAlignment(JLabel.CENTER);

	        designLabel(vidLabel);
	        designLabel(brandLabel);
	        designLabel(modelLabel);
	        designLabel(yearLabel);
	        designLabel(ftypeLabel);
	        designLabel(transmissionLabel);
	        designLabel(priceLabel);
	        designLabel(vtypeLabel);
	        designLabel(availableLabel);

	        vidLabel.setBounds(70, 70, 120, 30);
	        vidField.setBounds(200, 70, 180, 30);

	        brandLabel.setBounds(70, 110, 120, 30);
	        brandField.setBounds(200, 110, 180, 30);

	        modelLabel.setBounds(70, 150, 120, 30);
	        modelField.setBounds(200, 150, 180, 30);

	        yearLabel.setBounds(70, 190, 120, 30);
	        yearField.setBounds(200, 190, 180, 30);

	        ftypeLabel.setBounds(70, 230, 120, 30);
	        ftypeField.setBounds(200, 230, 180, 30);

	        transmissionLabel.setBounds(70, 270, 120, 30);
	        transmissionField.setBounds(200, 270, 180, 30);

	        priceLabel.setBounds(70, 310, 120, 30);
	        priceField.setBounds(200, 310, 180, 30);

	        vtypeLabel.setBounds(70, 350, 120, 30);
	        vtypeBox.setBounds(200, 350, 180, 30);

	        availableLabel.setBounds(70, 390, 120, 30);
	        availableBox.setBounds(200, 390, 180, 30);

	        addButton.setBounds(40, 440, 120, 40);
	        updateButton.setBounds(170, 440, 100, 40);
	        deleteButton.setBounds(280, 440, 100, 40);
	        searchButton.setBounds(390, 440, 100, 40);

	        Color blue = new Color(30, 120, 220);
	        Color hoverBlue = new Color(15, 85, 170);

	        designButton(addButton);
	        designButton(updateButton);
	        designButton(deleteButton);
	        designButton(searchButton);

	        addButton.setBackground(blue);
	        updateButton.setBackground(blue);
	        deleteButton.setBackground(blue);
	        searchButton.setBackground(blue);

	        addHoverEffect(addButton, blue, hoverBlue);
	        addHoverEffect(updateButton, blue, hoverBlue);
	        addHoverEffect(deleteButton, blue, hoverBlue);
	        addHoverEffect(searchButton, blue, hoverBlue);

	        add(title);

	        add(vidLabel);
	        add(vidField);

	        add(brandLabel);
	        add(brandField);

	        add(modelLabel);
	        add(modelField);

	        add(yearLabel);
	        add(yearField);

	        add(ftypeLabel);
	        add(ftypeField);

	        add(transmissionLabel);
	        add(transmissionField);

	        add(priceLabel);
	        add(priceField);

	        add(vtypeLabel);
	        add(vtypeBox);

	        add(availableLabel);
	        add(availableBox);

	        add(addButton);
	        add(updateButton);
	        add(deleteButton);
	        add(searchButton);
	        
	        db = new DBconnection();

	        try {
	            db.connect();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

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

		private void addHoverEffect(JButton button, Color normalColor, Color hoverColor) {

		    button.addMouseListener(new MouseAdapter() 
		    {
		        public void mouseEntered(MouseEvent e) 
		        {
		            button.setBackground(hoverColor);
		        }

		        public void mouseExited(MouseEvent e) 
		        {
		        	button.setBackground(normalColor);
		        }
		    });
		}
	   
	   public static void main(String[] args)
	   {
		   new Vehiclegui();
	   }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == addButton)
	    {
	        String vid = vidField.getText();
	        String brand = brandField.getText();
	        String model = modelField.getText();
	        String yearText = yearField.getText();
	        String ftype = ftypeField.getText();
	        String transmission = transmissionField.getText();
	        String priceText = priceField.getText();

	        String vtype = (String) vtypeBox.getSelectedItem();

	        String availableText = (String) availableBox.getSelectedItem();
	        
	        if(vid.isEmpty() || brand.isEmpty() || model.isEmpty() || yearText.isEmpty() || ftype.isEmpty() || transmission.isEmpty() || priceText.isEmpty())
	        {
	        	JOptionPane.showMessageDialog(this, "please fill all fields");
	        	return;
	        }
	        
	        try
	        {
	            int year = Integer.parseInt(yearText);
	            double price = Double.parseDouble(priceText);

	            boolean available;

	            if(availableText.equals("Available"))
	            {
	                available = true;
	            }
	            else
	            {
	                available = false;
	            }

	            vehicle v;
	            
	            if(vtype.equals("Seden"))
	            {
	            	v = new Seden(vid, brand, model, year, ftype, transmission, price, available, vtype);
	            }
	            else if(vtype.equals("SUV"))
	            {
	            	v = new SUV(vid, brand, model, year, ftype, transmission, price, available, vtype);
	            }
	            else
	            {
	            	v = new Hatchback(vid, brand, model, year, ftype, transmission, price, available, vtype);
	            }
	            
	            db.insertVehicle(v);
	            
	            JOptionPane.showMessageDialog(this, "vehicle added successfully");
	            
	            vidField.setText("");
	            brandField.setText("");
	            modelField.setText("");
	            yearField.setText("");
	            ftypeField.setText("");
	            transmissionField.setText("");
	            priceField.setText("");
	        }
	        catch(NumberFormatException e1)
	        {
	        	JOptionPane.showMessageDialog(this, "year must be valid number");
	        }
	        catch(SQLException e1)
	        {
	        	e1.printStackTrace();
	        	
	        	JOptionPane.showMessageDialog(this, "vehicle id already exist");
	        }
	    }
		
		else if(e.getSource() == searchButton)
		{
		    String vid = vidField.getText();

		    if(vid.isEmpty())
		    {
		    	JOptionPane.showMessageDialog(this, "please enter vehicle id");
		    	return;
		    }
		    
		    try
		    {
		    	vehicle v = db.findVehicle(vid);
		    	
		    	if(v == null)
		    	{
		    		JOptionPane.showMessageDialog(this, "vehicle not found");
		    		
		    		brandField.setText("");
		            modelField.setText("");
		            yearField.setText("");
		            ftypeField.setText("");
		            transmissionField.setText("");
		            priceField.setText("");

		            return;
		    	}
		    	
		    	 brandField.setText(v.getBrand());
		         modelField.setText(v.getModel());
		         yearField.setText(String.valueOf(v.getYear()));
		         ftypeField.setText(v.getFtype());
		         transmissionField.setText(v.getTransmission());
		         priceField.setText(String.valueOf(v.getPrice()));

		         vtypeBox.setSelectedItem(v.getvtype());
		         
		         if(v.getAvailable())
		         {
		        	 availableBox.setSelectedItem("Available");
		         }
		         else
		         {
		        	 availableBox.setSelectedItem("Not Available");
		         }
		         
		         JOptionPane.showMessageDialog(this, "vehicle found");
		    }
		    catch(SQLException e1)
		    {
		    	e1.printStackTrace();
		    	
		    	JOptionPane.showMessageDialog(this, "database error: " + e1.getMessage());
		    }
		}
		
		else if(e.getSource() == updateButton)
		{
		    String vid = vidField.getText();
		    String brand = brandField.getText();
		    String model = modelField.getText();
		    String yearText = yearField.getText();
		    String ftype = ftypeField.getText();
		    String transmission = transmissionField.getText();
		    String priceText = priceField.getText();

		    String vtype = (String) vtypeBox.getSelectedItem();
		    String availableText = (String) availableBox.getSelectedItem();
		    
		    if(vid.isEmpty() || brand.isEmpty() || model.isEmpty() || yearText.isEmpty() || ftype.isEmpty() || transmission.isEmpty() || priceText.isEmpty())
		    {
		    	JOptionPane.showMessageDialog(this, "please fill all fields");
		    	return;
		    }
		    
		    try
		    {
		    	vehicle existingVehicle = db.findVehicle(vid);

		        if(existingVehicle == null)
		        {
		        	JOptionPane.showMessageDialog(this, "vehicle not found");
		        	return;
		        }
		        
		        int year = Integer.parseInt(yearText);
		        double price = Double.parseDouble(priceText);

		        boolean available;

		        if(availableText.equals("Available"))
		        {
		            available = true;
		        }
		        else
		        {
		            available = false;
		        }

		        vehicle v;

		        if(vtype.equals("Seden"))
		        {
		            v = new Seden(vid, brand, model, year, ftype, transmission, price, available, vtype);
		        }
		        else if(vtype.equals("SUV"))
		        {
		        	v = new SUV(vid, brand, model, year, ftype, transmission, price, available, vtype);
		        }
		        else
		        {
		        	v = new Hatchback(vid, brand, model, year, ftype, transmission, price, available, vtype);
		        }
		        
		        db.updateVehicle(v);
		        
		        JOptionPane.showMessageDialog(this, "vehicle updated successfullt");
		        
		        vidField.setText("");
		        brandField.setText("");
		        modelField.setText("");
		        yearField.setText("");
		        ftypeField.setText("");
		        transmissionField.setText("");
		        priceField.setText("");
		    }
		    catch(NumberFormatException e1)
		    {
		    	JOptionPane.showMessageDialog(this, "year and price must be valid number");
		    }
		    catch(SQLException e1)
		    {
		    	e1.printStackTrace();
		    	
		    	JOptionPane.showMessageDialog(this, "database error: " + e1.getMessage());
		    }
		}
		
		else if(e.getSource() == deleteButton)
		{
		    String vid = vidField.getText();

		    if(vid.isEmpty())
		    {
		        JOptionPane.showMessageDialog(
		            this,
		            "Please enter Vehicle ID"
		        );

		        return;
		    }

		    try
		    {
		        vehicle v = db.findVehicle(vid);

		        if(v == null)
		        {
		            JOptionPane.showMessageDialog(
		                this,
		                "Vehicle not found"
		            );

		            return;
		        }

		        int choice = JOptionPane.showConfirmDialog(this, "are you sure you want to delete vehicle" + vid + "?", "confirm delete", JOptionPane.YES_NO_OPTION);
		        
		        if(choice == JOptionPane.YES_OPTION)
		        {
		        	db.deleteVehicle(vid);

		            JOptionPane.showMessageDialog(this, "vehicle deleted successfully");
		            
		            vidField.setText("");
		            brandField.setText("");
		            modelField.setText("");
		            yearField.setText("");
		            ftypeField.setText("");
		            transmissionField.setText("");
		            priceField.setText("");

		            vtypeBox.setSelectedIndex(0);
		            availableBox.setSelectedIndex(0);
		        }
		    }
		    catch(SQLException e1)
		    {
		    	e1.printStackTrace();
		    	JOptionPane.showMessageDialog(this, "database error: " + e1.getMessage());
		    }
		}
	}
}