package miniguiproject1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class availableVehicleTablegui extends JFrame implements ActionListener{
	
	private JTable table;
    private DefaultTableModel model;

    private JButton refreshButton;
    private JButton closeButton;

    private DBconnection db;

    public availableVehicleTablegui() {

        model = new DefaultTableModel();

        model.addColumn("Vehicle ID");
        model.addColumn("Brand");
        model.addColumn("Model");
        model.addColumn("Year");
        model.addColumn("Fuel Type");
        model.addColumn("Transmission");
        model.addColumn("Price");
        model.addColumn("Type");

        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 70, 850, 280);

        refreshButton = new JButton("Refresh");
        refreshButton.setBounds(320, 380, 100, 40);

        closeButton = new JButton("Close");
        closeButton.setBounds(460, 380, 100, 40);

        setLayout(null);

        getContentPane().setBackground(new Color(245, 248, 252));

        JLabel title = new JLabel("AVAILABLE VEHICLES");
        title.setBounds(300, 20, 300, 35);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(new Color(20, 50, 90));
        title.setHorizontalAlignment(JLabel.CENTER);

        add(title);
        add(scrollPane);
        add(refreshButton);
        add(closeButton);

        table.setFont(new Font("Arial", Font.PLAIN, 13));
        table.setRowHeight(25);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));

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

        setTitle("Car Rental - Available Vehicles");
        setSize(900, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setVisible(true);

        loadVehicle();
    }
    
    private void designButton(JButton button) {

        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
    }
    
    private void addHoverEffect(JButton button, Color normalColor, Color hoverColor) {

        button.addMouseListener(new java.awt.event.MouseAdapter() 
        {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) 
            {
                button.setBackground(hoverColor);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) 
            {
                button.setBackground(normalColor);
            }
        });
    }
    
    private void loadVehicle()
    {
    	 model.setRowCount(0);

         try {

             ResultSet rs = db.getAvailableVehicles();

             while (rs.next()) {

                 model.addRow(new Object[] {
                     rs.getString("vid"),
                     rs.getString("brand"),
                     rs.getString("model"),
                     rs.getInt("year"),
                     rs.getString("ftype"),
                     rs.getString("transmission"),
                     rs.getDouble("price"),
                     rs.getString("vtype")
                 });
             }

             rs.close();

         } catch (SQLException e) {

             e.printStackTrace();

             JOptionPane.showMessageDialog(this,"Database Error: " + e.getMessage());
         }
     }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource() == refreshButton) {

            loadVehicle();

        }
        else if (e.getSource() == closeButton) {

            this.dispose();
        }
	}
	
	public static void main(String[] args) 
	{
        new availableVehicleTablegui();
    }
}
