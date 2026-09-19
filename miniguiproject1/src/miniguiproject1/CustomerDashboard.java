package miniguiproject1;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class CustomerDashboard extends JFrame implements ActionListener{

	private JLabel title;

    private JButton browse;
    private JButton booking;
    private JButton rentals;
    private JButton payment;
    private JButton receipts;
    private JButton profile;
    private JButton logout;
    
    private String customerId;
    private DBconnection db;

    public CustomerDashboard(String customerId)
    {
    	this.customerId = customerId;
    	
        title = new JLabel("CUSTOMER DASHBOARD");

        browse = new JButton("Browse Cars");
        booking = new JButton("Book Vehicle");
        rentals = new JButton("My Rentals");
        payment = new JButton("Payment");
        receipts = new JButton("My Receipts");
        profile = new JButton("My Profile");
        logout = new JButton("Logout");

        setTitle("Car Rental - Customer");
        setSize(700, 650);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setBackground(new Color(245, 248, 252));

        title.setBounds(215, 40, 400, 40);

        title.setFont(new Font("Arial", Font.BOLD, 22));

        title.setForeground(new Color(20, 50, 90));

        add(title);

        browse.setBounds(210, 120, 280, 45);
        booking.setBounds(210, 175, 280, 45);
        rentals.setBounds(210, 230, 280, 45);
        payment.setBounds(210, 285, 280, 45);
        receipts.setBounds(210, 340, 280, 45);
        profile.setBounds(210, 395, 280, 45);
        logout.setBounds(210, 465, 280, 45);

        add(browse);
        add(booking);
        add(rentals);
        add(payment);
        add(receipts);
        add(profile);
        add(logout);

        Color blue = new Color(30, 120, 220);
        Color hoverBlue = new Color(15, 85, 170);

        Color logoutColor = new Color(55, 75, 100);
        Color logoutHover = new Color(35, 55, 80);

        browse.setBackground(blue);
        booking.setBackground(blue);
        rentals.setBackground(blue);
        payment.setBackground(blue);
        receipts.setBackground(blue);
        profile.setBackground(blue);

        logout.setBackground(logoutColor);

        designButton(browse);
        designButton(booking);
        designButton(rentals);
        designButton(payment);
        designButton(receipts);
        designButton(profile);
        designButton(logout);

        addHoverEffect(browse, blue, hoverBlue);
        addHoverEffect(booking, blue, hoverBlue);
        addHoverEffect(rentals, blue, hoverBlue);
        addHoverEffect(payment, blue, hoverBlue);
        addHoverEffect(receipts, blue, hoverBlue);
        addHoverEffect(profile, blue, hoverBlue);

        addHoverEffect(logout, logoutColor, logoutHover);

        browse.addActionListener(this);
        booking.addActionListener(this);
        rentals.addActionListener(this);
        payment.addActionListener(this);
        receipts.addActionListener(this);
        profile.addActionListener(this);
        logout.addActionListener(this);

        db = new DBconnection();

        try {
            db.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        setVisible(true);
    }

	private void designButton(JButton button) {
		// TODO Auto-generated method stub
		
		 button.setFont(new Font("Arial", Font.BOLD, 15));

	        button.setForeground(Color.WHITE);

	        button.setFocusPainted(false);

	        button.setBorderPainted(false);
	}
	
	private void addHoverEffect(JButton button, Color blue, Color hoverBlue) {
		// TODO Auto-generated method stub
		
		 button.addMouseListener(new MouseAdapter() {

	            @Override
	            public void mouseEntered(MouseEvent e) {

	                button.setBackground(hoverBlue);
	            }


	            @Override
	            public void mouseExited(MouseEvent e) {

	                button.setBackground(blue);
	            }
	        });
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == browse)
	    {
	        new availableVehicleTablegui();
	    }
	    else if(e.getSource() == booking)
	    {
	        new BookVehiclegui();
	    }
	    else if(e.getSource() == rentals)
	    {
	        new myRentalTablegui(customerId);
	    }
	    else if(e.getSource() == payment)
	    {
	        new customerPaymentgui(customerId);
	    }
	    else if(e.getSource() == receipts)
	    {
	        new myReceiptgui(customerId);
	    }
	    else if(e.getSource() == profile)
	    {
	        new myProfilegui(customerId);
	    }
	    else if(e.getSource() == logout)
	    {
	        new Login();
	        this.dispose();
	    }
	}
	 public static void main(String[] args)
	    {
	        new CustomerDashboard("c101");
	    }
}
