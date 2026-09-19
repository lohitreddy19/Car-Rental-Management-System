package miniguiproject1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.LocalDate;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Returngui extends JFrame implements ActionListener{
	
	 private JLabel title;
	    private JLabel customerLabel;
	    private JLabel vehicleLabel;
	    private JLabel startDateLabel;
	    private JLabel expectedReturnLabel;
	    private JLabel actualReturnLabel;
	    private JLabel lateDaysLabel;
	    private JLabel lateChargeLabel;
	    private JLabel finalAmountLabel;

	    private JTextField customerField;
	    private JTextField vehicleField;
	    private JTextField startDateField;
	    private JTextField expectedReturnField;
	    private JTextField actualReturnField;
	    private JTextField lateDaysField;
	    private JTextField lateChargeField;
	    private JTextField finalAmountField;

	    private JTable table;
	    private DefaultTableModel model;

	    private JButton returnButton;
	    private JButton refreshButton;
	    private JButton closeButton;

	    private DBconnection db;

	    private String selectedRentalId;
	 
	 public Returngui()
	    {
	        title = new JLabel("RETURN VEHICLE");

	        customerLabel = new JLabel("Customer");
	        vehicleLabel = new JLabel("Vehicle");
	        startDateLabel = new JLabel("Start Date");
	        expectedReturnLabel = new JLabel("Expected Return");
	        actualReturnLabel = new JLabel("Actual Return");
	        lateDaysLabel = new JLabel("Late Days");
	        lateChargeLabel = new JLabel("Late Charge");
	        finalAmountLabel = new JLabel("Final Amount");

	        customerField = new JTextField();
	        vehicleField = new JTextField();
	        startDateField = new JTextField();
	        expectedReturnField = new JTextField();
	        actualReturnField = new JTextField();
	        lateDaysField = new JTextField();
	        lateChargeField = new JTextField();
	        finalAmountField = new JTextField();

	        customerField.setEditable(false);
	        vehicleField.setEditable(false);
	        startDateField.setEditable(false);
	        expectedReturnField.setEditable(false);
	        actualReturnField.setEditable(false);
	        lateDaysField.setEditable(false);
	        lateChargeField.setEditable(false);
	        finalAmountField.setEditable(false);

	        model = new DefaultTableModel();

	        model.addColumn("Rental ID");
	        model.addColumn("Customer ID");
	        model.addColumn("Vehicle ID");
	        model.addColumn("Days");
	        model.addColumn("Status");
	        model.addColumn("Start Date");

	        table = new JTable(model);

	        table.setFont(new Font("Arial", Font.PLAIN, 13));
	        table.setRowHeight(25);
	        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));

	        JScrollPane scrollPane = new JScrollPane(table);

	        returnButton = new JButton("Process Return");
	        refreshButton = new JButton("Refresh");
	        closeButton = new JButton("Close");

	        db = new DBconnection();

	        try {
	            db.connect();
	        }
	        catch(SQLException e) 
	        {
	            JOptionPane.showMessageDialog(this, "Database connection error: " + e.getMessage());
	        }

	        setLayout(null);

	        getContentPane().setBackground(new Color(245, 248, 252));

	        title.setFont(new Font("Arial", Font.BOLD, 22));
	        title.setForeground(new Color(20, 50, 90));

	        title.setBounds(350, 20, 500, 40);

	        scrollPane.setBounds(30, 70, 840, 180);

	        customerLabel.setBounds(50, 270, 130, 30);
	        customerField.setBounds(190, 270, 180, 30);

	        vehicleLabel.setBounds(450, 270, 130, 30);
	        vehicleField.setBounds(590, 270, 180, 30);

	        startDateLabel.setBounds(50, 315, 130, 30);
	        startDateField.setBounds(190, 315, 180, 30);

	        expectedReturnLabel.setBounds(450, 315, 130, 30);
	        expectedReturnField.setBounds(590, 315, 180, 30);

	        actualReturnLabel.setBounds(50, 360, 130, 30);
	        actualReturnField.setBounds(190, 360, 180, 30);

	        lateDaysLabel.setBounds(450, 360, 130, 30);
	        lateDaysField.setBounds(590, 360, 180, 30);

	        lateChargeLabel.setBounds(50, 405, 130, 30);
	        lateChargeField.setBounds(190, 405, 180, 30);

	        finalAmountLabel.setBounds(450, 405, 130, 30);
	        finalAmountField.setBounds(590, 405, 180, 30);

	        returnButton.setBounds(250, 460, 160, 40);
	        refreshButton.setBounds(430, 460, 100, 40);
	        closeButton.setBounds(550, 460, 100, 40);

	        add(title);

	        add(scrollPane);

	        add(customerLabel);
	        add(customerField);

	        add(vehicleLabel);
	        add(vehicleField);

	        add(startDateLabel);
	        add(startDateField);

	        add(expectedReturnLabel);
	        add(expectedReturnField);

	        add(actualReturnLabel);
	        add(actualReturnField);

	        add(lateDaysLabel);
	        add(lateDaysField);

	        add(lateChargeLabel);
	        add(lateChargeField);

	        add(finalAmountLabel);
	        add(finalAmountField);

	        add(returnButton);
	        add(refreshButton);
	        add(closeButton);

	        designLabel(customerLabel);
	        designLabel(vehicleLabel);
	        designLabel(startDateLabel);
	        designLabel(expectedReturnLabel);
	        designLabel(actualReturnLabel);
	        designLabel(lateDaysLabel);
	        designLabel(lateChargeLabel);
	        designLabel(finalAmountLabel);

	        designButton(returnButton);
	        designButton(refreshButton);
	        designButton(closeButton);

	        returnButton.setBackground(new Color(30, 120, 220));
	        refreshButton.setBackground(new Color(30, 120, 220));
	        closeButton.setBackground(new Color(55, 75, 100));

	        addHoverEffect(returnButton,
	                new Color(30, 120, 220),
	                new Color(15, 85, 170));

	        addHoverEffect(refreshButton,
	                new Color(30, 120, 220),
	                new Color(15, 85, 170));

	        addHoverEffect(closeButton,
	                new Color(55, 75, 100),
	                new Color(35, 55, 80));

	        table.addMouseListener(new java.awt.event.MouseAdapter() {

	            @Override
	            public void mouseClicked(java.awt.event.MouseEvent e) 
	            {
	                int row = table.getSelectedRow();

	                if(row != -1) 
	                {
	                    selectedRentalId = model.getValueAt(row, 0).toString();
	                    loadRentalDetails(selectedRentalId);
	                }
	            }
	        });

	        returnButton.addActionListener(this);
	        refreshButton.addActionListener(this);
	        closeButton.addActionListener(this);

	        setTitle("Car Rental - Return Management");
	        setSize(920, 570);
	        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        setLocationRelativeTo(null);

	        setVisible(true);
	        loadReturnRequest();
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
	 
	 private void loadReturnRequest()
	 {
		 model.setRowCount(0);
		 selectedRentalId = null;
		 clearFields(); 
		 
		 try
		 {
			 PreparedStatement p = db.c.prepareStatement(
		                "SELECT rid, cid, vid, days, status, start_date " +
		                "FROM rental " +
		                "WHERE status = 'Return Requested'");

		            ResultSet rs = p.executeQuery();

		            while(rs.next()) 
		            {
		                model.addRow(new Object[] {

		                    rs.getString("rid"),
		                    rs.getString("cid"),
		                    rs.getString("vid"),
		                    rs.getInt("days"),
		                    rs.getString("status"),
		                    rs.getDate("start_date")
		                });
		            }

		            rs.close();
		            p.close();
		 }
		 catch(SQLException e)
		 {
			 JOptionPane.showMessageDialog(this,"Database Error: " + e.getMessage());
		 }
	 }

	    public static void main(String[] args)
	    {
	        new Returngui();
	    }
	    
	    private void loadRentalDetails(String rid) 
	    {
	        try 
	        {
	            Rental r = db.findRental(rid);

	            if(r == null) 
	            {
	                JOptionPane.showMessageDialog(this,"Rental not found");
	                return;
	            }


	            customerField.setText(r.getCustomer().getCname());

	            vehicleField.setText(r.getVehicle().getModel());

	            startDateField.setText(String.valueOf(r.getstartDate()));

	            expectedReturnField.setText(String.valueOf(r.getExpectedReturnDate()));
	        }
	        catch(SQLException e) 
	        {
	            JOptionPane.showMessageDialog(this,"Database Error: " + e.getMessage());
	        }
	        catch(NullPointerException e) 
	        {
	            JOptionPane.showMessageDialog(this,"Rental start date is missing");
	        }
	    }
	    
	    private void processReturn() 
	    {
	        if(selectedRentalId == null) 
	        {
	            JOptionPane.showMessageDialog(this,"Please select a return request");
	            return;
	        }

	        try 
	        {
	            Rental r = db.findRental(selectedRentalId);

	            if(r == null) 
	            {
	                JOptionPane.showMessageDialog(this,"Rental not found");
	                return;
	            }

	            if(!r.getstatus().equalsIgnoreCase("Return Requested")) 
	            {
	                JOptionPane.showMessageDialog(this,"This rental is not waiting for return");
	                return;
	            }

	            r.setreturnedDate(java.time.LocalDate.now());

	            long lateDays = r.calculateLateDays();

	            double lateCharge = r.calculateLateCharge();

	            double finalAmount = r.calculateFinalAmount();

	            r.setstatus("Returned");

	            db.updateRental(r);

	            r.getVehicle().setAvailable(true);

	            db.updateVehicle(r.getVehicle());

	            actualReturnField.setText(
	                String.valueOf(r.getreturnedDate())
	            );

	            lateDaysField.setText(
	                String.valueOf(lateDays)
	            );

	            lateChargeField.setText(
	                String.valueOf(lateCharge)
	            );

	            finalAmountField.setText(
	                String.valueOf(finalAmount)
	            );


	            JOptionPane.showMessageDialog(
	                this,
	                "Vehicle returned successfully\n\n"
	                + "Rental ID: " + r.getRid()
	                + "\nLate Days: " + lateDays
	                + "\nLate Charge: ₹" + lateCharge
	                + "\nFinal Amount: ₹" + finalAmount
	            );

	            loadReturnRequest();
	        }
	        catch(SQLException e) 
	        {
	            JOptionPane.showMessageDialog(this,"Database Error: " + e.getMessage());
	        }
	    }
	    
	    private void clearFields() 
	    {
	        customerField.setText("");
	        vehicleField.setText("");
	        startDateField.setText("");
	        expectedReturnField.setText("");
	        actualReturnField.setText("");
	        lateDaysField.setText("");
	        lateChargeField.setText("");
	        finalAmountField.setText("");
	    } 

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			 if(e.getSource() == returnButton) {

		            processReturn();
		        }

		        else if(e.getSource() == refreshButton) {

		            loadReturnRequest();
		        }

		        else if(e.getSource() == closeButton) {

		            this.dispose();
		        }
		}
}