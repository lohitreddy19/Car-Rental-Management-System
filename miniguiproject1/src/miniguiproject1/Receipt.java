package miniguiproject1;

public class Receipt {
	
	private String rid;
	private Payment payment;
	private customer customer;
	private vehicle vehicle;
	private double totalamount;
	
	public Receipt(String rid, miniguiproject1.Payment payment, miniguiproject1.customer customer,
			miniguiproject1.vehicle vehicle, double totalamount) {
		this.rid = rid;
		this.payment = payment;
		this.customer = customer;
		this.vehicle = vehicle;
		this.totalamount = totalamount;
	}

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
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

	public double getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(double totalamount) {
		this.totalamount = totalamount;
	}
	
	public void printreceipt()
	{
		System.out.println("Receipt id: " + rid);
		System.out.println("Payment Method: " + payment.getPmethod());
		System.out.println("Payment Status: " + payment.getstatus());
		System.out.println("Customer: " + customer.getCname());
		System.out.println("Vehicle: " + vehicle.getModel());
		System.out.println("Total Amount: " + totalamount);
	}
	
	

}
