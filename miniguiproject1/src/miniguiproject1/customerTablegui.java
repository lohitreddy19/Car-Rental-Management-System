package miniguiproject1;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class customerTablegui extends JFrame{

	 private JLabel title;

	 private JTable table;

	 private JButton refreshButton;
	 private JButton closeButton;

	 private DefaultTableModel model;

	 private DBconnection db;

	 public customerTablegui()
	    {
	        title = new JLabel("CUSTOMER REPORT");

	        refreshButton = new JButton("Refresh");
	        closeButton = new JButton("Close");

	        model = new DefaultTableModel();

	        model.addColumn("Customer ID");
	        model.addColumn("Name");
	        model.addColumn("Mobile");
	        model.addColumn("Email");

	        table = new JTable(model);

	        JScrollPane scrollPane = new JScrollPane(table);

	        setLayout(null);

	        getContentPane().setBackground(new Color(245, 248, 252));

	        title.setBounds(200, 20, 250, 40);

	        title.setFont(new Font("Arial", Font.BOLD, 22));

	        title.setForeground(new Color(20, 50, 90));

	        title.setHorizontalAlignment(JLabel.CENTER);

	        scrollPane.setBounds(40, 80, 500, 300);

	        refreshButton.setBounds(150, 410, 120, 40);
	        closeButton.setBounds(300, 410, 120, 40);

	        add(title);
	        add(scrollPane);
	        add(refreshButton);
	        add(closeButton);

	        designButton(refreshButton);
	        designButton(closeButton);

	        refreshButton.setBackground(new Color(30, 120, 220));

	        closeButton.setBackground(new Color(55, 75, 100));

	        addHoverEffect(refreshButton,
	                new Color(30, 120, 220),
	                new Color(15, 85, 170));

	        addHoverEffect(closeButton,
	                new Color(55, 75, 100),
	                new Color(35, 55, 80));

	        setTitle("Car Rental - Customer Report");

	        setSize(600, 520);

	        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	        db = new DBconnection();

	        try {
	            db.connect();
	        }
	        catch (SQLException e1) {
	            e1.printStackTrace();
	        }

	        loadCustomers();

	        refreshButton.addActionListener(new ActionListener() 
	        {
	            @Override
	            public void actionPerformed(ActionEvent e) 
	            {
	                model.setRowCount(0);
	                loadCustomers();
	            }
	        });


	        closeButton.addActionListener(new ActionListener() 
	        {
	            @Override
	            public void actionPerformed(ActionEvent e) 
	            {
	                dispose();
	            }
	        });

	        setLocationRelativeTo(null);
	        setVisible(true);
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
	 
	 public void loadCustomers()
	    {
	        try
	        {
	            ResultSet rs = db.getCustomer();

	            while(rs.next())
	            {
	                model.addRow(new Object[] {
	                    rs.getString("cid"),
	                    rs.getString("cname"),
	                    rs.getString("mobile"),
	                    rs.getString("email")});
	            }

	            rs.close();
	        }
	        catch(SQLException e)
	        {
	        	e.printStackTrace();
	        	
	        	JOptionPane.showMessageDialog(this, "database error: " + e.getMessage());
	        }
	    }
	 
	 public static void main(String[] args)
	 {
		 new customerTablegui();
	 }
}
