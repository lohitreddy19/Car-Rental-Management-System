package miniguiproject1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class customerPaymentgui extends JFrame implements ActionListener{

	 private JLabel title;
	 private JLabel rentalIdLabel;
	 private JLabel customerLabel;
	 private JLabel vehicleLabel;
	 private JLabel amountLabel;
	 private JLabel methodLabel;
	 private JLabel statusLabel;

	 private JTextField rentalIdField;
	 private JTextField customerField;
	 private JTextField vehicleField;
	 private JTextField amountField;
	 private JComboBox<String> methodBox;
	 private JTextField statusField;

	 private JButton searchButton;
	 private JButton payButton;
	 private JButton clearButton;
	 private JButton closeButton;
	 
	 private DBconnection db;

	 private String customerId;

	 public customerPaymentgui(String customerId) {

	        this.customerId = customerId;

	        title = new JLabel("CUSTOMER PAYMENT");

	        rentalIdLabel = new JLabel("Rental ID");
	        customerLabel = new JLabel("Customer");
	        vehicleLabel = new JLabel("Vehicle");
	        amountLabel = new JLabel("Amount");
	        methodLabel = new JLabel("Payment Method");
	        statusLabel = new JLabel("Payment Status");

	        rentalIdField = new JTextField();
	        customerField = new JTextField();
	        vehicleField = new JTextField();
	        amountField = new JTextField();

	        methodBox = new JComboBox<>(
	            new String[] {
	                "Cash",
	                "UPI",
	                "Card",
	                "Net Banking"
	            }
	        );

	        statusField = new JTextField();

	        searchButton = new JButton("SEARCH");
	        payButton = new JButton("MAKE PAYMENT");
	        clearButton = new JButton("CLEAR");
	        closeButton = new JButton("CLOSE");

	        customerField.setEditable(false);
	        vehicleField.setEditable(false);
	        amountField.setEditable(false);
	        statusField.setEditable(false);

	        setLayout(null);

	        getContentPane().setBackground(new Color(245, 248, 252));

	        title.setFont(new Font("Arial", Font.BOLD, 24));
	        title.setForeground(new Color(20, 50, 90));
	        title.setHorizontalAlignment(JLabel.CENTER);

	        designLabel(rentalIdLabel);
	        designLabel(customerLabel);
	        designLabel(vehicleLabel);
	        designLabel(amountLabel);
	        designLabel(methodLabel);
	        designLabel(statusLabel);

	        Color blue = new Color(30, 120, 220);
	        Color hoverBlue = new Color(15, 85, 170);

	        designButton(searchButton);
	        designButton(payButton);
	        designButton(clearButton);
	        designButton(closeButton);

	        searchButton.setBackground(blue);
	        payButton.setBackground(blue);
	        clearButton.setBackground(blue);
	        closeButton.setBackground(blue);

	        addHoverEffect(searchButton, blue, hoverBlue);
	        addHoverEffect(payButton, blue, hoverBlue);
	        addHoverEffect(clearButton, blue, hoverBlue);
	        addHoverEffect(closeButton, blue, hoverBlue);
	        
	        title.setBounds(200, 20, 200, 40);

	        rentalIdLabel.setBounds(80, 80, 120, 40);
	        rentalIdField.setBounds(210, 80, 200, 40);
	        searchButton.setBounds(430, 80, 100, 40);

	        customerLabel.setBounds(80, 140, 120, 40);
	        customerField.setBounds(210, 140, 200, 40);

	        vehicleLabel.setBounds(80, 200, 120, 40);
	        vehicleField.setBounds(210, 200, 200, 40);

	        amountLabel.setBounds(80, 260, 120, 40);
	        amountField.setBounds(210, 260, 200, 40);

	        methodLabel.setBounds(80, 320, 120, 40);
	        methodBox.setBounds(210, 320, 200, 40);

	        statusLabel.setBounds(80, 380, 120, 40);
	        statusField.setBounds(210, 380, 200, 40);

	        payButton.setBounds(70, 450, 150, 40);
	        clearButton.setBounds(240, 450, 100, 40);
	        closeButton.setBounds(360, 450, 100, 40);

	        add(title);

	        add(rentalIdLabel);
	        add(rentalIdField);
	        add(searchButton);

	        add(customerLabel);
	        add(customerField);

	        add(vehicleLabel);
	        add(vehicleField);

	        add(amountLabel);
	        add(amountField);

	        add(methodLabel);
	        add(methodBox);

	        add(statusLabel);
	        add(statusField);

	        add(payButton);
	        add(clearButton);
	        add(closeButton);

	        searchButton.addActionListener(this);
	        payButton.addActionListener(this);
	        clearButton.addActionListener(this);
	        closeButton.addActionListener(this);

	        db = new DBconnection();

	        try {
	            db.connect();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        setTitle("Car Rental - Customer Payment");
	        setSize(600, 560);
	        setLocationRelativeTo(null);
	        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	        setVisible(true);
	 }
	 
	 private void designLabel(JLabel label) 
	 {
		    label.setFont(new Font("Arial", Font.BOLD, 14));
		    label.setForeground(new Color(20, 50, 90));
	 }
	 
	 private void designButton(JButton button) {

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
		
		 if(e.getSource() == searchButton) {

	            searchRental();

	        }
	        else if(e.getSource() == payButton) {

	            makePayment();

	        }
	        else if(e.getSource() == clearButton) {

	            clearFields();

	        }
	        else if(e.getSource() == closeButton) {

	            this.dispose();
	        }
	}

	private void searchRental() {
		// TODO Auto-generated method stub

		String rid = rentalIdField.getText().trim();

        if(rid.isEmpty()) 
        {
            JOptionPane.showMessageDialog(this,"Please enter Rental ID");
            return;
        }
        
        try {

            Rental r = db.findRental(rid);

            if(r == null) {

                JOptionPane.showMessageDialog(this,"Rental not found");
                return;
            }
            
            if(!r.getCustomer().getCid().equals(customerId)) {

                JOptionPane.showMessageDialog(this,"You cannot access this rental");
                clearRentalDetails();
                return;
            }
            
            customerField.setText(r.getCustomer().getCname());

            vehicleField.setText(r.getVehicle().getModel());

            amountField.setText(String.valueOf(r.calculateFinalAmount()));

            statusField.setText(r.getstatus());
        }
        catch(SQLException e)
        {
        	e.printStackTrace();
        }
	}
	
	private void makePayment() 
	{
	    String rid = rentalIdField.getText().trim();

	    if(rid.isEmpty()) 
	    {
	        JOptionPane.showMessageDialog(this, "Please search a Rental first");
	        return;
	    }

	    try {

	        Rental r = db.findRental(rid);

	        if(r == null) 
	        {
	            JOptionPane.showMessageDialog(this, "Rental not found");
	            return;
	        }

	        if(!r.getCustomer().getCid().equals(customerId)) 
	        {
	            JOptionPane.showMessageDialog(this,"You cannot pay for this rental");
	            return;
	        }

	        if(!"Returned".equalsIgnoreCase(r.getstatus())) 
	        {
	            JOptionPane.showMessageDialog(this,"Payment can be made only after vehicle return");
	            return;
	        }

	        if(db.paymentExistsForRental(rid)) 
	        {
	            JOptionPane.showMessageDialog(this, "Payment already exists for this rental");
	            return;
	        }

	        String pid = db.generatePaymentId();

	        double amount = r.calculateFinalAmount();

	        String method = (String) methodBox.getSelectedItem();

	        Payment payment = new Payment(pid, r, amount, method, "Successful");

	        db.insertPayment(payment);

	        String receiptId = db.generateReceiptId();

	        Receipt receipt =
	                new Receipt(
	                        receiptId,
	                        payment,
	                        r.getCustomer(),
	                        r.getVehicle(),
	                        amount
	                );

	        db.insertReceipt(receipt);

	        JOptionPane.showMessageDialog(this,
	                "Payment Successful!\n"
	                + "Payment ID: " + pid
	                + "\nReceipt ID: " + receiptId
	                + "\nRental ID: " + rid
	                + "\nAmount: ₹" + amount
	                + "\nMethod: " + method);

	        statusField.setText("Paid");
	    }
	    catch(SQLException e) 
	    {
	        e.printStackTrace();

	        JOptionPane.showMessageDialog(this,"Database Error: " + e.getMessage());
	    }
	}
	 
	 private void clearFields() 
	 {
	        rentalIdField.setText("");
	        customerField.setText("");
	        vehicleField.setText("");
	        amountField.setText("");
	        statusField.setText("");
	        methodBox.setSelectedIndex(0);
	    }
	 
	 private void clearRentalDetails() 
	 {
	        customerField.setText("");
	        vehicleField.setText("");
	        amountField.setText("");
	        statusField.setText("");
	    }
	 
	 public static void main(String[] args) 
	 {
	        new customerPaymentgui("c101");
	 }
}