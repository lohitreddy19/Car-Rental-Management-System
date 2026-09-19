package miniguiproject1;

import java.time.LocalDate;

public class Rental {
	
	private String rid;
	private customer customer;
	private vehicle vehicle;
	private int days;
	private String status;
	
	private LocalDate startDate;
	private LocalDate returnedDate;
	
	public Rental(String rid, miniguiproject1.customer customer, miniguiproject1.vehicle vehicle, int days, String status, LocalDate startDate, LocalDate returnedDate) {
		super();
		this.rid = rid;
		this.customer = customer;
		this.vehicle = vehicle;
		this.days = days;
		this.status = status;
		this.startDate = startDate;
		this.returnedDate = returnedDate;
	}

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public customer getCustomer() {
		return customer;
	}

	public void setCustomer(customer customer) {
		this.customer = customer;
	}

	public vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}
	
	public String getstatus() {
		return status;
	}
	
	public void setstatus(String status) {
		this.status = status;
	}
	
	public LocalDate getstartDate() {
		return startDate;
	}
	
	public void setstartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	
	public LocalDate getreturnedDate() {
		return returnedDate;
	}
	
	public void setreturnedDate(LocalDate returnedDate) {
		this.returnedDate = returnedDate;
	}

	public double calculateTotalRent()
	{
		return vehicle.calculateRent(days);
		
	}
	
	public LocalDate getExpectedReturnDate()
	{
	    return startDate.plusDays(days);
	}
	
	public long calculateLateDays()
	{
	    if(returnedDate == null)
	    {
	        return 0;
	    }

	    LocalDate expectedDate = getExpectedReturnDate();

	    if(returnedDate.isAfter(expectedDate))
	    {
	        return expectedDate.until(returnedDate).getDays();
	    }

	    return 0;
	}
	
	public double calculateLateCharge()
	{
	    long lateDays = calculateLateDays();

	    double lateFeePerDay = 500;

	    return lateDays * lateFeePerDay;
	}
	
	public double calculateFinalAmount()
	{
	    return calculateTotalRent() + calculateLateCharge();
	}
}
