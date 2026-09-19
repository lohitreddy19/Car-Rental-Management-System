package miniguiproject1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Rentalgui extends JFrame implements ActionListener{
	
	private JLabel title;
    private JLabel searchLabel;

    private JTextField searchField;

    private JButton searchButton;
    private JButton refreshButton;
    private JButton closeButton;

    private JTable table;
    private DefaultTableModel model;

    private DBconnection db;
    
    public Rentalgui()
    {
        title = new JLabel("RENTAL MANAGEMENT");

        searchLabel = new JLabel("Rental ID");

        searchField = new JTextField();

        searchButton = new JButton("Search");
        refreshButton = new JButton("Refresh");
        closeButton = new JButton("Close");

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

        db = new DBconnection();

        try 
        {
            db.connect();
        }
        
        catch(SQLException e) 
        {
            JOptionPane.showMessageDialog(this, "Database connection error: " + e.getMessage());
        }

        setLayout(null);

        title.setBounds(210, 30, 200, 40);

        title.setBounds(350, 20, 200, 40);

        searchLabel.setBounds(50, 80, 100, 30);
        searchField.setBounds(130, 80, 180, 30);

        searchButton.setBounds(330, 80, 100, 30);
        refreshButton.setBounds(450, 80, 100, 30);

        scrollPane.setBounds(30, 140, 840, 300);

        closeButton.setBounds(390, 470, 100, 40);

        add(title);

        add(searchLabel);
        add(searchField);

        add(searchButton);
        add(refreshButton);

        add(scrollPane);

        add(closeButton);

        searchButton.addActionListener(this);
        refreshButton.addActionListener(this);
        closeButton.addActionListener(this);

        setTitle("Car Rental - Rental Management");
        setSize(920, 570);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setVisible(true);

        loadAllRentals();
    }
    
    private void loadAllRentals()
    {
    	model.setRowCount(0);
    	
    	try
    	{
    		ResultSet rs = db.getAllRentals();
    		
    		while(rs.next())
    		{
    			 model.addRow(new Object[] {

    	                    rs.getString("rid"),
    	                    rs.getString("cid"),
    	                    rs.getString("vid"),
    	                    rs.getInt("days"),
    	                    rs.getString("status"),
    	                    rs.getDate("start_date"),
    	                    rs.getDate("returned_date")});
    		}
    		rs.close();
        }
    	catch(SQLException e)
    	{
    		JOptionPane.showMessageDialog(this, e.getMessage());
    	}
    }
    
    private void searchRental() {

        String rid = searchField.getText().trim();

        if(rid.isEmpty()) {

            JOptionPane.showMessageDialog(this, "Please enter Rental ID");
            return;
        }

        model.setRowCount(0);
        
        try {

            java.sql.PreparedStatement p = db.c.prepareStatement(
                "SELECT rid, cid, vid, days, status, start_date, returned_date " +
                "FROM rental WHERE rid = ?");

            p.setString(1, rid);

            ResultSet rs = p.executeQuery();

            if(rs.next()) {

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
            else 
            {
                JOptionPane.showMessageDialog(this,"Rental ID not found");
                loadAllRentals();
            }

            rs.close();
            p.close();
        }
        catch(SQLException e)
        {
        	JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    public static void main(String[] args)
    {
        new Rentalgui();
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		 if(e.getSource() == searchButton) 
		 {
	         searchRental();
	     }

	        else if(e.getSource() == refreshButton) 
	        {
	            searchField.setText("");

	            loadAllRentals();
	        }

	        else if(e.getSource() == closeButton) 
	        {

	            this.dispose();
	        }
	}
}
