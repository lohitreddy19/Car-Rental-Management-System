package miniguiproject1;

import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Paymentgui extends JFrame implements ActionListener{
	
	JLabel title; 
	JLabel searchLabel;
    JTextField searchField;

    JButton searchButton, refreshButton, closeButton;

    JTable table;
    DefaultTableModel model;

    DBconnection db;
    
    public Paymentgui()
    {
    	setTitle("Payment Management");
        setSize(700, 500);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        getContentPane().setBackground(new Color(245, 248, 252));

        db = new DBconnection();

        try {
            db.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        title = new JLabel("PAYMENT MANAGEMENT");
        title.setBounds(250, 20, 300, 40);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(new Color(20, 50, 90));
        add(title);

        searchLabel = new JLabel("Payment ID:");
        searchLabel.setBounds(30, 70, 100, 30);
        searchLabel.setFont(new Font("Arial", Font.BOLD, 14));
        searchLabel.setForeground(new Color(20, 50, 90));
        add(searchLabel);

        searchField = new JTextField();
        searchField.setBounds(120, 70, 150, 30);
        add(searchField);

        searchButton = new JButton("Search");
        searchButton.setBounds(290, 70, 100, 30);
        add(searchButton);

        refreshButton = new JButton("Refresh");
        refreshButton.setBounds(400, 70, 100, 30);
        add(refreshButton);

        model = new DefaultTableModel();

        model.addColumn("Payment ID");
        model.addColumn("Rental ID");
        model.addColumn("Amount");
        model.addColumn("Method");
        model.addColumn("Status");

        table = new JTable(model);

        table.setFont(new Font("Arial", Font.PLAIN, 13));
        table.setRowHeight(25);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(30, 120, 620, 250);
        add(scroll);

        closeButton = new JButton("Close");
        closeButton.setBounds(280, 400, 100, 30);
        add(closeButton);

        designButton(searchButton);
        designButton(refreshButton);
        designButton(closeButton);

        searchButton.setBackground(new Color(30, 120, 220));
        refreshButton.setBackground(new Color(30, 120, 220));
        closeButton.setBackground(new Color(55, 75, 100));

        addHoverEffect(searchButton,
                new Color(30, 120, 220),
                new Color(15, 85, 170));

        addHoverEffect(refreshButton,
                new Color(30, 120, 220),
                new Color(15, 85, 170));

        addHoverEffect(closeButton,
                new Color(55, 75, 100),
                new Color(35, 55, 80));

        loadPayments();
        
        searchButton.addActionListener(new ActionListener() { 

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			 String pid = searchField.getText();

             if (pid.isEmpty()) {

                 JOptionPane.showMessageDialog(Paymentgui.this,"Enter Payment ID");
                 return;
             }

             model.setRowCount(0);

             try 
             {
                 PreparedStatement p = db.c.prepareStatement("SELECT * FROM payment WHERE pid = ?");

                 p.setString(1, pid);

                 ResultSet rs = p.executeQuery();

                 if (rs.next()) {

                     model.addRow(new Object[] {
                             rs.getString("pid"),
                             rs.getString("rid"),
                             rs.getDouble("amount"),
                             rs.getString("pmethod"),
                             rs.getString("status")
                     });

                 } else {

                     JOptionPane.showMessageDialog(Paymentgui.this, "Payment not found");
                 }

             } catch (SQLException ex) {
                 ex.printStackTrace();
             }
         }
     });


     refreshButton.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {

             searchField.setText("");

             loadPayments();
         }
     });


     closeButton.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {

             dispose();
         }
     });

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
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	// TODO Auto-generated method stub
    	
    }

    public static void main(String[] args) 
    {

        new Paymentgui();
    }
    
    public void loadPayments() {

        model.setRowCount(0);

        try {

            ResultSet rs = db.getAllPayments();

            while (rs.next()) {

                model.addRow(new Object[] {
                        rs.getString("pid"),
                        rs.getString("rid"),
                        rs.getDouble("amount"),
                        rs.getString("pmethod"),
                        rs.getString("status")
                });
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
}