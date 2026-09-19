package miniguiproject1;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class RentalManager {
	
	private DBconnection db;
	
	public RentalManager()
	{
		db = new DBconnection();
		try {
			db.connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private ArrayList<customer> al = new ArrayList<>();
	private ArrayList<vehicle> alv = new ArrayList<>();
	private ArrayList<Rental> alr = new ArrayList<>();
	private ArrayList<Payment> alp = new ArrayList<>();
	private ArrayList<Receipt> ar = new ArrayList<>();
	
	public void addCustomer(customer c)
	{
		al.add(c);
		
		try {
			db.insertCustomer(c);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void displayCustomer()
	{
		for(customer c : al)
		{
			System.out.println(c.getCname());
		}
	}
	
	
	public void addvehicle(vehicle v)
	{
		alv.add(v);
		
		try {
			db.insertVehicle(v);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void displayvehicle()
	{
		for(vehicle v : alv)
		{
			System.out.println(v.getBrand());
		}
	}
	
	public void addRental(Rental r)
	{
		alr.add(r);
		
		try {
			db.insertRental(r);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void displayRental()
	{
		for(Rental ren : alr)
		{
			System.out.println(ren.getCustomer().getCname());
		}
	}
	
	public void addPayment(Payment p) throws SQLException
	{
		String rid = p.getRental().getRid();
		
		if(db.paymentExistsForRental(rid))
		{
			System.out.println("Payment already exist for this Rental");
		}
		else {
			alp.add(p);
			db.insertPayment(p);
			System.out.println("Payment Added Successfully");
		}
		
	}
	
	public void displayPayment()
	{
		for(Payment pay : alp)
		{
			System.out.println(pay.getPmethod());
		}
	}
	
	public void addReceipt(Receipt rr) throws SQLException
	{
		ar.add(rr);
		db.insertReceipt(rr);
	}
	
	public void displayReceipt()
	{
		for(Receipt rr : ar)
		{
			System.out.println(rr.getTotalamount());
		}
	}
	
	public customer findCustomer(String cid)
	{
		for(customer c : al)
		{
			if(c.getCid().equals(cid))
			{
				return c;
			}
		}
		
		try {
			customer result = db.findCustomer(cid);
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public vehicle findVehicle(String vid)
	{
		for(vehicle v : alv)
		{
			if(v.getVid().equals(vid))
			{
				return v;
			}
		}
		
		try {
			vehicle result = db.findVehicle(vid);
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean vehicleAvailable(String vid)
	{
	    vehicle v = findVehicle(vid);

	    if(v != null)
	    {
	        return v.getAvailable();
	    }

	    return false;
	}
	
	public boolean rentVehicle(String vid) throws SQLException
	{
			if(vehicleAvailable(vid))
			{
				vehicle v = findVehicle(vid);
				v.setAvailable(false);
				db.updateVehicle(v);
				return true;
			}
		return false;
	}
		
	public Rental createRental(String rid, String cid, String vid, int days) throws invalidRentalException, SQLException
	{
		customer c = findCustomer(cid);
		
		if(c == null)
		{
			System.out.println("customer not found");
			return null;
		}
		
		vehicle v = findVehicle(vid);
		
		if(v == null)
		{
			System.out.println("Vehicle is not found");
			return null;
		}
		
		if(v.getAvailable() == false)
		{
			System.out.println("Vehicle is Unavailable");
			return null;
		}
		
		if(days <= 0)
		{
			throw new invalidRentalException("Rental days must be greayer than 0");
		}
		
		LocalDate startDate = LocalDate.now();
		LocalDate returnedDate = null;
		String status = "Active";
		
		Rental r = new Rental(rid, c, v, days, status, startDate, returnedDate);
		addRental(r);
		rentVehicle(vid);
		return r;
	}
	
	public boolean returnVehicle(String rid) throws SQLException
	{
		Rental r = db.findRental(rid);
		
		if(r == null)
		{
			System.out.println("Rental not found");
			return false;
		}
		
		r.setstatus("Returned");
		
		r.setreturnedDate(LocalDate.now());
		
		db.updateRental(r);
		
		vehicle v = r.getVehicle();
		v.setAvailable(true);
		db.updateVehicle(v);
		return true;
	}
	
}
