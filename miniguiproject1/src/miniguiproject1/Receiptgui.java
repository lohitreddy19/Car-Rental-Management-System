package miniguiproject1;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Receiptgui extends JFrame implements ActionListener{
	
	private JLabel title;
    private JLabel receiptIdLabel;
    private JLabel paymentIdLabel;
    private JLabel rentalIdLabel;
    private JLabel customerLabel;
    private JLabel vehicleLabel;
    private JLabel methodLabel;
    private JLabel statusLabel;
    private JLabel amountLabel;

    private JTextField receiptIdField;
    private JTextField paymentIdField;
    private JTextField rentalIdField;
    private JTextField customerField;
    private JTextField vehicleField;
    private JTextField methodField;
    private JTextField statusField;
    private JTextField amountField;

    private JButton searchButton;
    private JButton printButton;
    
    private DBconnection db;
    private String rid;
    
    public Receiptgui()
    {
    	 title = new JLabel("RECEIPT");

         receiptIdLabel = new JLabel("Receipt ID");
         paymentIdLabel = new JLabel("Payment ID");
         rentalIdLabel = new JLabel("Rental ID");
         customerLabel = new JLabel("Customer");
         vehicleLabel = new JLabel("Vehicle");
         methodLabel = new JLabel("Payment Method");
         statusLabel = new JLabel("Payment Status");
         amountLabel = new JLabel("Total Amount");

         receiptIdField = new JTextField();
         paymentIdField = new JTextField();
         rentalIdField = new JTextField();
         customerField = new JTextField();
         vehicleField = new JTextField();
         methodField = new JTextField();
         statusField = new JTextField();
         amountField = new JTextField();

         paymentIdField.setEditable(false);
         rentalIdField.setEditable(false);
         customerField.setEditable(false);
         vehicleField.setEditable(false);
         methodField.setEditable(false);
         statusField.setEditable(false);
         amountField.setEditable(false);

         searchButton = new JButton("Search");
         printButton = new JButton("Print Receipt");

         setLayout(null);

         getContentPane().setBackground(new Color(245, 248, 252));

         title.setBounds(240, 25, 150, 40);

         title.setFont(new Font("Arial", Font.BOLD, 30));

         title.setForeground(new Color(20, 50, 90));

         title.setHorizontalAlignment(JLabel.CENTER);


         receiptIdLabel.setBounds(80, 80, 130, 30);
         receiptIdField.setBounds(220, 80, 200, 30);
         searchButton.setBounds(430, 80, 100, 30);

         paymentIdLabel.setBounds(80, 125, 130, 30);
         paymentIdField.setBounds(220, 125, 200, 30);

         rentalIdLabel.setBounds(80, 170, 130, 30);
         rentalIdField.setBounds(220, 170, 200, 30);

         customerLabel.setBounds(80, 215, 130, 30);
         customerField.setBounds(220, 215, 200, 30);

         vehicleLabel.setBounds(80, 260, 130, 30);
         vehicleField.setBounds(220, 260, 200, 30);

         methodLabel.setBounds(80, 305, 130, 30);
         methodField.setBounds(220, 305, 200, 30);

         statusLabel.setBounds(80, 350, 130, 30);
         statusField.setBounds(220, 350, 200, 30);

         amountLabel.setBounds(80, 395, 130, 30);
         amountField.setBounds(220, 395, 200, 30);

         printButton.setBounds(210, 450, 160, 40);

         add(title);

         add(receiptIdLabel);
         add(receiptIdField);
         add(searchButton);

         add(paymentIdLabel);
         add(paymentIdField);

         add(rentalIdLabel);
         add(rentalIdField);

         add(customerLabel);
         add(customerField);

         add(vehicleLabel);
         add(vehicleField);

         add(methodLabel);
         add(methodField);

         add(statusLabel);
         add(statusField);

         add(amountLabel);
         add(amountField);

         add(printButton);

         designLabel(receiptIdLabel);
         designLabel(paymentIdLabel);
         designLabel(rentalIdLabel);
         designLabel(customerLabel);
         designLabel(vehicleLabel);
         designLabel(methodLabel);
         designLabel(statusLabel);
         designLabel(amountLabel);

         designButton(searchButton);
         designButton(printButton);

         searchButton.setBackground(new Color(30, 120, 220));

         printButton.setBackground(new Color(30, 120, 220));


         addHoverEffect(searchButton,
                 new Color(30, 120, 220),
                 new Color(15, 85, 170));

         addHoverEffect(printButton,
                 new Color(30, 120, 220),
                 new Color(15, 85, 170));


         setTitle("Car Rental - Receipt");
         setSize(600, 550);
         setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         setLocationRelativeTo(null);

         db = new DBconnection();

         try {
             db.connect();
         }
         catch (SQLException e) {
             e.printStackTrace();
         }

         searchButton.addActionListener(this);
         printButton.addActionListener(this);
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
    
    public static void main(String[] args)
    {
    	new Receiptgui();
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		 if(e.getSource() == searchButton)
		    {
		        String receiptId = receiptIdField.getText();

		        if(receiptId.isEmpty())
		        {
		            JOptionPane.showMessageDialog(this, "pelase enter receipt id");
		            return;
		        }
		        
		        try
		        {
		        	Receipt r = db.findReceipt(receiptId);
		        	
		        	if(r == null)
		        	{
		        		JOptionPane.showMessageDialog(this, "receipt not found");
		        		return;
		        	}
		        	
		        	paymentIdField.setText(r.getPayment().getPid());
		        	rentalIdField.setText(r.getPayment().getRental().getRid());
		        	customerField.setText(r.getCustomer().getCname());
		        	vehicleField.setText(r.getVehicle().getModel());
		        	methodField.setText(r.getPayment().getPmethod());
		        	statusField.setText(r.getPayment().getstatus());
		        	amountField.setText(String.valueOf(r.getTotalamount()));
		        	
		        	JOptionPane.showMessageDialog(this,  "receipt found successfully");
		        }
		        catch(SQLException e1)
		        {
		        	e1.printStackTrace();
		        	
		        	JOptionPane.showMessageDialog(this, "database error: " + e1.getMessage());
		        }
		    }
		 else if(e.getSource() == printButton)
		 {
			 String receiptId = receiptIdField.getText();
			 
			 if(receiptId.isEmpty())
			 {
				 JOptionPane.showMessageDialog(this, "please enter receipt id");
				 return;
			 }
			 
			 try
			 {
				 Receipt r = db.findReceipt(receiptId);
				 
				 if(r == null)
				 {
					 JOptionPane.showMessageDialog(this, "receipt not found");
					 return;
				 }
				 
				 r.printreceipt();
				 
				 JOptionPane.showMessageDialog(this,  "RECEIPT\n\n"
				            + "Receipt ID: " + r.getRid()
				            + "\nPayment ID: " + r.getPayment().getPid()
				            + "\nRental ID: " + r.getPayment().getRental().getRid()
				            + "\nCustomer: " + r.getCustomer().getCname()
				            + "\nVehicle: " + r.getVehicle().getModel()
				            + "\nPayment Method: " + r.getPayment().getPmethod()
				            + "\nPayment Status: " + r.getPayment().getstatus()
				            + "\nTotal Amount: ₹" + r.getTotalamount());
			 }
			 catch(SQLException e1)
			 {
				 e1.printStackTrace();
				 
				 JOptionPane.showMessageDialog(this, "database error: " + e1.getMessage());
			 }
		 }
	}
}