package miniguiproject1;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AdminDashboard extends JFrame implements ActionListener{

	 private JLabel title;

	 private JButton customer;
	 private JButton vehicle;
	 private JButton rental;
	 private JButton returnvehicle;
	 private JButton payment;
	 private JButton receipt;
	 private JButton reports;
	 private JButton logout;
	 
	 public AdminDashboard()
	 {
		 title = new JLabel("ADMIN DASHBOARD");

	        customer = new JButton("Customer Management");
	        vehicle = new JButton("Vehicle Management");
	        rental = new JButton("Rental Management");
	        returnvehicle = new JButton("Return Management");
	        payment = new JButton("Payment Management");
	        receipt = new JButton("Receipt Management");
	        reports = new JButton("Reports");
	        logout = new JButton("Logout");

	        setTitle("Car Rental - Admin");
	        setSize(700, 700);
	        setLocationRelativeTo(null);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLayout(null);

	        getContentPane().setBackground(new Color(245, 248, 252));

	        title.setBounds(230, 40, 300, 50);

	        title.setFont(new Font("Arial", Font.BOLD, 28));

	        title.setForeground(new Color(20, 50, 90));

	        add(title);

	        customer.setBounds(210, 120, 280, 45);
	        vehicle.setBounds(210, 175, 280, 45);
	        rental.setBounds(210, 230, 280, 45);
	        returnvehicle.setBounds(210, 285, 280, 45);
	        payment.setBounds(210, 340, 280, 45);
	        receipt.setBounds(210, 395, 280, 45);
	        reports.setBounds(210, 450, 280, 45);
	        logout.setBounds(210, 520, 280, 45);

	        add(customer);
	        add(vehicle);
	        add(rental);
	        add(returnvehicle);
	        add(payment);
	        add(receipt);
	        add(reports);
	        add(logout);

	        Color blue = new Color(30, 120, 220);
	        Color hoverBlue = new Color(15, 85, 170);

	        Color logoutColor = new Color(55, 75, 100);
	        Color logoutHover = new Color(35, 55, 80);

	        customer.setBackground(blue);
	        vehicle.setBackground(blue);
	        rental.setBackground(blue);
	        returnvehicle.setBackground(blue);
	        payment.setBackground(blue);
	        receipt.setBackground(blue);
	        reports.setBackground(blue);

	        logout.setBackground(logoutColor);

	        designButton(customer);
	        designButton(vehicle);
	        designButton(rental);
	        designButton(returnvehicle);
	        designButton(payment);
	        designButton(receipt);
	        designButton(reports);
	        designButton(logout);

	        addHoverEffect(customer, blue, hoverBlue);
	        addHoverEffect(vehicle, blue, hoverBlue);
	        addHoverEffect(rental, blue, hoverBlue);
	        addHoverEffect(returnvehicle, blue, hoverBlue);
	        addHoverEffect(payment, blue, hoverBlue);
	        addHoverEffect(receipt, blue, hoverBlue);
	        addHoverEffect(reports, blue, hoverBlue);

	        addHoverEffect(logout, logoutColor, logoutHover);

	        customer.addActionListener(this);
	        vehicle.addActionListener(this);
	        rental.addActionListener(this);
	        returnvehicle.addActionListener(this);
	        payment.addActionListener(this);
	        receipt.addActionListener(this);
	        reports.addActionListener(this);
	        logout.addActionListener(this);

	        setVisible(true);

	 }

	private void designButton(JButton button) {
		// TODO Auto-generated method stub
	
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
		
	        if(e.getSource() == customer)
	        {
	            new Customergui();
	        }
	        else if(e.getSource() == vehicle)
	        {
	            new Vehiclegui();
	        }
	        else if(e.getSource() == rental)
	        {
	            new Rentalgui();
	        }
	        else if(e.getSource() == returnvehicle)
	        {
	            new Returngui();
	        }
	        else if(e.getSource() == payment)
	        {
	            new Paymentgui();
	        }
	        else if(e.getSource() == receipt)
	        {
	            new Receiptgui();
	        }
	        else if(e.getSource() == reports)
	        {
	            new customerTablegui();
	        }
	        else if(e.getSource() == logout)
	        {
	            new Login();
	            this.dispose();
	        }
	}

	    public static void main(String[] args)
	    {
	        new AdminDashboard();
	    }
}