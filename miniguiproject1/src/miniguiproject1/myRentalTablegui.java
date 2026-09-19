package miniguiproject1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class myRentalTablegui extends JFrame implements ActionListener{
	
	private JTable table;
	private DefaultTableModel model;
	
	private JButton refreshButton;
	private JButton returnRequestButton;
	private JButton closeButton;

	private DBconnection db;
    private String customerId;
    
    public myRentalTablegui(String customerId) {

    	this.customerId = customerId;
    	db = new DBconnection();
    	
    	try {
			db.connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
	        model = new DefaultTableModel();

	        model.addColumn("Rental ID");
	        model.addColumn("Customer ID");
	        model.addColumn("Vehicle ID");
	        model.addColumn("Days");
	        model.addColumn("Status");
	        model.addColumn("Start Date");
	        model.addColumn("Returned Date");

	        table = new JTable(model);

	        JScrollPane scrollPane = new JScrollPane(table);
	        scrollPane.setBounds(20, 70, 850, 280);

	        JLabel title = new JLabel("MY RENTALS");
	        title.setBounds(320, 20, 260, 35);
	        title.setFont(new Font("Arial", Font.BOLD, 24));
	        title.setForeground(new Color(20, 50, 90));
	        title.setHorizontalAlignment(JLabel.CENTER);

	        returnRequestButton = new JButton("Request Return");
	        returnRequestButton.setBounds(180, 380, 140, 40);

	        refreshButton = new JButton("Refresh");
	        refreshButton.setBounds(340, 380, 100, 40);

	        closeButton = new JButton("Close");
	        closeButton.setBounds(460, 380, 100, 40);

	        setLayout(null);

	        getContentPane().setBackground(new Color(245, 248, 252));

	        add(title);
	        add(scrollPane);
	        add(returnRequestButton);
	        add(refreshButton);
	        add(closeButton);

	        table.setFont(new Font("Arial", Font.PLAIN, 13));
	        table.setRowHeight(25);
	        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));

	        Color blue = new Color(30, 120, 220);
	        Color hoverBlue = new Color(15, 85, 170);

	        designButton(returnRequestButton);
	        designButton(refreshButton);
	        designButton(closeButton);

	        returnRequestButton.setBackground(blue);
	        refreshButton.setBackground(blue);
	        closeButton.setBackground(blue);

	        addHoverEffect(returnRequestButton, blue, hoverBlue);
	        addHoverEffect(refreshButton, blue, hoverBlue);
	        addHoverEffect(closeButton, blue, hoverBlue);

	        returnRequestButton.addActionListener(this);
	        refreshButton.addActionListener(this);
	        closeButton.addActionListener(this);

	        setTitle("Car Rental - My Rentals");
	        setSize(900, 480);
	        setLocationRelativeTo(null);
	        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        setVisible(true);

	        loadRentals();
	    }
    
    private void designButton(JButton button) {

        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
    }
    
    private void addHoverEffect(JButton button, Color normalColor, Color hoverColor) 
    {
        button.addMouseListener(new MouseAdapter() {

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

	    private void loadRentals() {

	    	System.out.println("Customer ID: " + customerId);
	        model.setRowCount(0);

	        try {

	            ResultSet rs = db.getRentalsByCustomer(customerId);

	            while (rs.next()) {

	                model.addRow(new Object[] {
	                    rs.getString("rid"),
	                    rs.getString("cid"),
	                    rs.getString("vid"),
	                    rs.getInt("days"),
	                    rs.getString("status"),
	                    rs.getDate("start_date"),
	                    rs.getDate("returned_date")
	                });
	            }

	            rs.close();

	        } catch (SQLException e) {

	            e.printStackTrace();

	            JOptionPane.showMessageDialog(this,"Database Error: " + e.getMessage());
	        }
	    }
	    
	    private void requestReturn() 
	    {
	        int selectedRow = table.getSelectedRow();

	        if (selectedRow == -1) 
	        {
	            JOptionPane.showMessageDialog(this,"Please select a rental");
	            return;
	        }
	        
	        String rentalId = model.getValueAt(selectedRow, 0).toString();

	        String status = model.getValueAt(selectedRow, 4).toString();  
	        
	        if (!status.equalsIgnoreCase("Active")) 
	        {
	            JOptionPane.showMessageDialog(this,"Return request can be made only for Active rentals");
	            return;
	        }
	        
	        int choice = JOptionPane.showConfirmDialog(
	                this,
	                "Do you want to request return for " + rentalId + "?",
	                "Return Request",
	                JOptionPane.YES_NO_OPTION);

	            if (choice != JOptionPane.YES_OPTION) 
	            {
	                return;
	            }
	            
	            try 
	            {
	                db.requestReturn(rentalId);

	                JOptionPane.showMessageDialog(this,"Return request submitted successfully");
	                loadRentals();
	            }
	            catch (SQLException e) 
	            {
	                e.printStackTrace();
	            }
	    }

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			if (e.getSource() == returnRequestButton) 
			{
	            requestReturn();
	        } 
			else if (e.getSource() == refreshButton) 
			{
	            loadRentals();
	        } 
			else if (e.getSource() == closeButton) 
			{
	            dispose();
	        }
		}
		
		public static void main(String[] args) 
		{
		    new myRentalTablegui("c108");
		}
}
