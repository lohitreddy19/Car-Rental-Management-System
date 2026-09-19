package miniguiproject1;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Dashboard extends JFrame implements ActionListener{
	
	private JLabel title;
	private JButton customer;
	private JButton vehicle;
	private JButton rental;
	private JButton returnvehicle;
	private JButton payment;
	private JButton receipt;
	private JButton customerReport;
	private JButton logout;
		
	public Dashboard()
	{
		title = new JLabel("DASHBOARD");
		
		customer = new JButton("Customer Management");
		vehicle = new JButton("Vehicle Management");
		rental = new JButton("Rental");
		returnvehicle = new JButton("Return Vehicle");
		payment = new JButton("Payment");
		receipt = new JButton("Receipt");
		logout = new JButton("Logout");
		
		customerReport = new JButton("Customer Report");
		
		setLayout(null);
		
		title.setBounds(250, 30, 120, 40);
		
		customer.setBounds(150, 100, 250, 40);
		vehicle.setBounds(150, 160, 250, 40);
		rental.setBounds(150, 220, 250, 40);
		returnvehicle.setBounds(150, 280, 250, 40);
		payment.setBounds(150, 340, 250, 40);
		receipt.setBounds(150, 400, 250, 40);
		customerReport.setBounds(150, 520, 250, 40);
		logout.setBounds(150, 460, 250, 40);
		
		add(title);

		add(customer);
		add(vehicle);
		add(rental);
		add(returnvehicle);
		add(payment);
		add(receipt);
		add(customerReport);
		add(logout);
		
		setTitle("Car Rental Management System");
		setSize(800, 800);
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		customer.addActionListener(this);
		
		vehicle.addActionListener(this);
		rental.addActionListener(this);
		returnvehicle.addActionListener(this);
		payment.addActionListener(this);
		receipt.addActionListener(this);
		customerReport.addActionListener(this);
		logout.addActionListener(this);
	}
	
	public static void main(String[] args)
	{
		new Dashboard();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == customer)
		{
			Customergui c = new Customergui();
			c.setVisible(true);
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
		    else if(e.getSource() == customerReport)
		    {
		        new customerTablegui();
		    }
		    else if(e.getSource() == logout)
		    {
		        new Login();
		        this.dispose();
		    }	
	}
}
