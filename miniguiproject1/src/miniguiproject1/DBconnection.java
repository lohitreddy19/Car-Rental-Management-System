package miniguiproject1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBconnection {
	
	Connection c;
	
	public void connect() throws SQLException
	{
		try
		{
		c = DriverManager.getConnection(DBConfig.URL,DBConfig.USER,DBConfig.PASSWORD);
			
		System.out.println("Database Connected Successfully");
		
//		PreparedStatement p = c.prepareStatement("insert into customer values(?,?,?,?)");
//		
//		p.setString(1, "c108");
//		p.setString(2, "Hemanth");
//		p.setString(3, "321567890");
//		p.setString(4, "Hemanth@gmail.com");
//		
//		p.executeUpdate();
//		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void insertCustomer(customer c1) throws SQLException
	{
		PreparedStatement p = c.prepareStatement("insert into customer values(?, ?, ?, ?)");
		
		p.setString(1, c1.getCid());
		p.setString(2, c1.getCname());
		p.setString(3, c1.getMobile());
		p.setString(4, c1.getEmail());
		
		p.executeUpdate();
	}
	
	public customer findCustomer(String cid) throws SQLException
	{
		PreparedStatement p = c.prepareStatement("select * from customer where cid = ?");
		
		p.setString(1, cid);
		
		ResultSet rs = p.executeQuery();
		
		if(rs.next())
		{
			String id = rs.getString("cid");
			String name = rs.getString("cname");
			String mobile = rs.getString("mobile");
			String email = rs.getString("email");
			
			customer c1 = new customer(id, name, mobile, email);
			
			return c1;
		}
		return null;
	}
	
	public void updateCustomer(customer c1) throws SQLException
	{
		PreparedStatement p = c.prepareStatement("update customer set cname = ?, mobile = ?, email = ? where cid = ?");
		
		p.setString(1, c1.getCname());
		p.setString(2, c1.getMobile());
		p.setString(3, c1.getEmail());
		p.setString(4, c1.getCid());
		
		int rows = p.executeUpdate();
		System.out.println("rows updated: " + rows);
	} 
	
	public void deleteCustomer(String cid) throws SQLException
	{
		PreparedStatement p = c.prepareStatement("delete from customer where cid = ?");
		
		p.setString(1, cid);
		
		p.executeUpdate();
	}
	
	public void insertVehicle(vehicle v) throws SQLException
	{
		PreparedStatement p = c.prepareStatement("insert into vehicle values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
		
		p.setString(1, v.getVid());
		p.setString(2, v.getBrand());
		p.setString(3, v.getModel());
		p.setInt(4, v.getYear());
		p.setString(5, v.getFtype());
		p.setString(6, v.getTransmission());
		p.setDouble(7, v.getPrice());
		p.setBoolean(8, v.getAvailable());
		p.setString(9, v.getvtype());
		
		p.executeUpdate();
	}
	
	public vehicle findVehicle(String vid) throws SQLException
	{
		PreparedStatement p = c.prepareStatement("select * from vehicle where vid = ?");
		
		p.setString(1, vid);
		
		ResultSet rs = p.executeQuery();
		
		if(rs.next())
		{
			System.out.println("Vehicle ID: " + rs.getString("vid"));
			System.out.println("Brand: " + rs.getString("brand"));
			System.out.println("Model: " + rs.getString("model"));
			System.out.println("Year: " + rs.getInt("year"));
			System.out.println("Fuel Typr: " + rs.getString("ftype"));
			System.out.println("Transmission: " + rs.getString("transmission"));
			System.out.println("Price: " + rs.getDouble("price"));
			System.out.println("Available: " + rs.getBoolean("available"));
			System.out.println("Vehicle type: " + rs.getString("vtype"));
			
			String id = rs.getString("vid");
			String brand = rs.getString("brand");
			String model = rs.getString("model");
			int year = rs.getInt("year");
			String ftype = rs.getString("ftype");
			String transmission = rs.getString("transmission");
			double price = rs.getDouble("price");
			boolean available = rs.getBoolean("available");
			String vtype = rs.getString("vtype");
			
			if(vtype.equals("Seden"))
			{
				return new Seden(id, brand, model, year, ftype, transmission, price, available, vtype);
			}
			else if(vtype.equals("SUV"))
			{
				return new SUV(id, brand, model, year, ftype, transmission, price, available, vtype);
			}
			else if(vtype.equals("Hatchback"))
			{
				return new Hatchback(id, brand, model, year, ftype, transmission, price, available, vtype);
			}
			return null;
		}
		return null;
	}
	
	public void updateVehicle(vehicle v) throws SQLException
	{
		PreparedStatement p = c.prepareStatement("update vehicle set brand = ?, model = ?, year = ?, ftype = ?, transmission = ?, price = ?, available = ?, vtype = ? where vid = ?");
		
		p.setString(1, v.getBrand());
		p.setString(2, v.getModel());
		p.setInt(3, v.getYear());
		p.setString(4, v.getFtype());
		p.setString(5, v.getTransmission());
		p.setDouble(6, v.getPrice());
		p.setBoolean(7, v.getAvailable());
		p.setString(8, v.getvtype());
		p.setString(9, v.getVid());
		
		p.executeUpdate();
	}
	
	public void deleteVehicle(String vid) throws SQLException
	{
		PreparedStatement p = c.prepareStatement("delete from vehicle where vid = ?");
		
		p.setString(1, vid);
		
		p.executeUpdate();
		
	}
	
	public void insertRental(Rental r) throws SQLException
	{
		PreparedStatement p = c.prepareStatement("insert into rental values(?, ?, ?, ?, ?, ?, ?)");
		
		p.setString(1, r.getRid());
		p.setString(2, r.getCustomer().getCid());
		p.setString(3, r.getVehicle().getVid());
		p.setInt(4, r.getDays());
		p.setString(5, r.getstatus());
		p.setObject(6, r.getstartDate());
		p.setObject(7, r.getreturnedDate());
		
		p.executeUpdate();
	}
	
	public Rental findRental(String rid) throws SQLException
	{
		PreparedStatement p = c.prepareStatement("select * from rental where rid = ?");
		
		p.setString(1, rid);
		
		ResultSet rs = p.executeQuery();
		
		if(rs.next())
		{
			
			System.out.println("Rental id: " + rs.getString("rid"));
			System.out.println("Customer id: " + rs.getString("cid"));
			System.out.println("Vehicle id: " + rs.getString("vid"));
			System.out.println("Days: " + rs.getString("days"));
			System.out.println("Status: " + rs.getString("Status"));
			System.out.println("Start Date: " + rs.getObject("start_Date"));
			System.out.println("Returned Date: " + rs.getObject("returned_Date"));
			
			String id = rs.getString("rid");
			String cid = rs.getString("cid");
			String vid = rs.getString("vid");
			int days = rs.getInt("days");
			String status = rs.getString("status");
			LocalDate startDate = rs.getObject("start_Date", LocalDate.class);
			LocalDate returnedDate = rs.getObject("returned_Date", LocalDate.class);
			
				customer c1 = findCustomer(cid);
				vehicle v1 = findVehicle(vid);
				
				Rental r = new Rental(id, c1, v1, days, status, startDate, returnedDate);
				return r;
			
		}
		return null;
	}
	
	public void updateRental(Rental r) throws SQLException
	{
		PreparedStatement p = c.prepareStatement("update rental set cid = ?, vid = ?, days = ?, status = ?, start_Date = ?, returned_Date = ? where rid = ?");
		
		p.setString(1, r.getCustomer().getCid());
		p.setString(2, r.getVehicle().getVid());
		p.setInt(3, r.getDays());
		p.setString(4, r.getstatus());
		p.setObject(5, r.getstartDate());
		p.setObject(6, r.getreturnedDate());
		p.setString(7, r.getRid());
		
		p.executeUpdate();
	}
	
	public void deleteRental(String rid) throws SQLException
	{
		PreparedStatement p = c.prepareStatement("delete from rental where rid = ?");
		
		p.setString(1, rid);
		
		p.executeUpdate();
	}
	
	public void insertPayment(Payment p) throws SQLException
	{
		PreparedStatement ps = c.prepareStatement("insert into payment values (?, ?, ?, ?, ?)");
		
		ps.setString(1, p.getPid());
		ps.setString(2, p.getRental().getRid());
		ps.setDouble(3, p.getAmount());
		ps.setString(4, p.getPmethod());
		ps.setString(5, p.getstatus());
		
		ps.executeUpdate();
		
	}
	
	public void insertReceipt(Receipt r) throws SQLException
	{
		PreparedStatement p = c.prepareStatement("insert into receipt value(?, ?, ?, ?, ?)");
		
		p.setString(1, r.getRid());
		p.setString(2, r.getPayment().getPid());
		p.setString(3, r.getCustomer().getCid());
		p.setString(4, r.getVehicle().getVid());
		p.setDouble(5, r.getTotalamount());
		
		p.executeUpdate();
	}
	
	public Payment findPayment(String pid) throws SQLException
	{
		PreparedStatement p = c.prepareStatement("select * from payment where pid = ?");
		
		p.setString(1, pid);
		
		ResultSet rs = p.executeQuery();
		
		if(rs.next())
		{
			System.out.println("Payment ID: " + rs.getString("pid"));
			System.out.println("Receipt ID: " + rs.getString("rid"));
			System.out.println("Amount: " + rs.getDouble("amount"));
			System.out.println("Payment Method: " + rs.getString("pmethod"));
			System.out.println("Status: " + rs.getString("status"));
			
			String id = rs.getString("pid");
			String rid = rs.getString("rid");
			Double amount = rs.getDouble("amount");
			String pmethod = rs.getString("pmethod");
			String status = rs.getString("status");
			
			Rental r = findRental(rid);
			
			Payment pay = new Payment(id, r, amount, pmethod, status);
			
			return pay;
		}
		return null;
	}
	
	public Receipt findReceipt(String rid) throws SQLException
	{
		PreparedStatement p = c.prepareStatement("select * from receipt where rid = ?");
		
		p.setString(1, rid);
		
		ResultSet rs = p.executeQuery();
		
		if(rs.next())
		{
			System.out.println("Receipt ID: " + rs.getString("rid"));
			System.out.println("Payment ID: " + rs.getString("pid"));
			System.out.println("Customer ID: " + rs.getString("cid"));
			System.out.println("Vehicle ID: " + rs.getString("vid"));
			System.out.println("Total Amount: " + rs.getDouble("totalamount"));
			
			String id = rs.getString("rid");
			String pid = rs.getString("pid");
			String cid = rs.getString("cid");
			String vid = rs.getString("vid");
			Double totalamount = rs.getDouble("totalamount");
			
			
			Payment pay = findPayment(pid); 
			customer c = findCustomer(cid);
			vehicle v = findVehicle(vid);
			
			Receipt receipt = new Receipt(id, pay, c, v, totalamount);
			
			return receipt;
		}
		return null;
	}
	
	public boolean paymentExistsForRental(String rid) throws SQLException
	{
		PreparedStatement p = c.prepareStatement("select * from payment where rid = ?");
		
		p.setString(1, rid);
		
		ResultSet rs = p.executeQuery();
		
		if(rs.next())
		{
			return true;
		}
		return false;
	}

	public ResultSet getCustomer() throws SQLException 
	{
		// TODO Auto-generated method stub
		
		PreparedStatement p = c.prepareStatement("select * from customer");
		return p.executeQuery();
	}
	
	public ResultSet getAvailableVehicles() throws SQLException 
	{
	    PreparedStatement p = c.prepareStatement("select * from vehicle where available = true");
	    return p.executeQuery();
	}
	
	public ResultSet getRentalsByCustomer(String cid) throws SQLException
	{
		PreparedStatement p = c.prepareStatement("SELECT * FROM rental WHERE cid = ?");

		p.setString(1, cid);

		return p.executeQuery();
		
	}
	
	public String generatePaymentId() throws SQLException {

	    PreparedStatement p = c.prepareStatement(
	        "SELECT pid FROM payment " +
	        "WHERE pid LIKE 'PAY%' " +
	        "ORDER BY CAST(SUBSTRING(pid, 4) AS UNSIGNED) DESC " +
	        "LIMIT 1");

	    ResultSet rs = p.executeQuery();

	    if (rs.next()) 
	    {
	        String lastId = rs.getString("pid");
	        int number = Integer.parseInt(lastId.substring(3));
	        number++;

	        return String.format("PAY%03d", number);
	    }

	    return "PAY001";
	}
	
	public ResultSet getReceiptsByCustomer(String cid) throws SQLException {

	    PreparedStatement p = c.prepareStatement("SELECT * FROM receipt WHERE cid = ?");

	    p.setString(1, cid);

	    return p.executeQuery();
	}
	
	public String generateReceiptId() throws SQLException {

	    PreparedStatement p = c.prepareStatement(
	        "SELECT rid FROM receipt " +
	        "WHERE rid LIKE 'REC%' " +
	        "ORDER BY CAST(SUBSTRING(rid, 4) AS UNSIGNED) DESC " +
	        "LIMIT 1");

	    ResultSet rs = p.executeQuery();

	    if(rs.next()) 
	    {
	        String lastId = rs.getString("rid");

	        int number = Integer.parseInt(lastId.substring(3));
	        number++;

	        return String.format("REC%03d", number);
	    }

	    return "REC001";
	}
	
	public customer findCustomerByEmail(String email) throws SQLException {

	    PreparedStatement p = c.prepareStatement("SELECT * FROM customer WHERE email = ?");

	    p.setString(1, email);

	    ResultSet rs = p.executeQuery();

	    if (rs.next()) 
	    {
	        String id = rs.getString("cid");
	        String name = rs.getString("cname");
	        String mobile = rs.getString("mobile");
	        String customerEmail = rs.getString("email");

	        return new customer(id,name,mobile,customerEmail);
	    }
	    return null;
	}
	
	public void requestReturn(String rid) throws SQLException 
	{
	    PreparedStatement p = c.prepareStatement("UPDATE rental SET status = ? WHERE rid = ?");

	    p.setString(1, "Return Requested");
	    p.setString(2, rid);

	    p.executeUpdate();
	}
	
	public ResultSet getAllRentals() throws SQLException {

	    PreparedStatement p = c.prepareStatement(
	        "SELECT rid, cid, vid, days, status, start_date, returned_date " +
	        "FROM rental");

	    return p.executeQuery();
	}
	
	public ResultSet getAllPayments() throws SQLException {

	    PreparedStatement p = c.prepareStatement(
	        "SELECT pid, rid, amount, pmethod, status FROM payment");

	    return p.executeQuery();
	}
	
//	public int getTotalCustomers() throws SQLException {
//
//	    PreparedStatement p = c.prepareStatement("SELECT COUNT(*) FROM customer");
//
//	    ResultSet rs = p.executeQuery();
//
//	    rs.next();
//
//	    return rs.getInt(1);
//	}
//	
//	public int getTotalVehicles() throws SQLException {
//
//	    PreparedStatement p = c.prepareStatement("SELECT COUNT(*) FROM vehicle");
//
//	    ResultSet rs = p.executeQuery();
//
//	    rs.next();
//
//	    return rs.getInt(1);
//	}
//	
//	public int AvailableVehicles() throws SQLException {
//
//	    PreparedStatement p = c.prepareStatement("SELECT COUNT(*) FROM vehicle WHERE available = 1");
//
//	    ResultSet rs = p.executeQuery();
//
//	    rs.next();
//
//	    return rs.getInt(1);
//	}
//	
//	public int getTotalRentals() throws SQLException {
//
//	    PreparedStatement p = c.prepareStatement("SELECT COUNT(*) FROM rental");
//
//	    ResultSet rs = p.executeQuery();
//
//	    rs.next();
//
//	    return rs.getInt(1);
//	}
//	
//	public int getActiveRentals() throws SQLException {
//
//	    PreparedStatement p = c.prepareStatement("SELECT COUNT(*) FROM rental WHERE status = 'Active'");
//
//	    ResultSet rs = p.executeQuery();
//
//	    rs.next();
//
//	    return rs.getInt(1);
//	}
//	
//	public int getReturnedRentals() throws SQLException {
//
//	    PreparedStatement p = c.prepareStatement("SELECT COUNT(*) FROM rental WHERE status = 'Returned'");
//
//	    ResultSet rs = p.executeQuery();
//
//	    rs.next();
//
//	    return rs.getInt(1);
//	}
//	
//	public int getTotalPayments() throws SQLException {
//
//	    PreparedStatement p = c.prepareStatement("SELECT COUNT(*) FROM payment");
//
//	    ResultSet rs = p.executeQuery();
//
//	    rs.next();
//
//	    return rs.getInt(1);
//	}
//	
//	public double getTotalRevenue() throws SQLException {
//
//	    PreparedStatement p = c.prepareStatement("SELECT SUM(amount) FROM payment WHERE status = 'Successful'");
//
//	    ResultSet rs = p.executeQuery();
//
//	    rs.next();
//
//	    return rs.getDouble(1);
//	}
}
