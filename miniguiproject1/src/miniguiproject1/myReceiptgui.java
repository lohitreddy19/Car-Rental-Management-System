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

public class myReceiptgui extends JFrame implements ActionListener{

	 private JTable table;
	 private DefaultTableModel model;

	 private JButton refreshButton;   
	 private JButton closeButton;

	 private DBconnection db;

	 private String customerId;

	 public myReceiptgui(String customerId) {

		 this.customerId = customerId;

	        model = new DefaultTableModel();

	        model.addColumn("Receipt ID");
	        model.addColumn("Payment ID");
	        model.addColumn("Customer ID");
	        model.addColumn("Vehicle ID");
	        model.addColumn("Total Amount");

	        table = new JTable(model);

	        JScrollPane scrollPane = new JScrollPane(table);
	        scrollPane.setBounds(20, 70, 650, 280);

	        refreshButton = new JButton("Refresh");
	        refreshButton.setBounds(220, 380, 100, 40);

	        closeButton = new JButton("Close");
	        closeButton.setBounds(350, 380, 100, 40);

	        setLayout(null);

	        getContentPane().setBackground(new Color(245, 248, 252));

	        JLabel title = new JLabel("MY RECEIPTS");
	        title.setBounds(220, 20, 250, 35);
	        title.setFont(new Font("Arial", Font.BOLD, 24));
	        title.setForeground(new Color(20, 50, 90));
	        title.setHorizontalAlignment(JLabel.CENTER);

	        add(title);
	        add(scrollPane);
	        add(refreshButton);
	        add(closeButton);

	        table.setFont(new Font("Arial", Font.PLAIN, 13));
	        table.setRowHeight(25);
	        table.getTableHeader().setFont(
	            new Font("Arial", Font.BOLD, 13)
	        );

	        Color blue = new Color(30, 120, 220);
	        Color hoverBlue = new Color(15, 85, 170);

	        designButton(refreshButton);
	        designButton(closeButton);

	        refreshButton.setBackground(blue);
	        closeButton.setBackground(blue);

	        addHoverEffect(refreshButton, blue, hoverBlue);
	        addHoverEffect(closeButton, blue, hoverBlue);

	        refreshButton.addActionListener(this);
	        closeButton.addActionListener(this);

	        db = new DBconnection();

	        try {
	            db.connect();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        setTitle("Car Rental - My Receipts");
	        setSize(700, 480);
	        setLocationRelativeTo(null);
	        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        
	        setVisible(true);
	        loadReceipts();
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

	    private void loadReceipts() 
	    {
	    	model.setRowCount(0);

	        try {
	            ResultSet rs = db.getReceiptsByCustomer(customerId);

	            while (rs.next()) {

	                model.addRow(new Object[] {
	                    rs.getString("rid"),
	                    rs.getString("pid"),
	                    rs.getString("cid"),
	                    rs.getString("vid"),
	                    rs.getDouble("totalamount")});
	            }
	            rs.close();
	        }
	        catch(SQLException e) 
	        {
	            e.printStackTrace();

	            JOptionPane.showMessageDialog(this,"Database Error: " + e.getMessage());
	        }
	    }

	    @Override
	    public void actionPerformed(ActionEvent e) {

	        if(e.getSource() == refreshButton) 
	        {
	            loadReceipts();
	        }
	        else if(e.getSource() == closeButton) 
	        {
	            this.dispose();
	        }
	    }
	    
	    public static void main(String[] args) 
	    {
	        new myReceiptgui("c101");
	    }
}
