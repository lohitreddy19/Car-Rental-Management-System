package miniguiproject1;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Receiptgui extends JFrame implements ActionListener{
	
	private JLabel title;
	private JLabel customerIdLabel;
	private JLabel receiptIdLabel;

	private JTextField customerIdField;
	private JTextField receiptIdField;

	private JButton viewAllButton;
	private JButton searchButton;
	private JButton detailsButton;
	private JButton printButton;
	private JButton deleteButton;
	private JButton closeButton;

	private JTable receiptTable;
	private JScrollPane scrollPane;

	private DefaultTableModel model;

	private DBconnection db;
    
    public Receiptgui()
    {
    	title = new JLabel("RECEIPT");

    	customerIdLabel = new JLabel("Customer ID");
 		receiptIdLabel = new JLabel("Receipt ID");

 		customerIdField = new JTextField();
 		receiptIdField = new JTextField();

 		viewAllButton = new JButton("VIEW ALL");
 		searchButton = new JButton("SEARCH");
 		detailsButton = new JButton("VIEW DETAILS");
 		printButton = new JButton("PRINT");
 		deleteButton = new JButton("DELETE");
 		closeButton = new JButton("CLOSE");

 		model = new DefaultTableModel();

 		receiptTable = new JTable(model);

 		scrollPane = new JScrollPane(receiptTable);

         setLayout(null);

         getContentPane().setBackground(new Color(245, 248, 252));

 		title.setBounds(190, 20, 250, 40);

 		title.setFont(new Font("Arial", Font.BOLD, 25));

 		title.setForeground(new Color(20, 50, 90));

 		title.setHorizontalAlignment(JLabel.CENTER);

 		customerIdLabel.setBounds(40, 80, 100, 30);

 		customerIdField.setBounds(140, 80, 150, 30);


 		searchButton.setBounds(300, 80, 100, 30);

 		viewAllButton.setBounds(410, 80, 120, 30);


 		receiptIdLabel.setBounds(40, 125, 100, 30);

 		receiptIdField.setBounds(140, 125, 150, 30);


 		detailsButton.setBounds(300, 125, 120, 30);

 		printButton.setBounds(430, 125, 100, 30);


 		scrollPane.setBounds(40, 175, 490, 220);


 		deleteButton.setBounds(100, 420, 100, 40);

 		closeButton.setBounds(380, 420, 100, 40);

 		model.addColumn("Receipt ID");
		model.addColumn("Payment ID");
		model.addColumn("Customer ID");
		model.addColumn("Vehicle ID");
		model.addColumn("Amount");
		
         add(title);

         add(customerIdLabel);
 		add(customerIdField);
 		add(searchButton);
 		add(viewAllButton);

 		add(receiptIdLabel);
 		add(receiptIdField);
 		add(detailsButton);
 		add(printButton);

 		add(scrollPane);

 		add(deleteButton);
 		add(closeButton);


 		designLabel(customerIdLabel);
 		designLabel(receiptIdLabel);


 		designButton(viewAllButton);
 		designButton(searchButton);
 		designButton(detailsButton);
 		designButton(printButton);
 		designButton(deleteButton);
 		designButton(closeButton);

 		Color blue = new Color(30, 120, 220);
		Color hoverBlue = new Color(15, 85, 170);

		viewAllButton.setBackground(blue);
		searchButton.setBackground(blue);
		detailsButton.setBackground(blue);
		printButton.setBackground(blue);
		deleteButton.setBackground(blue);
		closeButton.setBackground(new Color(55, 75, 100));


		addHoverEffect(
				viewAllButton,
				blue,
				hoverBlue);

		addHoverEffect(
				searchButton,
				blue,
				hoverBlue);

		addHoverEffect(
				detailsButton,
				blue,
				hoverBlue);

		addHoverEffect(
				printButton,
				blue,
				hoverBlue);

		addHoverEffect(
				deleteButton,
				blue,
				hoverBlue);


		addHoverEffect(
				closeButton,
				new Color(55, 75, 100),
				new Color(35, 55, 80));


		db = new DBconnection();

		try
		{
			db.connect();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}


		viewAllButton.addActionListener(this);
		searchButton.addActionListener(this);
		detailsButton.addActionListener(this);
		printButton.addActionListener(this);
		deleteButton.addActionListener(this);
		closeButton.addActionListener(this);


		receiptTable.addMouseListener(
				new MouseAdapter()
				{
					@Override
					public void mouseClicked(MouseEvent e)
					{
						int row = receiptTable.getSelectedRow();

						if(row >= 0)
						{
							String rid =model.getValueAt(row, 0).toString();

							receiptIdField.setText(rid);
						}
					}
				});


		setTitle("Car Rental - Receipt Management");

		setSize(600, 530);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setLocationRelativeTo(null);

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
		
		if(e.getSource() == viewAllButton)
		{
			viewAllReceipts();
		}

		else if(e.getSource() == searchButton)
		{
			searchByCustomer();
		}

		else if(e.getSource() == detailsButton)
		{
			viewDetails();
		}

		else if(e.getSource() == printButton)
		{
			printReceipt();
		}

		else if(e.getSource() == deleteButton)
		{
			deleteReceipt();
		}

		else if(e.getSource() == closeButton)
		{
			this.dispose();
		}
	}
	
	private void viewAllReceipts()
	{
		model.setRowCount(0);

		try
		{
			ResultSet rs = db.getAllReceipts();

			while(rs.next())
			{
				model.addRow(new Object[]
				{
					rs.getString("rid"),
					rs.getString("pid"),
					rs.getString("cid"),
					rs.getString("vid"),
					rs.getDouble("totalamount")
				});
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();

			JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage());
		}
	}
	
	private void searchByCustomer()
	{
		String cid =customerIdField.getText();

		if(cid.isEmpty())
		{
			JOptionPane.showMessageDialog(this, "Please enter Customer ID");

			return;
		}

		model.setRowCount(0);

		try
		{
			ResultSet rs = db.getReceiptsByCustomerId(cid);

			boolean found = false;

			while(rs.next())
			{
				found = true;

				model.addRow(new Object[]
				{
					rs.getString("rid"),
					rs.getString("pid"),
					rs.getString("cid"),
					rs.getString("vid"),
					rs.getDouble("totalamount")
				});
			}

			if(!found)
			{
				JOptionPane.showMessageDialog(this, "No receipts found for Customer ID " + cid);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();

			JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage());
		}
	}
	
	private void viewDetails()
	{
		String rid = receiptIdField.getText();

		if(rid.isEmpty())
		{
			JOptionPane.showMessageDialog(this, "Please select or enter Receipt ID");

			return;
		}

		try
		{
			Receipt r =db.findReceipt(rid);

			if(r == null)
			{
				JOptionPane.showMessageDialog(this, "Receipt not found");

				return;
			}

			JOptionPane.showMessageDialog(
					this,
					"RECEIPT DETAILS\n\n"
					+ "Receipt ID: "
					+ r.getRid()
					+ "\nPayment ID: "
					+ r.getPayment().getPid()
					+ "\nRental ID: "
					+ r.getPayment()
						.getRental().getRid()
					+ "\nCustomer: "
					+ r.getCustomer().getCname()
					+ "\nVehicle: "
					+ r.getVehicle().getModel()
					+ "\nPayment Method: "
					+ r.getPayment().getPmethod()
					+ "\nPayment Status: "
					+ r.getPayment().getstatus()
					+ "\nTotal Amount: ₹"
					+ r.getTotalamount());
		}
		catch(SQLException e)
		{
			e.printStackTrace();

			JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage());
		}
	}

	private void printReceipt()
	{
		String rid = receiptIdField.getText();

		if(rid.isEmpty())
		{
			JOptionPane.showMessageDialog(this, "Please select or enter Receipt ID");
			return;
		}

		try
		{
			Receipt r = db.findReceipt(rid);

			if(r == null)
			{
				JOptionPane.showMessageDialog(this, "Receipt not found");
				return;
			}

			r.printreceipt();

			JOptionPane.showMessageDialog(
					this,
					"RECEIPT\n\n"
					+ "Receipt ID: "
					+ r.getRid()
					+ "\nPayment ID: "
					+ r.getPayment().getPid()
					+ "\nRental ID: "
					+ r.getPayment()
						.getRental().getRid()
					+ "\nCustomer: "
					+ r.getCustomer().getCname()
					+ "\nVehicle: "
					+ r.getVehicle().getModel()
					+ "\nPayment Method: "
					+ r.getPayment().getPmethod()
					+ "\nPayment Status: "
					+ r.getPayment().getstatus()
					+ "\nTotal Amount: ₹"
					+ r.getTotalamount());
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage());
		}
	}
	
	private void deleteReceipt()
	{
		String rid = receiptIdField.getText();

		if(rid.isEmpty())
		{
			JOptionPane.showMessageDialog(this, "Please select a receipt");
			return;
		}

		int result =
				JOptionPane.showConfirmDialog(
						this,
						"Delete Receipt "
						+ rid + "?",
						"Confirm Delete",
						JOptionPane.YES_NO_OPTION);

		if(result != JOptionPane.YES_OPTION)
		{
			return;
		}

		try
		{
			db.deleteReceipt(rid);
			JOptionPane.showMessageDialog(this, "Receipt deleted successfully");
			receiptIdField.setText("");
			viewAllReceipts();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage());
		}
	}

}